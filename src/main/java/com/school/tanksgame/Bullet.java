package com.school.tanksgame;

import processing.core.PApplet;
import processing.core.PVector;

public class Bullet {
    PApplet p;

    PVector location;
    PVector velocity;
    float force = 20;

    float radius = 5;
    float health = 5;

    public Bullet(PApplet parent, PVector location, float rotation) {
        p = parent;

        this.location = location;
        PVector dirVec = PVector.fromAngle(rotation);
        velocity = dirVec.mult(force);
    }

    void update() {
        location.add(velocity);

        //general window borders
        if (location.x - radius < 0) {
            location.x = radius;
            velocity.x *= -1;

            health--;
        } else if (location.x + radius > p.width) {
            location.x = p.width- radius;
            velocity.x *= -1;
            health--;
        }

        if (location.y - radius < 0) {
            location.y = radius;
            velocity.y *= -1;
            health--;
        } else if (location.y + radius > p.height) {
            location.y = p.height - radius;
            velocity.y *= -1;
            health--;
        }
    }

    void draw() {
        if(isAlive()) {
            p.ellipse(location.x, location.y, radius*2, radius*2);
        }
    }
    boolean isAlive() {
        return health > 0 ? true : false;
    }

}
