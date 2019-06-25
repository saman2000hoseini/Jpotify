package View;

import Controller.AudioPlayer;
import Controller.FileAndFolderBrowsing;
import Model.Music;
import Model.Sort;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Vector;

import static View.MainFrame.*;

public class PlayPanel extends JPanel
{

    protected static void sortPlaylist()
    {
        MainFrame.index = 0;
        MainFrame.sort = new Sort(MainFrame.playlist);
        switch (MainFrame.sortState)
        {
            case 0:
                MainFrame.sort.alphabeticalAscending();
                break;
            case 1:
                MainFrame.sort.alphabeticalDescending();
                break;
            case 2:
                MainFrame.sort.alphabeticalAlbumAscending();
                break;
            case 3:
                MainFrame.sort.alphabeticalAlbumDescending();
                break;
            case 4:
                MainFrame.sort.alphabeticalArtistAscending();
                break;
            case 5:
                MainFrame.sort.alphabeticalArtistDescending();
                break;
            case 6:
                MainFrame.sort.addDateAscending();
                break;
            case 7:
                MainFrame.sort.addDateDescending();
                break;
            case 8:
                MainFrame.sort.recentlyPlayedAscending();
                break;
            case 9:
                MainFrame.sort.recentlyPlayedDescending();
                break;
            case 10:
                MainFrame.sort.shuffle();
                break;
        }
    }

    public void setPlaylist(Vector<Music> playlist)
    {
        MainFrame.index = 0;
        MainFrame.playlist = playlist;
        sortPlaylist();
    }

    static JLabel shuffle = new JLabel("\uD83D\uDD00")
    {
        @Override
        public JToolTip createToolTip()
        {
            JToolTip tip = super.createToolTip();
            tip.setFont(new Font("Proxima Nova Rg", Font.BOLD, 15));
            tip.setBackground(new Color(24, 24, 24));
            tip.setForeground(new Color(180, 180, 180));
            tip.setBorder(null);
            return tip;
        }

        @Override
        public Point getToolTipLocation(MouseEvent event)
        {
            return new Point(-1 * super.getWidth() + 3, super.getHeight());
        }
    };
    static JLabel skip_backward = new JLabel("⏮")
    {
        @Override
        public JToolTip createToolTip()
        {
            JToolTip tip = super.createToolTip();
            tip.setFont(new Font("Proxima Nova Rg", Font.BOLD, 15));
            tip.setBackground(new Color(24, 24, 24));
            tip.setForeground(new Color(180, 180, 180));
            tip.setBorder(null);
            return tip;
        }

        @Override
        public Point getToolTipLocation(MouseEvent event)
        {
            return new Point(-1 * super.getWidth(), super.getHeight());
        }
    };
    static JLabel play = new JLabel(Icons.rescaleIcon(Icons.PLAY_ICON, 35, 35))
    {
        @Override
        public JToolTip createToolTip()
        {
            JToolTip tip = super.createToolTip();
            tip.setFont(new Font("Proxima Nova Rg", Font.BOLD, 15));
            tip.setBackground(new Color(24, 24, 24));
            tip.setForeground(new Color(180, 180, 180));
            tip.setBorder(null);
            return tip;
        }

        @Override
        public Point getToolTipLocation(MouseEvent event)
        {
            return new Point(0, super.getHeight());
        }
    };
    static JLabel skip_forward = new JLabel("⏭")
    {
        @Override
        public JToolTip createToolTip()
        {
            JToolTip tip = super.createToolTip();
            tip.setFont(new Font("Proxima Nova Rg", Font.BOLD, 15));
            tip.setBackground(new Color(24, 24, 24));
            tip.setForeground(new Color(180, 180, 180));
            tip.setBorder(null);
            return tip;
        }

        @Override
        public Point getToolTipLocation(MouseEvent event)
        {
            return new Point(-10, super.getHeight());
        }
    };
    static JLabel repeat = new JLabel("\uD83D\uDD01")
    {
        @Override
        public JToolTip createToolTip()
        {
            JToolTip tip = super.createToolTip();
            tip.setFont(new Font("Proxima Nova Rg", Font.BOLD, 15));
            tip.setBackground(new Color(24, 24, 24));
            tip.setForeground(new Color(180, 180, 180));
            tip.setBorder(null);
            return tip;
        }

        @Override
        public Point getToolTipLocation(MouseEvent event)
        {
            return new Point(-1 * super.getWidth() + 5, super.getHeight());
        }
    };



    PlayPanel(int width, Vector<Music> playlist)
    {
        super();
        MainFrame.playlist = playlist;
        sortPlaylist();
        setBackground(new Color(40, 40, 40));
        this.setSize(width, 88);
        GroupLayout layout = new GroupLayout(this);
        skip_backward.setFont(new Font(skip_backward.getFont().getName(), Font.PLAIN, 20));
        skip_forward.setFont(new Font(skip_forward.getFont().getName(), Font.PLAIN, 20));
        shuffle.setFont(new Font(skip_backward.getFont().getName(), Font.PLAIN, 15));
        repeat.setFont(new Font(skip_forward.getFont().getName(), Font.PLAIN, 15));
        skip_backward.setForeground(new Color(155, 155, 155));
        skip_forward.setForeground(new Color(155, 155, 155));
        repeat.setForeground(new Color(155, 155, 155));
        shuffle.setForeground(new Color(155, 155, 155));
        play.setAlignmentY(CENTER_ALIGNMENT);
        skip_backward.setAlignmentY(CENTER_ALIGNMENT);
        skip_forward.setAlignmentY(CENTER_ALIGNMENT);
        repeat.setAlignmentY(BOTTOM_ALIGNMENT);
        shuffle.setAlignmentY(BOTTOM_ALIGNMENT);
        ListenerForMouse listenerForMouse = ListenerForMouse.getInstance();
        System.out.println(listenerForMouse.hashCode());
        skip_forward.addMouseListener(listenerForMouse);
        skip_backward.addMouseListener(listenerForMouse);
        play.addMouseListener(listenerForMouse);
        repeat.addMouseListener(listenerForMouse);
        shuffle.addMouseListener(listenerForMouse);
        skip_forward.setToolTipText("Next");
        skip_backward.setToolTipText("Previous");
        repeat.setToolTipText("Repeat");
        shuffle.setToolTipText("Shuffle");
        play.setToolTipText("Play");
        shuffleState = false;
        repeatState = 0;
        playState = 0;
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addContainerGap((getWidth() / 2) - 115, (getWidth() / 2) - 115)
                .addComponent(shuffle, 25, 25, 25)
                .addGap(23, 23, 23)
                .addComponent(skip_backward, 25, 25, 25)
                .addGap(20, 20, 20)
                .addComponent(play, 35, 35, 35)
                .addGap(25, 25, 25)
                .addComponent(skip_forward, 25, 25, 25)
                .addGap(23, 23, 23)
                .addComponent(repeat, 25, 25, 25));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGap(5, 15, 15)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(play, 35, 35, 35)
                        .addComponent(skip_backward, 25, 25, 25)
                        .addComponent(skip_forward, 25, 25, 25)
                        .addGroup(layout.createSequentialGroup().addGap(2, 2, 2).addGroup(layout.createParallelGroup()
                                .addComponent(shuffle, 25, 25, 25)
                                .addComponent(repeat, 25, 25, 25))))
                .addContainerGap(30, 30));
        this.setLayout(layout);
        setVisible(true);
    }
}
class ListenerForMouse implements MouseListener
{
    protected ListenerForMouse()
    {

    }
    @Override
    public void mouseClicked(MouseEvent e)
    {
        if (e.getSource() == PlayPanel.shuffle)
        {
            if (!shuffleState)
            {
                PlayPanel.shuffle.setForeground(new Color(1, 180, 53));
                shuffleState = true;
                MainFrame.lastSort = MainFrame.sortState;
                MainFrame.sortState = 10;
                PlayPanel.sortPlaylist();
            }
            else
            {
                PlayPanel.shuffle.setForeground(new Color(255, 255, 255));
                shuffleState = false;
                MainFrame.sortState = MainFrame.lastSort;
                PlayPanel.sortPlaylist();
            }

        }
        if (e.getSource() == PlayPanel.skip_backward)
        {
            PlayPanel.skip_backward.setForeground(new Color(255, 255, 255));
            try
            {
                MainFrame.audioPlayer.Stop();
//                    player.stop();
//                    player.interrupt();
            }
            catch (Exception ex)
            {

            }
            finally
            {
                if (playState != 2)
                    PlayPanel.play.setIcon(Icons.rescaleIcon(Icons.PAUSE_ICON, 35, 35));
            }
            MainFrame.index--;
            if (MainFrame.index < 0)
                MainFrame.index = MainFrame.playlist.size() - 1;
            startPlayingMusic();
            playState = 2;
        }
        if (e.getSource() == PlayPanel.play)
        {
            if (playState != 2)
            {
                PlayPanel.play.setIcon(Icons.rescaleIcon(Icons.PAUSE_ICON, 35, 35));
                if (playState == 0)
                    startPlayingMusic();
                else
                    MainFrame.audioPlayer.resume();
                playState = 2;
            }
            else
            {
                PlayPanel.play.setIcon(Icons.rescaleIcon(Icons.PLAY_ICON, 35, 35));
                playState = 1;
                MainFrame.audioPlayer.pause();
            }

        }
        if (e.getSource() == PlayPanel.skip_forward)
        {
            PlayPanel.skip_forward.setForeground(new Color(255, 255, 255));
            try
            {
                MainFrame.audioPlayer.Stop();
//                    player.stop();
//                    player.interrupt();
            }
            catch (Exception ex)
            {

            }
            finally
            {
                if (playState != 2)
                    PlayPanel.play.setIcon(Icons.rescaleIcon(Icons.PAUSE_ICON, 35, 35));
            }
            MainFrame.index++;
            if (MainFrame.index > MainFrame.playlist.size() - 1)
                MainFrame.index = 0;
            startPlayingMusic();
            playState = 2;
        }
        if (e.getSource() == PlayPanel.repeat)
        {
            if (repeatState == 0)
            {
                PlayPanel.repeat.setText("\uD83D\uDD01");
                PlayPanel.repeat.setForeground(new Color(1, 180, 53));
                repeatState = 1;
            }
            else if (repeatState == 1)
            {
                PlayPanel.repeat.setText("\uD83D\uDD02");
                repeatState = 2;
                PlayPanel.repeat.setForeground(new Color(1, 180, 49));
            }
            else if (repeatState == 2)
            {
                PlayPanel.repeat.setText("\uD83D\uDD01");
                repeatState = 0;
                PlayPanel.repeat.setForeground(new Color(255, 255, 255));
            }
        }
    }

    private void startPlayingMusic()
    {
        try
        {
//                Mp3File mp3File = new Mp3File();
            MainFrame.audioPlayer = AudioPlayer.getAudioPlayer();
//                player=new Thread(audioPlayer);
            MainFrame.musics.get(MainFrame.index).setLastPlayed(LocalDateTime.now());
            MainFrame.fileAndFolderBrowsing.saveMusics(MainFrame.playlist);
            System.out.println("here");
            MainFrame.audioPlayer.Play(MainFrame.playlist.get(MainFrame.index).getFileLocation());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        mouseActions(e);
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        mouseActions(e);
    }

    private void mouseActions(MouseEvent e)
    {
        refreshIcons(e);
        if (e.getSource() == PlayPanel.repeat)
        {
            if (e.getSource() == PlayPanel.repeat)
            {
                if (repeatState == 0)
                {
                    PlayPanel.repeat.setText("\uD83D\uDD01");
                    PlayPanel.repeat.setForeground(new Color(155, 155, 155));
                }
                if (repeatState == 1)
                {
                    PlayPanel.repeat.setText("\uD83D\uDD01");
                    PlayPanel.repeat.setForeground(new Color(1, 155, 49));
                }
                if (repeatState == 2)
                {
                    PlayPanel.repeat.setText("\uD83D\uDD02");
                    PlayPanel.repeat.setForeground(new Color(1, 155, 49));
                }
            }
        }
    }

    private void refreshIcons(MouseEvent e)
    {
        if (e.getSource() == PlayPanel.shuffle)
        {
            if (!shuffleState)
            {
                PlayPanel.shuffle.setForeground(new Color(155, 155, 155));
            }
            else
            {
                PlayPanel.shuffle.setForeground(new Color(1, 155, 49));
            }
        }
        if (e.getSource() == PlayPanel.skip_backward)
        {
            PlayPanel.skip_backward.setForeground(new Color(155, 155, 155));
        }
        if (e.getSource() == PlayPanel.play)
        {
            if (playState != 2)
                PlayPanel.play.setIcon(Icons.rescaleIcon(Icons.PLAY_ICON, 35, 35));
            else
                PlayPanel.play.setIcon(Icons.rescaleIcon(Icons.PAUSE_ICON, 35, 35));
        }
        if (e.getSource() == PlayPanel.skip_forward)
        {
            PlayPanel.skip_forward.setForeground(new Color(155, 155, 155));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
        if (e.getSource() == PlayPanel.shuffle)
        {
            if (!shuffleState)
            {
                PlayPanel.shuffle.setForeground(new Color(255, 255, 255));
            }
            else
            {
                PlayPanel.shuffle.setForeground(new Color(1, 180, 50));
            }
        }
        if (e.getSource() == PlayPanel.skip_backward)
        {
            PlayPanel.skip_backward.setForeground(new Color(255, 255, 255));
        }
        if (e.getSource() == PlayPanel.play)
        {
            if (playState != 2)
                PlayPanel.play.setIcon(Icons.rescaleIcon(Icons.PLAY_ICON, 37, 37));
            else
                PlayPanel.play.setIcon(Icons.rescaleIcon(Icons.PAUSE_ICON, 37, 37));
        }
        if (e.getSource() == PlayPanel.skip_forward)
        {
            PlayPanel.skip_forward.setForeground(new Color(255, 255, 255));
        }
        if (e.getSource() == PlayPanel.repeat)
        {
            if (repeatState == 0)
            {
                PlayPanel.repeat.setText("\uD83D\uDD01");
                PlayPanel.repeat.setForeground(new Color(255, 255, 255));
            }
            if (repeatState == 1)
            {
                PlayPanel.repeat.setText("\uD83D\uDD01");
                PlayPanel.repeat.setForeground(new Color(1, 180, 55));
            }
            if (repeatState == 2)
            {
                PlayPanel.repeat.setText("\uD83D\uDD02");
                PlayPanel.repeat.setForeground(new Color(1, 180, 56));
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
        refreshIcons(e);
        if (e.getSource() == PlayPanel.repeat)
        {
            if (repeatState == 0)
            {
                PlayPanel.repeat.setText("\uD83D\uDD01");
                PlayPanel.repeat.setForeground(new Color(155, 155, 155));
            }
            if (repeatState == 1)
            {
                PlayPanel.repeat.setText("\uD83D\uDD01");
                PlayPanel.repeat.setForeground(new Color(1, 155, 49));
            }
            if (repeatState == 2)
            {
                PlayPanel.repeat.setText("\uD83D\uDD02");
                PlayPanel.repeat.setForeground(new Color(1, 155, 49));
            }
        }
    }
    private static ListenerForMouse listenerForMouse = null;
    protected static ListenerForMouse getInstance()
    {
        if (listenerForMouse == null)
            listenerForMouse = new ListenerForMouse();

        return listenerForMouse;
    }
}
