package com.school.tanksgame.sprites;

import com.school.tanksgame.Constants;
import processing.core.PVector;

import static processing.core.PConstants.CENTER;

public class Wall extends Sprite {

    private PVector startLoc;
    private PVector endLoc;
    private final float width;

    public Wall(PVector startLoc, PVector endLoc) {
        this.startLoc = startLoc;
        this.endLoc = endLoc;
        this.width = Constants.WALL_WIDTH;
    }

    public Wall(PVector startLoc, PVector endLoc, float width) {
        this.startLoc = startLoc;
        this.endLoc = endLoc;
        this.width = width;
    }

    public float getA() {
        return (this.startLoc.y - this.endLoc.y) / (this.startLoc.x - this.endLoc.x);
    }

    public float getB() {
        return (-this.getA() * this.startLoc.x + this.startLoc.y);
    }

    public boolean isCrossingOther(Wall other) {
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

    public float distanceToPoint(PVector point) {
        float a = this.getA();
        float b = this.getB();
        float dist = (float) (Math.abs(a * point.x + b - point.y) / Math.sqrt(Math.pow(a, 2)+1));
        return dist;
    }

    public void draw() {
        parent.pushMatrix();
        float distX = startLoc.x - endLoc.x;
        float distY = startLoc.y - endLoc.y;
        float length = (float) Math.sqrt(Math.pow(distX, 2) + Math.pow(distY, 2));
        float angle = (float) Math.atan(distY /  distX);

        parent.fill(255);
        parent.translate(startLoc.x, startLoc.y);
        parent.rotate(angle);
        parent.rectMode(CENTER);
        parent.rect(length/2, 0, length, this.width);

        parent.popMatrix();
    }

}