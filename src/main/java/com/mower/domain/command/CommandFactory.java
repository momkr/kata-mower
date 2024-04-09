package com.mower.domain.command;

import com.mower.domain.model.ForwardCommand;
import com.mower.domain.model.LeftCommand;
import com.mower.domain.model.RightCommand;
import org.springframework.stereotype.Component;

@Component
public class CommandFactory {

    private CommandFactory(){}

    public static Command of(String command) {
        return switch (command) {
            case "A" -> new ForwardCommand();
            case "G" -> new LeftCommand();
            case "D" -> new RightCommand();
            default -> throw new IllegalArgumentException("Unknown command type: " + command);
        };
    }
}
