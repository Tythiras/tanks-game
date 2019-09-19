package com.school.tanksgame;

public class Constants {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 1000;

    public static final float TANK_DRIVING_VEL = 3;
    public static final float TANK_ROTATIONAL_VEL = 0.05f;
    public static final float TANK_WIDTH = 30;
    public static final float TANK_HEIGHT = 30;
    public static final float TANK_SHAFT_HEIGHT = 20;
    public static final float TANK_SHAFT_WIDTH = 5;
    public static final float TANK_START_HEALTH = 10;
    public static final int TANK_FIRE_DELAY = 10;
    public static final float TANK_HITBOX = Constants.TANK_HEIGHT / 2 + Constants.TANK_SHAFT_HEIGHT;


    public static final float WALL_WIDTH = 5;

    public static final float HEALTHBAR_WIDTH = 70;
    public static final float HEALTHBAR_HEIGHT = 10;
    public static final float HEALTHBAR_OFFSET = 50;

    public static final float HEALTHPAD_RADIUS = 15;

    public static final int BULLET_HEALTH = 3;
    public static final int BULLET_RADIUS = 5;


    private Constants() {}
}
