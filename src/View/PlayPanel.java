package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class PlayPanel extends JPanel {
    JLabel shuffle = new JLabel(Icons.rescaleIcon(Icons.SHUFFLE_ICON, 21, 21));
    JLabel skip_backward = new JLabel("▕◀");
    JLabel play = new JLabel(Icons.rescaleIcon(Icons.PLAY_ICON, 35, 35));
    JLabel skip_forward = new JLabel("▶▏");
    JLabel repeat = new JLabel(Icons.rescaleIcon(Icons.REPEAT_ICON, 22, 22));


    PlayPanel(int width) {
        super();
        setBackground(new Color(40, 40, 40));
        this.setSize(width, 88);
        GroupLayout layout = new GroupLayout(this);
        skip_backward.setFont(new Font(skip_backward.getFont().getName(), Font.PLAIN, 13));
        skip_forward.setFont(new Font(skip_forward.getFont().getName(), Font.PLAIN, 13));
        skip_backward.setForeground(new Color(155, 155, 155));
        skip_forward.setForeground(new Color(155, 155, 155));
        play.setAlignmentY(CENTER_ALIGNMENT);
        skip_backward.setAlignmentY(CENTER_ALIGNMENT);
        skip_forward.setAlignmentY(CENTER_ALIGNMENT);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addContainerGap((getWidth() / 2 ) - 115,(getWidth() / 2) - 115)
                .addComponent(shuffle, 25, 25, 25)
                .addGap(23,23,23)
                .addComponent(skip_backward, 25, 25, 25)
                .addGap(27,27,27)
                .addComponent(play, 35, 35, 35)
                .addGap(25,25,25)
                .addComponent(skip_forward, 25, 25, 25)
                .addGap(23,23,23)
                .addComponent(repeat, 25, 25, 25));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addContainerGap(17, 17)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(play, 35, 35, 35)
                        .addComponent(shuffle, 25, 25, 25)
                        .addComponent(skip_backward, 25, 25, 25)
                        .addComponent(skip_forward, 25, 25, 25)
                        .addComponent(repeat, 25, 25, 25))
                        .addContainerGap(30, 30));
        this.setLayout(layout);
        setVisible(true);
    }
}
