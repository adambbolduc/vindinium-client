package com.brianstempin.vindiniumclient.bot;

public class Pair <LEFT, RIGHT> {
    public final LEFT left;
    public final RIGHT right;

    public Pair(LEFT left, RIGHT right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "("+ left.toString()+ ", "+ right.toString()+ ")";
    }
}
