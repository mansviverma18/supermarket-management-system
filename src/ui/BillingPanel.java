package ui;

import dao.ProductDAO;
import models.Product;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BillingPanel extends JPanel {

    private final JTextArea receipt = new JTextArea();
    private final JComboBox<String> box;
    private final List<Product> products = ProductDAO.all();
    private double total = 0;

    public BillingPanel() {
        setLayout(new BorderLayout());
        setBackground(UIUtil.OFFWHITE);

        /* -------- top bar -------- */
        box = new JComboBox<>(products.stream().map(Product::getName).toArray(String[]::new));

        JButton addBtn    = UIUtil.styledButton("Add");
        JButton finishBtn = UIUtil.styledButton("Finish");
        JButton exportBtn = UIUtil.styledButton("Export PNG");

        JPanel top = new JPanel();
        top.setBackground(UIUtil.NAVY);
        top.add(box);
        top.add(addBtn);
        top.add(finishBtn);
        top.add(exportBtn);

        add(top, BorderLayout.NORTH);

        /* -------- receipt area -------- */
        receipt.setFont(new Font("Monospaced", Font.PLAIN, 16));
        receipt.setEditable(false);
        add(new JScrollPane(receipt), BorderLayout.CENTER);

        /* -------- actions -------- */
        addBtn.addActionListener(e -> {
            Product p = products.get(box.getSelectedIndex());
            receipt.append(String.format("%-28s ₹%.2f%n", p.getName(), p.getPrice()));
            total += p.getPrice();
        });

        finishBtn.addActionListener(e -> {
            receipt.append("\n----------------------------------------\n");
            receipt.append(String.format("%-28s ₹%.2f%n", "TOTAL:", total));
        });

        exportBtn.addActionListener(e ->
                ScreenshotUtil.quickSave(this, "bill"));   // save entire panel as PNG
    }
}
