package Controller;
import com.sun.deploy.util.StringUtils;
import getlyrics.RavGetLyrics;
import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

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
        List<String> lyrics = new ArrayList<String>();
        Document doc = Jsoup.connect(songLyricsURL + "/" + band.replace(" ", "-").toLowerCase() + "/" + songTitle.replace(" ", "-").toLowerCase() + "-lyrics/").get();
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

    public void getLyrics(String song , String singer) throws IOException
    {
//        song = song.replaceAll(" ", "+");
//        String url = "https://www.azlyrics.com/lyrics/" + singer.toLowerCase()+"/"+song.toLowerCase()+".html";
        String url = "https://genius.com/"+ singer.substring(0,1).toUpperCase()+singer.replace(" ","-").substring(1).toLowerCase()+"-"+song.replace(" ","-").toLowerCase()+"-lyrics";
        Document lyricPage = Jsoup.connect(url).get();
        Elements lyricTags = lyricPage.select("div");
        String lyrics = new String();
//        System.out.println(lyricTags.get(1).outerHtml());
        for (Element elm : lyricTags)
        {
            System.out.println(elm.outerHtml());
            if (elm.attr("class").equals("div-share noprint") || elm.attr("class").equals("collapse noprint") || elm.attr("class").equals("panel album-panel noprint") || elm.attr("class").contains("noprint") || elm.attr("class").equals("smt") || elm.attr("class").equals("hidden") || elm.attr("class").equals("smt noprint") || elm.attr("class").equals("div-share") || elm.attr("class").equals("lyricsh") || elm.attr("class").equals("ringtone"))
            {
                continue;
            }
            lyrics = elm.text();
            break;
        }
        System.out.println(lyrics+"finished");
    }

    public void getSong(String author,String song)
    {
        RavGetLyrics ravGetLyrics = new RavGetLyrics();
        System.out.println(ravGetLyrics.getSong(song.toLowerCase(),author.toLowerCase()));
    }
}
