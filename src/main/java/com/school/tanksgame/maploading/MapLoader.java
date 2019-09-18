package com.school.tanksgame.maploading;

import com.school.tanksgame.sprites.HealthPad;
import com.school.tanksgame.sprites.Wall;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import processing.core.PApplet;
import processing.core.PVector;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MapLoader {
    private String loadFilePath;
    private ArrayList<Map> maps;
    private PApplet parent;

    public MapLoader(String loadFilePath, PApplet parent) {
        this.loadFilePath = loadFilePath;
        this.parent = parent;
        this.maps = new ArrayList<>();
    }

    public void load() {
        JSONParser parser = new JSONParser();
        try {
            JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(loadFilePath));
            for (JSONObject jsonObject : (Iterable<JSONObject>) jsonArray) {
                ArrayList<Wall> walls = getWallsFromArray((JSONArray) jsonObject.get("walls"));
                ArrayList<HealthPad> healthPads = getHealthPadsFromArray((JSONArray) jsonObject.get("health_pads"));

                String name = (String) jsonObject.get("name");

                Map map = new Map(name);
                map.setWalls(walls);
                map.setHealthPads(healthPads);
                map.setParent(parent);

                maps.add(map);
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Wall> getWallsFromArray(JSONArray wallsArray) {
        ArrayList<Wall> walls = new ArrayList<>();
        for (JSONObject wallObject : (Iterable<JSONObject>) wallsArray) {
            Wall wall;

            JSONArray wallCoords = (JSONArray) wallObject.get("coords");

            PVector startCoords = new PVector(
                    Float.valueOf((Long) wallCoords.get(0)),
                    Float.valueOf((Long) wallCoords.get(1))
            );
            PVector endCoords = new PVector(
                    Float.valueOf((Long) wallCoords.get(2)),
                    Float.valueOf((Long) wallCoords.get(3))
            );

            float width = Float.valueOf((Long) wallObject.getOrDefault("width", 0L));

            if (width == 0)
                wall = new Wall(startCoords, endCoords);
            else
                wall = new Wall(startCoords, endCoords, width);

            walls.add(wall);
        }
        return  walls;
    }

    private ArrayList<HealthPad> getHealthPadsFromArray(JSONArray healthPadsArray) {
        ArrayList<HealthPad> healthPads = new ArrayList<>();
        for (JSONArray healthPadCoords: (Iterable<JSONArray>) healthPadsArray) {
            PVector location = new PVector(
                    Float.valueOf((Long) healthPadCoords.get(0)),
                    Float.valueOf((Long) healthPadCoords.get(1))
            );
            HealthPad healthPad = new HealthPad(location);
            healthPads.add(healthPad);
        }
        return healthPads;
    }

    public Map getMap(int index) {
        return maps.get(index);
    }
}
