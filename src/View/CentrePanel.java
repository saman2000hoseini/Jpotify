package View;

import javax.swing.*;
import java.awt.*;

public class CentrePanel extends JPanel {
    private CustomTextField search = new CustomTextField(175, 24, Icons.rescaleIcon(Icons.SEARCH2_ICON, 15, 15)
            , Icons.rescaleIcon(Icons.CLOSE2_ICON, 10, 10));
    private JLabel previous = new JLabel(Icons.rescaleIcon(Icons.LEFT_ARROW_ICON, 19, 19));
    private JLabel next = new JLabel(Icons.rescaleIcon(Icons.RIGHT_ARROW_ICON, 19, 19));
    private JLabel userPic = new JLabel(Icons.rescaleIcon(Icons.USER4_ICON, 25, 25));
    private JLabel userName = new JLabel("Roham");
    private JLabel userMenu = new JLabel(Icons.rescaleIcon(Icons.DOWN_ARROW_ICON, 20, 20));

    CentrePanel() {
        super();
        setBackground(new Color(24, 24, 24));
        search.setBackground(new Color(24, 24, 24));
        search.textField.setFont(new Font("Proxima Nova Rg", Font.PLAIN, 15));
        userName.setFont(new Font("Proxima Nova Rg", Font.BOLD, 14));
        userName.setForeground(new Color(255, 255, 255));
        setVisible(true);
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addContainerGap(11, 11)
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
                .addComponent(userMenu,  20, 20, 20));

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
