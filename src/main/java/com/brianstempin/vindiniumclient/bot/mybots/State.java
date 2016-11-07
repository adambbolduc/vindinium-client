package com.brianstempin.vindiniumclient.bot.mybots;

import com.brianstempin.vindiniumclient.bot.BotMove;
import com.brianstempin.vindiniumclient.bot.Pair;

import java.util.List;

public class State {

    public final Pair<Integer, Integer> heroPosition;
    public final List<BotMove> plan;

    public State(Pair<Integer, Integer> heroPosition, List<BotMove> plan) {
        this.heroPosition = heroPosition;
        this.plan = plan;
    }

    @Override
    public String toString() {
        return "(" + heroPosition.toString() + ", " + plan.toString() + ")";
    }
}
