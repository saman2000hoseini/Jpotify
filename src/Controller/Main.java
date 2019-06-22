package Controller;

import Network.Client.MainClient;
import Network.Server.MainServer;
import View.MainFrame;
import org.farng.mp3.TagException;

import java.io.File;
import java.io.IOException;

public class Main
{
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
        MainClient main = new MainClient();
        MainFrame m = new MainFrame();
    }

    private static void makeDir(String path)
    {
        File file = new File(path);
        if (!file.exists())
            file.mkdir();
    }
}
