package ui;

import javax.swing.*;
import java.awt.*;

public class WelcomePanel extends JPanel {
    private final JLabel welcomeLabel;
    private Timer glowTimer;
    private float glowAlpha = 0f;
    private boolean increasing = true;

    public WelcomePanel() {
        setBackground(UIUtil.OFFWHITE);
        setLayout(new GridBagLayout()); // center it vertically and horizontally

        welcomeLabel = new JLabel("Welcome to Supermarket Management System");
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 36)); // Bigger font
        welcomeLabel.setForeground(UIUtil.NAVY_DARK);

        add(welcomeLabel);

        animateGlow();
    }

    private void animateGlow() {
        glowTimer = new Timer(40, e -> {
            if (increasing) {
                glowAlpha += 0.05f;
                if (glowAlpha >= 0.9f) increasing = false;
            } else {
                glowAlpha -= 0.05f;
                if (glowAlpha <= 0.3f) increasing = true;
            }

            welcomeLabel.setForeground(blend(UIUtil.NAVY_DARK, UIUtil.CYAN, glowAlpha));
            repaint();
        });
        glowTimer.start();
    }

    private Color blend(Color c1, Color c2, float ratio) {
        int r = (int) (c1.getRed()   * (1 - ratio) + c2.getRed()   * ratio);
        int g = (int) (c1.getGreen() * (1 - ratio) + c2.getGreen() * ratio);
        int b = (int) (c1.getBlue()  * (1 - ratio) + c2.getBlue()  * ratio);
        return new Color(r, g, b);
    }
}
