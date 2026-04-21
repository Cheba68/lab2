package command;

import network.Response;

public class HistoryCommand extends AbstractCommand {

    private final CommandManager manager;

    public HistoryCommand(CommandManager manager) {
        super("history", "вывести последние 14 команд");
        this.manager = manager;
    }

    @Override
    public Response execute(Object arg) {
        return new Response("execute_script не реализован");
    }
}
