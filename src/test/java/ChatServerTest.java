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
//    private static final String TEST_SETTING_FILE = "test-setting.txt";
//    private static final String TEST_LOG_FILE = "test-file.log";
//    private static final String LOG_FILE = "file.log";

    private ChatServer server;
    private Thread serverThread;

    @Test
    public void testLoadSetting() {
        ChatServer server = new ChatServer();
        // Проверяем загрузку настроек из файла
        server.loadSetting();
        assertEquals(PORT, server.getPort());
    }
}
