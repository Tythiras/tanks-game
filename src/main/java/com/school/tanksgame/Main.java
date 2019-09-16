package com.school.tanksgame;

import processing.core.PApplet;

public class Main extends PApplet {
    public static void main(String[] args) {
        PApplet.main("com.school.tanksgame.Main");
    }

    @Override
    public void settings() {
        size(500, 500);
    }

    @Override
    public void setup() {

    }

    @Override
    public void draw() {
        rect(width/2, height/2, 50,50);
    }
}
