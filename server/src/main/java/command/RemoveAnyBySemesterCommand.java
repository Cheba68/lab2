package command;

import manager.CollectionManager;
import model.Semester;
import network.Response;

public class RemoveAnyBySemesterCommand extends AbstractCommand {

    private final CollectionManager collectionManager;

    public RemoveAnyBySemesterCommand(CollectionManager collectionManager) {
        super("remove_any_by_semester", "удалить любой элемент с заданным semesterEnum");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Object arg) {

        if (arg == null || !(arg instanceof Semester)) {
            return new Response("Ошибка: требуется значение Semester");
        }

        try {
            Semester semester = (Semester) arg;

            boolean removed = collectionManager.removeAnyBySemester(semester);

            if (removed) {
                return new Response("Элемент удалён");
            } else {
                return new Response("Элемент с таким semester не найден");
            }

        } catch (Exception e) {
            return new Response("Ошибка: " + e.getMessage());
        }
    }
}