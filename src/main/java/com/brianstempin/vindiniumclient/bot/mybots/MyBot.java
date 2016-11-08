package com.brianstempin.vindiniumclient.bot.mybots;

import com.brianstempin.vindiniumclient.bot.BotMove;
import com.brianstempin.vindiniumclient.bot.Pair;
import com.brianstempin.vindiniumclient.bot.simple.SimpleBot;
import com.brianstempin.vindiniumclient.dto.GameState;
import javaslang.collection.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

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
        Coordinate heroPosition = new Coordinate(gameState.getHero().getPos().getX(), gameState.getHero().getPos().getY());
        State state = new State(new GameMap(gameState.getGame().getBoard().getSize(), gameState.getGame().getBoard().getTiles()), heroPosition, List.empty());

        agent.resetMetric();
        Pair<Double, State> solution = agent.evaluate(state, 8);



        logger.info("We are going : " + solution.right.plan);
        logger.info("Agent evaluated " + agent.getMetric() + " times");

        logger.info(state.gameMap);
        logger.info("I am at : " + state.heroPosition);

        return solution.right.plan.head();
    }

    @Override
    public void setup() {
    }

    @Override
    public void shutdown() {
        // No-op
    }
}
