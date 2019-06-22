package Model;

import java.io.*;
import java.util.ArrayList;

public class Library
{
    private ArrayList<Music> musics = new ArrayList<>();
    private String name ;

    public Library(String name)
    {
        this.name = name;
    }

    public void addMusics(ArrayList<Music> musics)
    {
        this.musics.addAll(musics);
    }

    public void savePlaylist()
    {
        try
        {
            FileOutputStream fileOutputStream = new FileOutputStream("./Library/"+name.toLowerCase().trim()+".dat");
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
}
