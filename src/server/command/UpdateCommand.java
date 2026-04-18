package command;

import manager.CollectionManager;
import util.InputManager;
import model.StudyGroup;

public class UpdateCommand extends AbstractCommand {

    private final CollectionManager collectionManager;
    private final InputManager inputManager;

    public UpdateCommand(CollectionManager collectionManager, InputManager inputManager) {
        super("update", "обновить элемент по id");
        this.collectionManager = collectionManager;
        this.inputManager = inputManager;
    }

    @Override
    public Reponse execute(String[] args) {

        if (args.length == 0) {
            System.out.println("Не указан id.");
            return new Reponse(false, "Не указан id.");
        }

        try {
            Long id = Long.parseLong(args[0]);

            if (!collectionManager.removeById(id)) {
                System.out.println("Элемент с таким id не найден.");
                return;
            }

            StudyGroup updated = inputManager.readStudyGroup(id);

            if (updated != null) {
                collectionManager.add(updated);
                System.out.println("Элемент обновлён.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Некорректный id.");
        }
    }
}
