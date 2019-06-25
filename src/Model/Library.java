package Model;

import java.io.*;
import java.util.ArrayList;
import java.util.Vector;

public class Library
{
    private Vector<Music> musics = new Vector<>();
    private String name ;

    public Library(String name)
    {
        this.name = name;
    }

    public void addMusics(Vector<Music> musics)
    {
        this.musics.addAll(musics);
    }

    public void savePlaylist()
    {
        try
        {
            FileOutputStream fileOutputStream = new FileOutputStream("./Library/"+name.toLowerCase().trim()+".bin");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            for (Music music:musics)
            {
                objectOutputStream.writeObject(music);
            }
            objectOutputStream.close();
            fileOutputStream.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void removeMusics(ArrayList<Music> musics)
    {
        try
        {
            for (Music music:musics)
            {
                this.musics.remove(music);
            }
        }
        catch (Exception e)
        {

        }
        savePlaylist();
    }

    public void suicide()
    {
        try
        {
            File file = new File("./Library/"+this.name+".bin");
            if (!name.equals("Favourites") && !name.equals("Shared Playlist"))
                file.delete();
        }catch (Exception e)
        {

        }
    }
}
