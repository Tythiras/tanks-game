package com.school.tanksgame;

import processing.core.PApplet;
import processing.core.PVector;
import processing.event.KeyEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main extends PApplet {
    private ArrayList<Tank> tanks = new ArrayList<>();

    @Override
    public void settings() {
        size(500, 500);
    }

    @Override
    public void setup() {
        Map<Integer, Controls> controlsMap = new HashMap<>();
        controlsMap.put(39, Controls.ROTATE_UP);
        controlsMap.put(37, Controls.ROTATE_DOWN);
        controlsMap.put(38, Controls.DRIVING_FORWARD);
        controlsMap.put(40, Controls.DRIVING_BACKWARDS);

        Tank newTank = new Tank(this, new PVector(width/2f, height/2f), controlsMap);
        tanks.add(newTank);

    }

    @Override
    public void draw() {
        clear();
        for(Tank tank : tanks) {
            tank.update();
            tank.draw();
        }
    }

    @Override
    public void keyPressed(KeyEvent event) {
        for(Tank tank : tanks)
            tank.keyAction(event);
    }

    @Override
    public void keyReleased(KeyEvent event) {
        for(Tank tank : tanks)
            tank.keyAction(event);
    }

    public static void main(String[] args) {
        PApplet.main("com.school.tanksgame.Main");
    }
}
