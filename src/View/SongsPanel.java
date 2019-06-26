package View;

import javax.swing.*;
import java.awt.*;

public class SongsPanel extends JPanel {
    private JLabel songs = new JLabel("Songs");
    private CustomLabelForSongsPanel customLabelForSongsPanel = new CustomLabelForSongsPanel("PLAY", 108, 32);
    private GroupLayout layout;

    SongsPanel() {
        super();
        songs.setForeground(Color.white);
        songs.setFont(new Font("Proxima Nova Rg", Font.BOLD, 30));
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
}
