package com.school.tanksgame.maploading;

import com.school.tanksgame.sprites.HealthPad;
import com.school.tanksgame.sprites.Sprite;
import com.school.tanksgame.sprites.Tank;
import com.school.tanksgame.sprites.Wall;
import processing.core.PApplet;

import java.util.ArrayList;

public class Map extends Sprite {
    private String name;
    private ArrayList<Wall> walls;
    private ArrayList<HealthPad> healthPads;
    private ArrayList<Tank> tanks;
    private ArrayList<Sprite> sprites;

    public Map(String name, ArrayList<Wall> walls, ArrayList<HealthPad> healthPads, ArrayList<Tank> tanks) {
        this.name = name;
        this.walls = walls;
        this.healthPads = healthPads;
        this.tanks = tanks;

        sprites = new ArrayList<>();
        sprites.addAll(walls);
        sprites.addAll(healthPads);
        sprites.addAll(tanks);
    }

    @Override
    public void setParent(PApplet parent) {
        super.setParent(parent);
        setParentForSprites();
    }

    private void setParentForSprites() {
        for (Sprite sprite : sprites)
            sprite.setParent(parent);
    }

    public ArrayList<Wall> getWalls() {
        return walls;
    }
    public ArrayList<HealthPad> getHealthPads() {
        return healthPads;
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

    public void draw() {
        for (Sprite sprite: sprites)
            sprite.draw();
    }
 }
