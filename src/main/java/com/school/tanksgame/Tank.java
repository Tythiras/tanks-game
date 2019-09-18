package com.school.tanksgame;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;
import processing.event.KeyEvent;

import java.util.ArrayList;
import java.util.Map;

public class Tank {
    Map<Integer, Controls> controls;
    ArrayList<Bullet> bullets;

    private PApplet p;
    private PVector location;
    private float rotation;

    private boolean shoot;

    private boolean rotatingUp;
    private boolean rotatingDown;
    private boolean drivingForwards;
    private boolean drivingBackwards;

    public Tank(PApplet parent, PVector location, Map<Integer, Controls> controls) {
        this.p = parent;
        this.controls = controls;
        this.location = location;

        this.rotation = 0;

        bullets = new ArrayList<>();

        this.rotatingUp = false;
        this.rotatingDown = false;
        this.drivingForwards = false;
        this.drivingBackwards = false;
        this.shoot = false;
    }

    public void shoot() {
        PVector dirVec = PVector.fromAngle(this.rotation);
        PVector startLoc = this.location.copy().add(dirVec.setMag((Constants.TANK_HEIGHT / 2) +Constants.TANK_SHAFT_HEIGHT));

        bullets.add(new Bullet(p, startLoc, this.rotation));
    }

    public void keyAction(KeyEvent event) {
       Controls control = controls.get(event.getKeyCode());
       int keyAction = event.getAction();

       if (control == null)
           return;

       switch (control) {
           // keyAction == 1 means that it's keyPressed, otherwise keyReleased
           case SHOOT:
               shoot = keyAction == 1;
               break;
           case ROTATE_UP:
               rotatingUp = keyAction == 1;
               break;
           case ROTATE_DOWN:
               rotatingDown = keyAction == 1;
               break;
           case DRIVING_FORWARD:
               drivingForwards = keyAction == 1;
               break;
           case DRIVING_BACKWARDS:
               drivingBackwards = keyAction == 1;
               break;
       }
    }

    void update() {
        //update rotation
        if (rotatingUp) {
            rotation += Constants.TANK_ROTATIONAL_VEL;
            if (rotation > 2*Math.PI) {
                rotation = 0;
            }
        }
        else if (rotatingDown) {
            rotation -= Constants.TANK_ROTATIONAL_VEL;
            if (rotation < 0) {
                rotation = (float) (2*Math.PI);
            }
        }

        //update location and make sure it isn't over the window boundaries
        PVector newLoc = new PVector(location.x, location.y);
        PVector dirVec = PVector.fromAngle(rotation);
        dirVec.setMag(Constants.TANK_DRIVING_VEL);

        if (drivingForwards) {
            newLoc.add(dirVec);
        }
        if (drivingBackwards) {
            dirVec.mult(-1);
            newLoc.add(dirVec);
        }
        if(newLoc.x < p.width && newLoc.x > 0 && newLoc.y < p.height && newLoc.y > 0 ) {
            location = newLoc;
        }

        //shoot if button is pressed
        if(shoot) {
            this.shoot();

            //no machine guns here
            shoot = false;
        }


        //update bullets
        for(int i = bullets.size(); i > 0; i--) {
            Bullet bullet = bullets.get(i-1);
            bullet.update();
            if(!bullet.isAlive()) {
                bullets.remove(bullet);
            }
        }
    }

    void draw() {
        p.pushMatrix();
        p.translate(location.x, location.y);
        p.rotate(rotation);

        p.rectMode(PConstants.CENTER);
        //main body
        p.rect(0, 0, Constants.TANK_WIDTH, Constants.TANK_HEIGHT);

        //cannon shaft
        float shaftWidth = Constants.TANK_SHAFT_WIDTH;
        float shaftHeight = Constants.TANK_SHAFT_HEIGHT;
        p.rect(shaftHeight / 2 + Constants.TANK_WIDTH / 2, 0, shaftHeight, shaftWidth);

        p.popMatrix();

        //draw bullets
        for(Bullet bullet : bullets) {
            bullet.draw();
        }
    }
}
