package Network.Client;

import Controller.FileAndFolderBrowsing;
import Controller.Main;
import Listeners.AddPlayingMusic;
import Listeners.RequestToGetMusic;
import Model.*;
import View.MainFrame;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import org.farng.mp3.TagException;

import java.io.*;
import java.net.Socket;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Sharing implements Runnable, RequestToGetMusic
{
    private FileAndFolderBrowsing fileAndFolderBrowsing = new FileAndFolderBrowsing();
    private Vector<Socket> connections;
    private Vector<User> users = new Vector<>();
    static Music music;
    private AddPlayingMusic addPlayingMusic = null;
    private static int count = 0;

    public Sharing(Vector<Socket> connections, Socket client) throws IOException
    {

        this.connections = connections;
        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        exec.scheduleAtFixedRate(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    Request request = null;
                    try
                    {
                        count++;
                        System.out.println("playing music is " + music);
                        if (music != null && count % 2 == 1)
                        {
                            request = new Request(music, new User(MainClient.user.getUserName(), MainClient.user.getIp()));
                            System.out.println("sending music " + music.getName());
                            shareMusic(request);
                        }
                        if (Main.sharedPlaylist != null && Main.sharedPlaylist.getMusics().size() > 0 && count % 2 == 0)
                        {
                            request = new Request(Main.sharedPlaylist, new User(MainClient.user.getUserName(), MainClient.user.getIp()));
                            shareMusic(request);
                        }
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }, 0, 10, TimeUnit.SECONDS);
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

    public void hiServer() throws IOException
    {
        System.out.println(MainClient.user.getUserName());
        MainClient.user.getObjectOutputStream().writeObject(new Request(new User(MainClient.user.getUserName(), MainClient.user.getIp())));
    }

    public void shareMusic(Request request) throws IOException
    {
        try
        {
            MainClient.user.getObjectOutputStream().writeObject(request);
            System.out.println("sending to " + request.getUser().getUserName());
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
                Request request = (Request) MainClient.user.getObjectInputStream().readObject();
                if (request.getReqsMusic() == 1)
                {
                    System.out.println(request.getUser().getUserName() + " is playing music" + request.getMusic().getName());
                    addPlayingMusic.addMusicToActivity(music, request.getUser());
                }
                else if (request.getReqsMusic() == 2)
                {
                    System.out.println(request.getUser().getUserName() + " wants music " + request.getMusic().getName());
                    File file = new File(MainClient.musics.get(MainClient.musics.indexOf(request.getMusic())).getFileLocation());
                    FileInputStream inputStream = (new FileInputStream(file));
                    Request req = new Request((int) file.length(), request.getMusic(), request.getWants());
                    MainClient.user.getObjectOutputStream().writeObject(req);
                    System.out.println("Sending the file");
                    byte[] bytes = new byte[req.getFileSize()];
                    inputStream.read(bytes);
                    MainClient.user.getObjectOutputStream().write(bytes);
//                    byte[] bytes = new byte[8*1024];
//                    int count;
//                    while ((count = inputStream.read(bytes)) > 0) {
//                        MainClient.user.getObjectOutputStream().write(bytes, 0, count);
//                    }
                    inputStream.close();
                }
                else if (request.getReqsMusic() == 3)
                {
                    System.out.println(request.getMusic().getName() + " Receiving from " + request.getUser().getUserName());
                    byte[] byteArray = new byte[request.getFileSize()];
                    MainClient.user.getObjectInputStream().read(byteArray);
                    OutputStream outputStream = new ObjectOutputStream(new FileOutputStream("./SharedMusics/" + request.getMusic().getName().toLowerCase() + ".mp3"));
                    request.getMusic().setFileLocation("./SharedMusics/" + request.getMusic().getName().toLowerCase() + ".mp3");
                    outputStream.write(byteArray);
                    outputStream.close();
                    fileAndFolderBrowsing.addFileFolder(request.getMusic().getFileLocation(), MainClient.musics);
                    MainClient.mainFrame.setMusics(MainClient.musics);
                }
                else if (request.getReqsMusic() == 4)
                {
                    Main.sharedPlaylist.setMusics(request.getSharedLibrary());
                    for (Music m : Main.sharedPlaylist.getMusics())
                        System.out.println(m.getName());
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
            catch (InvalidDataException e)
            {
                e.printStackTrace();
            }
            catch (UnsupportedTagException e)
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
        if (this.connections == null)
            this.connections = new Vector<>();
        hiFriend(connections.get(connections.size() - 1));
        System.out.println("saying hello");
    }

    public static void setMusic(Music music)
    {
        Sharing.music = music;
    }

    public void setAddPlayingMusic(AddPlayingMusic addPlayingMusic)
    {
        this.addPlayingMusic = addPlayingMusic;
    }

    @Override
    public void send(Request request) throws IOException
    {
        shareMusic(request);
    }
}