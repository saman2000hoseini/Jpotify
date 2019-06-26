package Controller;

import Listeners.TopLeftMenuListener;
import Model.Albums;
import Model.CustomizedFileChooser;
import Model.Library;
import Model.Music;
import View.MainFrame;
import org.farng.mp3.TagException;

import javax.swing.*;
import java.io.IOException;
import java.util.Vector;

public class TopLeftMenuActions implements TopLeftMenuListener
{
    private Vector<Music> musics;
    private Albums albums;
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
                    System.out.println(musics.size());
                    for(Library library:albums.getAlbums())
                        System.out.println(library.getName()+" "+library.getMusics().size());
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
            }
        }
    }
}
