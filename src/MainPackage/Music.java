package MainPackage;

import java.io.Serializable;

public class Music implements Serializable
{
    private String fileLocation;
    private String artist;
    private String name;

    public Music(String fileLocation, String artist, String name)
    {
        this.fileLocation = fileLocation;
        this.artist = artist;
        this.name = name;
    }

    public String getFileLocation()
    {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation)
    {
        this.fileLocation = fileLocation;
    }

    public String getArtist()
    {
        return artist;
    }

    public void setArtist(String artist)
    {
        this.artist = artist;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
