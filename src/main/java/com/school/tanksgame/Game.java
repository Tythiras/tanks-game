package com.school.tanksgame;

import com.school.tanksgame.maploading.Map;
import com.school.tanksgame.maploading.MapLoader;
import processing.core.PApplet;
import processing.event.KeyEvent;

public class Game extends PApplet {

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
    }

    @Override
    public void draw() {
        clear();
        background(255);
        map.update();
        map.draw();
    }

    @Override
    public void keyPressed(KeyEvent event) {
        map.keyAction(event);
    }

    @Override
    public void keyReleased(KeyEvent event) {
        map.keyAction(event);
    }

    public static void main(String[] args) {
        PApplet.main("com.school.tanksgame.Game");
    }
}
