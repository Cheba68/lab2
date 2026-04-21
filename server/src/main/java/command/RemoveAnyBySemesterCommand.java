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

        if (!(arg instanceof Semester semester)) {
            return new Response("Ошибка: требуется значение Semester");
        }

        boolean removed = collectionManager.removeAnyBySemester(semester);

        return removed
                ? new Response("Элемент удалён")
                : new Response("Элемент с таким semester не найден");
    }
}