import javax.swing.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ShoppingGUI();
            }
        });

        WestminsterShoppingManager westminstershoppingManager = new WestminsterShoppingManager();
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("Menu:");
            System.out.println("1. Add a new product");
            System.out.println("2. Delete a product");
            System.out.println("3. Print all products");
            System.out.println("4. Save products to file");
            System.out.println("5. Read products from file");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    westminstershoppingManager.addProductFromConsole();
                    break;
                case 2:
                    westminstershoppingManager.deleteProductFromConsole();
                    break;
                case 3:
                    westminstershoppingManager.printProductList();
                    break;
                case 4:
                    westminstershoppingManager.saveProductsToFile("productList.dat");
                    break;
                case 5:
                    westminstershoppingManager.readProductsFromFile("productList.dat");
                    break;
                case 6:
                    System.out.println("Exiting the program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }

        } while (choice != 6);
    }
}
