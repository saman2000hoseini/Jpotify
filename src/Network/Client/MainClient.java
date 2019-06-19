package Network.Client;

import com.sun.deploy.util.SessionState;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class MainClient implements Runnable
{

    private ObjectOutputStream outStream;
    private ObjectInputStream inputStream;
    private Socket client;
    private ArrayList<String> friends = new ArrayList<>();
    private ArrayList<Socket> connections = new ArrayList<>();
    private int port;

    public MainClient()
    {
        friends.add("localhost");
        port = 6500;
        try
        {

//            client.getInetAddress();
//            Formatter f = new Formatter(client.getOutputStream());
//            f.format("hi");
//            f.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    @Override
    public void run()
    {

        while (true)
        {
            try
            {
                if (friends != null)
                    for (String socket : friends)
                    {
                        connections.add(new Socket(socket,port));
                    }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            }
        }
    }
}