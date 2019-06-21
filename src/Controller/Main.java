package Controller;

import Network.Client.MainClient;
import Network.Server.MainServer;
import View.MainFrame;

import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        LoadingLibrary lb = new LoadingLibrary();
        lb.loadFiles("C:\\Users\\Saman\\Downloads\\Telegram Desktop");
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
//        lb.getSong("Lady Gaga","Shallow");
//        lb.getSong("adele","lay+me+down");
//        lb.getSong("Someone like you","adele");
//        System.out.println(lb.getSongLyrics("Lady Gaga","Shallow"));
//        System.out.println(lb.getSongLyrics("U2", "With or Without You"));
//        MainFrame m = new MainFrame();
        lb.getLyrics("Hello","adele");
    }
}
