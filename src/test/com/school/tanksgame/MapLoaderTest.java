package com.school.tanksgame;

import com.school.tanksgame.maploading.MapLoader;
import org.junit.jupiter.api.Test;

import javax.swing.*;

class MapLoaderTest {

    @Test
    void load() {
        MapLoader mapLoader = new MapLoader("maps.json", null);
        mapLoader.load();
    }

    @Test
    void casting() {
        System.out.println(Float.valueOf(1000l));
        System.out.println(Long.decode("0xFF0000FF"));
    }

    @Test
    void hex() {
        System.out.println(getDecimal("0xFF0000FF"));
        System.out.println(getDecimal("0xFF8B0000"));
        System.out.println((int) Long.parseLong("FF0000FF", 16));
    }

    public static int getDecimal(String hex){
        String digits = "0123456789ABCDEF";
        hex = hex.toUpperCase();
        int val = 0;
        for (int i = 0; i < hex.length(); i++)
        {
            char c = hex.charAt(i);
            int d = digits.indexOf(c);
            val = 16*val + d;
        }
        return val;
    }
}