package Network.Client;

import Model.Library;
import Model.PlayingMusic;
import Model.Request;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

public class Sharing implements Runnable
{
    private Vector<Socket> connections;
    private ObjectInputStream objectInputStream;
    private Vector<ObjectOutputStream> objectOutputStreams = new Vector<>();
    private Library sharedLibrary;
    public Sharing(Vector<Socket> connections,Socket client) throws IOException
    {
        this.connections = connections;
        objectInputStream = new ObjectInputStream(client.getInputStream());
        for (Socket socket:connections)
        {
            objectOutputStreams.add((ObjectOutputStream) socket.getOutputStream());
        }
    }
    public void hiServer(ObjectOutputStream objectOutputStream) throws IOException
    {
       objectOutputStream.writeObject(new Request(new PlayingMusic(null,null,null,null,null,null,null,null,null,true)));
    }

    public void shareMusic(Request request) throws IOException
    {
        try
        {
            for (ObjectOutputStream outputStream : objectOutputStreams)
            {
                outputStream.writeObject(request);
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
                if (request.getReqsMusic()==0 && !request.getMusic().isLocal())
                {

                }
                else if(request.getReqsMusic()==1 && request.wantsMusic())
                {

                }
                else if(request.getReqsMusic()==1)
                {
                    byte[] byteArray  = new byte[request.getFileSize()];
                    objectInputStream.read(byteArray);
                    OutputStream outputStream = new ObjectOutputStream(new FileOutputStream("./SharedMusics/"+request.getMusic().getName().toLowerCase()+".mps"));
                    outputStream.write(byteArray);
                }
                else if (request.getReqsMusic()==2)
                    sharedLibrary = request.getSharedLibrary();
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
