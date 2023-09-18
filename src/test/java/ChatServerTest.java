import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ChatServerTest {
    private static final int PORT = 12345;

    @Test
    public void testLoadSetting() {
        ChatServer server = new ChatServer();
        // Проверяем загрузку настроек из файла
        server.loadSetting();
        assertEquals(PORT, server.getPort());
    }
}
