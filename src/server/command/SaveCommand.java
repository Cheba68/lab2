package server.command;

import common.network.Response;
import server.manager.CollectionManager;
import server.manager.FileManager;

public class SaveCommand extends AbstractCommand {

    private final CollectionManager collectionManager;
    private final FileManager fileManager;

    public SaveCommand(CollectionManager collectionManager, FileManager fileManager) {
        super("save", "сохранить коллекцию в файл (только сервер)");
        this.collectionManager = collectionManager;
        this.fileManager = fileManager;
    }

    @Override
    public Response execute(Object arg) {
        fileManager.save(collectionManager.getAll());
        return new Response("Коллекция сохранена", true);
    }
}