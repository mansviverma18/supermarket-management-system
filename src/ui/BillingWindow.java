package ui;

import javax.swing.*;
import java.awt.*;

public class BillingWindow extends JFrame {

    public BillingWindow() {
        setTitle("Billing Window");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JTextArea billArea = new JTextArea();
        billArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        billArea.setEditable(false);

        JButton closeBtn = new JButton("Close");
        closeBtn.addActionListener(e -> dispose());

        add(new JScrollPane(billArea), BorderLayout.CENTER);
        add(closeBtn, BorderLayout.SOUTH);

        billArea.setText("Customer Bill\n\nProduct X - ₹100\nProduct Y - ₹200\n\nTotal: ₹300");

        setVisible(true);
    }
}
