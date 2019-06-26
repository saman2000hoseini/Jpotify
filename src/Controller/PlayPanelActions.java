package Controller;

import Listeners.PlayPanelListener;
import Model.Music;
import Model.Sort;
import com.mpatric.mp3agic.Mp3File;
import javazoom.jl.player.AudioDevice;

import java.time.LocalDateTime;
import java.util.Vector;

public class PlayPanelActions implements PlayPanelListener
{
    private static AudioPlayer audioPlayer;
    private FileAndFolderBrowsing fileAndFolderBrowsing = new FileAndFolderBrowsing();
    private static int index = 0;
    private Vector<Music> playlist;
    private static Sort sort;
    private static int sortState = 8, lastSort = 8;
//    private AudioDevice audioDevice = System.out. ;
    private static Thread player;

    public PlayPanelActions(Vector<Music> playlist)
    {
        this.playlist = playlist;
        sortPlaylist();
    }

    @Override
    public void state(boolean shuffleState, int repeatState, int playState, int plPaSk)
    {
        switch (plPaSk)
        {
            case 0:
                if (!shuffleState)
                {
                    lastSort = sortState;
                    sortState = 10;
                    sortPlaylist();
                }
                else
                {
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
                if (index < 0)
                    index = playlist.size() - 1;
                startPlayingMusic();
                break;
            case 2:
                if (playState != 2)
                {
                    System.out.println(playState);
                    if (playState == 0)
                        startPlayingMusic();
                    else
                        audioPlayer.resume();
                }
                else
                {
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
                startPlayingMusic();
                break;
            case 4:
                if (repeatState == 0)
                {
                    repeatState = 1;
                }
                else if (repeatState == 1)
                {
                    repeatState = 2;
                }
                else if (repeatState == 2)
                {
                    repeatState = 0;
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

    public void setPlaylist(Vector<Music> playlist)
    {
        index = 0;
        this.playlist = playlist;
        sortPlaylist();
    }
}
