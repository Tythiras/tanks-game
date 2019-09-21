package com.school.tanksgame.player;

import java.util.ArrayList;

public class PlayerFactory {

    private static ArrayList<Player> players;
    private static int index = 0;

    static {
        players = new ArrayList<>();
        players.add(new Player("bob", new PlayerOneControls()));
        players.add(new Player("alice", new PlayerTwoControls()));
    }

    public static Player getPlayer() {
        return players.get(index++ % players.size());
    }

    private PlayerFactory() {}
}
