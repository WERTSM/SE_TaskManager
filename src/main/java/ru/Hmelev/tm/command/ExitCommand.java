package ru.Hmelev.tm.command;

public class ExitCommand extends Command {
    public ExitCommand() {

        super("exit", "Exit from the program");
    }

    @Override
    public void execute() {
        Thread.currentThread().interrupt();
    }
}