package com.school.tanksgame.sprites;

import com.school.tanksgame.Collision;
import com.school.tanksgame.Constants;
import processing.core.PConstants;
import processing.core.PVector;

public class Wall extends Sprite {

    PVector startLoc;
    PVector endLoc;
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

    public PVector getLine() {
        return PVector.sub(endLoc, startLoc);
    }

    public float getWidth() {
        return width;
    }


    public void draw() {
        parent.strokeWeight(width);
        parent.strokeCap(PConstants.SQUARE);
        parent.stroke(0);
        parent.line(startLoc.x, startLoc.y, endLoc.x, endLoc.y);
    }
}
