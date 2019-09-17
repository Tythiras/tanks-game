package com.school.tanksgame;

import processing.core.PApplet;
import processing.core.PVector;

public class HealthPad {
    PApplet p;

    PVector location;

    HealthPad(PApplet parent, PVector location) {
        p = parent;
        this.location = location;
    }

    void draw() {
        p.ellipse(location.x, location.y, 20, 20);
    }
}
