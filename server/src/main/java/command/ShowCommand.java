package command;

import java.util.List;
import java.util.stream.Collectors;

import manager.CollectionManager;
import model.StudyGroup;
import network.Response;

public class ShowCommand extends AbstractCommand {

    private final CollectionManager collectionManager;

    public ShowCommand(CollectionManager collectionManager) {
        super("show", "вывести все элементы коллекции");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Object arg) {

        List<StudyGroup> groups = collectionManager.getAll();

        if (groups.isEmpty()) {
            return new Response("Коллекция пуста");
        }

        String result = groups.stream()
                .map(StudyGroup::toString)
                .collect(Collectors.joining("\n"));

        return new Response(result);
    }
}