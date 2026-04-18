package client;

import common.model.StudyGroup;
import common.network.Request;
import common.network.Response;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class Client {

    private static final String HOST = "localhost";
    private static final int PORT = 5555;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("> ");
            String command = scanner.nextLine().trim();

            if (command.equals("exit")) break;

            Request request;

            switch (command) {
                case "add":
                    StudyGroup group = createStudyGroup(scanner);
                    request = new Request("add", group);
                    break;

                case "remove_by_id":
                    System.out.print("Введите id: ");
                    Long id = Long.parseLong(scanner.nextLine());
                    request = new Request("remove_by_id", id);
                    break;

                case "show":
                    request = new Request("show", null);
                    break;

                default:
                    request = new Request(command, null);
            }

            try (SocketChannel channel = SocketChannel.open()) {

                channel.configureBlocking(false);
                channel.connect(new InetSocketAddress(HOST, PORT));

                while (!channel.finishConnect()) {
                    // ждём подключения
                }

                // сериализация запроса
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutputStream out = new ObjectOutputStream(bos);
                out.writeObject(request);
                out.flush();

                ByteBuffer buffer = ByteBuffer.wrap(bos.toByteArray());
                channel.write(buffer);

                // чтение ответа
                ByteBuffer responseBuffer = ByteBuffer.allocate(10000);
                channel.read(responseBuffer);

                ObjectInputStream in = new ObjectInputStream(
                        new ByteArrayInputStream(responseBuffer.array())
                );

                Response response = (Response) in.readObject();
                System.out.println(response.getMessage());

            } catch (Exception e) {
                System.out.println("Сервер временно недоступен");
            }
        }
    }

    private static StudyGroup createStudyGroup(Scanner scanner) {
        StudyGroup group = new StudyGroup();

        System.out.print("Введите имя: ");
        group.setName(scanner.nextLine());

        System.out.print("Введите количество студентов: ");
        group.setStudentsCount(Integer.parseInt(scanner.nextLine()));

        return group;
    }
}