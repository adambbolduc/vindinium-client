package com.brianstempin.vindiniumclient.bot.mybots;

import com.brianstempin.vindiniumclient.bot.BotMove;
import com.brianstempin.vindiniumclient.bot.Pair;
import com.brianstempin.vindiniumclient.dto.Move;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Agent {
    private int metric;

    public Pair<Double, State> evaluate(State state, int maxDepth) {
        metric++;
        if (state.plan.size() > maxDepth) {
            return evaluateApprox(state);
        } else {
            return findSuccessors(state).stream()
                    .map(state1 -> evaluate(state1, maxDepth))
                    .sorted((first, second) -> second.left.compareTo(first.left))
                    .findFirst()
                    .orElse(new Pair<>(0.0, state));
        }
    }

    private Pair<Double, State> evaluateApprox(State state) {
        Double score = Double.valueOf(state.heroPosition.right);
        return new Pair<>(score, state);
    }

    private List<State> findSuccessors(State state) {
        State northState = new State(new Pair<>(state.heroPosition.left - 1, state.heroPosition.right), new ArrayList<>(state.plan));
        northState.plan.add(BotMove.NORTH);
        State southState = new State(new Pair<>(state.heroPosition.left + 1, state.heroPosition.right), new ArrayList<>(state.plan));
        southState.plan.add(BotMove.SOUTH);
        State eastState = new State(new Pair<>(state.heroPosition.left, state.heroPosition.right + 1), new ArrayList<>(state.plan));
        eastState.plan.add(BotMove.EAST);
        State westState = new State(new Pair<>(state.heroPosition.left, state.heroPosition.right - 1), new ArrayList<>(state.plan));
        westState.plan.add(BotMove.WEST);
        return Arrays.asList(northState, southState, eastState, westState);
    }

    public void resetMetric() {
        metric = 0;
    }

    public int getMetric() {
        return metric;
    }



}
