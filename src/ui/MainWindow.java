package ui;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private final CardLayout cl  = new CardLayout();
    private final JPanel cards   = new JPanel(cl);

    public MainWindow() {
        setTitle("Supermarket Management System");
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // sidebar
        add(new SidebarPanel(this), BorderLayout.WEST);

        // animated welcome panel
        cards.add(new WelcomePanel(), "HOME");
        cards.add(new ProductPanel(), "PRODUCTS");
        cards.add(new BillingPanel(), "BILLING");

        add(cards, BorderLayout.CENTER);
        show("HOME");
        setVisible(true);
    }

    public void show(String card) {
        cl.show(cards, card);
    }
}
