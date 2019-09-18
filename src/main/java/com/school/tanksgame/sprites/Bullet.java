package com.school.tanksgame.sprites;

import com.school.tanksgame.Constants;
import processing.core.PVector;

public class Bullet extends Sprite {
    PVector location;
    PVector velocity;
    float force = 10;

    float radius = Constants.BULLET_RADIUS;
    float health = Constants.BULLET_HEALTH;

    public Bullet(PVector location, float rotation) {
        this.location = location;
        PVector dirVec = PVector.fromAngle(rotation);
        velocity = dirVec.mult(force);
    }

    public void setVelocity(PVector velocity) {
        this.velocity = velocity;
    }

    public PVector getVelocity() {
        return velocity;
    }

    void update() {
        location.add(velocity);

        //general window borders
        if (location.x - radius < 0) {
            location.x = radius;
            velocity.x *= -1;

            health--;
        } else if (location.x + radius > parent.width) {
            location.x = parent.width- radius;
            velocity.x *= -1;
            health--;
        }

        if (location.y - radius < 0) {
            location.y = radius;
            velocity.y *= -1;
            health--;
        } else if (location.y + radius > parent.height) {
            location.y = parent.height - radius;
            velocity.y *= -1;
            health--;
        }
    }

    public void draw() {
        if(isAlive()) {
            parent.fill(255 - (255 / Constants.BULLET_HEALTH) * health);
            parent.ellipse(location.x, location.y, radius*2, radius*2);
        }
    }
    boolean isAlive() {
        return health > 0 ? true : false;
    }

}
