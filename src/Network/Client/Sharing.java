package Network.Client;

import Controller.FileAndFolderBrowsing;
import Controller.Main;
import Model.Library;
import Model.PlayingMusic;
import Model.Request;
import Model.User;
import org.farng.mp3.TagException;

import java.io.*;
import java.net.Socket;
import java.util.Iterator;
import java.util.Vector;

public class Sharing implements Runnable
{
    private FileAndFolderBrowsing fileAndFolderBrowsing = new FileAndFolderBrowsing();
    private Vector<Socket> connections;
    private Vector<User> users = new Vector<>();
    private ObjectInputStream objectInputStream;
    private Library sharedLibrary;

    public Sharing(Vector<Socket> connections, Socket client) throws IOException
    {
        this.connections = connections;
        objectInputStream = new ObjectInputStream(client.getInputStream());

    }

    public void hiFriend(Socket socket)
    {
        try
        {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(new Request(MainClient.user));
            objectOutputStream.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void hiServer(ObjectOutputStream objectOutputStream) throws IOException
    {
        objectOutputStream.writeObject(new Request(new PlayingMusic(null, null, null, null, null, null, null, null, null, true), MainClient.user));
    }

    public void shareMusic(Request request) throws IOException
    {
        try
        {
            for (User user : users)
            {
                user.getObjectOutputStream().writeObject(request);
            }
        }
        catch (Exception e)
        {

        }
    }

    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                Request request = (Request) objectInputStream.readObject();
                if (request.getReqsMusic() == 3)
                {
                    Iterator iterator = connections.iterator();
                    while (iterator.hasNext())
                    {
                        Socket temp = (Socket) iterator.next();
                        if (temp.getInetAddress().equals(request.getUser().getIp()))
                        {
                            iterator.remove();
                            break;
                        }
                    }
                    Socket temp = new Socket(request.getUser().getIp(),6500);
                    request.getUser().setObjectOutputStream((ObjectOutputStream)temp.getOutputStream());
                    users.add(request.getUser());
                }
                else if (request.getReqsMusic() == 0 && !request.getMusic().isLocal())
                {

                }
                else if (request.getReqsMusic() == 1 && request.wantsMusic())
                {
                    User wants = users.get(users.indexOf(request.getUser()));
                    File file = new File(MainClient.musics.get(MainClient.musics.indexOf(request.getMusic())).getFileLocation());
                    ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));
                    Request req = new Request((int) file.length(),request.getMusic(),MainClient.user);
                    wants.getObjectOutputStream().writeObject(req);
                    byte[] bytes = new byte[req.getFileSize()];
                    inputStream.read(bytes);
                    wants.getObjectOutputStream().writeObject(bytes);
                }
                else if (request.getReqsMusic() == 1)
                {
                    byte[] byteArray = new byte[request.getFileSize()];
                    objectInputStream.read(byteArray);
                    if (!MainClient.musics.contains(request.getMusic()))
                    {
                        OutputStream outputStream = new ObjectOutputStream(new FileOutputStream("./SharedMusics/" + request.getMusic().getName().toLowerCase() + ".mps"));
                        request.getMusic().setFileLocation("./SharedMusics/" + request.getMusic().getName().toLowerCase() + ".mps");
                        outputStream.write(byteArray);
                        fileAndFolderBrowsing.addFileFolder(request.getMusic().getFileLocation(), MainClient.musics);
                    }
                }
                else if (request.getReqsMusic() == 2)
                {
                    sharedLibrary = request.getSharedLibrary();
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
            catch (TagException e)
            {
                e.printStackTrace();
            }
        }
    }

    public Vector<Socket> getConnections()
    {
        return connections;
    }

    public void setConnections(Vector<Socket> connections) throws IOException
    {
        this.connections = connections;
        if (this.connections==null)
            this.connections=new Vector<>();
        for (int i = users.size(); i < connections.size(); i++)
        {
            hiFriend(connections.get(i));
        }
    }
}
