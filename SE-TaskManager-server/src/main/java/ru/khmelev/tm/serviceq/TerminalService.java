package ru.khmelev.tm.serviceq;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.service.ITerminalService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TerminalService implements ITerminalService {

    @Getter
    @NotNull
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @NotNull
    @Override
    public String readLine() throws IOException {
        return reader.readLine();
    }
}