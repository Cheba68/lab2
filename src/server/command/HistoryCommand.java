package command;

public class HistoryCommand extends AbstractCommand {

    private final CommandManager manager;

    public HistoryCommand(CommandManager manager) {
        super("history", "вывести последние 14 команд");
        this.manager = manager;
    }

    @Override
    public Reponse execute(String[] args) {
        manager.getHistory().forEach(System.out::println);
    }
}
