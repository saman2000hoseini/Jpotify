package View;

import Listeners.AddPlayingMusic;
import Model.Music;
import Model.User;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class SongPanelForPlayPanel extends JPanel implements AddPlayingMusic{
    private JLabel musicImage = new JLabel();
    private JLabel songName = new JLabel();
    private JLabel songArtist = new JLabel();
    GroupLayout layout = new GroupLayout(this);
    SongPanelForPlayPanel()
    {
        setBackground(new Color(40, 40, 40));
        musicImage.setBackground(new Color(40, 40, 40));
        songName.setBackground(new Color(40, 40, 40));
        songArtist.setBackground(new Color(40, 40, 40));
        songName.setForeground(Color.WHITE);
        songArtist.setForeground(new Color(170, 170, 170));
        songName.setFont(new Font("Proxima Nova Rg", Font.BOLD, 15));
        songArtist.setFont(new Font("Proxima Nova Rg", Font.BOLD, 13));
        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addComponent(musicImage, 50, 50, 50)
            .addGap(5, 5, 5)
            .addGroup(layout.createParallelGroup()
                    .addComponent(songName, 80, 80, 80)));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addContainerGap(5, 5)
                .addGroup(layout.createParallelGroup()
                            .addComponent(musicImage, 50, 50, 50)
                            .addGroup(layout.createSequentialGroup()
                                        .addGap(5, 5, 5)
                                        .addComponent(songName,15, 15, 15)
                                        .addGap(5, 5, 5)
                                        .addComponent(songArtist, 15, 15, 15))));
    }

    public void setMusicImage(BufferedImage image)
    {
        musicImage.setIcon(ImageEditor.rescaleImage(image, 50, 50));
    }

    public void setSongName(String songName)
    {
        this.songName.setText(songName);
    }

    public void setSongArtist(String songArtist)
    {
        this.songArtist.setText(songArtist);
    }

    @Override
    public void addMusicToActivity(Music music, User user) throws InvalidDataException, IOException, UnsupportedTagException {
        Mp3File song = new Mp3File(music.getFileLocation());
        if (song.hasId3v2Tag()){
            ID3v2 id3v2tag = song.getId3v2Tag();
            byte[] imageData = id3v2tag.getAlbumImage();
            //converting the bytes to an image
            setMusicImage(ImageIO.read(new ByteArrayInputStream(imageData)));
        }
        setSongName(music.getName());
        setSongArtist(music.getArtist());
        this.repaint();
    }
}
