package com.school.tanksgame.sprites;

import com.school.tanksgame.maploading.Map;
import processing.core.PApplet;

public interface PAppletChild {
    void setParent(PApplet parent);
    void setMap(Map map);
    void update();
    void draw();
    void damage();
    boolean isAlive();
}
