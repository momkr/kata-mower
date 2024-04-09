package com.mower.domain.command;

import com.mower.domain.model.Mower;
import com.mower.domain.model.MowerCommands;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;

@Slf4j
@Service
public class CommandHandler implements Consumer<MowerCommands> {

    @Override
    public void accept(MowerCommands mowerCommands) {
        Mower mower = mowerCommands.getMower();
        List<Command> commands = mowerCommands.getCommands();
        log.info(">>> Starting to move a new Mower");
        commands.forEach(command -> command.execute(mower));
    }
}
