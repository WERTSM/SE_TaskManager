package ru.khmelev.tm.api;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public interface ITerminalService {
    @NotNull String readLine() throws IOException;
}