import java.io.Serializable;

public class Electronics extends Product {

    private String brand;
    private int warrantyPeriod;

    public Electronics(String productId, String productName, int availableItems, double price) {
        super(productId, productName, availableItems, price);
    }

    public Electronics(String productId, String productName, int availableItems, double price, String brand, int warrantyPeriod) {
        super(productId, productName, availableItems, price);
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }

    @Override
    public void displayProductDetails() {
        System.out.println("--------------------------------------------------");
        System.out.println("| Type: Electronics                               |");
        System.out.println("--------------------------------------------------");
        System.out.println("| Product ID        | " + String.format("%-26s", getProductId()) + " |");
        System.out.println("| Product Name      | " + String.format("%-26s", getProductName()) + " |");
        System.out.println("| Available Items   | " + String.format("%-26s", getAvailableItems()) + " |");
        System.out.println("| Price             | " + String.format("%-26s", "Rs." + getPrice()) + " |");
        System.out.println("| Brand             | " + String.format("%-26s", getBrand()) + " |");
        System.out.println("| Warranty Period   | " + String.format("%-26s", getWarrantyPeriod()) + " |");
        System.out.println("--------------------------------------------------");

    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }
}
