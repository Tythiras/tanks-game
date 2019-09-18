package com.school.tanksgame;

import processing.core.PApplet;
import processing.core.PVector;

public class HealthPad extends Sprite {
    PVector location;

    HealthPad(PVector location) {
        this.location = location;
    }

    public void draw() {
        parent.ellipse(location.x, location.y, 20, 20);
    }
}
