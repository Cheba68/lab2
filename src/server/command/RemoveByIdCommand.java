package server.command;

import common.network.Response;
import server.manager.CollectionManager;

public class RemoveByIdCommand extends AbstractCommand {

    private final CollectionManager collectionManager;

    public RemoveByIdCommand(CollectionManager collectionManager) {
        super("remove_by_id", "удалить элемент по id");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Object arg) {

        if (arg == null || !(arg instanceof Long)) {
            return new Response("Ошибка: требуется id типа Long", false);
        }

        try {
            Long id = (Long) arg;

            boolean removed = collectionManager.removeById(id);

            if (removed) {
                return new Response("Элемент удалён", true);
            } else {
                return new Response("Элемент с таким id не найден", false);
            }

        } catch (Exception e) {
            return new Response("Ошибка: " + e.getMessage(), false);
        }
    }
}