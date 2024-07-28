package cafebillingsystem.View;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Diwas
 */
public class BillDetails extends javax.swing.JFrame {
    private JTable billTable;
    private DefaultTableModel tableModel;
    private JButton backButton;

    public BillDetails() {
        initComponents();
        loadData();
    }

    private void initComponents() {
        setTitle("Bill Details");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel for the content
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Create a table with a default model
        tableModel = new DefaultTableModel(new String[]{"Bill No", "Tea", "Momo", "Grilled Chicken", "Coke", "Coffee", "Burger", "Total Quantity", "Total Price"}, 0);
        billTable = new JTable(tableModel);

        // Customize the table header
        JTableHeader header = billTable.getTableHeader();
        header.setBackground(Color.GREEN); // Sets the header background to the predefined green color

        header.setForeground(Color.BLACK); // Optional: Set header text color

        // Add table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(billTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Create and add a back button
        backButton = new JButton("Back");
        backButton.setBackground(Color.BLUE);
        backButton.setForeground(Color.WHITE);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.addActionListener(e -> {
            // Go back to the previous screen (e.g., Dashboard)
            new Dashboard().setVisible(true);
            this.dispose();
        });

        panel.add(backButton, BorderLayout.SOUTH);

        // Add panel to the frame
        add(panel);
    }

    private void loadData() {
        String url = "jdbc:mysql://localhost:3306/hamrocafe";
        String userName = "root";
        String password = "Bk2k5@#$";

        try (Connection conn = DriverManager.getConnection(url, userName, password)) {
            System.out.println("Database connection successful!");

            String query = "SELECT * FROM bills";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String billNo = rs.getString("billNo");
                int tea = rs.getInt("tea");
                int momo = rs.getInt("momo");
                int grilledChicken = rs.getInt("grilled_chicken");
                int coke = rs.getInt("coke");
                int coffee = rs.getInt("coffee");
                int burger = rs.getInt("burger");
                int totalQuantity = rs.getInt("total_quantity");
                int totalPrice = rs.getInt("total_price");

                tableModel.addRow(new Object[]{billNo, tea, momo, grilledChicken, coke, coffee, burger, totalQuantity, totalPrice});
            }

        } catch (SQLException ex) {
            System.out.println("Error loading data!");
            ex.printStackTrace(); // Print stack trace for debugging
            JOptionPane.showMessageDialog(this, "Error loading data! " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BillDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new BillDetails().setVisible(true));
    }
}