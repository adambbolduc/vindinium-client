package com.brianstempin.vindiniumclient.bot.mybots;

import com.brianstempin.vindiniumclient.bot.BotMove;
import javaslang.collection.List;


public class State {

    public final GameMap gameMap;
    public final Coordinate heroPosition;
    public final List<BotMove> plan;

    public State(GameMap gameMap, Coordinate heroPosition, List<BotMove> plan) {
        this.gameMap = gameMap;
        this.heroPosition = heroPosition;
        this.plan = plan;
    }

    @Override
    public String toString() {
        return "(" + heroPosition.toString() + ", " + plan.toString() + ")";
    }
}
