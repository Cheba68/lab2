package command;

import manager.CollectionManager;

public class HeadCommand extends AbstractCommand {

    private final CollectionManager collectionManager;

    public HeadCommand(CollectionManager collectionManager) {
        super("head", "вывести первый элемент коллекции");
        this.collectionManager = collectionManager;
    }

    @Override
    public Reponse execute(String[] args) {
        if (collectionManager.getCollection().isEmpty()) {
            System.out.println("Коллекция пуста.");
        } else {
            System.out.println(collectionManager.getCollection().getFirst());
        }
    }
}
