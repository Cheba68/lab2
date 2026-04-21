package command;

import network.Response;

public class ExitCommand extends AbstractCommand {

    public ExitCommand() {
        super("exit", "завершить работу клиента");
    }

    @Override
    public Response execute(Object arg) {
        // сервер НЕ завершается, только клиент
        return new Response("Клиент завершает работу");
    }
}