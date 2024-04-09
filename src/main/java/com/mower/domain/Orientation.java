package com.mower.domain;

public enum Orientation {
    N,W,S,E;

    private static final Orientation[] ORIENTATIONS = values();

    public Orientation turnLeft() {
        int index = ordinal();
        return ORIENTATIONS[(index + 1) % ORIENTATIONS.length];
    }

    public Orientation turnRight() {
        int index = ordinal();
        return ORIENTATIONS[(index == 0 ? values().length - 1 : index - 1) % ORIENTATIONS.length];
    }
}
