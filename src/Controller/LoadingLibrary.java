package Controller;

import com.sun.deploy.util.StringUtils;
import getlyrics.RavGetLyrics;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

public class LoadingLibrary
{
    public ArrayList<String> loadFiles(String path)
    {
        ArrayList<String> audios = new ArrayList<>();
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++)
        {
            if (listOfFiles[i].isFile())
            {
                String[] audio = listOfFiles[i].getAbsolutePath().split("\\.");
                if (audio.length != 0 && audio[audio.length - 1].equals("mp3"))
                {
                    System.out.println(listOfFiles[i].getAbsolutePath());
                    audios.add(listOfFiles[i].getAbsolutePath());
                }
            }
        }
        return audios;
    }


    private final static String songLyricsURL = "http://www.songlyrics.com";


    public List<String> getSongLyrics(String band, String songTitle) throws IOException
    {
        Connection connection;

        //connect to the website
        connection = Jsoup.connect(songLyricsURL + "/" + band.replace(" ", "-").toLowerCase() + "/" + songTitle.replace(" ", "-").toLowerCase() + "-lyrics/");

        //specify user agent
        connection.userAgent("Mozilla/5.0");

        //get the HTML document
        List<String> lyrics = new ArrayList<>();
        Document doc = connection.get();
        String title = doc.title();
        System.out.println(title);
        Element p = doc.select("p.songLyricsV14").get(0);
        for (Node e : p.childNodes())
        {
            if (e instanceof TextNode)
            {
                lyrics.add(((TextNode) e).getWholeText());
            }
        }
        return lyrics;
    }

    public List<String> getLyrics(String song, String singer) throws IOException
    {
        Connection connection;

        //connect to the website
        connection = Jsoup.connect("https://genius.com/" + singer.substring(0, 1).toUpperCase() + singer.replace(" ", "-").substring(1).toLowerCase() + "-" + song.replace(" ", "-").toLowerCase() + "-lyrics");

        //specify user agent
        connection.userAgent("Mozilla/5.0");
        List<String> lyrics = new ArrayList<>();
        //get the HTML document
        Document lyricPage = connection.get();
        Elements lyricTags = lyricPage.select("div.lyrics > p > a");
        for (int i = 0; i <lyricTags.size(); i++)
        {
            for (Node e : lyricTags.get(i).childNodes())
            {
                if (e instanceof TextNode)
                {
                    lyrics.add(((TextNode) e).getWholeText());
                }
            }
        }
        return lyrics;
    }
}
