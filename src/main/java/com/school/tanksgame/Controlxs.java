package com.school.tanksgame;

import java.util.HashMap;

public enum Controlxs {
    ROTATE_DOWN,
    ROTATE_UP,
    DRIVING_FORWARD,
    DRIVING_BACKWARDS,
    SHOOT;

    public static class PlayerOneControls {
        private static HashMap<Integer, Controlxs> controls;
        static {
            controls = new HashMap<>();
            controls.put(10, Controlxs.SHOOT);
            controls.put(39, Controlxs.ROTATE_UP);
            controls.put(37, Controlxs.ROTATE_DOWN);
            controls.put(38, Controlxs.DRIVING_FORWARD);
            controls.put(40, Controlxs.DRIVING_BACKWARDS);
        }

        static Controlxs getControl(int keyCode) {
            return controls.get(keyCode);
        }
    }
}
