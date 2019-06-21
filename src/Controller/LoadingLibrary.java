package Controller;

/*import com.sun.deploy.util.StringUtils;
import getlyrics.RavGetLyrics;*/

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
        Elements elements = doc.select("p.songLyricsV14");
        Element p;
        if (elements.size()>0)
        {
            p=elements.get(0);
        }
        else
            throw new IOException("null");
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
        //get the HTML document
        List<String> lyrics = new ArrayList<>();
        Document lyricPage = connection.get();
        Elements lyricTags = lyricPage.select("div.lyrics > p > a");
        for (int i = 0; i < lyricTags.size(); i++)
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
    public List<String> getPersianSongLyrics(String band, String songTitle) throws IOException
    {
        Connection connection;
        //connect to the website
        connection = Jsoup.connect("https://www.texahang.org/متن-آهنگ-" + band.replace(" ", "-") + "-بنام-" + songTitle.replace(" ", "-") + "/");
        //specify user agent
        connection.userAgent("Mozilla/5.0");
        //get the HTML document
        List<String> lyrics = new ArrayList<>();
        Document doc = connection.get();
        Elements lyricTags = doc.select("div.main-post > p");
        for (int i = 0; i < lyricTags.size(); i++)
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

    public List<String> getPersianLyrics(String band, String songTitle) throws IOException
    {
        Connection connection;

        //connect to the website
        connection = Jsoup.connect("https://nex1music.ir/آهنگ-" + band.replace(" ", "-") + "-" + songTitle.replace(" ", "-") + "/");

        //specify user agent
        connection.userAgent("Mozilla/5.0");

        //get the HTML document
        List<String> lyrics = new ArrayList<>();
        Document doc = connection.get();
        Elements elements = doc.select("div.lyrics");
        Element p;
        if (elements.size()>0)
        {
            p=elements.get(0);
        }
        else
            throw new IOException("null");
//        for (Node e : p.childNodes())
//        {
//            if (e instanceof TextNode)
//            {
//                lyrics.add(((TextNode) e).getWholeText());
//            }
//        }
        lyrics.add(p.text());
        return lyrics;
    }
    private static int counter = 0;

    public List<String> findLyrics(String singer, String song)
    {
        List<String> lyrics = null;
        try
        {
            if (counter < 2)
            {
                lyrics = this.getSongLyrics(singer, song);
            }
            else if (counter < 4)
            {
                try
                {
                    lyrics = this.getPersianSongLyrics(singer,song);
                }
                catch (IOException e)
                {
                    try
                    {
                        lyrics = this.getPersianLyrics(singer, song);
                    }
                    catch (IOException ex)
                    {
                        counter++;
                        lyrics = this.findLyrics(song,singer);
                    }
                }
            }
        }
        catch (IOException e)
        {
            counter++;
            try
            {
                lyrics = this.getLyrics(song, singer);
            }
            catch (IOException ex)
            {
                lyrics = this.findLyrics(song, singer);
            }
        }
        return lyrics;
    }

    public void restCounter()
    {
        counter=0;
    }
}

