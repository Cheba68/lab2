package command;

import manager.CollectionManager;
import network.Response;

public class RemoveByIdCommand extends AbstractCommand {

    private final CollectionManager collectionManager;

    public RemoveByIdCommand(CollectionManager collectionManager) {
        super("remove_by_id", "удалить элемент по id");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Object arg) {

        if (arg == null || !(arg instanceof Long)) {
            return new Response("Ошибка: требуется id типа Long");
        }

        try {
            Long id = (Long) arg;

            boolean removed = collectionManager.removeById(id);

            if (removed) {
                return new Response("Элемент удалён");
            } else {
                return new Response("Элемент с таким id не найден");
            }

        } catch (Exception e) {
            return new Response("Ошибка: " + e.getMessage());
        }
    }
}