import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class ShoppingGUI extends JFrame {
    private WestminsterShoppingManager shoppingManager;
    private ShoppingCart shoppingCart;
    private Map<String, Integer> purchaseHistory;

    private JComboBox<String> productTypeComboBox;
    private JTable productTable;
    private DefaultTableModel tableModel;
    private JTextArea productDetailsTextArea;
    private JButton addToCartButton;
    private JButton viewShoppingCartButton;


    public ShoppingGUI() {
        this.shoppingManager = new WestminsterShoppingManager();
        this.shoppingCart = new ShoppingCart();
        this.purchaseHistory = new HashMap<>();

        // Initialize components
        productTypeComboBox = new JComboBox<>(new String[]{"All", "Electronics", "Clothing"});
        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Items", "Price", "Type"}, 0);
        productTable = new JTable(tableModel);
        productDetailsTextArea = new JTextArea();
        addToCartButton = new JButton("Add to Cart");
        viewShoppingCartButton = new JButton("Shopping Cart");

        // Set up the main frame
        setTitle("Westminster Shopping App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Set up the product table
        JScrollPane scrollPane = new JScrollPane(productTable);
        add(scrollPane, BorderLayout.CENTER);

        // Set up the product details panel
        JPanel detailsPanel = new JPanel(new BorderLayout());
        detailsPanel.add(new JLabel("Product Details"), BorderLayout.NORTH);
        detailsPanel.add(new JScrollPane(productDetailsTextArea), BorderLayout.CENTER);
        add(detailsPanel, BorderLayout.SOUTH);

        // Set up the control panel
        JPanel controlPanel = new JPanel(new FlowLayout());
        controlPanel.add(new JLabel("Product Type:"));
        controlPanel.add(productTypeComboBox);
        controlPanel.add(addToCartButton);
        controlPanel.add(viewShoppingCartButton);
        add(controlPanel, BorderLayout.NORTH);

        // Set up action listeners
        productTypeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shoppingManager.readProductsFromFile("productList.dat");
                updateProductTable();
            }
        });

        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addToShoppingCart();
            }
        });

        viewShoppingCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewShoppingCart();
            }
        });

        // Display the GUI
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updateProductTable() {
        String selectedType = (String) productTypeComboBox.getSelectedItem();
        List<Product> filteredProducts;

        if (selectedType.equals("All")) {
            filteredProducts = shoppingManager.getSysProductList();
        } else if (selectedType.equals("Electronics")) {
            filteredProducts = shoppingManager.getElectronicsList();
        } else {
            filteredProducts = shoppingManager.getClothingList();
        }

        // Clear the table
        tableModel.setRowCount(0);

        // Populate the table
        for (Product product : filteredProducts) {
            Object[] rowData = {product.getProductId(), product.getProductName(), product.getAvailableItems(),
                    product.getPrice(), (product instanceof Electronics) ? "Electronics" : "Clothing"};

            if (product.getAvailableItems() < 3) {
                tableModel.addRow(rowData);
                int row = tableModel.getRowCount() - 1;
                for (int col = 0; col < tableModel.getColumnCount(); col++) {
                    tableModel.setValueAt(rowData[col], row, col);
                    productTable.setValueAt(Color.RED, row, col);
                }
            } else {
                tableModel.addRow(rowData);
            }
        }
    }

    private void addToShoppingCart() {
        int selectedRow = productTable.getSelectedRow();
        if (selectedRow != -1) {
            String productId = (String) productTable.getValueAt(selectedRow, 0);
            Product selectedProduct = shoppingManager.getProductById(productId);

            shoppingCart.addProduct(selectedProduct);

            JOptionPane.showMessageDialog(this, "Product added to the shopping cart.");
        } else {
            JOptionPane.showMessageDialog(this, "Please select a product from the table.");
        }
    }

    private void viewShoppingCart() {
        if (shoppingCart.getProducts().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Shopping cart is empty.");
            return;
        }

        StringBuilder cartDetails = new StringBuilder("Shopping Cart:\n");
        double totalPrice = 0;

        for (Product product : shoppingCart.getProducts()) {
            cartDetails.append(product.getProductName()).append(" - Rs").append(product.getPrice()).append("\n");
            totalPrice += product.getPrice();
        }

        // Apply discounts
        totalPrice = applyDiscounts(totalPrice);

        cartDetails.append("\nTotal Price: Rs").append(totalPrice);

        JOptionPane.showMessageDialog(this, cartDetails.toString(), "Shopping Cart", JOptionPane.INFORMATION_MESSAGE);
    }

    private double applyDiscounts(double totalPrice) {
        String selectedType = (String) productTypeComboBox.getSelectedItem();
        int itemCount = shoppingCart.getProducts().size();

        if (itemCount >= 3) {
            totalPrice *= 0.8;
        }

        if (!purchaseHistory.containsKey(selectedType)) {
            totalPrice *= 0.9;
            purchaseHistory.put(selectedType, 1);
        }

        return totalPrice;
    }


}
