package ru.Hmelev.tm.service;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TerminalService {
    @Getter
    @NotNull
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @NotNull
    public String readLine() throws IOException {
        return reader.readLine();
    }
}