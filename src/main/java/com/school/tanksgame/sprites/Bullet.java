package com.school.tanksgame.sprites;

import com.school.tanksgame.Constants;
import processing.core.PVector;

public class Bullet extends Sprite {
    private PVector location;
    private PVector velocity;
    private float force = 10;

    private float radius = Constants.BULLET_RADIUS;
    private float health = Constants.BULLET_HEALTH;

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

    public void update() {
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

    @Override
    public void draw() {
        if(!isAlive())
            return;

        parent.strokeWeight(1);
        parent.fill(255 - (255 / Constants.BULLET_HEALTH) * health);
        parent.ellipse(location.x, location.y, radius*2, radius*2);
    }

    @Override
    public void damage() {
        this.health--;
    }

    @Override
    public void die() {
        health = -1;
    }

    @Override
    public boolean isAlive() {
        return health > 0;
    }

    public PVector getLocation() {
        return location;
    }

    public float getRadius() {
        return radius;
    }
}
