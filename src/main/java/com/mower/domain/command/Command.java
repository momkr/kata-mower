package com.mower.domain.command;

import com.mower.domain.model.Mower;

public interface Command {
    void execute(Mower mower);
}
