package ui;

import dao.ProductDAO;
import models.Product;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ProductForm extends JPanel {
    DefaultTableModel model;
    JTable table;

    public ProductForm() {
        setLayout(new BorderLayout());

        model = new DefaultTableModel(new Object[]{"ID", "Name", "Category", "Qty", "Price"}, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton refreshBtn = new JButton("Load Products");
        refreshBtn.addActionListener(e -> loadData());
        add(refreshBtn, BorderLayout.SOUTH);

        loadData();
    }

    private void loadData() {
        model.setRowCount(0);
        ProductDAO dao = new ProductDAO();
        List<Product> products = ProductDAO.all();
        for (Product p : products) {
            model.addRow(new Object[]{
                    p.getId(), p.getName(), p.getCategory(), p.getQuantity(), p.getPrice()
            });
        }
    }
}
