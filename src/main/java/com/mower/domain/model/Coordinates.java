package com.mower.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Coordinates {
    private int x;
    private int y;

    public Coordinates incrementX() {
        if (this.x < Lawn.getInstance().getLength()) {
            return Coordinates.builder().x(this.x + 1).y(this.y).build();
        }
        else {
            return this;
        }
    }

    public Coordinates incrementY() {
        if (this.y < Lawn.getInstance().getHeight()) {
            return Coordinates.builder().x(this.x).y(this.y + 1).build();
        }
        else {
            return this;
        }
    }

    public Coordinates decrementX() {
        if (this.x > 0) {
            return Coordinates.builder().x(this.x - 1).y(this.y).build();
        }
        else {
            return this;
        }
    }

    public Coordinates decrementY(){
        if (this.y > 0) {
            return Coordinates.builder().x(this.x).y(this.y - 1).build();
        }
        else {
            return this;
        }
    }

    @Override
    public String toString() {
        return "Coordinates: {" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
