package Model;

import java.io.InputStream;
import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class PlayingMusic extends Music implements Serializable
{
    private boolean isLocal;
    public PlayingMusic(Music music, boolean isLocal)
    {
        super(music.getFileLocation(), music.getArtist(), music.getName(),music.getYear(),music.getAddDate(),music.getLastPlayed(),music.getGenre(),music.getAlbum());
        this.isLocal=isLocal;
    }

    public boolean isLocal()
    {
        return isLocal;
    }
}
