package com.school.tanksgame;

import com.school.tanksgame.maploading.MapLoader;
import org.junit.jupiter.api.Test;

class MapLoaderTest {

    @Test
    void load() {
        MapLoader mapLoader = new MapLoader("maps.json");
        mapLoader.load();
    }

    @Test
    void casting() {
        System.out.println(Float.valueOf(1000l));
    }
}