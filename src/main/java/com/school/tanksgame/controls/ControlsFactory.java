package com.school.tanksgame.controls;

import java.util.ArrayList;

public class ControlsFactory {
    private static ArrayList<Controls> controls;

    static {
        controls = new ArrayList<>();
        controls.add(new PlayerOneControls());
        controls.add(new PlayerTwoControls());
    }

    public static Controls getControls() {
        return controls.remove(0);
    }

    private ControlsFactory() {}
}
