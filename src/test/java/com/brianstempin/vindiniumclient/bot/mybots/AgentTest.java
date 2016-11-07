package com.brianstempin.vindiniumclient.bot.mybots;

import com.brianstempin.vindiniumclient.bot.Pair;
import org.junit.Test;

import java.util.ArrayList;

public class AgentTest {
    @Test
    public void test() {
        Agent agent = new Agent();
        agent.resetMetric();
        State state = new State(new Pair<>(5,5), new ArrayList<>());
        System.out.println(agent.evaluate(state, 8));
        System.out.println(agent.getMetric());
    }
}
