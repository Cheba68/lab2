package client;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

import model.StudyGroup;
import network.Request;
import network.Response;

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
                    Thread.sleep(10); // 🔥 ВАЖНО
                }

                // --- сериализация запроса ---
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutputStream out = new ObjectOutputStream(bos);
                out.writeObject(request);
                out.flush();

                ByteBuffer buffer = ByteBuffer.wrap(bos.toByteArray());

                // --- запись (важно через цикл) ---
                while (buffer.hasRemaining()) {
                    channel.write(buffer);
                }

                // --- чтение ответа ---
                ByteBuffer responseBuffer = ByteBuffer.allocate(10000);
                ByteArrayOutputStream responseData = new ByteArrayOutputStream();

                Thread.sleep(100); // даём серверу время ответить

                int bytesRead = channel.read(responseBuffer);
                            
                if (bytesRead <= 0) {
                    System.out.println("Ответ от сервера не получен");
                    continue;
                }
                
                responseBuffer.flip();
                responseData.write(responseBuffer.array(), 0, bytesRead);

                ObjectInputStream in = new ObjectInputStream(
                        new ByteArrayInputStream(responseData.toByteArray())
                );

                Response response = (Response) in.readObject();
                System.out.println(response.getMessage());

            } catch (IOException e) {
                System.out.println("Сервер временно недоступен. Попробуйте позже.");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ignored) {}
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
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