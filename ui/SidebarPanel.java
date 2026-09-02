package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SidebarPanel extends JPanel {

    public SidebarPanel(MainWindow mw) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(180, getHeight()));
        setBackground(UIUtil.NAVY);

        // TOP branding
        JPanel topPanel = new JPanel();
        topPanel.setBackground(UIUtil.NAVY);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setBorder(new EmptyBorder(20, 0, 10, 0));

        JLabel brand = new JLabel("Supermarket", JLabel.CENTER);
        brand.setFont(UIUtil.FONT_L);
        brand.setForeground(UIUtil.OFFWHITE);
        brand.setAlignmentX(Component.CENTER_ALIGNMENT);
        topPanel.add(brand);

        // CENTER nav buttons
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(4, 1, 10, 10));  // 4 equal boxes
        centerPanel.setBackground(UIUtil.NAVY);
        centerPanel.setBorder(new EmptyBorder(20, 10, 20, 10));

        centerPanel.add(createNavBox("Home", "/icons/home.png", () -> mw.show("HOME")));
        centerPanel.add(createNavBox("Products", "/icons/cart.png", () -> mw.show("PRODUCTS")));
        centerPanel.add(createNavBox("Billing", "/icons/billing.png", () -> mw.show("BILLING")));
        centerPanel.add(createNavBox("Logout", "/icons/logout.png", () -> {
            mw.dispose();
            new LoginWindow();
        }));

        // BOTTOM version
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(UIUtil.NAVY);
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.setBorder(new EmptyBorder(8, 0, 8, 0));

        JLabel copy = new JLabel("v1.0  ©2025", JLabel.CENTER);
        copy.setFont(UIUtil.FONT_S);
        copy.setForeground(UIUtil.OFFWHITE);
        copy.setAlignmentX(Component.CENTER_ALIGNMENT);
        bottomPanel.add(copy);

        // assemble sidebar
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel createNavBox(String label, String iconPath, Runnable action) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(UIUtil.NAVY_DARK);
        panel.setBorder(BorderFactory.createLineBorder(UIUtil.OFFWHITE, 1));
        panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Load icon using class loader
        ImageIcon icon = new ImageIcon(getClass().getResource(iconPath));
        Image scaled = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        JLabel iconLabel = new JLabel(new ImageIcon(scaled));
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel textLabel = new JLabel(label);
        textLabel.setForeground(UIUtil.OFFWHITE);
        textLabel.setFont(UIUtil.FONT_M);
        textLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        textLabel.setBorder(new EmptyBorder(10, 0, 0, 0));

        panel.add(Box.createVerticalGlue());
        panel.add(iconLabel);
        panel.add(textLabel);
        panel.add(Box.createVerticalGlue());

        // Hover effect
        panel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mouseEntered(java.awt.event.MouseEvent evt) {
                panel.setBackground(UIUtil.CYAN);
                textLabel.setForeground(Color.BLACK);
            }

            @Override public void mouseExited(java.awt.event.MouseEvent evt) {
                panel.setBackground(UIUtil.NAVY_DARK);
                textLabel.setForeground(UIUtil.OFFWHITE);
            }

            @Override public void mouseClicked(java.awt.event.MouseEvent evt) {
                action.run();
            }
        });

        return panel;
    }
}
