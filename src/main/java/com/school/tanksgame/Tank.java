package com.school.tanksgame;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

import java.util.HashMap;
import java.util.Map;

public class Tank {
    PApplet p;

    Map<Controls, Integer> controlsMap = new HashMap<>();


    PVector location;
    float rotation;
    float drivingVelocity = 3;
    float rotationVelocity = (float) 0.05;

    boolean isRotatingUp = false;
    boolean isRotatingDown = false;
    boolean isDrivingForward = false;
    boolean isDrivingBackwards = false;

    float width = 50;
    float height = 20;

    public Tank(PApplet parent, PVector location, Map<Controls, Integer> controlsMap) {
        p = parent;

        this.controlsMap = controlsMap;

        this.location = location;
        rotation = 0;
    }

    void update() {
        //update rotation
        if (isRotatingUp) {
            rotation += rotationVelocity;
            if (rotation > 2*Math.PI) {
                rotation = 0;
            }
        }
        if (isRotatingDown) {
            rotation -= rotationVelocity;
            if (rotation < 0) {
                rotation = (float) (2*Math.PI);
            }
        }

        //update location and make sure it isn't over the window boundaries
        PVector newLoc = new PVector(location.x, location.y);
        PVector dirVec = PVector.fromAngle(rotation);
        dirVec.setMag(drivingVelocity);

        if (isDrivingForward) {
            newLoc.add(dirVec);
        }
        if (isDrivingBackwards) {
            dirVec.mult(-1);
            newLoc.add(dirVec);
        }
        p.println(newLoc);
        if(newLoc.x < p.width && newLoc.x > 0 && newLoc.y < p.height && newLoc.y > 0 ) {
            location = newLoc;
        }
    }

    void draw() {
        p.pushMatrix();
        p.translate(location.x, location.y);
        p.rotate(rotation);

        p.rectMode(PConstants.CENTER);
        //main body
        p.rect(0, 0, width, height);

        //cannon shaft
        float shaftWidth = (float) (width / 1.25);
        float shaftHeight = height / 5;
        p.rect(shaftWidth / 2 + width / 2, 0, shaftWidth, shaftHeight);


        p.popMatrix();
    }
}
