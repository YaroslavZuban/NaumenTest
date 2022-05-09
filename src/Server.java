import java.io.IOException;

public class Server {
    public static void main(String[] args) {
        try {
            СacheServer сacheServer = new СacheServer("input.txt");
            сacheServer.dataWork();
            сacheServer.writingFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
