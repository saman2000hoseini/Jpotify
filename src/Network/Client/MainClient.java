package Network.Client;

import Model.PlayingMusic;
import Network.Server.MainServer;
import com.sun.deploy.util.SessionState;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class MainClient
{
    private ObjectOutputStream outStream;
    private ObjectInputStream inputStream;
    private Socket client;
    private Vector<String> friends = new Vector<>();
    private Vector<Socket> connections = new Vector<>();
    private Sharing sharing;
    private int port;

    public MainClient() throws IOException
    {
        port = 6500;
        client = new Socket("localhost", port);
        try
        {
            sharing = new Sharing(connections,client);
            Thread t = new Thread(sharing);
            t.start();
//            sharing.hiServer((ObjectOutputStream) client.getOutputStream());
            System.out.println();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException
    {
        int port = 6500;
        try
        {
            Thread t = new Thread(new MainServer(port));
            t.start();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        MainClient main = new MainClient();
    }

    public void addFriend(String ip) throws IOException
    {
        friends.add(ip);
        openSocket(ip);
    }

    public void openSocket(String ip) throws IOException
    {
        connections.add(new Socket(ip, port));
        sharing.setConnections(connections);
    }
}