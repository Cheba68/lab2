package server.manager;

import common.network.Request;
import common.network.Response;
import server.command.Command;

import java.util.HashMap;
import java.util.Map;

public class CommandManager {

    private final Map<String, Command> commands = new HashMap<>();

    public void register(Command command) {
        commands.put(command.getName(), command);
    }

    public Response executeCommand(Request request) {
        if (request == null) {
            return new Response("Пустой запрос", false);
        }

        String commandName = request.getCommandName();

        Command command = commands.get(commandName);

        if (command == null) {
            return new Response("Неизвестная команда: " + commandName, false);
        }

        try {
            return command.execute(request.getArgument());
        } catch (Exception e) {
            return new Response("Ошибка при выполнении команды: " + e.getMessage(), false);
        }
    }

    public Map<String, Command> getCommands() {
        return commands;
    }
}