package View;

import javax.swing.*;
import java.awt.*;

public class CentrePanel extends JPanel {
    private CustomTextField search = new CustomTextField(175, 24, Icons.rescaleIcon(Icons.SEARCH2_ICON, 15, 15)
            , Icons.rescaleIcon(Icons.CLOSE2_ICON, 10, 10));
    private JLabel previous = new JLabel("‹");
    private JLabel next = new JLabel("›");
    private JLabel userPic = new JLabel(Icons.rescaleIcon(Icons.USER4_ICON, 25, 25));
    private JLabel userName = new JLabel("Roham");
    private JLabel userMenu = new JLabel("⌵");

    CentrePanel() {
        super();
        setBackground(new Color(24, 24, 24));
        search.setBackground(new Color(24, 24, 24));
        search.textField.setFont(new Font("Proxima Nova Rg", Font.PLAIN, 15));
        userName.setFont(new Font("Proxima Nova Rg", Font.BOLD, 14));
        userMenu.setFont(new Font("Sefir", Font.PLAIN, 24));
        previous.setFont(new Font("Proxima Nova Rg", Font.PLAIN, 45));
        next.setFont(new Font("Proxima Nova Rg", Font.PLAIN, 45));
        userName.setForeground(new Color(255, 255, 255));
        userMenu.setForeground(new Color(255, 255, 255));
        previous.setForeground(new Color(155, 155, 155));
        next.setForeground(new Color(155, 155, 155));
        setVisible(true);
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addContainerGap(20, 20)
                .addComponent(previous, 20, 20, 20)
                .addGap(11, 11, 11)
                .addComponent(next, 20, 20, 20)
                .addGap(17, 17, 17)
                .addComponent(search, 175, 175, 175)
                .addGap(50, 660, 660)
                .addComponent(userPic, 25, 25 ,25)
                .addGap(11, 11, 11)
                .addComponent(userName, 50, 50 ,50)
                .addGap(12, 12, 12)
                .addComponent(userMenu,  20, 20, 20)
                .addContainerGap(35, 35));

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addContainerGap(4, 4)
                .addGroup(layout.createParallelGroup()
                        .addComponent(previous, 20, 20, 20)
                        .addComponent(next, 20, 20, 20)
                        .addComponent(search, 24, 24, 24)
                        .addComponent(userPic, 25, 25 ,25)
                        .addComponent(userName, 25, 25 ,25)
                        .addComponent(userMenu,  20, 20, 20)));
    }
}
