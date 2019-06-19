package Network.Client;

import Model.PlayingMusic;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

public class Sharing implements Runnable
{
    private Vector<Socket> connections = new Vector<>();
    private ObjectInputStream objectInputStream;
    private Vector<ObjectOutputStream> objectOutputStreams = new Vector<>();

    public Sharing(Vector<Socket> connections,Socket client) throws IOException
    {
        this.connections = connections;
//        objectInputStream = (ObjectInputStream) client.getInputStream();
        for (Socket socket:connections)
        {
            objectOutputStreams.add((ObjectOutputStream) socket.getOutputStream());
        }
    }
    public void hiServer(ObjectOutputStream objectOutputStream) throws IOException
    {
       objectOutputStream.writeObject(new PlayingMusic(null,null,null,null,null,null,true));
    }

    public void shareMusic(PlayingMusic playingMusic) throws IOException
    {
        try
        {
            for (ObjectOutputStream outputStream : objectOutputStreams)
            {
                outputStream.writeObject(playingMusic);
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
//            try
//            {
//                PlayingMusic playingMusic = (PlayingMusic)objectInputStream.readObject();
//                if (!playingMusic.isLocal())
//                {
//
//                }
//            }
//            catch (IOException e)
//            {
//                e.printStackTrace();
//            }
//            catch (ClassNotFoundException e)
//            {
//                e.printStackTrace();
//            }
        }
    }

    public Vector<Socket> getConnections()
    {
        return connections;
    }

    public void setConnections(Vector<Socket> connections) throws IOException
    {
        this.connections = connections;
        for (int i = objectOutputStreams.size(); i <connections.size() ; i++)
        {
            objectOutputStreams.add((ObjectOutputStream) connections.get(i).getOutputStream());
        }
    }
}
