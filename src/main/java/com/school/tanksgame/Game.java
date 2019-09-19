package com.school.tanksgame;

import com.school.tanksgame.controls.PlayerOneControls;
import com.school.tanksgame.controls.PlayerTwoControls;
import com.school.tanksgame.maploading.Map;
import com.school.tanksgame.maploading.MapLoader;
import com.school.tanksgame.sprites.HealthPad;
import com.school.tanksgame.sprites.Tank;
import processing.core.PApplet;
import processing.core.PVector;
import processing.event.KeyEvent;

import java.util.ArrayList;
import java.util.HashMap;

public class Game extends PApplet {

    private ArrayList<Tank> tanks = new ArrayList<>();
    private MapLoader mapLoader;
    private Map map;

    void gameOver() {

    }

    void startGame() {

    }

    void loadMap() {

    }

    @Override
    public void settings() {
        size(Constants.WIDTH, Constants.HEIGHT);
    }

    @Override
    public void setup() {
        mapLoader = new MapLoader("maps.json", this);
        mapLoader.load();
        map = mapLoader.getMap(0);

        java.util.Map<Integer, Controlxs> controlsMapTank1 = new HashMap<>();
        controlsMapTank1.put(10, Controlxs.SHOOT);
        controlsMapTank1.put(39, Controlxs.ROTATE_UP);
        controlsMapTank1.put(37, Controlxs.ROTATE_DOWN);
        controlsMapTank1.put(38, Controlxs.DRIVING_FORWARD);
        controlsMapTank1.put(40, Controlxs.DRIVING_BACKWARDS);

        Tank newTank1 = new Tank(new PVector(50, 50), new PlayerOneControls(), -16776961);
        newTank1.setParent(this);
        tanks.add(newTank1);

        java.util.Map<Integer, Controlxs> controlsMapTank2 = new HashMap<>();
        controlsMapTank2.put(32, Controlxs.SHOOT);
        controlsMapTank2.put(68, Controlxs.ROTATE_UP);
        controlsMapTank2.put(65, Controlxs.ROTATE_DOWN);
        controlsMapTank2.put(87, Controlxs.DRIVING_FORWARD);
        controlsMapTank2.put(83, Controlxs.DRIVING_BACKWARDS);

        Tank newTank2 = new Tank(new PVector(50, 50), new PlayerTwoControls(), -7667712);
        newTank2.setParent(this);
        tanks.add(newTank2);
    }

    @Override
    public void draw() {
        clear();
        background(255);

        map.draw();
        for(Tank tank : tanks) {
            tank.update(map.getWalls(), tanks);
            tank.draw();
            if(tank.getHealth() == Constants.TANK_START_HEALTH)
                continue;

            ArrayList<HealthPad> healthPads = map.getHealthPads();
            for(int i = healthPads.size(); i > 0; i--) {
                HealthPad healthPad = healthPads.get(i-1);


                float dist = Collision.dist(healthPad.getLocation(), tank.getLocation());
                if(dist < Constants.HEALTHPAD_RADIUS) {
                    healthPads.remove(healthPad);
                    tank.addHealth();
                }
            }

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
