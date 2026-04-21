package command;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import network.Request;
import network.Response;

/**
 * Команда выполнения скрипта.
 */
public class ExecuteScriptCommand extends AbstractCommand {

    private final CommandManager commandManager;
    private final Set<String> executingScripts = new HashSet<>();

    public ExecuteScriptCommand(CommandManager commandManager) {
        super("execute_script",
                "считать и исполнить скрипт из указанного файла");
        this.commandManager = commandManager;
    }

    public Response execute(String[] args) {

        if (args.length == 0) {
            System.out.println("Не указано имя файла.");
            return Response("не указано имя файла");
        }

        String fileName = args[0];

        if (executingScripts.contains(fileName)) {
            System.out.println("Обнаружена рекурсия! Скрипт уже выполняется.");
            return Response("обнаружена рекурсия! скрипт уже выполняется");
        }

        File file = new File(fileName);

        try (Scanner fileScanner = new Scanner(file)) {

            executingScripts.add(fileName);

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (!line.isEmpty()) {
                    System.out.println("> " + line);
                    commandManager.executeCommand(new Request(line, fileScanner));
                }
            }

            executingScripts.remove(fileName);

        } catch (FileNotFoundException e) {
            System.out.println("Файл скрипта не найден.");
        }
        return null;
    }

    private Response Response(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Response'");
    }

    @Override
    public Response execute(Object arg) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }
}
