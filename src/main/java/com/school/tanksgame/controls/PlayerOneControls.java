package com.school.tanksgame.controls;

public class PlayerOneControls extends Controls {

     public PlayerOneControls() {
        controlsMap.put(10, ControlType.SHOOT);
        controlsMap.put(39, ControlType.ROTATE_UP);
        controlsMap.put(37, ControlType.ROTATE_DOWN);
        controlsMap.put(38, ControlType.DRIVING_FORWARD);
        controlsMap.put(40, ControlType.DRIVING_BACKWARDS);
    }
}
