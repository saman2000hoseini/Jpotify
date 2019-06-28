package Controller;

import Listeners.*;
import Model.Music;
import Model.Sort;
import Network.Client.Sharing;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import org.farng.mp3.TagException;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Vector;

public class PlayPanelActions implements PlayPanelListener, SongsTableButtons, SongsPanelListener, PlayListChanged, MusicFinishedListener,JsliderValueChanged
{
    private static AudioPlayer audioPlayer;
    private FileAndFolderBrowsing fileAndFolderBrowsing = new FileAndFolderBrowsing();
    private static int index = 0;
    static Vector<Music> playlist;
    private static Sort sort;
    private static int sortState = 8, lastSort = 8;
    //    private AudioDevice audioDevice = System.out. ;
    private static Thread player;
    private boolean shuffleState = false;
    private int repeatState = 0;
    private int playState = 0;
    private PlayingMusicChanged playingMusicChanged = null;
    private JSliderListener jSliderListener = null;
    private LoadPlayingPanel loadPlayingPanel = null;

    public PlayPanelActions(Vector<Music> playlist)
    {
        this.playlist = playlist;
        sortPlaylist();
    }

    @Override
    public void state(boolean shuffleState, int repeatState, int playState, int plPaSk) throws InterruptedException, UnsupportedTagException, InvalidDataException, TagException, IOException
    {
        this.repeatState = repeatState;
        this.playState = playState;
        this.shuffleState = shuffleState;
        switch (plPaSk)
        {
            case 0:
                if (!shuffleState)
                {
                    this.shuffleState = true;
                    lastSort = sortState;
                    sortState = 10;
                    sortPlaylist();
                }
                else
                {
                    this.shuffleState = false;
                    sortState = lastSort;
                    sortPlaylist();
                }
                break;
            case 1:
                try
                {
                    audioPlayer.stop();
                }
                catch (Exception ex)
                {

                }
                index--;
                this.playState = 2;
                if (index < 0)
                    index = playlist.size() - 1;
                playingMusicChanged.setRow(index);
                jSliderListener.playPause(playlist.get(index), 0);
                startPlayingMusic();
                break;
            case 2:
                if (playState != 2)
                {
                    if (playState == 0)
                    {
                        jSliderListener.playPause(playlist.get(index), 0);
                        startPlayingMusic();
                        this.playState = 2;
                    }
                    else
                    {
                        jSliderListener.playPause(playlist.get(index), 1);
                        this.playState = 2;
                        audioPlayer.resume();
                    }
                }
                else
                {
                    jSliderListener.playPause(playlist.get(index), 2);
                    this.playState = 1;
                    audioPlayer.pauseMusic();
                }
                break;
            case 3:
                try
                {
                    audioPlayer.stop();
                }
                catch (Exception ex)
                {

                }
                index++;
                if (index > playlist.size() - 1)
                    index = 0;
                this.playState = 2;
                playingMusicChanged.setRow(index);
                jSliderListener.playPause(playlist.get(index), 0);
                startPlayingMusic();
                break;
            case 4:
                if (repeatState == 0)
                {
                    this.repeatState = 1;
                }
                else if (repeatState == 1)
                {
                    this.repeatState = 2;
                }
                else if (repeatState == 2)
                {
                    this.repeatState = 0;
                }
                break;
        }
    }

    private void startPlayingMusic()
    {
        try
        {
            Mp3File mp3File = new Mp3File(playlist.get(index).getFileLocation());
            audioPlayer = new AudioPlayer(playlist.get(index).getFileLocation(), mp3File.getFrameCount());
            player = new Thread(audioPlayer);
            playlist.get(index).setLastPlayed(LocalDateTime.now());
            audioPlayer.playMusic(player);
            loadPlayingPanel.addMusicToActivity(playlist.get(index));
            Sharing.setMusic(playlist.get(index));
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    private void sortPlaylist()
    {
        index = 0;
        sort = new Sort(this.playlist);
        switch (sortState)
        {
            case 0:
                sort.alphabeticalAscending();
                break;
            case 1:
                sort.alphabeticalDescending();
                break;
            case 2:
                sort.alphabeticalAlbumAscending();
                break;
            case 3:
                sort.alphabeticalAlbumDescending();
                break;
            case 4:
                sort.alphabeticalArtistAscending();
                break;
            case 5:
                sort.alphabeticalArtistDescending();
                break;
            case 6:
                sort.addDateAscending();
                break;
            case 7:
                sort.addDateDescending();
                break;
            case 8:
                sort.recentlyPlayedAscending();
                break;
            case 9:
                sort.recentlyPlayedDescending();
                break;
            case 10:
                sort.shuffle();
                break;
        }
    }

    @Override
    public void setPlaylist(Vector<Music> playlist)
    {
        for (int i = 0; i < playlist.size(); i++)
        {
            int temp = this.playlist.indexOf(playlist.get(i));
            playlist.set(i, this.playlist.get(temp));
        }
        index = playlist.indexOf(this.playlist.get(index));
        this.playlist = playlist;
    }

    @Override
    public void doAction(int col, String name, String artist) throws InterruptedException, UnsupportedTagException, TagException, InvalidDataException, IOException
    {
        if (col == 0)
        {
            Music temp = new Music(null, artist, name, null, null, null, null, null);
//            index = playlist.indexOf(temp) - 1;
//            temp = playlist.get(index + 1);
            index = playlist.indexOf(temp);
            temp = playlist.get(index);
            if (playState == 2 && audioPlayer != null && audioPlayer.getPath() == temp.getFileLocation())
            {
                state(shuffleState, repeatState, playState, 2);
                playState = 1;
            }
            else
            {
                if (audioPlayer != null && audioPlayer.getPath() == temp.getFileLocation())
                    state(shuffleState, repeatState, playState, 2);
                else if (audioPlayer == null)
                    state(shuffleState, repeatState, playState, 2);
                else
                {
                    audioPlayer.stop();
                    playState = 0;
                    state(shuffleState, repeatState, playState, 2);
                }
                //                    state(shuffleState, repeatState, playState, 3);
                playState = 2;
            }
        }
        else if (col == 1)
        {
            Music temp = new Music(null, artist, name, null, null, null, null, null);
            temp = playlist.get(playlist.indexOf(temp));
            File file = new File(temp.getFileLocation());
            file.delete();
            playlist.remove(temp);
            if (playState == 2 && audioPlayer.getPath() == temp.getFileLocation())
            {
                index = 0;
                playState = 0;
                audioPlayer.stop();
            }
        }
    }

    @Override
    public void playMusic(boolean isPaused, String name, String artist) throws InterruptedException, UnsupportedTagException, TagException, InvalidDataException, IOException
    {
        Music temp = new Music(null, artist, name, null, null, null, null, null);
        temp = playlist.get(playlist.indexOf(temp));
        if (isPaused)
        {
            state(shuffleState, repeatState, playState, 2);
        }
        else if (playState == 0)
        {
            index = playlist.indexOf(temp);
            state(shuffleState, repeatState, playState, 2);
        }
        else
        {
            state(shuffleState, repeatState, playState, 2);
        }
    }

    public void setPlayingMusicChanged(PlayingMusicChanged playingMusicChanged)
    {
        this.playingMusicChanged = playingMusicChanged;
    }

    public void setjSliderListener(JSliderListener jSliderListener)
    {
        this.jSliderListener = jSliderListener;
    }

    public void setLoadPlayingPanel(LoadPlayingPanel loadPlayingPanel)
    {
        this.loadPlayingPanel = loadPlayingPanel;
    }

    @Override
    public void doRepeat() throws InterruptedException, UnsupportedTagException, InvalidDataException, TagException, IOException
    {
        if (repeatState == 0)
        {
            if (index == playlist.size() - 1)
                jSliderListener.playPause(playlist.get(index), 4);
            else
                state(shuffleState,repeatState,playState,3);
        }
        else if (repeatState == 1)
        {
            state(shuffleState,repeatState,playState,3);
        }
        else
        {
            index--;
            state(shuffleState,repeatState,playState,3);
        }
    }

    @Override
    public void setAudioPlayer(int value,int max)
    {
        try
        {
            jSliderListener.playPause(playlist.get(index),2);
            System.out.println("reached here "+ value);
            audioPlayer.stop();
            player.stop();
            Mp3File mp3File = new Mp3File(playlist.get(index).getFileLocation());
            audioPlayer = new AudioPlayer(playlist.get(index).getFileLocation(), mp3File.getFrameCount());
            audioPlayer.setPlayLocation(value* mp3File.getFrameCount()/max);
            player = new Thread(audioPlayer);
            playlist.get(index).setLastPlayed(LocalDateTime.now());
            audioPlayer.playMusic(player);
            jSliderListener.playPause(playlist.get(index),1);

            loadPlayingPanel.addMusicToActivity(playlist.get(index));
            Sharing.setMusic(playlist.get(index));
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
