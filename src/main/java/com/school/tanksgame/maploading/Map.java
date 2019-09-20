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
        sprites.addAll(tanks);
        sprites.addAll(healthPads);
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

    public void addBullet(Bullet bullet) {
        bullets.add(bullet);
        // if we add to the front of the list, then the bullet gets updated,
        // before we check if it hit something
        sprites.add(0, bullet);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Wall> getWalls() {
        // YIKES
        // Blame Rasmus for this atrocity
        // jk
        return walls;
    }

    private void detectBulletCollision() {
        for (int i = bullets.size(); i > 0; i--) {
            Bullet bullet = bullets.get(i-1);

            // check if bullet hits a wall
            for (Wall wall : walls) {
                boolean hit = Collision.lineCircle(
                        wall.getStartLoc(),
                        wall.getEndLoc(),
                        bullet.getLocation(),
                        bullet.getRadius() + wall.getWidth() / 2
                );

                if (hit) {
                    PVector line = wall.getLine();
                    PVector normal = new PVector(-line.y, line.x).setMag(1);
                    PVector curr = bullet.getVelocity().copy();

                    //reflection angle is taken from http://mathworld.wolfram.com/Reflection.html
                    PVector newVector = curr.sub(normal.mult(2 * PVector.dot(curr, normal)));
                    bullet.setVelocity(newVector);
                    bullet.damage();
                }
            }

            // check if bullet hits a tank
            for(Tank tank : tanks) {
                float height = Constants.TANK_HEIGHT / 2;
                float width = Constants.TANK_WIDTH / 2;

                float rotation = tank.getRotation();
                PVector location = tank.getLocation();
                PVector[] corners = new PVector[] {
                        new PVector(width, height).rotate(rotation).add(location),
                        new PVector(width, -height).rotate(rotation).add(location),
                        new PVector(-width, height).rotate(rotation).add(location),
                        new PVector(-width, -height).rotate(rotation).add(location)
                };

                for(int k = 0; k < corners.length; k++) {
                    int nextIndex = k + 1;
                    nextIndex = nextIndex >= corners.length ? 0 : nextIndex;

                    PVector corner1 = corners[k];
                    PVector corner2 = corners[nextIndex];
                    boolean hit = Collision.lineCircle(corner1, corner2, bullet.getLocation(), bullet.getRadius());
                    if(hit) {
                        bullet.die();
                        tank.damage();
                        break;
                    }
                }
            }

            if(!bullet.isAlive()) {
                bullets.remove(bullet);
                sprites.remove(bullet);
            }
        }
    }

    private void detectHealthPadCollision() {
        for(Tank tank : tanks) {
            if(tank.getHealth() == Constants.TANK_START_HEALTH)
                continue;

            for(int i = healthPads.size(); i > 0; i--) {
                HealthPad healthPad = healthPads.get(i-1);

                float dist = Collision.dist(healthPad.getLocation(), tank.getLocation());
                if(dist < Constants.HEALTHPAD_RADIUS) {
                    healthPads.remove(healthPad);
                    sprites.remove(healthPad);
                    tank.addHealth();
                }
            }
        }
    }

    private void detectDeadTanks() {
        for (int i=tanks.size(); i>0; i--) {
            Tank tank = tanks.get(i-1);
            if (tank.isAlive())
                continue;

            sprites.remove(tank);
            tanks.remove(tank);
        }
    }

    public void update() {
        for (int i=sprites.size(); i>0; i--)
            sprites.get(i-1).update();
        detectBulletCollision();
        detectHealthPadCollision();
        detectDeadTanks();
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
