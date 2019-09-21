package com.school.tanksgame.player;

import java.util.HashMap;

public abstract class Controls {
    protected HashMap<Integer, ControlType> controlsMap = new HashMap<>();

    public ControlType get(int keyCode) {
        return controlsMap.get(keyCode);
    }

    public enum ControlType {
        ROTATE_DOWN,
        ROTATE_UP,
        DRIVING_FORWARD,
        DRIVING_BACKWARDS,
        SHOOT
    }
}
