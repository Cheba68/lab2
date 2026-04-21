package command;

import manager.CollectionManager;
import network.Response;

public class UpdateCommand extends AbstractCommand {

    private final CollectionManager collectionManager;

    public UpdateCommand(CollectionManager collectionManager) {
        super("update", "обновить элемент");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Object arg) {
        return new Response("Команда update пока не реализована");
    }
}