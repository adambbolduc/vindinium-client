package com.brianstempin.vindiniumclient.bot.mybots;

import com.brianstempin.vindiniumclient.bot.BotMove;
import com.brianstempin.vindiniumclient.bot.Pair;

import javaslang.collection.List;

public class Agent {
    private int metric;
    private static List<BotMove> ALL_MOVES = List.of(
            BotMove.EAST,
            BotMove.WEST,
            BotMove.NORTH,
            BotMove.SOUTH,
            BotMove.STAY
    );

    public Pair<Double, State> evaluate(State state, int maxDepth) {
        metric++;
        if (state.plan.size() > maxDepth) {
            return evaluateApprox(state);
        } else {
            return findSuccessors(state)
                    .map(state1 -> evaluate(state1, maxDepth))
                    .sorted((first, second) -> second.left.compareTo(first.left))
                    .head();
        }
    }

    private Pair<Double, State> evaluateApprox(State state) {
        Double score1 = 1.0 * state.heroPosition.y;
        Double score2 = 1.0 * state.heroPosition.x;
        Double score3 = -0.5 * state.plan.count(move -> move != BotMove.STAY);
        return new Pair<>(score1 + score2 + score3, state);
    }

    private List<State> findSuccessors(State state) {
        List<State> movingStates = ALL_MOVES
                .filter(move -> move == BotMove.STAY || state.gameMap.canPassOn(state.heroPosition.applyMove(move)))
                .map(move -> new State(state.gameMap, state.heroPosition.applyMove(move), state.plan.append(move)));
        return movingStates;
    }

    public void resetMetric() {
        metric = 0;
    }

    public int getMetric() {
        return metric;
    }



}
