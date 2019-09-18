package com.school.tanksgame.sprites;

import processing.core.PApplet;

public interface PAppletChild {
    void setParent(PApplet parent);
    void draw();
}
