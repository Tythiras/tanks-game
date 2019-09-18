package com.school.tanksgame.sprites;

import processing.core.PVector;

public class HealthPad extends Sprite {
    PVector location;

    public HealthPad(PVector location) {
        this.location = location;
    }

    public void draw() {
        parent.strokeWeight(1);
        parent.ellipse(location.x, location.y, 20, 20);
    }
}
