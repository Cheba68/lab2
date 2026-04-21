package command;

import manager.CollectionManager;
import network.Response;

public class InfoCommand extends AbstractCommand {

    private final CollectionManager collectionManager;

    public InfoCommand(CollectionManager collectionManager) {
        super("info", "вывести информацию о коллекции");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Object arg) {
        try {
            String info = "Тип коллекции: " + collectionManager.getClass().getSimpleName() +
                    "\nКоличество элементов: " + collectionManager.size();

            return new Response(info);

        } catch (Exception e) {
            return new Response("Ошибка: " + e.getMessage());
        }
    }
}