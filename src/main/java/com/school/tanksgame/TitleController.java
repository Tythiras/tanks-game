package com.school.tanksgame;

import processing.core.PApplet;
import processing.core.PConstants;

import java.util.HashMap;
import java.util.Map;

public class TitleController {
    PApplet parent;
    HashMap<String, Integer> titles = new HashMap<String, Integer>();

    TitleController(PApplet parent) {
        this.parent = parent;
    }
    public boolean isActive() {
        return titles.entrySet().iterator().hasNext();
    }
    public void update() {
        Map.Entry<String, Integer> entry = titles.entrySet().iterator().next();
        int time = entry.getValue();
        if(time > 0) {
            entry.setValue(time-1);
        } else {
            titles.remove(entry.getKey());
        }
    }
    public void draw() {
        Map.Entry<String, Integer> entry = titles.entrySet().iterator().next();
        parent.fill(0);
        parent.textAlign(PConstants.CENTER);
        parent.textSize(30);
        parent.text(entry.getKey(), parent.height / 2, parent.width / 2);
    }
    public void addTitle(String title, int time) {
        titles.put(title, time);
    }
}
