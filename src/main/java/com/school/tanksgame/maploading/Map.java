package com.school.tanksgame.maploading;

import com.school.tanksgame.sprites.HealthPad;
import com.school.tanksgame.sprites.Sprite;
import com.school.tanksgame.sprites.Wall;

import java.util.ArrayList;

public class Map extends Sprite {
    private String name;
    private ArrayList<Wall> walls;
    private ArrayList<HealthPad> healthPads;

    public Map(String name) {
        this.name = name;
        this.walls = new ArrayList<>();
        this.healthPads = new ArrayList<>();
    }

    public void setWalls(ArrayList<Wall> walls) {
        this.walls = walls;
    }

    public void setHealthPads(ArrayList<HealthPad> healthPads) {
        this.healthPads = healthPads;
    }

    void addWall(Wall wall) {
        this.walls.add(wall);
    }

    void addHealthPad(HealthPad healthPad) {
        this.healthPads.add(healthPad);
    }

    public void draw() {
        for (Wall wall : walls) {
            wall.draw();
        }

        for (HealthPad healthPad : healthPads) {
            healthPad.draw();
        }
    }
 }
