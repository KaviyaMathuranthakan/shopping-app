public interface ShoppingManager {
    void addNewProductToSystem(Product product);
    void deleteProductFromSystem(String productId);
    void printProductList();
    void saveProductsToFile(String fileName);
    void readProductsFromFile(String fileName);
}
