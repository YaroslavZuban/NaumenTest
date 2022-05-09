import java.io.IOException;
import java.net.CacheRequest;

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
