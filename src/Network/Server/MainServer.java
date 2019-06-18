package Network.Server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class MainServer implements Runnable
{

    private ServerSocket serverSocket;
    private Vector<Socket> clients = new Vector<>();
    static Vector<ObjectOutputStream> outputStreams = new Vector<>();
    private ObjectOutputStream out;
    public MainServer(int port) throws IOException
    {
        serverSocket = new ServerSocket(port);
    }

    public void run() {
        while (true)
        {
            Socket client = null;
            try
            {
                client = serverSocket.accept();
                System.out.println("new client accepted!");
                clients.add(client);
                out = new ObjectOutputStream(client.getOutputStream());
                outputStreams.add(out);
                ClientHandler clientHandler = new ClientHandler(client,out);
//                OutputStream objectOutputStream = client.getOutputStream();
//                objectOutputStream.write();

            } catch (IOException e)
            {
                e.printStackTrace();
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        int port = 6500;
        try
        {
            Thread t = new Thread(new MainServer(port));
            t.start();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

