
public class Clothing extends Product {

    private String size;
    private String color;

    public Clothing(String productId, String productName, int availableItems, double price) {
        super(productId, productName, availableItems, price);
    }

    public Clothing(String productId, String productName, int availableItems, double price, String size, String color) {
        super(productId, productName, availableItems, price);
        this.color = color;
        this.size = size;
    }

    @Override
    public void displayProductDetails() {
        System.out.println("--------------------------------------------------");
        System.out.println("| Type: Clothing                                |");
        System.out.println("--------------------------------------------------");
        System.out.println("| Product ID        | " + String.format("%-26s", getProductId()) + " |");
        System.out.println("| Product Name      | " + String.format("%-26s", getProductName()) + " |");
        System.out.println("| Available Items   | " + String.format("%-26s", getAvailableItems()) + " |");
        System.out.println("| Price             | " + String.format("%-26s", "Rs." + getPrice()) + " |");
        System.out.println("| Size              | " + String.format("%-26s", getSize()) + " |");
        System.out.println("| Color             | " + String.format("%-26s", getColor()) + " |");
        System.out.println("--------------------------------------------------");

    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


}
