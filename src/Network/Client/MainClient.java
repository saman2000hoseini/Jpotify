package Network.Client;

import Model.Music;
import Model.User;
//import com.sun.deploy.util.SessionState;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;

public class MainClient
{
    private ObjectOutputStream outStream;
    private ObjectInputStream inputStream;
    private Socket client;
    private Vector<String> friends = new Vector<>();
    private Vector<Socket> connections = new Vector<>();
    static Vector<Music> musics = new Vector<>();
    private Sharing sharing;
    private int port;
    static User user;
    public MainClient(Vector<Music> musics,User user) throws IOException
    {
        this.user = user;
        this.musics=musics;
        port = 6500;
        client = new Socket("localhost", port);
        try
        {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(client.getOutputStream());
            sharing = new Sharing(connections,client);
            Thread t = new Thread(sharing);
            t.start();
            sharing.hiServer(objectOutputStream);
            System.out.println();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
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