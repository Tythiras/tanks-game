package com.school.tanksgame.sprites;

import com.school.tanksgame.Constants;
import processing.core.PVector;

public class HealthPad extends Sprite {
    private PVector location;
    private boolean alive;

    public HealthPad(PVector location) {
        this.location = location;
        this.alive = true;
    }

    public PVector getLocation() {
        return location;
    }


    public void draw() {
        parent.strokeWeight(0);
        parent.stroke(0);
        parent.fill(0xFF00FF00);
        parent.ellipse(location.x, location.y, Constants.HEALTHPAD_RADIUS * 2, Constants.HEALTHPAD_RADIUS * 2);

        float radiusOff = Constants.HEALTHPAD_RADIUS * 0.6f;
        parent.stroke(255);
        parent.strokeWeight(3);
        parent.line(location.x, location.y-radiusOff, location.x, location.y+radiusOff);
        parent.line(location.x-radiusOff, location.y, location.x+radiusOff, location.y);
    }

    @Override
    public void damage() {
        alive = false;
    }

    @Override
    public boolean isAlive() {
        return alive;
    }
}
