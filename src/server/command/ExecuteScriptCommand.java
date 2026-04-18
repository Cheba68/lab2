package command;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

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

    @Override
    public Reponse execute(String[] args) {

        if (args.length == 0) {
            System.out.println("Не указано имя файла.");
            return;
        }

        String fileName = args[0];

        if (executingScripts.contains(fileName)) {
            System.out.println("Обнаружена рекурсия! Скрипт уже выполняется.");
            return;
        }

        File file = new File(fileName);

        try (Scanner fileScanner = new Scanner(file)) {

            executingScripts.add(fileName);

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (!line.isEmpty()) {
                    System.out.println("> " + line);
                    commandManager.execute(line);
                }
            }

            executingScripts.remove(fileName);

        } catch (FileNotFoundException e) {
            System.out.println("Файл скрипта не найден.");
        }
    }
}
