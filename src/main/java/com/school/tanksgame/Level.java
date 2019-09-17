package com.school.tanksgame;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

public class Level {
    PApplet p;



    private ArrayList<Wall> walls = new ArrayList<>();
    public Level(PApplet parent) {
        p = parent;
    }


    void generateLevel(float wallCount) {
        while(walls.size() < wallCount) {
            PVector startLoc = new PVector(p.random(0, p.width-20), p.random(0, p.height-20));
            PVector endLoc = new PVector(p.random(startLoc.x, p.width-20), p.random(0, p.height-20));
            Wall newWall = new Wall(p, startLoc, endLoc);

            Boolean colliding = false;
            //detect if it collides with other walls
            for(Wall wall : walls) {
                if(wall.isCrossingOther(newWall) || Math.abs(wall.getA() - newWall.getA()) < 1) {
                    colliding = true;
                }
            }

            if(!colliding) {
                walls.add(newWall);
            }
        }
    }

    void draw() {
        for(Wall wall : walls) {
            wall.draw();
        }
    }
}
