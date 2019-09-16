package com.school.tanksgame;

import processing.core.PVector;

public class Tank {
    PVector location;
    float rotation;

    public Tank(PVector location) {
            this.location = location;
            rotation = 0;
    }
}
