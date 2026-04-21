package command;

import manager.CollectionManager;
import network.Response;

public class CountLessThanStudentsCountCommand extends AbstractCommand {

    private final CollectionManager collectionManager;

    public CountLessThanStudentsCountCommand(CollectionManager collectionManager) {
        super("count_less_than_students_count", "вывести количество элементов, у которых studentsCount меньше заданного");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Object arg) {

        if (!(arg instanceof Integer count)) {
            return new Response("Ошибка: требуется число");
        }

        long result = collectionManager.countLessThanStudentsCount(count);

        return new Response("Количество элементов: " + result);
    }
}