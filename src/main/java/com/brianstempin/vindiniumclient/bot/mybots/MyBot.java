package com.brianstempin.vindiniumclient.bot.mybots;

import com.brianstempin.vindiniumclient.bot.BotMove;
import com.brianstempin.vindiniumclient.bot.simple.SimpleBot;
import com.brianstempin.vindiniumclient.dto.GameState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class MyBot implements SimpleBot {
    private static final Logger logger = LogManager.getLogger(MyBot.class);
    private GameStateFactory gameStateFactory = new GameStateFactory();

    @Override
    public BotMove move(GameState gameState) {
        Instant start = Instant.now();

        BotMove botMove = myMove(gameStateFactory.makeGameState(gameState));

        Instant end = Instant.now();

        logger.info(ChronoUnit.MILLIS.between(start, end));
        return botMove;
    }

    private BotMove myMove(MyGameState gameState) {
        int randomNumber = (int)(Math.random() * 4);
        switch(randomNumber) {
            case 1:
                return BotMove.NORTH;
            case 2:
                return BotMove.SOUTH;
            case 3:
                return BotMove.EAST;
            case 4:
                return BotMove.WEST;
            default:
                return BotMove.STAY;
        }
    }

    @Override
    public void setup() {
    }

    @Override
    public void shutdown() {
        // No-op
    }
}
