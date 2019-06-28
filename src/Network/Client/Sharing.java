package Network.Client;

import Controller.FileAndFolderBrowsing;
import Controller.Main;
import Listeners.AddPlayingMusic;
import Listeners.RequestToGetMusic;
import Model.*;
import View.MainFrame;
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
    private ObjectInputStream objectInputStream;
    private Library sharedLibrary;
    static Music music;
    private AddPlayingMusic addPlayingMusic = null;

    public Sharing(Vector<Socket> connections, Socket client) throws IOException
    {
        this.connections = connections;
        objectInputStream = new ObjectInputStream(client.getInputStream());
        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        exec.scheduleAtFixedRate(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    Request request = null;
                    if (music != null)
                        request = new Request(new PlayingMusic(music, false), MainClient.user);
                    sharedLibrary = Main.getPlayLists().get(Main.getPlayLists().indexOf(new Library("Shared playlist")));
                    try
                    {
                        if (music != null)
                        {
                            System.out.println("sending music " + music.getName());
                            shareMusic(request);
                        }
                        request = new Request(sharedLibrary, MainClient.user);
                        if (sharedLibrary != null)
                            shareMusic(request);
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
        }, 0, 30, TimeUnit.SECONDS);
    }

    public void hiFriend(Socket socket)
    {
        try
        {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
//            MainClient.user.
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
        objectOutputStream.writeObject(new Request(new PlayingMusic(new Music(null, null, null, null, null, null, null, null), true), MainClient.user));
    }

    public void shareMusic(Request request) throws IOException
    {
        try
        {
            for (User user : users)
            {
                if (!user.getUserName().equals(MainClient.user.getUserName()))
                    user.getObjectOutputStream().writeObject(request);
                System.out.println("sending to " + user.getUserName());
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
                    Socket temp = new Socket(request.getUser().getIp(), 6500);
                    System.out.println(temp.getInetAddress()+" joinedddddddddddddddddddd");

                    request.getUser().setObjectOutputStream(new ObjectOutputStream(temp.getOutputStream()));
                    users.add(request.getUser());
                    System.out.println(request.getUser().getUserName() + " has joined Your server");
                }
                else if (request.getReqsMusic() == 0 && !request.getMusic().isLocal())
                {
                    System.out.println(request.getUser().getUserName() + " is playing music" + request.getMusic().getName());
                    addPlayingMusic.addMusicToActivity(music, request.getUser());
                }
                else if (request.getReqsMusic() == 1 && request.wantsMusic())
                {
                    System.out.println(request.getUser().getUserName() + " wants music " + request.getMusic().getName());
                    User wants = users.get(users.indexOf(request.getUser()));
                    File file = new File(MainClient.musics.get(MainClient.musics.indexOf(request.getMusic())).getFileLocation());
                    ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));
                    Request req = new Request((int) file.length(), request.getMusic(), MainClient.user);
                    wants.getObjectOutputStream().writeObject(req);
                    byte[] bytes = new byte[req.getFileSize()];
                    inputStream.read(bytes);
                    wants.getObjectOutputStream().writeObject(bytes);
                }
                else if (request.getReqsMusic() == 1)
                {
                    System.out.println(request.getMusic().getName() + " Sending to " + request.getUser().getUserName());
                    byte[] byteArray = new byte[request.getFileSize()];
                    objectInputStream.read(byteArray);
                    if (!MainClient.musics.contains(request.getMusic()))
                    {
                        OutputStream outputStream = new ObjectOutputStream(new FileOutputStream("./SharedMusics/" + request.getMusic().getName().toLowerCase() + ".mps"));
                        request.getMusic().setFileLocation("./SharedMusics/" + request.getMusic().getName().toLowerCase() + ".mps");
                        outputStream.write(byteArray);
                        fileAndFolderBrowsing.addFileFolder(request.getMusic().getFileLocation(), MainClient.musics);
                        MainClient.mainFrame.setMusics(MainClient.musics);
                    }
                }
                else if (request.getReqsMusic() == 2)
                {
                    sharedLibrary = request.getSharedLibrary();
                    Main.getPlayLists().set(Main.getPlayLists().indexOf(new Library("Shared playlist")), sharedLibrary);
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