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
//        System.out.println(lb.getSongLyrics("Lady Gaga","Shallow"));
//        System.out.println(lb.getSongLyrics("U2", "With or Without You"));
        MainFrame m = new MainFrame();
//        lb.getLyrics("With or Without You", "U2");
//        System.out.println(lb.getLyrics("Hello", "adele"));
//        System.out.println(lb.getPersianSongLyrics("سالار عقیلی","سپید یا سیاه"));
//        System.out.println(lb.findLyrics("محسن چاوشی","شبی که ماه کامل شد"));
//        System.out.println(lb.findLyrics("U2", "With or Without You"));
    }
}
