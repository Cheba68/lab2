package server.command;

import common.model.StudyGroup;
import common.network.Response;
import server.manager.CollectionManager;

public class AddCommand extends AbstractCommand {

    private final CollectionManager collectionManager;

    public AddCommand(CollectionManager collectionManager) {
        super("add", "добавить новый элемент в коллекцию");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Object arg) {

        if (arg == null || !(arg instanceof StudyGroup)) {
            return new Response("Ошибка: некорректный объект", false);
        }

        try {
            StudyGroup group = (StudyGroup) arg;

            collectionManager.add(group);

            return new Response("Элемент добавлен", true);

        } catch (Exception e) {
            return new Response("Ошибка: " + e.getMessage(), false);
        }
    }
}