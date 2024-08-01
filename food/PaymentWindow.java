package food;

import javax.swing.*;
import java.sql.*;

public class PaymentWindow extends JFrame {
    private Connection Con = null;
    private PreparedStatement pst = null;
    private foodr foodrInstance;

    public PaymentWindow(foodr foodrInstance,String custid, int pizza, int burger, int icecream, int natural, int carbonated, int tea, int coffee, String total) {
        setTitle("Payment Details");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Customer ID: " + custid));
        panel.add(new JLabel("Pizza: " + pizza));
        panel.add(new JLabel("Burger: " + burger));
        panel.add(new JLabel("Icecream: " + icecream));
        panel.add(new JLabel("Natural: " + natural));
        panel.add(new JLabel("Carbonated: " + carbonated));
        panel.add(new JLabel("Tea: " + tea));
        panel.add(new JLabel("Coffee: " + coffee));
        panel.add(new JLabel("Total Cost :" + total +" /-"));

        JButton confirmButton = new JButton("Confirm Payment");
        confirmButton.addActionListener(e -> {
            insertPaymentDetails(custid, pizza, burger, icecream, natural, carbonated, tea, coffee, total);
            JOptionPane.showMessageDialog(this, "Payment Successful!");
            dispose();
            if (foodrInstance != null) {
                foodrInstance.dispose(); // Close the foodr window(dispose)
                new f1().setVisible(true);
            }
        });

        panel.add(confirmButton);
        add(panel);
    }

    private void insertPaymentDetails(String custid, int pizza, int burger, int icecream, int natural, int carbonated, int tea, int coffee, String total) {
        try {
            // Establish the connection
            Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mini_project", "root", "");

            // SQL Insert query
            String query = "INSERT INTO Customer_Order_ (Customer_ID, Pizza, Burger, Icecream, Natural_D, Carbonated_D, Tea, Coffee, Total_Cost) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            // Prepare the statement
            pst = Con.prepareStatement(query);

            // Set parameters
            pst.setString(1, custid);
            pst.setInt(2, pizza);
            pst.setInt(3, burger);
            pst.setInt(4, icecream);
            pst.setInt(5, natural);
            pst.setInt(6, carbonated);
            pst.setInt(7, tea);
            pst.setInt(8, coffee);
            pst.setString(9, total);

            // Execute the query
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error occurred while saving payment details.");
        } finally {
            try {
                if (pst != null) pst.close();
                if (Con != null) Con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
