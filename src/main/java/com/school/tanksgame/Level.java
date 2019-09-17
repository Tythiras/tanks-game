package com.school.tanksgame;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

public class Level {
    PApplet p;



    private ArrayList<Wall> walls = new ArrayList<>();
    private ArrayList<HealthPad> healthPads = new ArrayList<>();


    public Level(PApplet parent) {
        p = parent;
    }


    void generateLevel(int wallCount, int healthPadsCount ) {
        float paddingWalls = 50;
        while(walls.size() < wallCount) {
            PVector startLoc = new PVector(p.random(paddingWalls, p.width-paddingWalls), p.random(paddingWalls, p.height-paddingWalls));
            PVector endLoc = new PVector(p.random(startLoc.x, p.width-paddingWalls), p.random(paddingWalls, p.height-paddingWalls));
            Wall newWall = new Wall(p, startLoc, endLoc);

            Boolean isAllowed = true;
            //detect if it collides with other walls
            for(Wall wall : walls) {
                p.println(Math.abs(wall.getA() - newWall.getA()) );
                if( Math.abs(wall.getA() - newWall.getA()) < 1.5 || wall.isCrossingOther(newWall) ) {
                    isAllowed = false;
                    break;
                }
            }

            if(isAllowed) {
                walls.add(newWall);
            }
        }
        float paddingHealthPads = 20;
        while(healthPads.size() < healthPadsCount) {
            PVector loc = new PVector(p.random(paddingHealthPads, p.width-paddingHealthPads), p.random(paddingHealthPads, p.height-paddingHealthPads));
            Boolean isAllowed = true;
            for(Wall wall : walls) {
                float dist = wall.distanceToPoint(loc);
                if(dist < paddingHealthPads) {
                    isAllowed = false;
                    break;
                }
            }
            if(isAllowed) {
                healthPads.add(new HealthPad(p, loc));
            }
        }
    }

    void draw() {
        for(Wall wall : walls) {
            wall.draw();
        }
        for(HealthPad healthPad : healthPads) {
            healthPad.draw();
        }
    }
}
