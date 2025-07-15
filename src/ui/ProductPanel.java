package ui;

import dao.ProductDAO;
import models.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ProductPanel extends JPanel {

    private DefaultTableModel dm = new DefaultTableModel(
            new String[]{"ID","Name","Category","Qty","Price"},0);
    private JTable table = new JTable(dm);
    private JTextField name=new JTextField(), cat=new JTextField(),
            qty=new JTextField(), price=new JTextField(),
            search=new JTextField();
    private int selId=-1;

    public ProductPanel() {

        setLayout(new BorderLayout());
        add(new JScrollPane(table),BorderLayout.CENTER);

        /* top search bar */
        JPanel top=new JPanel(new BorderLayout());
        top.setBackground(UIUtil.NAVY);
        JButton sBtn=UIUtil.styledButton("Search");
        top.add(search,BorderLayout.CENTER); top.add(sBtn,BorderLayout.EAST);
        add(top,BorderLayout.NORTH);

        /* form + buttons */
        JPanel form=new JPanel(new GridLayout(5,2,6,6)); form.setBackground(UIUtil.NAVY);
        for(Component c:new Component[]{new JLabel("Name"),name ,new JLabel("Category"),cat,
                new JLabel("Quantity"),qty,new JLabel("Price"),price}){
            form.add(c);
            if(c instanceof JLabel l) l.setForeground(Color.WHITE);
        }
        JButton add=UIUtil.styledButton("Add"),
                up =UIUtil.styledButton("Update"),
                del=UIUtil.styledButton("Delete"),
                exp=UIUtil.styledButton("Export PNG");           // ← NEW

        JPanel btns=new JPanel(); btns.setBackground(UIUtil.NAVY);
        btns.add(add); btns.add(up); btns.add(del); btns.add(exp); // add export button
        JPanel south=new JPanel(new BorderLayout());
        south.add(form,BorderLayout.CENTER); south.add(btns,BorderLayout.SOUTH);
        add(south,BorderLayout.SOUTH);

        /* events */
        sBtn .addActionListener(e->load(search.getText()));
        add .addActionListener(e->{ ProductDAO.insert(toProduct()); load(""); clear(); });
        up  .addActionListener(e->{ if(selId!=-1){ ProductDAO.update(toProduct()); load(""); clear(); }});
        del .addActionListener(e->{ if(selId!=-1){ ProductDAO.delete(selId); load(""); clear(); }});
        exp .addActionListener(e-> ScreenshotUtil.quickSave(this,"products")); // save panel as PNG

        table.getSelectionModel().addListSelectionListener(e->{
            if(!e.getValueIsAdjusting() && table.getSelectedRow()!=-1){
                selId=Integer.parseInt(dm.getValueAt(table.getSelectedRow(),0).toString());
                name .setText(dm.getValueAt(table.getSelectedRow(),1).toString());
                cat  .setText(dm.getValueAt(table.getSelectedRow(),2).toString());
                qty  .setText(dm.getValueAt(table.getSelectedRow(),3).toString());
                price.setText(dm.getValueAt(table.getSelectedRow(),4).toString());
            }
        });
        load("");
    }

    /* helper methods unchanged ... */
    private Product toProduct(){ return new Product(selId,name.getText(),cat.getText(),
            Integer.parseInt(qty.getText()),Double.parseDouble(price.getText())); }
    private void load(String k){ dm.setRowCount(0);
        List<Product> list=k.isEmpty()?ProductDAO.all():ProductDAO.search(k);
        for(Product p:list) dm.addRow(new Object[]{p.getId(),p.getName(),p.getCategory(),
                p.getQuantity(),p.getPrice()}); }
    private void clear(){ selId=-1; name.setText("");cat.setText("");
        qty.setText("");price.setText("");}
}
