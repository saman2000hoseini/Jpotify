package Controller;

import Listeners.PlayPanelListener;
import Model.Music;
import Model.User;
import Network.Client.MainClient;
import Network.Server.MainServer;
import View.MainFrame;
import View.PlayPanel;
import org.farng.mp3.TagException;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class Main
{
    private static Vector<Music> musics = new Vector<>();
    private static FileAndFolderBrowsing fileAndFolderBrowsing = new FileAndFolderBrowsing();
    private static MainFrame mainFrame;
    public static void main(String[] args) throws IOException, TagException
    {
        createFolders();
        LoadingLibrary lb = new LoadingLibrary();
        int port = 6500;
        try
        {
            Thread t = new Thread(new MainServer(port));
            t.start();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        fileAndFolderBrowsing.loadFiles(musics);
        mainFrame = new MainFrame(musics);
        setLinkers();
        MainClient main = new MainClient(musics,new User("test",null));
    }
    private static void createFolders()
    {
        makeDir("./Lyrics/");
        makeDir("./Library/");
        makeDir("./SharedMusics/");
    }
    private static void makeDir(String path)
    {
        File file = new File(path);
        if (!file.exists())
            file.mkdir();
    }
    private static void setLinkers()
    {
        PlayPanelActions playPanelActions = new PlayPanelActions(musics);
        mainFrame.getMainPanel().getPlayPanel().setPlayPanelListener(playPanelActions);
        System.out.println("set");
    }
}
