package server.command;

import common.network.Response;
import server.manager.CollectionManager;

public class CountLessThanStudentsCountCommand extends AbstractCommand {

    private final CollectionManager collectionManager;

    public CountLessThanStudentsCountCommand(CollectionManager collectionManager) {
        super("count_less_than_students_count", "вывести количество элементов, у которых studentsCount меньше заданного");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Object arg) {

        if (arg == null || !(arg instanceof Integer)) {
            return new Response("Ошибка: требуется число", false);
        }

        try {
            int count = (Integer) arg;

            long result = collectionManager.countLessThanStudentsCount(count);

            return new Response("Количество элементов: " + result, true);

        } catch (Exception e) {
            return new Response("Ошибка: " + e.getMessage(), false);
        }
    }
}