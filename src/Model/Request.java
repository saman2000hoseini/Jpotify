package Model;

import java.io.Serializable;

public class Request implements Serializable
{
    private PlayingMusic music = null;
    private Integer fileSize = null;
    private Library sharedLibrary =null;
    private boolean wantsMusic = false;
    private User user;
    private int reqsMusic;

    public Request(User user)
    {
        this.user = user;
        reqsMusic=3;
    }

    public Request(PlayingMusic music, User user)
    {
        this.user = user;
        reqsMusic = 0;
        this.music = music;
    }

    public User getUser()
    {
        return user;
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

    public Request(Integer fileSize, PlayingMusic music,User user)
    {
        this.user = user;
        reqsMusic = 1;
        this.music=music;
        this.fileSize = fileSize;
    }
    public Request(PlayingMusic music,boolean wantsMusic,User user)
    {
        this.user = user;
        this.wantsMusic = wantsMusic;
        reqsMusic = 1;
        this.music=music;
    }

    public Request(Library sharedLibrary,User user)
    {
        this.user = user;
        this.fileSize = fileSize;
        this.sharedLibrary = sharedLibrary;
        this.reqsMusic = 2;
    }
}
