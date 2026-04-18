package server.command;

import common.model.Semester;
import common.network.Response;
import server.manager.CollectionManager;

public class RemoveAnyBySemesterCommand extends AbstractCommand {

    private final CollectionManager collectionManager;

    public RemoveAnyBySemesterCommand(CollectionManager collectionManager) {
        super("remove_any_by_semester", "удалить любой элемент с заданным semesterEnum");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Object arg) {

        if (arg == null || !(arg instanceof Semester)) {
            return new Response("Ошибка: требуется значение Semester", false);
        }

        try {
            Semester semester = (Semester) arg;

            boolean removed = collectionManager.removeAnyBySemester(semester);

            if (removed) {
                return new Response("Элемент удалён", true);
            } else {
                return new Response("Элемент с таким semester не найден", false);
            }

        } catch (Exception e) {
            return new Response("Ошибка: " + e.getMessage(), false);
        }
    }
}