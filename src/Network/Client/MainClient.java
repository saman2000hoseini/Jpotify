package Network.Client;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MainClient implements Runnable{

    private ObjectOutputStream outStream;
    private ObjectInputStream inputStream;
    private Socket client;
    public MainClient() {
        String serverName = "localhost";
        int port = 6500;
        try
        {
            System.out.println("Connecting to " + serverName + " on port " + port);
            client = new Socket(serverName, port);
            System.out.println("Just connected to " + client.getRemoteSocketAddress());
            outStream = new ObjectOutputStream(client.getOutputStream());
            System.out.println("out stream");
//            inputStream = new ObjectInputStream(client.getInputStream());
            System.out.println("in stream");
//            Formatter f = new Formatter(client.getOutputStream());
//            f.format("hi");
//            f.flush();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

//    public void startNet() {
//
//    }

    @Override
    public void run() {
        try
        {
            inputStream = new ObjectInputStream(client.getInputStream());
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        while (true)
        {
            try
            {
            } catch (IOException e)
            {
                e.printStackTrace();
            } catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            }
        }
    }
}