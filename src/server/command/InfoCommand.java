package server.command;

import common.network.Response;
import server.manager.CollectionManager;

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

            return new Response(info, true);

        } catch (Exception e) {
            return new Response("Ошибка: " + e.getMessage(), false);
        }
    }
}