package com.mower.domain;

import com.mower.domain.model.MowerCommands;

import java.io.IOException;
import java.util.List;

public interface MowerRepository {

    List<MowerCommands> readFile(String fileName) throws IOException;
}
