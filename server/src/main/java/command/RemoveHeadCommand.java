package command;

import manager.CollectionManager;
import network.Response;

public class RemoveHeadCommand extends AbstractCommand {

    private final CollectionManager collectionManager;

    public RemoveHeadCommand(CollectionManager collectionManager) {
        super("remove_head", "удалить первый элемент");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Object arg) {
        if (collectionManager.getCollection().isEmpty()) {
            return new Response("Коллекция пуста");
        }

        collectionManager.getCollection().remove(0);
        return new Response("Первый элемент удалён");
    }
}