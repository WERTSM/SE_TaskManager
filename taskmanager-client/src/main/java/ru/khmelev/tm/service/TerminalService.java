package ru.khmelev.tm.service;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.ITerminalService;

import javax.enterprise.context.ApplicationScoped;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@ApplicationScoped
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