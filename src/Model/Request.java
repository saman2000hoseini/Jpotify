package Model;

import java.io.Serializable;

public class Request implements Serializable
{
    private PlayingMusic music = null;
    private Integer fileSize = null;
    private Library sharedLibrary =null;
    private boolean wantsMusic = false;
    private int reqsMusic;
    public Request(PlayingMusic music)
    {
        reqsMusic = 0;
        this.music = music;
    }

    public PlayingMusic getMusic()
    {
        return music;
    }

    public Integer getFileSize()
    {
        return fileSize;
    }

    public Library getSharedLibrary()
    {
        return sharedLibrary;
    }

    public boolean wantsMusic()
    {
        return wantsMusic;
    }

    public int getReqsMusic()
    {
        return reqsMusic;
    }

    public Request(Integer fileSize, PlayingMusic music)
    {
        reqsMusic = 1;
        this.music=music;
        this.fileSize = fileSize;
    }
    public Request(PlayingMusic music,boolean wantsMusic)
    {
        this.wantsMusic = wantsMusic;
        reqsMusic = 1;
        this.music=music;
    }

    public Request(Integer fileSize, Library sharedLibrary)
    {
        this.fileSize = fileSize;
        this.sharedLibrary = sharedLibrary;
        this.reqsMusic = 2;
    }
}
