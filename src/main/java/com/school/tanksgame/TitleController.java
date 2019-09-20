package com.school.tanksgame;

import processing.core.PApplet;
import processing.core.PConstants;

import java.util.ArrayList;

public class TitleController {
    private PApplet parent;
    private ArrayList<String> titles = new ArrayList<>();
    private int countdown;

    TitleController(PApplet parent) {
        this.parent = parent;
        this.countdown = Constants.TITLE_DELAY;
    }

    public boolean isActive() {
        return titles.iterator().hasNext();
    }

    private void update() {
        String entry = titles.iterator().next();
        if(countdown > 0) {
            countdown--;
            return;
        }
        countdown = Constants.TITLE_DELAY;
        titles.remove(entry);
    }

    public void draw() {
        String entry = titles.iterator().next();
        parent.fill(0);
        parent.textAlign(PConstants.CENTER);
        parent.textSize(30);
        parent.text(entry, parent.height / 2, parent.width / 2);
        update();
    }

    public void addTitle(String title) {
        titles.add(title);
    }
}
