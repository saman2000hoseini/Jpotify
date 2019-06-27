package View;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class LoginMainPanel extends JPanel {
    private CustomLabelForSongsPanel logIn;
    private JTextField userName;
    private JTextField ip;
    private GroupLayout layout;
    private boolean inserted;
    private JLabel logo = new JLabel(Icons.JPOTIFYLOGIN_ICON);
    private JLabel jPotify = new JLabel("JPotify");
    LoginMainPanel(int width, int height)
    {
        super();
        this.setBackground(new Color(24, 24, 24));
        userName = new JTextField("Username");
        userName.setCaretPosition(0);
        userName.setCaretColor(Color.white);
        ip = new JTextField("IP");
        ip.setCaretColor(Color.white);
        ip.setCaretPosition(0);
        userName.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(0, 0, 0, 0)));
        userName.setBorder(new CompoundBorder(new EmptyBorder(new Insets(0, 15, 0, 0)), userName.getBorder()));
        ip.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(0, 0, 0, 0)));
        ip.setBorder(new CompoundBorder(new EmptyBorder(new Insets(0, 15, 0, 0)), ip.getBorder()));
        userName.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {

            }

            public void removeUpdate(DocumentEvent e) {
                textHandler(false, userName, "Username");
            }

            public void insertUpdate(DocumentEvent e) {
                textHandler(true, userName, "Username");
            }
        });
        ip.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {

            }

            public void removeUpdate(DocumentEvent e) {
                textHandler(false, ip, "IP");
            }

            public void insertUpdate(DocumentEvent e) {
                textHandler(true, ip, "IP");
            }
        });
        userName.addFocusListener(new FocusListenerForLoginPanel(userName, "Username"));
        ip.addFocusListener(new FocusListenerForLoginPanel(ip, "IP"));
        userName.setBackground(new Color(40, 40, 40));
        ip.setBackground(new Color(40, 40, 40));
        userName.setForeground(new Color(115, 115, 115));
        ip.setForeground(new Color(115, 115, 115));
        setSize(width, height);
        layout = new GroupLayout(this);
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                .addContainerGap(610, 610)
                .addGroup(layout.createParallelGroup()
                        .addComponent(userName, 340, 340, 340)
                        .addComponent(ip, 340, 340, 340))
                .addContainerGap(610, 610));
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addContainerGap(350, 350)
                        .addComponent(userName, 30, 30, 30)
                        .addGap(25, 25, 25)
                        .addComponent(ip, 30, 30, 30)
                        .addContainerGap(550, 550));
        this.setLayout(layout);
    }

    private void textHandler(boolean flag, JTextField textField, String baseText) {
        Runnable textChecker = new Runnable() {
            @Override
            public void run() {
                if (!flag) {
                    if (textField.getCaretPosition() == 0) {
                        inserted = false;
                        textField.setText(baseText);
                        textField.setCaretPosition(0);
                        textField.setForeground(new Color(115, 115, 115));
                    }
                } else {
                    if (inserted == false) {
                        if (!textField.getText().equals(baseText)) {
                            textField.setText(textField.getText().replace(baseText, ""));
                            inserted = true;
                            textField.setForeground(new Color(170, 170, 170));
                        }
                    }
                }
            }
        };
        SwingUtilities.invokeLater(textChecker);
    }

    private class FocusListenerForLoginPanel implements FocusListener {
        JTextField textField;
        String baseText;
        FocusListenerForLoginPanel(JTextField textField, String baseText)
        {
            this.textField = textField;
            this.baseText = baseText;
        }
        @Override
        public void focusLost(FocusEvent e) {
                if (textField.getText().equals("")) {
                    textField.setText(baseText);
                    textField.setForeground(new Color(115, 115, 115));
                }
        }

        @Override
        public void focusGained(FocusEvent e) {
            if (textField.getText().equals(baseText)) {
                textField.setText("");
                inserted = true;
            }
        }
    }
}
