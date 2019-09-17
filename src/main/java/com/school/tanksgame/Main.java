package com.school.tanksgame;

import processing.core.PApplet;
import processing.core.PVector;

public class Main extends PApplet {
    public static void main(String[] args) {
        PApplet.main("com.school.tanksgame.Main");
    }
    Tank tank;
    @Override
    public void settings() {
        size(500, 500);
    }

    @Override
    public void setup() {
        tank = new Tank(this, new PVector(width/3, height/2));

    }

    @Override
    public void draw() {
        clear();

        tank.update();
        tank.draw();
    }

    @Override
    public void keyPressed() {
        //right arrow key
        if (keyCode == 39) {
            tank.isRotatingUp = true;
        //left arrow key
        } else if (keyCode == 37) {
            tank.isRotatingDown = true;
        //up arrow key
        } else if (keyCode == 38) {
            tank.isDrivingForward = true;
        //down arrow key
        } else if (keyCode == 40) {
            tank.isDrivingBackwards = true;
        }
    }

    @Override
    public void keyReleased() {
        //right arrow key
        if (keyCode == 39) {
            tank.isRotatingUp = false;
            //left arrow key
        } else if (keyCode == 37) {
            tank.isRotatingDown = false;
            //up arrow key
        } else if (keyCode == 38) {
            tank.isDrivingForward = false;
            //down arrow key
        } else if (keyCode == 40) {
            tank.isDrivingBackwards = false;
        }
    }

}
