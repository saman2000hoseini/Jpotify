package Controller;

import Model.Music;
import Network.Client.MainClient;
import Network.Server.MainServer;
import View.MainFrame;
import org.farng.mp3.TagException;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class Main
{
    private static Vector<Music> musics;
    private static FileAndFolderBrowsing fileAndFolderBrowsing = new FileAndFolderBrowsing();
    public static void main(String[] args) throws IOException, TagException
    {
        makeDir("./Lyrics/");
        makeDir("./Library/");

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
        MainClient main = new MainClient(musics);
        MainFrame m = new MainFrame();
    }

    private static void makeDir(String path)
    {
        File file = new File(path);
        if (!file.exists())
            file.mkdir();
    }
}
