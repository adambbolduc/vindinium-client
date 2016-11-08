package com.brianstempin.vindiniumclient.bot.mybots;

import com.brianstempin.vindiniumclient.bot.BotMove;

public class Coordinate {
    public final int x;
    public final int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate applyMove(BotMove move) {
        switch (move){
            case STAY:
                return new Coordinate(x, y);
            case NORTH:
                return new Coordinate(x - 1, y);
            case EAST:
                return new Coordinate(x, y + 1);
            case SOUTH:
                return new Coordinate(x + 1, y);
            case WEST:
                return new Coordinate(x, y - 1);
        }
        throw new RuntimeException("Undefined Move");
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
