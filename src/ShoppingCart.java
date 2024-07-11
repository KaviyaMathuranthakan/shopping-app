import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Product> products;

    public ShoppingCart() {
        this.products = new ArrayList<>();
    }

    // Adding the product to the cart.
    public void addProduct(Product product) {
        products.add(product);
    }

    // Removing the product from the cart.
    public void removeProduct(Product product) {
        products.remove(product);
    }

    // Total cost of the products in the cart
    public double calculateTotalCost() {
        double totalCost = 0.0;

        for (Product product : products) {
            totalCost += product.getPrice();
        }
        return totalCost;
    }

    public List<Product> getProducts() {
        return products;
    }

}
