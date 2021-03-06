package com.brianstempin.vindiniumclient.bot.simple;

import com.brianstempin.vindiniumclient.bot.BotMove;
import com.brianstempin.vindiniumclient.bot.mybots.GameMap;
import com.brianstempin.vindiniumclient.dto.GameState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Example bot
 */
public class RandomBot implements SimpleBot {
    private static final Logger logger = LogManager.getLogger(RandomBot.class);

    private void get_child(GameState.Game game) {
        int size = game.getBoard().getSize();
        String board = game.getBoard().getTiles();
        logger.info(size);
        logger.info(board.length());
    }

    @Override
    public BotMove move(GameState gameState) {
        get_child(gameState.getGame());
        GameMap map = new GameMap(gameState.getGame().getBoard().getSize(), gameState.getGame().getBoard().getTiles());
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
        // No-op
    }

    @Override
    public void shutdown() {
        // No-op
    }
}
