package ru.khmelev.tm.api;

import java.io.IOException;

public interface ITerminalService {
    String readLine() throws IOException;
}
