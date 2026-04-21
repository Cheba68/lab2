package command;

import network.Response;

public interface Command {

    String getName();

    String getDescription();

    Response execute(Object arg);
}