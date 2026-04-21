package command;

import manager.CollectionManager;
import network.Response;

public class ClearCommand extends AbstractCommand {

    private final CollectionManager collectionManager;

    public ClearCommand(CollectionManager collectionManager) {
        super("clear", "очистить коллекцию");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Object arg) {
        try {
            collectionManager.clear();
            return new Response("Коллекция очищена");
        } catch (Exception e) {
            return new Response("Ошибка: " + e.getMessage());
        }
    }
}