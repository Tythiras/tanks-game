package com.school.tanksgame;

import com.school.tanksgame.maploading.Map;
import com.school.tanksgame.maploading.MapLoader;
import com.school.tanksgame.sprites.HealthPad;
import com.school.tanksgame.sprites.Tank;
import com.school.tanksgame.sprites.Wall;
import processing.core.PApplet;
import processing.core.PVector;
import processing.event.KeyEvent;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Game extends PApplet {

    private ArrayList<Tank> tanks = new ArrayList<>();
    private MapLoader mapLoader;
    private Map map;

    @Override
    public void settings() {
        size(Constants.WIDTH, Constants.HEIGHT);
    }

    @Override
    public void setup() {
        mapLoader = new MapLoader("maps.json", this);
        mapLoader.load();
        map = mapLoader.getMap(0);

        java.util.Map<Integer, Controls> controlsMapTank1 = new HashMap<>();
        controlsMapTank1.put(10, Controls.SHOOT);
        controlsMapTank1.put(39, Controls.ROTATE_UP);
        controlsMapTank1.put(37, Controls.ROTATE_DOWN);
        controlsMapTank1.put(38, Controls.DRIVING_FORWARD);
        controlsMapTank1.put(40, Controls.DRIVING_BACKWARDS);

        Tank newTank1 = new Tank(new PVector(50, 50), controlsMapTank1, 0xFF0000FF);
        newTank1.setParent(this);
        tanks.add(newTank1);

        java.util.Map<Integer, Controls> controlsMapTank2 = new HashMap<>();
        controlsMapTank2.put(32, Controls.SHOOT);
        controlsMapTank2.put(65, Controls.ROTATE_UP);
        controlsMapTank2.put(68, Controls.ROTATE_DOWN);
        controlsMapTank2.put(87, Controls.DRIVING_FORWARD);
        controlsMapTank2.put(83, Controls.DRIVING_BACKWARDS);

        Tank newTank2 = new Tank(new PVector(50, 50), controlsMapTank2, 0xFF8B0000);
        newTank2.setParent(this);
        tanks.add(newTank2);
    }

    @Override
    public void draw() {


        clear();
        background(255);

        map.draw();
        for(Tank tank : tanks) {
            ArrayList<HealthPad> healthPads = map.getHealthPads();
            for(int i = healthPads.size(); i > 0; i--) {
                HealthPad healthPad = healthPads.get(i-1);
                float distX = healthPad.getLocation().x - tank.getLocation().x;
                float distY = healthPad.getLocation().y - tank.getLocation().y;
                float dist = (float) Math.sqrt(Math.pow(distX, 2) + Math.pow(distY, 2));
                if(dist<Constants.HEALTHPAD_RADIUS&&tank.getHealth()<Constants.TANK_START_HEALTH) {
                    healthPads.remove(healthPad);
                    tank.addHealth();
                }
            }

            tank.update(map.getWalls(), tanks);
            tank.draw();
        }
    }

    @Override
    public void keyPressed(KeyEvent event) {
        for(Tank tank : tanks)
            tank.keyAction(event);
    }

    @Override
    public void keyReleased(KeyEvent event) {
        for(Tank tank : tanks)
            tank.keyAction(event);
    }

    public static void main(String[] args) {
        PApplet.main("com.school.tanksgame.Game");
    }
}
