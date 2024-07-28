package cafebillingsystem.View;
import java.awt.*;
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
    public static void main(String args[]) {

    }
}