package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CustomButton extends JButton {
    private Color hoverBackgroundColor = new Color(0, 31, 84);
    private Color pressedBackgroundColor = new Color(3, 64, 120);
    private Color defaultBackgroundColor = Color.WHITE;

    public CustomButton(String text) {
        super(text);
        setFocusPainted(false);
        setBackground(defaultBackgroundColor);
        setForeground(new Color(10, 17, 40));
        setFont(new Font("SansSerif", Font.BOLD, 14));
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        setContentAreaFilled(true);

        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                setBackground(hoverBackgroundColor);
                setForeground(Color.WHITE);
            }

            public void mouseExited(MouseEvent e) {
                setBackground(defaultBackgroundColor);
                setForeground(new Color(10, 17, 40));
            }

            public void mousePressed(MouseEvent e) {
                setBackground(pressedBackgroundColor);
            }

            public void mouseReleased(MouseEvent e) {
                setBackground(hoverBackgroundColor);
            }
        });
    }
}
