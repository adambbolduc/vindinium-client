package com.brianstempin.vindiniumclient.bot.mybots;

import com.brianstempin.vindiniumclient.bot.BotMove;
import com.brianstempin.vindiniumclient.bot.Pair;
import com.brianstempin.vindiniumclient.bot.simple.SimpleBot;
import com.brianstempin.vindiniumclient.dto.GameState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class MyBot implements SimpleBot {
    private static final Logger logger = LogManager.getLogger(MyBot.class);
    private Agent agent = new Agent();

    @Override
    public BotMove move(GameState gameState) {
        Instant start = Instant.now();

        BotMove botMove = myMove(gameState);

        Instant end = Instant.now();

        logger.info("Time to choose a move :" + ChronoUnit.MILLIS.between(start, end) + " ms");
        return botMove;
    }

    private BotMove myMove(GameState gameState) {
        Pair<Integer, Integer> heroPosition = new Pair<>(gameState.getHero().getPos().getX(), gameState.getHero().getPos().getY());
        State state = new State(heroPosition, new ArrayList<>());

        agent.resetMetric();
        BotMove move = agent.evaluate(state, 7).right.plan.stream().findFirst().orElse(BotMove.STAY);

        logger.info("We are going : " + move);
        logger.info("Agent evaluated " + agent.getMetric() + " times");

        return move;
    }

    @Override
    public void setup() {
    }

    @Override
    public void shutdown() {
        // No-op
    }
}
