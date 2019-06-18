package Network.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;

public class ClientHandler implements Runnable
{
    private Socket socket;
    private ObjectInputStream in ;
    private String userName;
    static Vector<String> users= new Vector<>();
    private ObjectOutputStream thisOut;
    public ClientHandler(Socket client, ObjectOutputStream thisOut) throws Exception {
        if (client == null) throw new Exception("client can't be null");
        this.socket = client;
        this.thisOut=thisOut;
        in = new ObjectInputStream(client.getInputStream());
        System.out.println("wait for client to send its username!");
    }

    public void run () {

        try
        {
            while (!socket.isClosed())
            {

            }
        } catch (IOException e)
        {
            System.out.println(userName+" Left");
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        try
        {

        } catch (IOException e)
        {
//            e.printStackTrace();
        }
    }
}
