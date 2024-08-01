package food;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class customer2 extends javax.swing.JFrame {
    Connection Con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public customer2() {
        initComponents();
        setTitle("Customer Orders");
        DefaultTableModel DM = (DefaultTableModel) jTable1.getModel();
        DM.setRowCount(0);

        try {
            String sql = "SELECT * FROM customer_order_";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mini_project", "root", "");
            JOptionPane.showMessageDialog(null, "Connected");

            String colhead[] = {"Order_ID", "Customer_ID", "Pizza", "Burger", "Icecream", "Natural_D", "Carbonated_D", "Tea", "Coffee", "Total_Cost"};
            DM.setColumnIdentifiers(colhead);

            Statement stmt = Con.createStatement();
            rs = stmt.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int cols = rsmd.getColumnCount();

            while (rs.next()) {
                Object[] obj = new Object[cols];
                for (int i = 0; i < cols; i++) {
                    obj[i] = rs.getObject(i + 1);
                }
                DM.addRow(obj);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Not connected: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jTextFieldOrderId = new javax.swing.JTextField();
        jLabelOrderId = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 102));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Order_ID", "Customer_ID", "Pizza", "Burger", "Icecream", "Natural_D", "Carbonated_D", "Tea", "Coffee", "Total_Cost"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton1.setFont(new java.awt.Font("Sylfaen", 2, 18)); // NOI18N
        jButton1.setText("Order Data");

        jButton2.setText("Back");
        jButton2.addActionListener(evt -> jButton2ActionPerformed(evt));

        jButtonDelete.setText("Dispatch Order");
        jButtonDelete.addActionListener(evt -> jButtonDeleteActionPerformed(evt));

        jLabelOrderId.setText("Enter Order ID:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 597, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(349, 349, 349)
                        .addComponent(jButton2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(313, 313, 313)
                        .addComponent(jButton1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(jLabelOrderId)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldOrderId, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonDelete)))
                .addContainerGap(184, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelOrderId)
                    .addComponent(jTextFieldOrderId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonDelete))
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(7, 7, 7))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        new f6().setVisible(true);
        this.dispose();
    }

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {
        String orderId = jTextFieldOrderId.getText();
        
        if (orderId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an Order ID.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to dispatch Order ID: " + orderId + "?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                String sql = "DELETE FROM customer_order_ WHERE Order_ID = ?";
                pst = Con.prepareStatement(sql);
                pst.setString(1, orderId);
                int rowsAffected = pst.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Order ID " + orderId + " deleted successfully.");
                    // Refresh the table after deletion
                    DefaultTableModel DM = (DefaultTableModel) jTable1.getModel();
                    DM.setRowCount(0);
                    // Re-fetch data
                    String selectSql = "SELECT * FROM customer_order_";
                    ResultSet rs = Con.createStatement().executeQuery(selectSql);
                    ResultSetMetaData rsmd = rs.getMetaData();
                    int cols = rsmd.getColumnCount();
                    while (rs.next()) {
                        Object[] obj = new Object[cols];
                        for (int i = 0; i < cols; i++) {
                            obj[i] = rs.getObject(i + 1);
                        }
                        DM.addRow(obj);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Order ID " + orderId + " not found.");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error deleting record: " + e.getMessage());
            }
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new customer2().setVisible(true));
    }

    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JLabel jLabelOrderId;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldOrderId;
}
