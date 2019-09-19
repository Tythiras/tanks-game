package com.school.tanksgame.sprites;

import com.school.tanksgame.maploading.Map;
import processing.core.PApplet;

public abstract class Sprite implements PAppletChild {
    protected PApplet parent;
    protected Map map;

    @Override
    public void update() {}

    @Override
    public void draw() {}

    @Override
    public void damage() {}

    @Override
    public void die() {}

    @Override
    public boolean isAlive() {
        return true;
    }

    @Override
    public void setParent(PApplet parent) {
        this.parent = parent;
    }

    @Override
    public void setMap(Map map) {
        this.map = map;
    }

}
