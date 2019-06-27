package Controller;

import Listeners.SongsTableListener;
import Listeners.TopLeftMenuListener;
import Model.Albums;
import Model.CustomizedFileChooser;
import Model.Library;
import Model.Music;
import View.MainFrame;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import org.farng.mp3.TagException;

import javax.swing.*;
import java.io.IOException;
import java.util.Vector;

public class TopLeftMenuActions implements TopLeftMenuListener
{
    private Vector<Music> musics;
    private Albums albums;
    private SongsTableListener songsTableListener = null;
    private LoadingLibrary loadingLibrary = new LoadingLibrary();
    public TopLeftMenuActions(Vector<Music> musics,Albums albums)
    {
        this.albums=albums;
        this.musics = musics;
    }

    @Override
    public void state(int statues)
    {
        if (statues == 0)
        {
            System.exit(0);
        }
        else if (statues == 1)
        {
            CustomizedFileChooser customizedFileChooser = new CustomizedFileChooser();
            int returnVal = customizedFileChooser.showOpenDialog(null);
            if (returnVal== JFileChooser.APPROVE_OPTION)
            {
                try
                {
                    customizedFileChooser.writeFiles(musics);
                    albums.loadAlbums();
                    songsTableListener.addSongs(loadingLibrary.generateTable(musics));
                }
                catch (TagException ex)
                {
                    ex.printStackTrace();
                }
                catch (IOException ex)
                {
                    ex.printStackTrace();
                }
                catch (ClassNotFoundException ex)
                {
                    ex.printStackTrace();
                }
                catch (InvalidDataException e)
                {
                    e.printStackTrace();
                }
                catch (UnsupportedTagException e)
                {
                    e.printStackTrace();
                }
            }
        }
        else if (statues == 2)
        {
            System.out.println("here");
            String ip = JOptionPane.showInputDialog("Enter your friends ip ");
            try
            {
                Main.mainClient.addFriend(ip);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void setSongsTableListener(SongsTableListener songsTableListener)
    {
        this.songsTableListener = songsTableListener;
    }
}
