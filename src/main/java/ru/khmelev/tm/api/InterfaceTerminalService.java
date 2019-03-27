package ru.khmelev.tm.api;

import java.io.IOException;

public interface InterfaceTerminalService {
    String readLine() throws IOException;
}
