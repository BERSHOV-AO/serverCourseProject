import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ChatServer {
    private static final String SETTING_FILE = "settings.txt";
    private static final String LOG_FILE = "file.log";
    private int port;
    private PrintWriter logWriter;
    private List<Socket> clients = new ArrayList<>();
    private ServerSocket serverSocket;
    private boolean isRunning = false;

    public ChatServer() {
        loadSetting();
    }

    public void loadSetting() {
        try (Scanner scanner = new Scanner(new File(SETTING_FILE))) {
            port = Integer.parseInt(scanner.nextLine());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int getPort() {
        return port;
    }

    public boolean getServerIsRunning(){
        return isRunning;
    }

    public void startServer() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + port);
            logWriter = new PrintWriter(new FileWriter(LOG_FILE, true), true);
            isRunning = true;

            while (isRunning) {
                Socket clientSocket = serverSocket.accept();
                clients.add(clientSocket);

                Thread clientThread = new Thread(new ClientHandler(clientSocket));
                clientThread.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopServer() {
        try {
            isRunning = false;
            for (Socket clientSocket : clients) {
                clientSocket.close();
            }
            serverSocket.close();
            logWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void broadcastMessage(String message) {
        logWriter.println(message);

        for (Socket client : clients) {
            try {
                PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                out.println(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }

    private class ClientHandler implements Runnable {
        private Socket clientSocket;
        private String clientName;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                clientName = in.readLine();
                System.out.println("New client connected: " + clientName);

                String clientMessage;
                while ((clientMessage = in.readLine()) != null) {
                    String message = "[" + getCurrentTime() + "] " + clientName + ": " + clientMessage;
                    broadcastMessage(message);
                }

                System.out.println("Client disconnected: " + clientName);
                clients.remove(clientSocket);
                clientSocket.close();

            } catch (IOException e) {
              //  e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ChatServer server = new ChatServer();
        server.startServer();
    }
}
