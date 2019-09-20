package com.school.tanksgame;

import com.school.tanksgame.controls.Player;
import com.school.tanksgame.maploading.Map;
import com.school.tanksgame.maploading.MapLoader;
import processing.core.PApplet;
import processing.event.KeyEvent;

public class Game extends PApplet {

    private MapLoader mapLoader;
    private Map map;
    private TitleController titleController;

    @Override
    public void settings() {
        size(Constants.WIDTH, Constants.HEIGHT);
    }

    @Override
    public void setup() {
        titleController = new TitleController(this);
        mapLoader = new MapLoader("maps.json", this);
        loadNextMap();
    }

    @Override
    public void draw() {
        clear();
        background(255);

        if(titleController.isActive()) {
            titleController.draw();
            return;
        }

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

    public void gameOver(String message) {
        titleController.addTitle(message);
        loadNextMap();
    }

    private void loadNextMap() {
        map = mapLoader.nextMap();
        if (map == null) {
            mapLoader.loadMaps();
            loadNextMap();
        }
        titleController.addTitle("Loading map: "+map.getName());
    }

    public static void main(String[] args) {
        PApplet.main("com.school.tanksgame.Game");
    }
}
