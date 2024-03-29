package com.school.tanksgame.sprites;

import com.school.tanksgame.Collision;
import com.school.tanksgame.Constants;
import com.school.tanksgame.player.Player;
import com.school.tanksgame.player.Controls;
import processing.core.PConstants;
import processing.core.PVector;
import processing.event.KeyEvent;

public class Tank extends Sprite {
    private final Player player;
    private final Controls controls;

    private PVector location;
    private float rotation;
    private int color;
    private float health;
    private boolean shoot;
    private int fireDelay;

    private boolean rotatingUp;
    private boolean rotatingDown;
    private boolean drivingForwards;
    private boolean drivingBackwards;

    public Tank(PVector location, Player player, int color) {
        this.location = location;
        this.color = color;
        this.player = player;
        this.controls = player.getControls();

        this.rotation = 0;
        this.health = Constants.TANK_START_HEALTH;
        this.shoot = false;
        this.fireDelay = 0;

        this.rotatingUp = false;
        this.rotatingDown = false;
        this.drivingForwards = false;
        this.drivingBackwards = false;
        this.shoot = false;
    }

    public PVector getLocation() {
        return location;
    }

    public float getHealth() {
        return health;
    }

    public float getRotation() {
        return rotation;
    }

    public Player getPlayer() {
        return player;
    }

    public int getColor() {
        return color;
    }

    public void addHealth() {
       health = Math.min(health+Constants.HEALTHPAD_HEALTH, Constants.TANK_START_HEALTH);
    }

    public void keyAction(KeyEvent event) {
       Controls.ControlType control = controls.get(event.getKeyCode());
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

    @Override
    public void update() {
        //rapid fire reduce delay
        if(fireDelay>0) {
            fireDelay--;
        }

        //update location
        boolean locationUpdated = false;
        boolean block = false;

        float newRotation = rotation;
        //update rotation
        if (rotatingUp) {
            newRotation += Constants.TANK_ROTATIONAL_VEL;
            if (newRotation > 2*Math.PI) {
                newRotation = 0;
            }
        }
        if (rotatingDown) {
            newRotation -= Constants.TANK_ROTATIONAL_VEL;
            if (newRotation < 0) {
                newRotation = (float) (2*Math.PI);
            }
        }

        //update location and make sure it isn't over the window boundaries
        PVector newLoc = new PVector(location.x, location.y);
        PVector dirVec = PVector.fromAngle(newRotation);
        dirVec.setMag(Constants.TANK_DRIVING_VEL);

        if (drivingForwards) {
            newLoc.add(dirVec);
            locationUpdated = true;
        }
        if (drivingBackwards) {
            dirVec.mult(-1);
            newLoc.add(dirVec);
            locationUpdated = true;
        }

        //window border code
        //!(newLoc.x < parent.width && newLoc.x > 0 && newLoc.y < parent.height && newLoc.y > 0 );

        if(newLoc.x < 0 || newLoc.x > parent.width || newLoc.y < 0 || newLoc.y > parent.height) {
            newLoc = location;
        }

        //check for walls thingy
        int wallsColliding = 0;
        for (Wall wall : map.getWalls()) { // map.getWalls(), this is actually Game Over i mean
            float height = Constants.TANK_HEIGHT / 2 + wall.getWidth() / 2;
            float width = Constants.TANK_WIDTH / 2 + wall.getWidth() / 2;
            //find corners

            PVector[] corners = new PVector[] {
                    new PVector(width, height).rotate(newRotation).add(newLoc),
                    new PVector(width, -height).rotate(newRotation).add(newLoc),
                    new PVector(-width, height).rotate(newRotation).add(newLoc),
                    new PVector(-width, -height).rotate(newRotation).add(newLoc)
            };

            for(int k = 0; k < corners.length; k++) {
                int nextIndex = k + 1;
                nextIndex = nextIndex >= corners.length ? 0 : nextIndex;

                PVector corner1 = corners[k];
                PVector corner2 = corners[nextIndex];

                boolean hitting = Collision.linesIntersect(corner1, corner2, wall.getStartLoc(), wall.getEndLoc());
                if (hitting) {
                    wallsColliding++;
                    if (wallsColliding > 1) {
                        block = true;
                        break;
                    }
                    //if it's going inside a wall with new location
                    if (locationUpdated) {
                        PVector line = wall.getLine();
                        PVector blockedVelocity;

                        //if it's on the end of the walls detection
                        boolean onWallEdge = false;
                        //find the lowest point and highest point on the line
                        PVector lowPoint, highPoint;
                        if (wall.startLoc.y <= wall.endLoc.y) {
                            lowPoint = wall.startLoc;
                            highPoint = wall.endLoc;
                        } else {
                            lowPoint = wall.endLoc;
                            highPoint = wall.startLoc;
                        }
                        //get the perpendicular vectors of our line
                        PVector perpen = Collision.getPerpendicular(line);

                        //define line formulas for the highest and lowest point
                        float a = Collision.getLineA(perpen);
                        //if it's a horizontal line
                        if (a == Float.POSITIVE_INFINITY || a == Float.NEGATIVE_INFINITY) {
                            if (location.x < lowPoint.x || location.x > highPoint.x) {
                                onWallEdge = true;
                            }
                            //else i can use linear algebra
                        } else {
                            float b1 = lowPoint.y - a * lowPoint.x;
                            float b2 = highPoint.y - a * highPoint.x;

                            float yOnLine1 = a * location.x + b1;
                            float yOnLine2 = a * location.x + b2;
                            //check if it's below the lowest points line or above the highest points line
                            if (yOnLine1 > location.y || yOnLine2 < location.y) {
                                onWallEdge = true;
                            }
                        }

                        if (onWallEdge) {
                            blockedVelocity = Collision.projection(dirVec, perpen);
                        } else {
                            blockedVelocity = Collision.projection(dirVec, line);
                        }

                        //update location
                        newLoc = new PVector(location.x, location.y).add(blockedVelocity);
                    } else {
                        block = true;
                    }
                    break;
                }
            }
        }

        if(!block) {
            location = newLoc;
            rotation = newRotation;
        }

        //shoot if button is pressed
        if(shoot) {
            this.shoot();
        }
    }

    public void draw() {
        if(!isAlive())
            return;

        parent.stroke(0);
        parent.strokeWeight(1);
        parent.pushMatrix();
        parent.translate(location.x, location.y);

        parent.rectMode(PConstants.CORNER);
        //health bar
        float healthFactor = (255 / Constants.TANK_START_HEALTH) * this.health;
        parent.fill(255);
        parent.rect(-Constants.HEALTHBAR_WIDTH / 2, -(Constants.HEALTHBAR_OFFSET + Constants.HEALTHBAR_HEIGHT), Constants.HEALTHBAR_WIDTH, Constants.HEALTHBAR_HEIGHT);
        parent.fill(255 - healthFactor, healthFactor, 0);
        parent.rect(-Constants.HEALTHBAR_WIDTH / 2, -(Constants.HEALTHBAR_OFFSET + Constants.HEALTHBAR_HEIGHT), (Constants.HEALTHBAR_WIDTH / Constants.TANK_START_HEALTH) * this.health, Constants.HEALTHBAR_HEIGHT);


        parent.rectMode(PConstants.CENTER);
        //main body and shaft
        parent.fill(parent.color(this.color));
        parent.rotate(rotation);
        //main body
        parent.rect(0, 0, Constants.TANK_WIDTH, Constants.TANK_HEIGHT);

        //cannon shaft
        float shaftWidth = Constants.TANK_SHAFT_WIDTH;
        float shaftHeight = Constants.TANK_SHAFT_HEIGHT;
        parent.rect(shaftHeight / 2 + Constants.TANK_WIDTH / 2, 0, shaftHeight, shaftWidth);

        parent.popMatrix();

        //draw bullets
//            for (Bullet bullet : bullets) {
//                bullet.draw();
//            }

    }

    public void shoot() {
        if(fireDelay != 0)
            return;

        PVector dirVec = PVector.fromAngle(this.rotation);
        PVector startLoc = this.location.copy().add(dirVec.setMag((Constants.TANK_HEIGHT / 2) +Constants.TANK_SHAFT_HEIGHT));

        Bullet bullet = new Bullet(startLoc, this.rotation);
        bullet.setParent(parent);
        map.addBullet(bullet);
        fireDelay = Constants.TANK_FIRE_DELAY;

        shoot = false;
    }

    public void damage() {
        this.health--;
    }

    public boolean isAlive() {
        return health > 0;
    }
}
