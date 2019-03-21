package ru.Hmelev.tm.command.system;

import ru.Hmelev.tm.command.Command;

public class ExitCommand extends Command {
    public ExitCommand() {
        super("exit", "Exit from the program");
    }

    @Override
    public void execute() {
        Thread.currentThread().interrupt();
    }
}