package com.school.tanksgame;

import com.school.tanksgame.maploading.Map;
import com.school.tanksgame.maploading.MapLoader;
import processing.core.PApplet;
import processing.event.KeyEvent;

public class Game extends PApplet {

    private int titleTime = 0;
    private MapLoader mapLoader;
    private Map map;
    private TitleController titles;

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

    public void initializeMap(int index) {
        map = mapLoader.getMap(index);
        titles.addTitle("Loading map: "+map.getName(), 300);
    }
    @Override
    public void setup() {
        titles = new TitleController(this);
        mapLoader = new MapLoader("maps.json", this);
        mapLoader.load();
        this.initializeMap(0);
    }

    @Override
    public void draw() {
        clear();
        background(255);
        if(titles.isActive()) {
            titles.draw();
            titles.update();
        } else {
            map.update();
            map.draw();
        }
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
