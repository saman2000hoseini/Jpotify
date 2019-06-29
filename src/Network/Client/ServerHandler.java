package Network.Client;

import Model.Request;
import Model.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;

public class ServerHandler implements Runnable
{
    static Vector<User> users = new Vector<>();
    private Socket server;
    private ObjectInputStream objectInputStream = null;
    private ObjectOutputStream objectOutputStream = null;
    private boolean flag = false;
    private int fileSize;

    public ServerHandler(Socket server) throws IOException
    {
        this.server = server;
        objectOutputStream = new ObjectOutputStream(server.getOutputStream());
        objectInputStream = new ObjectInputStream(server.getInputStream());
    }

    @Override
    public void run()
    {
        try
        {
            while (!server.isClosed())
            {
                System.out.println("im listening");
                if (flag)
                {
//                    byte[] bytes = new byte[16*1024];
//                    int count;
//                    while ((count = objectInputStream.read(bytes,0,bytes.length)) != -1)
//                    {
//                        objectOutputStream.write(bytes, 0, count);
//                        System.out.println("here");
//                        fileSize-=count;
//                        System.out.println(fileSize);
//                    }
                    byte[] bytes = new byte[fileSize];
                    int count = objectInputStream.read(bytes);
                    objectOutputStream.write(bytes);
                    System.out.println(count);
                    System.out.println("reached here");
                    flag = false;
                }
                else
                {
                    Request request = (Request) objectInputStream.readObject();
                    System.out.println(request.getReqsMusic() + " :||||");
                    MainClient.user.getObjectOutputStream().writeObject(request);
                }
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

    public void hiFriend()
    {
        try
        {
            System.out.println(MainClient.user.getUserName() + ":" + MainClient.user.getIp());
            objectOutputStream.writeObject(new Request(new User(MainClient.user.getUserName(), MainClient.user.getIp())));

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
