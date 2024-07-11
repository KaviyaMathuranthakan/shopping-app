import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class WestminsterShoppingManager implements ShoppingManager{

    private List<Product> sysProductList;

    public WestminsterShoppingManager() {
        this.sysProductList = new ArrayList<>();
    }
    @Override
    public void addNewProductToSystem(Product product) {
        if (sysProductList.size() < 50) {
            sysProductList.add(product);
            System.out.println("Product added successfully.");
        } else {
            System.out.println("System products reached the Maximum(50). Cannot add more products.");
        }
    }

    @Override
    public void deleteProductFromSystem(String productId) {
        for (Product product : sysProductList) {
            if (product.getProductId().equals(productId)) {
                sysProductList.remove(product);
                System.out.println("Product deleted successfully.");
                System.out.println("Product Information:");
                product.displayProductDetails();
                System.out.println("Total number of products left in the system: " + sysProductList.size());
                return;
            }
        }
        System.out.println("Product with ID " + productId + " not found.");
    }

    @Override
    public void printProductList() {
        Collections.sort(sysProductList, (p1, p2) -> p1.getProductId().compareTo(p2.getProductId()));
        for (Product product : sysProductList) {
            product.displayProductDetails();
            System.out.println();
        }
    }

    @Override
    public void saveProductsToFile(String fileName) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            ObjectOutputStream objOutputStream = new ObjectOutputStream(fileOutputStream);
            objOutputStream.writeObject(sysProductList);
            System.out.println("Product list saved to file successfully.");
            objOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readProductsFromFile(String fileName) {
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            ObjectInputStream objInputStream = new ObjectInputStream(fileInputStream);
            sysProductList = (ArrayList<Product>) objInputStream.readObject();
            System.out.println("Product list read from file successfully.");
            fileInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addProductFromConsole() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter product type (1 for Electronics, 2 for Clothing): ");
        int productType = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter product ID: ");
        String productId = scanner.nextLine();

        System.out.print("Enter product name: ");
        String productName = scanner.nextLine();

        System.out.print("Enter available items: ");
        int availableItems = scanner.nextInt();

        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        if (productType == 1) {
            System.out.print("Enter brand: ");
            String brand = scanner.nextLine();

            System.out.print("Enter warranty period (in months): ");
            int warrantyPeriod = scanner.nextInt();

            Electronics electronics = new Electronics(productId, productName, availableItems, price, brand, warrantyPeriod);
            addNewProductToSystem(electronics);
        } else if (productType == 2) {
            System.out.print("Enter size: ");
            String size = scanner.nextLine();

            System.out.print("Enter color: ");
            String color = scanner.nextLine();

            Clothing clothing = new Clothing(productId, productName, availableItems, price, size, color);
            addNewProductToSystem(clothing);
        } else {
            System.out.println("Invalid product type. Please enter 1 for Electronics or 2 for Clothing.");
        }
    }

    public void deleteProductFromConsole() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter product ID to delete: ");
        String productId = scanner.nextLine();

        deleteProductFromSystem(productId);
    }

    public List<Product> getSysProductList() {
        return sysProductList;
    }

    public Product getProductById(String productId) {
        for (Product product : sysProductList) {
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }
        return null;
    }
    public List<Product> getElectronicsList() {
        List<Product> electronicsList = new ArrayList<>();
        for (Product product : sysProductList) {
            if (product instanceof Electronics) {
                electronicsList.add(product);
            }
        }
        return electronicsList;
    }
    public List<Product> getClothingList() {
        List<Product> clothingList = new ArrayList<>();
        for (Product product : sysProductList) {
            if (product instanceof Clothing) {
                clothingList.add(product);
            }
        }
        return clothingList;
    }
}
