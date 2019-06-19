package Model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Music implements Serializable
{
    private String fileLocation;
    private String artist;
    private String name;
    private LocalDateTime addDate;
    private LocalDateTime lastPlayed;


    public Music(String fileLocation, String artist, String name, LocalDateTime addDate, LocalDateTime lastPlayed)
    {
        this.fileLocation = fileLocation;
        this.artist = artist;
        this.name = name;
        this.addDate = addDate;
        this.lastPlayed = lastPlayed;
    }

    public LocalDateTime getAddDate()
    {
        return addDate;
    }

    public void setAddDate(LocalDateTime addDate)
    {
        this.addDate = addDate;
    }

    public LocalDateTime getLastPlayed()
    {
        return lastPlayed;
    }

    public void setLastPlayed(LocalDateTime lastPlayed)
    {
        this.lastPlayed = lastPlayed;
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
