package models;

public class Product {
    private int id, quantity;
    private String name, category;
    private double price;

    public Product(String n, String c, int q, double p) { this(0, n, c, q, p); }
    public Product(int id, String n, String c, int q, double p) {
        this.id = id; this.name = n; this.category = c; this.quantity = q; this.price = p;
    }

    public int getId()        { return id; }
    public String getName()   { return name; }
    public String getCategory() { return category; }
    public int getQuantity()  { return quantity; }
    public double getPrice()  { return price; }
}
