package at.oidastore;

public class Product {
    private String name;
    private String quantity;
    private double oldPrice;
    private double newPrice;
    private String imgName;
    private String description;

    public Product(String name, String quantity, double oldPrice, double newPrice, String imgName, String description) {
        this.name = name;
        this.quantity = quantity;
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
        this.imgName = imgName;
        this.description = description;
    }

    public String getName() { return name; }
    public String getQuantity() { return quantity; }
    public double getOldPrice() { return oldPrice; }
    public double getNewPrice() { return newPrice; }
    public String getImgName() { return imgName; }
    public String getDescription() { return description; }

    public void setOldPrice(double oldPrice) { this.oldPrice = oldPrice; }
    public void setNewPrice(double newPrice) { this.newPrice = newPrice; }

    @Override
    public String toString() {
        return String.format("{'%s', old='%.2f', new='%.2f'}", name, oldPrice, newPrice);
    }
}
