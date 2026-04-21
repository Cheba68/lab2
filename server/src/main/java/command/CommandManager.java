package command;

import java.util.HashMap;
import java.util.Map;

import network.Request;
import network.Response;

public class CommandManager {

    private final Map<String, Command> commands = new HashMap<>();

    // регистрация команды
    public void register(String name, Command command) {
        commands.put(name, command);
    }

    // выполнение команды
    public Response executeCommand(Request request) {
        if (request == null) {
            return new Response("Пустой запрос");
        }

        String commandName = request.getCommandName();
        Object arg = request.getArgument();

        Command command = commands.get(commandName);

        if (command == null) {
            return new Response("Неизвестная команда: " + commandName);
        }

        try {
            return command.execute(arg);
        } catch (Exception e) {
            return new Response("Ошибка выполнения: " + e.getMessage());
        }
    }

    public Map<String, Command> getCommands() {
        // TODO Auto-generated method stub
        return commands;
    }
}