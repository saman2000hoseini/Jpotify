package Controller;

import Listeners.PlayPanelListener;
import Model.Library;
import Model.Music;
import Model.User;
import Network.Client.MainClient;
import Network.Server.MainServer;
import View.MainFrame;
import View.PlayPanel;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class Main
{
    private static Vector<Music> musics = new Vector<>();
    private static FileAndFolderBrowsing fileAndFolderBrowsing = new FileAndFolderBrowsing();
    private static MainFrame mainFrame;
    private static Vector<Library> libraries;

    public static void main(String[] args) throws IOException
    {
        createDirs();
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
        libraries = fileAndFolderBrowsing.loadLibraries();
        System.out.println(libraries);
        mainFrame = new MainFrame(musics);
        setLinkers();
        MainClient main = new MainClient(musics, new User("test", null));
    }

    private static void createDirs() throws IOException
    {
        makeDir("./Lyrics/");
        makeDir("./Library/");
        makeDir("./SharedMusics/");
        makeDir("./Library/favourites.bin");
        makeDir("./Library/shared playlist.bin");
    }

    private static void makeDir(String path) throws IOException
    {
        File file = new File(path);
        if (!file.exists())
            if (file.isDirectory())
                file.mkdir();
            else
                file.createNewFile();
    }

    private static void setLinkers()
    {
        PlayPanelActions playPanelActions = new PlayPanelActions(musics);
        mainFrame.getMainPanel().getPlayPanel().setPlayPanelListener(playPanelActions);
        TopLeftMenuActions topLeftMenuActions = new TopLeftMenuActions(musics);
        mainFrame.getMainPanel().getWestPanel().getMenuForWestPanel().setTopLeftMenuListener(topLeftMenuActions);
    }
}
