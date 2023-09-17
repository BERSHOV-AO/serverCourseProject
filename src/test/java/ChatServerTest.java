import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class ChatServerTest {
    private static final int PORT = 12345;
    private static final String TEST_SETTING_FILE = "test-setting.txt";
    //  private static final String TEST_SETTING_FILE = "test-setting.txt";
    private static final String TEST_LOG_FILE = "test-file.log";
    private static final String LOG_FILE = "file.log";

    private ChatServer server;
    private Thread serverThread;

    @BeforeEach
    public void setUp() throws IOException {
        // Создаем временный файл настроек для тестов
//        PrintWriter settingWriter = new PrintWriter(TEST_SETTING_FILE);
//        settingWriter.println(PORT);
//        settingWriter.close();

        // Создаем сервер для каждого теста
        //  server = new ChatServer();
//        serverThread = new Thread(() -> server.startServer());
//        serverThread.start();

    }

    @AfterEach
    public void tearDown() {
        // Очищаем ресурсы после каждого теста
//        File settingFile = new File(TEST_SETTING_FILE);
//        settingFile.delete();
//
//        File logFile = new File(TEST_LOG_FILE);
//        logFile.delete();
    }

    @Test
    public void testLoadSetting() {
        ChatServer server = new ChatServer();
        // Проверяем загрузку настроек из файла
        server.loadSetting();
        assertEquals(PORT, server.getPort());
    }


//    @Test
//    public void testStartAndStopServer() throws IOException {
//        ChatServer server1 = new ChatServer();
//        //   Запуск сервера
//        serverThread = new Thread(() -> server1.startServer());
//        serverThread.start();
//
//        // Проверяем, что сервер запущен и слушает порт
//        try (Socket socket = new Socket("localhost", PORT)) {
//            assertTrue(socket.isConnected());
//        }
//
//        // Останавливаем сервер
//        server1.stopServer();
//
//        // Проверка, что сервер остановлен
//        assertFalse(server1.getServerIsRunning());
//        serverThread.interrupt();
//
//    }
}
//
//    @Test
//    public void testBroadcastMessage() throws IOException {
//        ChatServer server = new ChatServer();
//        serverThread = new Thread(() -> server.startServer());
//        serverThread.start();
//        // Создаем клиента и подключаем его к серверу
//        Socket clientSocket = new Socket("localhost", PORT);
//        PrintWriter logWriter = new PrintWriter(new FileWriter(LOG_FILE, true), true);
//        PrintWriter clientWriter = new PrintWriter(clientSocket.getOutputStream(), true);
//        String clientName = "TestClient";
//        clientWriter.println(clientName);
//
//
//        // Отправляем сообщение от клиента на сервер
//        String clientMessage = "Hello, server!";
//        logWriter.println(clientMessage);
//        logWriter.println("[" + server.getCurrentTime() + "] " + clientName + ": " + clientMessage);
//        clientWriter.println(clientMessage);
//
//        // Получаем входной поток сервера
//        FileInputStream logFileInputStream = new FileInputStream(LOG_FILE);
//        Scanner logScanner = new Scanner(logFileInputStream);
//
//        // Проверяем, что сообщение было отправлено всем клиентам и записано в файл логирования
//        boolean foundInLogFile = false;
//        while (logScanner.hasNextLine()) {
//            String line = logScanner.nextLine();
//            if (line.contains(clientName) && line.contains(clientMessage)) {
//                foundInLogFile = true;
//                break;
//            }
//        }
//
//        assertTrue(foundInLogFile);
//
//        // Закрываем ресурсы
//        clientWriter.close();
//        clientSocket.close();
//        logScanner.close();
//        logFileInputStream.close();
//    }
//}
