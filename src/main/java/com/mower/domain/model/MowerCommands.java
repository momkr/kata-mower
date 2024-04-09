package com.mower.domain.model;

import com.mower.domain.command.Command;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.List;

@Getter
@Builder
public class MowerCommands {

    private Mower mower;

    @Singular(ignoreNullCollections = true)
    private List<Command> commands;

}
