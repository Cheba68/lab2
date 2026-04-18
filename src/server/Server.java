package server;

import common.network.Request;
import common.network.Response;
import manager.FileManager;
import server.command.*;
import server.manager.CollectionManager;
import server.manager.CommandManager;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

import command.AddCommand;
import command.ClearCommand;
import command.CountLessThanStudentsCountCommand;
import command.ExitCommand;
import command.FilterStartsWithNameCommand;
import command.HelpCommand;
import command.InfoCommand;
import command.RemoveAnyBySemesterCommand;
import command.RemoveByIdCommand;
import command.ShowCommand;

public class Server {

    private static final int PORT = 5555;

    public static void main(String[] args) {
        try {
            
            FileManager fileManager = new FileManager("data.xml");

            CollectionManager collectionManager = new CollectionManager();
            collectionManager.getAll().addAll(fileManager.load());
            CommandManager commandManager = new CommandManager();

            // регистрация команд
            commandManager.register(new AddCommand(collectionManager));
            commandManager.register(new ShowCommand(collectionManager));
            commandManager.register(new RemoveByIdCommand(collectionManager));
            commandManager.register(new ClearCommand(collectionManager));
            commandManager.register(new InfoCommand(collectionManager));
            commandManager.register(new HelpCommand(commandManager));
            commandManager.register(new ExitCommand());
            commandManager.register(new FilterStartsWithNameCommand(collectionManager));
            commandManager.register(new CountLessThanStudentsCountCommand(collectionManager));
            commandManager.register(new RemoveAnyBySemesterCommand(collectionManager));
            commandManager.register(new SaveCommand(collectionManager, fileManager));

            Selector selector = Selector.open();

            ServerSocketChannel serverChannel = ServerSocketChannel.open();
            serverChannel.bind(new InetSocketAddress(PORT));
            serverChannel.configureBlocking(false);

            serverChannel.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("NIO сервер запущен на порту " + PORT);

            while (true) {
                selector.select();

                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();

                    if (key.isAcceptable()) {
                        SocketChannel client = serverChannel.accept();
                        client.configureBlocking(false);
                        client.register(selector, SelectionKey.OP_READ);
                    }

                    else if (key.isReadable()) {
                        SocketChannel client = (SocketChannel) key.channel();

                        try {
                            ByteBuffer buffer = ByteBuffer.allocate(10000);
                            int bytesRead = client.read(buffer);

                            if (bytesRead == -1) {
                                client.close();
                                continue;
                            }

                            byte[] data = buffer.array();

                            ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(data));
                            Request request = (Request) in.readObject();

                            Response response = commandManager.executeCommand(request);

                            ByteArrayOutputStream bos = new ByteArrayOutputStream();
                            ObjectOutputStream out = new ObjectOutputStream(bos);
                            out.writeObject(response);
                            out.flush();

                            ByteBuffer responseBuffer = ByteBuffer.wrap(bos.toByteArray());
                            client.write(responseBuffer);
                            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                                fileManager.save(collectionManager.getAll());
                                System.out.println("Коллекция сохранена");
                            }));

                        } catch (Exception e) {
                            System.out.println("Ошибка клиента: " + e.getMessage());
                            client.close();
                        }
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Ошибка сервера: " + e.getMessage());
        }
    }
}