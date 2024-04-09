package com.mower.domain.model;

import com.mower.domain.MowerNavigator;
import com.mower.domain.Orientation;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Builder
public class Mower implements MowerNavigator {

    private Coordinates coordinates;

    private Orientation orientation;

    @Override
    public void moveForward() {
        switch (orientation) {
            case N:
                this.coordinates = this.coordinates.incrementY();
                break;
            case S:
                this.coordinates = this.coordinates.decrementY();
                break;
            case E:
                this.coordinates = this.coordinates.incrementX();
                break;
            case W:
                this.coordinates = this.coordinates.decrementX();
                break;
        }
        log.info("> Moving forward. New position {}", this);
    }

    @Override
    public void turnLeft() {
        this.orientation = orientation.turnLeft();
        log.info("> Turning to the left. New position: {}", this);
    }

    @Override
    public void turnRight() {
        this.orientation = orientation.turnRight();
        log.info("> Turning to the right. New position: {}", this);
    }

    @Override
    public String toString() {
        return coordinates
                + ", Orientation: "
                + orientation;
    }
}
