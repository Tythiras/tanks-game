package com.school.tanksgame;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main extends PApplet {
    ArrayList<Tank> tanks = new ArrayList<>();


    public static void main(String[] args) {
        PApplet.main("com.school.tanksgame.Main");
    }

    @Override
    public void settings() {
        size(500, 500);
    }

    @Override
    public void setup() {
        Map<Controls, Integer> controlsMap = new HashMap<>();
        controlsMap.put(Controls.rotateUp, 39);
        controlsMap.put(Controls.rotateDown, 37);
        controlsMap.put(Controls.drivingForward, 38);
        controlsMap.put(Controls.drivingBackwards, 40);
        Tank newTank = new Tank(this, new PVector(width/2, height/2), controlsMap);
        tanks.add(newTank);

    }

    @Override
    public void draw() {
        clear();

    }

    @Override
    public void keyPressed() {
    }

    @Override
    public void keyReleased() {
    }

}
