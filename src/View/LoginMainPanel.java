package View;

import javax.swing.*;
import java.awt.*;

public class LoginMainPanel extends JPanel {
    private CustomLabelForSongsPanel logIn;
    private JTextField userName;
    private JTextField ip;
    private GroupLayout layout;
    private JLabel jPotify = new JLabel(Icons.JPOTIFYLOGIN_ICON);
    LoginMainPanel(int width, int height)
    {
        super();
        this.setBackground(new Color(24, 24, 24));
        userName = new JTextField("UserName");
        ip = new JTextField("IP");
        userName.setBackground(new Color(40, 40, 40));
        ip.setBackground(new Color(40, 40, 40));
        userName.setForeground(new Color(150, 150, 150));
        ip.setForeground(new Color(150, 150, 150));
        userName.setBorder(null);
        ip.setBorder(null);
        setSize(width, height);
        layout = new GroupLayout(this);
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                .addContainerGap(700, 700)
                .addGroup(layout.createParallelGroup()
                        .addComponent(userName, 200, 200, 200)
                        .addComponent(ip, 200, 200, 200))
                .addContainerGap(700, 700));
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addContainerGap(100, 100)
                        .addComponent(userName, 30, 30, 30)
                        .addGap(15, 15, 15)
                        .addComponent(ip, 30, 30, 30)
                        .addContainerGap(700, 700));
        this.setLayout(layout);
    }
}
