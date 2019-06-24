package Controller;

/*import com.sun.deploy.util.StringUtils;
import getlyrics.RavGetLyrics;*/

import java.io.*;

import java.nio.channels.FileChannel;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import Model.ID3v1;
import Model.Music;
import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;
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
    public Vector<Music> loadFilesFromFolders(String path) throws IOException, ClassNotFoundException, TagException
    {
        ArrayList<String> audios = new ArrayList<>();
        Vector<Music> musics = new Vector<>();
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++)
        {
            if (listOfFiles[i].isFile())
            {
                String[] audio = listOfFiles[i].getAbsolutePath().split("\\.");
                if (audio.length != 0 && audio[audio.length - 1].equals("mp3"))
                {
//                    System.out.println(listOfFiles[i].getAbsolutePath());
                    audios.add(listOfFiles[i].getAbsolutePath());
                }
            }
        }
        for (String directory : audios)
        {
            Music music = this.processFile(directory);
            if (music.getAddDate() == null)
                music.setAddDate(LocalDateTime.now());
            if (!musics.contains(music))
                musics.add(music);
        }
        return musics;
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
        if (elements.size() > 0)
        {
            p = elements.get(0);
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

    public List<String> getLyrics(String singer, String song) throws IOException
    {
        Connection connection;

        //connect to the website
        connection = Jsoup.connect("https://genius.com/" + singer.substring(0, 1).toUpperCase() + singer.replace(" ", "-").substring(1).toLowerCase() + "-" + song.replace(" ", "-").toLowerCase() + "-lyrics");

        //specify user agent
        connection.userAgent("Mozilla/5.0");
        connection.header("Host", "genius.com");
        connection.timeout(500);
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
        if (elements.size() > 0)
        {
            p = elements.get(0);
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
            if (counter < 4)
            {
                lyrics = this.getSongLyrics(singer, song);
            }
            else if (counter < 7)
            {
                try
                {
                    lyrics = this.getPersianSongLyrics(singer, song);
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
                        lyrics = this.findLyrics(singer, song);
                    }
                }
            }
        }
        catch (IOException e)
        {
            counter++;
            try
            {
                lyrics = this.getLyrics(singer, song);
            }
            catch (IOException ex)
            {
                lyrics = this.findLyrics(singer, song);
            }
        }
        return lyrics;
    }

    public void restCounter()
    {
        counter = 0;
    }

    public List<String> gatherMusic(String singer, String song) throws IOException
    {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        List<String> lyrics = null;
        String directory = "./Lyrics/" + song.replace(" ", "_").toLowerCase() + "_" + singer.replace(" ", "_").toLowerCase() + ".txt";
        try
        {
            fileReader = new FileReader(directory);
            bufferedReader = new BufferedReader(fileReader);
            lyrics = new ArrayList<>();
            String temp = bufferedReader.readLine();
            while (temp != null)
            {
                lyrics.add(temp);
                temp = bufferedReader.readLine();
            }
        }
        catch (FileNotFoundException e)
        {
            lyrics = this.findLyrics(singer, song);
            this.restCounter();
            if (lyrics != null)
            {
                FileWriter fileWriter = new FileWriter(directory);
                PrintWriter printWriter = new PrintWriter(fileWriter);
                for (String str : lyrics)
                {
                    printWriter.println(str);
                }
                printWriter.close();
                fileWriter.close();
            }
            else
            {
                lyrics.add("Please check Your Connection and try again");
            }
        }
        catch (EOFException e)
        {
            bufferedReader.close();
            fileReader.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return lyrics;
    }

    public Music processFile(String directory) throws IOException, TagException
    {
        File file = new File(directory);
        MP3File mp3File = new MP3File(file);
        String title, artist, album, year, genre;
        if (mp3File.getID3v2Tag() != null)
        {
            title = mp3File.getID3v2Tag().getSongTitle();
            artist = mp3File.getID3v2Tag().getLeadArtist();
            album = mp3File.getID3v2Tag().getAlbumTitle();
            year = mp3File.getID3v2Tag().getYearReleased();
            genre = mp3File.getID3v2Tag().getSongGenre();
        }
        else
        {
            title = mp3File.getID3v1Tag().getTitle();
            artist = mp3File.getID3v1Tag().getArtist();
            album = mp3File.getID3v1Tag().getAlbum();
            year = mp3File.getID3v1Tag().getYear();
            genre = new ID3v1().getGENRES(mp3File.getID3v1Tag().getGenre());
        }
        Music music = new Music(directory, artist, title, year, LocalDateTime.now(), null, genre, album);
        return music;
    }
}

