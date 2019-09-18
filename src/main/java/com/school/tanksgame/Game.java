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

        java.util.Map<Integer, Controls> controlsMap = new HashMap<>();
        controlsMap.put(10, Controls.SHOOT);
        controlsMap.put(39, Controls.ROTATE_UP);
        controlsMap.put(37, Controls.ROTATE_DOWN);
        controlsMap.put(38, Controls.DRIVING_FORWARD);
        controlsMap.put(40, Controls.DRIVING_BACKWARDS);

        Tank newTank = new Tank(new PVector(50, 50), controlsMap, 0xFF0000FF);
        newTank.setParent(this);
        tanks.add(newTank);
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
                if(dist<Constants.HEALTHPAD_RADIUS) {
                    healthPads.remove(healthPad);
                    //add health to tank here JINYANG
                }
            }

            tank.update(map.getWalls());
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
