package com.school.tanksgame.player;

public class Player {
    private final Controls controls;
    private int score;
    private String name;

    public Player(String name, Controls controls) {
        this.name = name;
        this.controls = controls;
        this.score = 0;

    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public Controls getControls() {
        return controls;
    }

    public void scoreAdd() {
        score++;
    }
}
