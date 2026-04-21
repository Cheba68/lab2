package command;

import manager.CollectionManager;
import network.Response;

public class HeadCommand extends AbstractCommand {

    private final CollectionManager collectionManager;

    public HeadCommand(CollectionManager collectionManager) {
        super("head", "вывести первый элемент коллекции");
        this.collectionManager = collectionManager;
    }

    public Response execute(String[] args) {
        if (collectionManager.getCollection().isEmpty()) {
            System.out.println("Коллекция пуста.");
        } else {
            System.out.println(collectionManager.getCollection().get(0));
        }
        return null;
    }

    @Override
    public Response execute(Object arg) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }
}
