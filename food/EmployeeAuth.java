package food;

import java.sql.*;
import javax.swing.JOptionPane;

public class EmployeeAuth extends javax.swing.JFrame {
    Connection Con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public EmployeeAuth() {
        initComponents();
        setTitle("Employee Authentication");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mini_project", "root", "");
            JOptionPane.showMessageDialog(null, "Connected to Database");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Database Connection Failed");
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        
        // Initialize components
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        EMAILID = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(null);

        // Set properties for the "Email:" label
        jLabel1.setText("Email:");
        jLabel1.setBounds(50, 50, 50, 30);
        jPanel1.add(jLabel1);

        // Set properties for the email text field
        EMAILID.setBounds(100, 50, 180, 30);
        jPanel1.add(EMAILID);

        // Set properties for the "Password:" label
        jLabel2.setText("Password:");
        jLabel2.setBounds(20, 100, 80, 30);
        jPanel1.add(jLabel2);

        // Set properties for the password field
        jPasswordField1.setBounds(100, 100, 180, 30);
        jPanel1.add(jPasswordField1);

        // Set properties for the "Authenticate" button
        jButton1.setText("Authenticate");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                authenticateEmployee(evt);
            }
        });
        jButton1.setBounds(100, 150, 180, 30);
        jPanel1.add(jButton1);

        // Set properties for the "Cancel" button
        jButton2.setText("Cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dispose();
            }
        });
        jButton2.setBounds(100, 200, 180, 30);
        jPanel1.add(jButton2);

        // Add panel to the frame and set size
        add(jPanel1);
        setSize(350, 300); // Adjusted size to fit all components
        setLocationRelativeTo(null);
    }

    private void authenticateEmployee(java.awt.event.ActionEvent evt) {
        String sql = "SELECT * FROM employee WHERE emailid=? AND pwd=?";
        try {
            pst = Con.prepareStatement(sql);
            pst.setString(1, EMAILID.getText());
            pst.setString(2, new String(jPasswordField1.getPassword()));

            rs = pst.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Authentication Successful");
                new f6().setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Credentials");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmployeeAuth().setVisible(true);
            }
        });
    }

    // Variables declaration
    private javax.swing.JTextField EMAILID;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration
}
