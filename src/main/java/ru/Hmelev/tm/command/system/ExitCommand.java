package ru.Hmelev.tm.command.system;

import ru.Hmelev.tm.command.Command;
import ru.Hmelev.tm.command.util.Security;

public class ExitCommand extends Command {
    public ExitCommand() {
        super("exit", "Exit from the program", Security.FREE);
    }

    @Override
    public void execute() {
        Thread.currentThread().interrupt();
    }
}