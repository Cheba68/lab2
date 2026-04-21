package command;

import manager.CollectionManager;
import manager.FileManager;
import network.Response;

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
        return new Response("Коллекция сохранена");
    }
}