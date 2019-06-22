package Model;

import java.io.InputStream;
import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class PlayingMusic extends Music implements Serializable
{
    private LocalTime startsAt;
    private boolean isLocal;
    public PlayingMusic(String fileLocation, String artist, String name,String genre, String year, LocalTime startsAt, LocalDateTime addDate, LocalDateTime lastPlayed,String album, boolean isLocal)
    {
        super(fileLocation, artist, name,year,addDate,lastPlayed,genre,album);
        this.startsAt = startsAt;
        this.isLocal=isLocal;
    }

    public LocalTime getStartsAt()
    {
        return startsAt;
    }

    public boolean isLocal()
    {
        return isLocal;
    }
}
