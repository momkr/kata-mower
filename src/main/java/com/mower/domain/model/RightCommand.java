package com.mower.domain.model;

import com.mower.domain.command.Command;
import org.springframework.stereotype.Component;

@Component
public class RightCommand implements Command {

    @Override
    public void execute(Mower mower) {
        mower.turnRight();
    }
}
