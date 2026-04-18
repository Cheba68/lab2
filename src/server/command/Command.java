package server.command;

import common.network.Response;

public interface Command {

    String getName();

    String getDescription();

    Response execute(Object arg);
}