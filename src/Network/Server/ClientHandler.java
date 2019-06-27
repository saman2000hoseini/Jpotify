package Network.Server;

import Model.PlayingMusic;
import Model.Request;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;

public class ClientHandler implements Runnable
{
    static Vector<String> users = new Vector<>();
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private static ObjectOutputStream thisOut;
    private Socket socket;

    public ClientHandler(Socket client) throws Exception
    {
        if (client == null) throw new Exception("client can't be null");
        this.socket = client;
        objectInputStream = new ObjectInputStream(client.getInputStream());
        objectOutputStream = new ObjectOutputStream(client.getOutputStream());
        System.out.println("wait for client to send its username!");
    }

    public void run()
    {
        try
        {
            while (!socket.isClosed())
            {
                Request request = (Request) objectInputStream.readObject();
                if (request.getMusic().isLocal())
                {
                    thisOut = objectOutputStream;
                    System.out.println("Welcome to your server "+request.getUser().getUserName());
                }
                else
                    thisOut.writeObject(request);
            }
        }
        catch (IOException e)
        {
            System.out.println("Someone Left");
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
