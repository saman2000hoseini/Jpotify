package Network.Server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class MainServer implements Runnable
{
    private ServerSocket serverSocket;
    private Vector<Socket> clients = new Vector<>();
    private Inet4Address ip = null;
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
}

