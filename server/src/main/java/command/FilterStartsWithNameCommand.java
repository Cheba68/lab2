package command;

import java.util.List;
import java.util.stream.Collectors;

import manager.CollectionManager;
import model.StudyGroup;
import network.Response;

public class FilterStartsWithNameCommand extends AbstractCommand {

    private final CollectionManager collectionManager;

    public FilterStartsWithNameCommand(CollectionManager collectionManager) {
        super("filter_starts_with_name", "вывести элементы, имя которых начинается с заданной строки");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Object arg) {

        if (!(arg instanceof String prefix)) {
            return new Response("Ошибка: требуется строка");
        }

        List<StudyGroup> result = collectionManager.filterStartsWithName(prefix);

        if (result.isEmpty()) {
            return new Response("Нет элементов с таким префиксом");
        }

        return new Response(
                result.stream()
                        .map(StudyGroup::toString)
                        .collect(Collectors.joining("\n"))
        );
    }
}