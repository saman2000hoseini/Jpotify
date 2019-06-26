package View;

import Listeners.SongsPanelListener;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;

public class SongsPanel extends JPanel
{
    private JLabel songs = new JLabel("Songs");
    static CustomLabelForSongsPanel customLabelForSongsPanel =
            new CustomLabelForSongsPanel("PLAY", 108, 32, new Color(29, 178, 73, 255));
    private GroupLayout layout;
    private SongsPanelListener songsPanelListener = null;
    SongsPanel()
    {
        super();
        songs.setForeground(Color.white);
        songs.setFont(new Font("Proxima Nova Rg", Font.BOLD, 30));
        customLabelForSongsPanel.addMouseListener(new ListenerForSongsCustomLabel(customLabelForSongsPanel));
        layout = new GroupLayout(this);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addContainerGap(30, 30)
                .addComponent(songs, 200, 200, 200)
                .addGap(200, 690, 690)
                .addComponent(customLabelForSongsPanel, 108, 108, 108)
                .addContainerGap(10, 10));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addContainerGap(30, 30)
                .addGroup(layout.createParallelGroup()
                        .addComponent(songs, 30, 30, 30)
                        .addComponent(customLabelForSongsPanel, 32, 32, 32)));
        this.setLayout(layout);
        setBackground(new Color(18, 18, 18));
    }

    public class ListenerForSongsCustomLabel extends MouseInputAdapter
    {
        private CustomLabelForSongsPanel customLabelForSongsPanel;

        ListenerForSongsCustomLabel(CustomLabelForSongsPanel label)
        {
            this.customLabelForSongsPanel = label;
        }

        @Override
        public void mouseClicked(MouseEvent e)
        {
            super.mouseClicked(e);
            if (customLabelForSongsPanel.getText().equals("PLAY"))
            {
                customLabelForSongsPanel.setText("PAUSE");
                PlayPanel.playState = 2;
                PlayPanel.play.setIcon(Icons.rescaleIcon(Icons.PAUSE_ICON, 35, 35));
                songsPanelListener.playMusic(false, (String) SongsTablePanel.defaultTableModel.getValueAt(0,2), (String) SongsTablePanel.defaultTableModel.getValueAt(0,3));
                //Pause the song
            }
            else
            {
                customLabelForSongsPanel.setText("PLAY");
                PlayPanel.play.setIcon(Icons.rescaleIcon(Icons.PLAY_ICON, 35, 35));
                PlayPanel.playState = 1;
                songsPanelListener.playMusic(true, (String) SongsTablePanel.defaultTableModel.getValueAt(0,2), (String) SongsTablePanel.defaultTableModel.getValueAt(0,3));
                //Resume the song if any song is playing or play the first row
            }
        }

        @Override
        public void mouseEntered(MouseEvent e)
        {
            super.mouseEntered(e);
            customLabelForSongsPanel.setColor(new Color(29, 205, 75, 255));
            customLabelForSongsPanel.repaint();
        }

        @Override
        public void mouseExited(MouseEvent e)
        {
            super.mouseExited(e);
            customLabelForSongsPanel.setColor(new Color(29, 178, 73, 255));
            customLabelForSongsPanel.repaint();
        }
    }

    public void setSongsPanelListener(SongsPanelListener songsPanelListener)
    {
        System.out.println("here");
        this.songsPanelListener = songsPanelListener;
    }
}
