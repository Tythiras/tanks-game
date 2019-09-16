package com.school.tanksgame;

import processing.core.PVector;

public class Wall {
    PVector startLoc;
    PVector endLoc;
    float width = 30;
    Wall(PVector startLoc, PVector endLoc) {
        this.startLoc = startLoc;
        this.endLoc = endLoc;
    }
}
