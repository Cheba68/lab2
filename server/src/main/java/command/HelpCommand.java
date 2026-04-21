package command;

import java.util.stream.Collectors;

import network.Response;

public class HelpCommand extends AbstractCommand {

    private final CommandManager commandManager;

    public HelpCommand(CommandManager commandManager) {
        super("help", "вывести справку по доступным командам");
        this.commandManager = commandManager;
    }

    @Override
    public Response execute(Object arg) {
        try {
            String result = commandManager.getCommands().values().stream()
                    .map(cmd -> cmd.getName() + " : " + cmd.getDescription())
                    .collect(Collectors.joining("\n"));
        
            return new Response(result);
        
        } catch (Exception e) {
            return new Response("Ошибка: " + e.getMessage());
        }
    }
}