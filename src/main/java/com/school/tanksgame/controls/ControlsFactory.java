package com.school.tanksgame.controls;

import java.util.ArrayList;

public class ControlsFactory {
    private static ArrayList<Controls> controls;
    private static int index = 0;

    static {
        controls = new ArrayList<>();
        controls.add(new PlayerOneControls());
        controls.add(new PlayerTwoControls());
    }

    public static Controls getControls() {
        return controls.get(++index % controls.size());
    }

    private ControlsFactory() {}
}
