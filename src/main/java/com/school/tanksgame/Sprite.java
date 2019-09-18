package com.school.tanksgame;

import processing.core.PApplet;

public class Sprite implements PAppletChild {
    protected PApplet parent;

    @Override
    public void setParent(PApplet parent) {
        this.parent = parent;
    }

    @Override
    public void draw() {

    }
}
