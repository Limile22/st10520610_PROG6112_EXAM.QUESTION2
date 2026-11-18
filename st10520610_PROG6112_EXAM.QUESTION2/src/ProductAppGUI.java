import javax.swing.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class ProductAppGUI extends JFrame implements ActionListener {

    // Declare components at the class level
    private JTextField txtCustomerName, txtPrice;
    private JComboBox<String> cmbProduct;
    private JButton btnProcess;
    private JMenuItem exitItem, processItem, clearItem;

    public ProductAppGUI() {
// 1. Window setup
        setTitle("Product App");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

// 2. Customer Name Label & Text Field
        JLabel lblCustomer = new JLabel("Customer Name:");
        lblCustomer.setBounds(30, 30, 120, 25);
        add(lblCustomer);

        txtCustomerName = new JTextField();
        txtCustomerName.setBounds(160, 30, 180, 25);
        add(txtCustomerName);

// 3. Product Dropdown (JComboBox)
        JLabel lblProduct = new JLabel("Select Product:");
        lblProduct.setBounds(30, 80, 120, 25);
        add(lblProduct);

        String[] products = {
                "Cozy Nights Monarch",
                "Restonic Barbados",
                "Sealy Phuket"
        };
        cmbProduct = new JComboBox<>(products);
        cmbProduct.setBounds(160, 80, 180, 25);
        add(cmbProduct);

// 4. Price Label & Text Field
        JLabel lblPrice = new JLabel("Enter Price:");
        lblPrice.setBounds(30, 130, 120, 25);
        add(lblPrice);

        txtPrice = new JTextField();
        txtPrice.setBounds(160, 130, 180, 25);
        add(txtPrice);

// 5. Process Button
        btnProcess = new JButton("PROCESS");
        btnProcess.setBounds(100, 180, 200, 30);
        btnProcess.addActionListener(this);
        add(btnProcess);

// --- 6. MENU SYSTEM SETUP ---
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu toolsMenu = new JMenu("Tools");

        exitItem = new JMenuItem("Exit");
        processItem = new JMenuItem("Process");
        clearItem = new JMenuItem("Clear");

        exitItem.addActionListener(this);
        processItem.addActionListener(this);
        clearItem.addActionListener(this);

        fileMenu.add(exitItem);
        toolsMenu.add(processItem);
        toolsMenu.add(clearItem);

        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);
        setJMenuBar(menuBar);

// Make window visible
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Handles button and menu item clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnProcess || e.getSource() == processItem) {
            processApplication();
        } else if (e.getSource() == clearItem) {
            txtCustomerName.setText("");
            txtPrice.setText("");
            cmbProduct.setSelectedIndex(0);
        } else if (e.getSource() == exitItem) {
            System.exit(0);
        }
    }

    // Handles calculations, popup dialog, and writing to data.txt
    private void processApplication() {
        try {
            String customer = txtCustomerName.getText().trim();
            String product = (String) cmbProduct.getSelectedItem();
            double price = Double.parseDouble(txtPrice.getText().trim());

// Uses your separate ProductCalculations class for math
            ProductCalculations calc = new ProductCalculations(price);
            double vat = calc.GetVAT();
            double commission = calc.GetCommission();
            double total = calc.GetTotal();

// Display message dialog matching the screenshot format
            String message = String.format(
                    "Customer Name: %s\nProduct: %s\nVAT: R %.1f\nCOMMISSION: R %.1f\nTOTAL: R %.1f",
                    customer, product, vat, commission, total
            );
            JOptionPane.showMessageDialog(this, message, "Message", JOptionPane.INFORMATION_MESSAGE);

// Save log into data.txt
            try (PrintWriter writer = new PrintWriter(new FileWriter("data.txt", true))) {
                writer.println("DATA LOG");
                writer.println("**************************************************");
                writer.println("Customer Name: " + customer);
                writer.println("Product: " + product);
                writer.printf("VAT: R %.1f\n", vat);
                writer.printf("COMMISSION: R %.1f\n", commission);
                writer.printf("TOTAL: R %.1f\n", total);
                writer.println("**************************************************");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving file.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid numeric price.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new ProductAppGUI();
    }
}