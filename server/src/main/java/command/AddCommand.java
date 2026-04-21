package command;

import manager.CollectionManager;
import model.StudyGroup;
import network.Response;

public class AddCommand extends AbstractCommand {

    private final CollectionManager collectionManager;

    public AddCommand(CollectionManager collectionManager) {
        super("add", "добавить новый элемент в коллекцию");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Object arg) {

        if (arg == null || !(arg instanceof StudyGroup)) {
            return new Response("Ошибка: некорректный объект");
        }

        try {
            StudyGroup group = (StudyGroup) arg;

            collectionManager.add(group);

            return new Response("Элемент добавлен");

        } catch (Exception e) {
            return new Response("Ошибка: " + e.getMessage());
        }
    }
}