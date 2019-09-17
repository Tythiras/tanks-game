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
        for(Tank tank : tanks) {
            tank.update();
            tank.draw();
        }

    }

    @Override
    public void keyPressed() {
        for(Tank tank : tanks) {
            for (Map.Entry<Controls, Integer>  control : tank.controlsMap.entrySet()) {
                if(control.getValue()==keyCode) {
                    if(control.getKey()==Controls.rotateUp) {
                        tank.isRotatingUp = true;
                    } else if(control.getKey()==Controls.rotateDown) {
                        tank.isRotatingDown = true;
                    } else if(control.getKey()==Controls.drivingForward) {
                        tank.isDrivingForward = true;
                    } else if(control.getKey()==Controls.drivingBackwards) {
                        tank.isDrivingBackwards = true;
                    }
                }
            }
        }
    }

    @Override
    public void keyReleased() {
        for(Tank tank : tanks) {
            for (Map.Entry<Controls, Integer>  control : tank.controlsMap.entrySet()) {
                if(control.getValue()==keyCode) {
                    if(control.getKey()==Controls.rotateUp) {
                        tank.isRotatingUp = false;
                    } else if(control.getKey()==Controls.rotateDown) {
                        tank.isRotatingDown = false;
                    } else if(control.getKey()==Controls.drivingForward) {
                        tank.isDrivingForward = false;
                    } else if(control.getKey()==Controls.drivingBackwards) {
                        tank.isDrivingBackwards = false;
                    }
                }
            }
        }
    }

}
