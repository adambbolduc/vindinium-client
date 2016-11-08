package com.brianstempin.vindiniumclient.bot.mybots;

import javaslang.collection.List;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;

public class AgentTest {

    @Mock
    private GameMap gameMap;

    @Ignore
    @Test
    public void test() {
        Agent agent = new Agent();
        agent.resetMetric();
        State state = new State(gameMap, new Coordinate(5,5), List.empty());
        System.out.println(agent.evaluate(state, 8));
        System.out.println(agent.getMetric());
    }
}
