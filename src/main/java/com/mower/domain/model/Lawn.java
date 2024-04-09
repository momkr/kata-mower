package com.mower.domain.model;


import lombok.Getter;

import java.util.Objects;

@Getter
public class Lawn {

    private static Lawn instance;

    private final int length;
    private final int height;

    private Lawn(int length, int height) {
        this.length = length;
        this.height = height;
    }

    public static void init(Coordinates coordinates) {
        if (Objects.isNull(instance)) {
            instance = new Lawn(coordinates.getX(), coordinates.getY());
        }
    }

    public static Lawn getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Lawn not initialized");
        }
        return instance;
    }

}
