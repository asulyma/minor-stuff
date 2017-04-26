package JDBC;

public class Product {
    private int id;
    private String nameProduct;
    private double priceProduct;
    private int shopID;

    public Product(int id, String nameProduct, double priceProduct, int shopID) {
        this.id = id;
        this.nameProduct = nameProduct;
        this.priceProduct = priceProduct;
        this.shopID = shopID;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", nameProduct='" + nameProduct + '\'' +
                ", priceProduct=" + priceProduct +
                ", shopID=" + shopID +
                '}';
    }
}
