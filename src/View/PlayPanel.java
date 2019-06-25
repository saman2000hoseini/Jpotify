package View;

import Controller.AudioPlayer;
import Controller.FileAndFolderBrowsing;
import Listeners.PlayPanelListener;
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

public class PlayPanel extends JPanel
{
    private boolean shuffleState = false;
    private int repeatState = 0;
    private int playState =0;
    private PlayPanelListener playPanelListener = null;

    public PlayPanelListener getPlayPanelListener()
    {
        return playPanelListener;
    }

    JLabel shuffle = new JLabel("\uD83D\uDD00")
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
    JLabel skip_backward = new JLabel("⏮")
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
    JLabel play = new JLabel(Icons.rescaleIcon(Icons.PLAY_ICON, 35, 35))
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
    JLabel skip_forward = new JLabel("⏭")
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
    JLabel repeat = new JLabel("\uD83D\uDD01")
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

    public void setPlayPanelListener(PlayPanelListener playPanelListener)
    {
        this.playPanelListener = playPanelListener;
    }

    public PlayPanel(int width, Vector<Music> playlist)
    {
        super();
        setBackground(new Color(40, 40, 40));
        this.setSize(width, 88);
        ListenerForMouse listenerForMouse = new ListenerForMouse();
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
        setVisible(true);
    }

    public  void update()
    {
        this.getLayout().removeLayoutComponent(this);
        GroupLayout layout = new GroupLayout(this);
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
    }

    private class ListenerForMouse implements MouseListener
    {

        @Override
        public void mouseClicked(MouseEvent e)
        {
            if (e.getSource() == shuffle)
            {
                playPanelListener.state(shuffleState,repeatState,playState,0);
                if (!shuffleState)
                {
                    shuffle.setForeground(new Color(1, 180, 53));
                    shuffleState = true;
                }
                else
                {
                    shuffle.setForeground(new Color(255, 255, 255));
                    shuffleState = false;
                }

            }
            if (e.getSource() == skip_backward)
            {
                playPanelListener.state(shuffleState,repeatState,playState,1);
                skip_backward.setForeground(new Color(255, 255, 255));
                try
                {
//                    player.stop();
//                    player.interrupt();
                }
                catch (Exception ex)
                {

                }
                finally
                {
                    if (playState != 2)
                        play.setIcon(Icons.rescaleIcon(Icons.PAUSE_ICON, 35, 35));
                }
                playState = 2;
            }
            if (e.getSource() == play)
            {
                playPanelListener.state(shuffleState,repeatState,playState,2);
                if (playState != 2)
                {
                    play.setIcon(Icons.rescaleIcon(Icons.PAUSE_ICON, 35, 35));
                    playState = 2;
                }
                else
                {
                    play.setIcon(Icons.rescaleIcon(Icons.PLAY_ICON, 35, 35));
                    playState = 1;
                }

            }
            if (e.getSource() == skip_forward)
            {
                playPanelListener.state(shuffleState,repeatState,playState,3);
                skip_forward.setForeground(new Color(255, 255, 255));
                try
                {
//                    player.stop();
//                    player.interrupt();
                }
                catch (Exception ex)
                {

                }
                finally
                {
                    if (playState != 2)
                        play.setIcon(Icons.rescaleIcon(Icons.PAUSE_ICON, 35, 35));
                }
                playState = 2;
            }
            if (e.getSource() == repeat)
            {
                playPanelListener.state(shuffleState,repeatState,playState,4);
                if (repeatState == 0)
                {
                    repeat.setText("\uD83D\uDD01");
                    repeat.setForeground(new Color(1, 180, 53));
                    repeatState = 1;
                }
                else if (repeatState == 1)
                {
                    repeat.setText("\uD83D\uDD02");
                    repeatState = 2;
                    repeat.setForeground(new Color(1, 180, 49));
                }
                else if (repeatState == 2)
                {
                    repeat.setText("\uD83D\uDD01");
                    repeatState = 0;
                    repeat.setForeground(new Color(255, 255, 255));
                }
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
            if (e.getSource() == repeat)
            {
                if (e.getSource() == repeat)
                {
                    if (repeatState == 0)
                    {
                        repeat.setText("\uD83D\uDD01");
                        repeat.setForeground(new Color(155, 155, 155));
                    }
                    if (repeatState == 1)
                    {
                        repeat.setText("\uD83D\uDD01");
                        repeat.setForeground(new Color(1, 155, 49));
                    }
                    if (repeatState == 2)
                    {
                        repeat.setText("\uD83D\uDD02");
                        repeat.setForeground(new Color(1, 155, 49));
                    }
                }
            }
        }

        private void refreshIcons(MouseEvent e)
        {
            if (e.getSource() == shuffle)
            {
                if (!shuffleState)
                {
                    shuffle.setForeground(new Color(155, 155, 155));
                }
                else
                {
                    shuffle.setForeground(new Color(1, 155, 49));
                }
            }
            if (e.getSource() == skip_backward)
            {
                skip_backward.setForeground(new Color(155, 155, 155));
            }
            if (e.getSource() == play)
            {
                if (playState != 2)
                    play.setIcon(Icons.rescaleIcon(Icons.PLAY_ICON, 35, 35));
                else
                    play.setIcon(Icons.rescaleIcon(Icons.PAUSE_ICON, 35, 35));
            }
            if (e.getSource() == skip_forward)
            {
                skip_forward.setForeground(new Color(155, 155, 155));
            }
        }

        @Override
        public void mouseEntered(MouseEvent e)
        {
            if (e.getSource() == shuffle)
            {
                if (!shuffleState)
                {
                    shuffle.setForeground(new Color(255, 255, 255));
                }
                else
                {
                    shuffle.setForeground(new Color(1, 180, 50));
                }
            }
            if (e.getSource() == skip_backward)
            {
                skip_backward.setForeground(new Color(255, 255, 255));
            }
            if (e.getSource() == play)
            {
                if (playState != 2)
                    play.setIcon(Icons.rescaleIcon(Icons.PLAY_ICON, 37, 37));
                else
                    play.setIcon(Icons.rescaleIcon(Icons.PAUSE_ICON, 37, 37));
            }
            if (e.getSource() == skip_forward)
            {
                skip_forward.setForeground(new Color(255, 255, 255));
            }
            if (e.getSource() == repeat)
            {
                if (repeatState == 0)
                {
                    repeat.setText("\uD83D\uDD01");
                    repeat.setForeground(new Color(255, 255, 255));
                }
                if (repeatState == 1)
                {
                    repeat.setText("\uD83D\uDD01");
                    repeat.setForeground(new Color(1, 180, 55));
                }
                if (repeatState == 2)
                {
                    repeat.setText("\uD83D\uDD02");
                    repeat.setForeground(new Color(1, 180, 56));
                }
            }
        }

        @Override
        public void mouseExited(MouseEvent e)
        {
            refreshIcons(e);
            if (e.getSource() == repeat)
            {
                if (repeatState == 0)
                {
                    repeat.setText("\uD83D\uDD01");
                    repeat.setForeground(new Color(155, 155, 155));
                }
                if (repeatState == 1)
                {
                    repeat.setText("\uD83D\uDD01");
                    repeat.setForeground(new Color(1, 155, 49));
                }
                if (repeatState == 2)
                {
                    repeat.setText("\uD83D\uDD02");
                    repeat.setForeground(new Color(1, 155, 49));
                }
            }
        }
    }
}
