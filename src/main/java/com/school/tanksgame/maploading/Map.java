package com.school.tanksgame.maploading;

import com.school.tanksgame.Collision;
import com.school.tanksgame.Constants;
import com.school.tanksgame.sprites.*;
import processing.core.PApplet;
import processing.core.PVector;
import processing.event.KeyEvent;

import java.util.ArrayList;

public class Map {
    private String name;
    private PApplet parent;
    private ArrayList<Wall> walls;
    private ArrayList<HealthPad> healthPads;
    private ArrayList<Tank> tanks;
    private ArrayList<Bullet> bullets;
    private ArrayList<Sprite> sprites;

    public Map(String name, ArrayList<Wall> walls, ArrayList<HealthPad> healthPads, ArrayList<Tank> tanks) {
        this.name = name;
        this.walls = walls;
        this.healthPads = healthPads;
        this.tanks = tanks;
        this.bullets = new ArrayList<>();

        sprites = new ArrayList<>();
        sprites.addAll(walls);
        sprites.addAll(healthPads);
        sprites.addAll(tanks);
    }

    public void init(PApplet parent) {
        this.parent = parent;
        setParentForSprites();
        setMapForSprites();
    }

    private void setParentForSprites() {
        for (Sprite sprite : sprites)
            sprite.setParent(parent);
    }

    private void setMapForSprites() {
        for (Sprite sprite : sprites)
            sprite.setMap(this);
    }

    public ArrayList<Wall> getWalls() {
        return walls;
    }
    public ArrayList<HealthPad> getHealthPads() {
        return healthPads;
    }

    public void addBullet(Bullet bullet) {
        bullets.add(bullet);
        // if we add to the front of the list, then the bullet gets updated,
        // before we check if it hit something
        sprites.add(0, bullet);
    }
//    public void setWalls(ArrayList<Wall> walls) {
//        this.walls = walls;
//    }
//
//    public void setHealthPads(ArrayList<HealthPad> healthPads) {
//        this.healthPads = healthPads;
//    }
//
//    public void setTanks(ArrayList<Tank> tanks) {
//        this.tanks = tanks;
//    }
//
//    void addWall(Wall wall) {
//        this.walls.add(wall);
//    }
//
//    void addHealthPad(HealthPad healthPad) {
//        this.healthPads.add(healthPad);
//    }

    private void detectBulletCollision() {
        for (int i = bullets.size(); i > 0; i--) {
            Bullet bullet = bullets.get(i-1);

            for (Wall wall : walls) {
                boolean hitting = Collision.lineCircle(wall.getStartLoc(), wall.getEndLoc(), bullet.getLocation(), bullet.getRadius() + wall.getWidth() / 2);
                if (hitting) {
                    PVector line = wall.getLine();
                    PVector normal = new PVector(-line.y, line.x).setMag(1);
                    PVector curr = bullet.getVelocity().copy();

                    //reflection angle is taken from http://mathworld.wolfram.com/Reflection.html
                    PVector newVector = curr.sub(normal.mult(2 * PVector.dot(curr, normal)));
                    bullet.setVelocity(newVector);
                    bullet.damage();
                }
            }

            for(Tank tank : tanks) {
                float dist = Collision.dist(bullet.getLocation(), tank.getLocation());
                if(dist < bullet.getRadius() + Constants.TANK_HITBOX) {
                    tank.damage();
                }
            }

            bullet.update();
            if(!bullet.isAlive()) {
                bullets.remove(bullet);
                sprites.remove(bullet);
            }
        }
    }

    public void detectTankCollision() {

    }

    public void update() {
        for (int i=sprites.size(); i>0; i--)
            sprites.get(i-1).update();
        detectBulletCollision();
    }

    public void draw() {
        for (int i=sprites.size(); i>0; i--)
            sprites.get(i-1).draw();
    }

    public void keyAction(KeyEvent event) {
        for (Tank tank : tanks)
            tank.keyAction(event);
    }
 }
