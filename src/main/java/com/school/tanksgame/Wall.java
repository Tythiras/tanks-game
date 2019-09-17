package com.school.tanksgame;

import processing.core.PApplet;
import processing.core.PVector;

import static processing.core.PConstants.CENTER;
import static processing.core.PConstants.CORNER;

public class Wall {
    PApplet p;

    PVector startLoc;
    PVector endLoc;
    float width = 5;
    Wall(PApplet parent, PVector startLoc, PVector endLoc) {
        p = parent;
        this.startLoc = startLoc;
        this.endLoc = endLoc;
    }

    float getA() {
        return (this.startLoc.y - this.endLoc.y) / (this.startLoc.x - this.endLoc.x);
    }
    float getB() {
        return (-this.getA() * this.startLoc.x + this.startLoc.y);
    }
    boolean isCrossingOther(Wall other) {
        float a1 = this.getA();
        float b1 = this.getB();

        float a2 = other.getA();
        float b2 = other.getB();

        if(a1 != a2) {
            float interX = -(b1 - b2) / (a1 - a2);
            //x is between one of the lines
            if(interX > startLoc.x && interX < endLoc.x) {
                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }

    }

    void draw() {
        p.pushMatrix();
        float distX = startLoc.x - endLoc.x;
        float distY = startLoc.y - endLoc.y;
        float length = (float) Math.sqrt(Math.pow(distX, 2) + Math.pow(distY, 2));
        float angle = (float) Math.atan(distY /  distX);

        p.translate(startLoc.x, startLoc.y);
        p.rotate(angle);
        p.rectMode(CENTER);
        p.rect(length/2, 0, length, width);

        p.popMatrix();
    }

}
