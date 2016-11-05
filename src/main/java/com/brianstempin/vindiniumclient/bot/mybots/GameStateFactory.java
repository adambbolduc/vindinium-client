package com.brianstempin.vindiniumclient.bot.mybots;

import com.brianstempin.vindiniumclient.dto.GameState;

public class GameStateFactory {
    public MyGameState makeGameState(GameState gameState) {
        return new MyGameState(new GameMap(gameState.getGame().getBoard().getSize(), gameState.getGame().getBoard().getTiles()));
    }
}
