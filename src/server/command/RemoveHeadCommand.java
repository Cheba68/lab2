package command;

import manager.CollectionManager;

public class RemoveHeadCommand extends AbstractCommand {

    private final CollectionManager collectionManager;

    public RemoveHeadCommand(CollectionManager collectionManager) {
        super("remove_head", "вывести и удалить первый элемент");
        this.collectionManager = collectionManager;
    }

    @Override
    public Reponse execute(String[] args) {
        if (collectionManager.getCollection().isEmpty()) {
            System.out.println("Коллекция пуста.");
        } else {
            System.out.println(collectionManager.getCollection().removeFirst());
        }
    }
}
