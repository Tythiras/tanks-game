package com.school.tanksgame.player;

public class PlayerTwoControls extends Controls {

    public PlayerTwoControls() {
        controlsMap.put(32, ControlType.SHOOT);
        controlsMap.put(68, ControlType.ROTATE_UP);
        controlsMap.put(65, ControlType.ROTATE_DOWN);
        controlsMap.put(87, ControlType.DRIVING_FORWARD);
        controlsMap.put(83, ControlType.DRIVING_BACKWARDS);
    }
}
