package View;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class SearchTextField extends JTextField {
    ImageIcon searchIcon;
    ImageIcon closeIcon;
    SearchTextField(ImageIcon searchIcon, ImageIcon closeIcon)
    {
        super();
        this.searchIcon = searchIcon;
        this.closeIcon = closeIcon;
        this.setBorder(null);
        this.setText("     Search");
        this.setCaretPosition(5);
        this.addFocusListener(new FocusListenerForSearchTextField());
        this.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {

            }
            public void removeUpdate(DocumentEvent e) {
                textHandler();
            }
            public void insertUpdate(DocumentEvent e) {
            }
        });
    }

    private void textHandler()
    {
        Runnable textChecker = new Runnable() {
            @Override
            public void run() {
                if (getCaretPosition() < 5)
                {
                    if (getText().length() >= 5)
                        setCaretPosition(5);
                    if (getText().length() < 5)
                    {
                        setText("     ");
                        setCaretPosition(5);
                    }
                }
            }
        };
        SwingUtilities.invokeLater(textChecker);
    }

    private class FocusListenerForSearchTextField implements FocusListener
    {
        @Override
        public void focusLost(FocusEvent e) {
            if (getText().length() == 5)
            {
                setCaretPosition(5);
                if (getText().equals("     "))
                    setText("     Search");
            }
        }

        @Override
        public void focusGained(FocusEvent e) {
            if (getText().equals("     Search")) {
                setCaretPosition(5);
                setText("     ");
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D gd = (Graphics2D) g.create();
        gd.drawImage(searchIcon.getImage(),0,4,15,15,null);
    }
}
