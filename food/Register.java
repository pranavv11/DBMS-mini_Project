package food;

import java.sql.*;
import javax.swing.*;
import javax.swing.JOptionPane;

public class Register extends javax.swing.JFrame {
    Connection Con = null;
    PreparedStatement pst = null;
    String gender ;

    public Register() {
        initComponents();
        setTitle("Register");
        setSize(400, 500);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mini_project", "root", "");
            JOptionPane.showMessageDialog(null, "Connected");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Not connected");
        }
    }

    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabelHeading = new javax.swing.JLabel(); 
        jradioMale = new javax.swing.JRadioButton("male");
        jradioFemale = new javax.swing.JRadioButton("female");
        BG = new ButtonGroup();
        BG.add(jradioMale);
        BG.add(jradioFemale);
        
        
        
        
        firstNameField = new javax.swing.JTextField();
        lastNameField = new javax.swing.JTextField();
        emailField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        addressField = new javax.swing.JTextField();
        streetField = new javax.swing.JTextField();
        //genderField = new javax.swing.JTextField();
        phoneField = new javax.swing.JTextField();
        //allergyField = new javax.swing.JTextField();
        registerButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jPanel1.setLayout(null);
        
        jLabelHeading.setText("Register");
        jLabelHeading.setFont(new java.awt.Font("Tahoma", 1, 24)); // Set font size and style for the heading
        jPanel1.add(jLabelHeading);
        jLabelHeading.setBounds(130, 20, 150, 30);

        //jLabel1.setText("Register");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(150, 20, 100, 30);

        jLabel2.setText("First Name:");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(50, 70, 100, 30);

        jLabel3.setText("Last Name:");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(50, 110, 100, 30);

        jLabel4.setText("Email:");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(50, 150, 100, 30);

        jLabel5.setText("Password:");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(50, 190, 100, 30);

        jLabel6.setText("Address:");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(50, 230, 100, 30);

        jLabel7.setText("Street:");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(50, 270, 100, 30);

        //jLabel8.setText("Gender:");
        jPanel1.add(jradioMale);
        jradioMale.setBounds(50, 310, 100, 30);
        
        jPanel1.add(jradioFemale);
        jradioFemale.setBounds(150, 310, 100, 30);

        jLabel9.setText("Phone No:");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(50, 350, 100, 30);
        
        //jLabel1.setText("Allergy:");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(50, 390, 100, 30); 

        jPanel1.add(firstNameField);
        firstNameField.setBounds(150, 70, 150, 30);

        jPanel1.add(lastNameField);
        lastNameField.setBounds(150, 110, 150, 30);

        jPanel1.add(emailField);
        emailField.setBounds(150, 150, 150, 30);

        jPanel1.add(passwordField);
        passwordField.setBounds(150, 190, 150, 30);

        jPanel1.add(addressField);
        addressField.setBounds(150, 230, 150, 30);

        jPanel1.add(streetField);
        streetField.setBounds(150, 270, 150, 30);

        //jPanel1.add(genderField);
        //genderField.setBounds(150, 310, 150, 30);

        jPanel1.add(phoneField);
        phoneField.setBounds(150, 350, 150, 30);

        //jPanel1.add(allergyField);
        //allergyField.setBounds(150, 390, 150, 30);

        registerButton.setText("Register");
        registerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerButtonActionPerformed(evt);
                new f4().setVisible(true);
                //this.dispose();//did not work here
            }
        });
        jPanel1.add(registerButton);
        registerButton.setBounds(100, 430, 150, 30);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 400, 500);

        pack();
    }

    
    private String submitGender() {
        

        if (jradioMale.isSelected()) {
        	gender = "M";
        	//return gender;
        } else if (jradioFemale.isSelected()) {
        	gender = "F";
        	//return gender;
        }

        if (gender == null) {
            JOptionPane.showMessageDialog(this, "Please select a gender", "Error", JOptionPane.ERROR_MESSAGE);
            
        }
		return gender;
    }
    private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());
        String address = addressField.getText();
        String street = streetField.getText();
        String gender = submitGender();
        String phone = phoneField.getText();
        //String allergy = allergyField.getText();

        String sql = "INSERT INTO customer (fname, lname, emailid, pwd, address, street, gender, phoneno) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        String checkEmailSql = "SELECT * FROM customer WHERE emailid = ?";
        
        try {
            // Check if the email already exists
            pst = Con.prepareStatement(checkEmailSql);
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                // Email already exists
                JOptionPane.showMessageDialog(null, "Email already exists");
                this.dispose();
            } else {
                // Email does not exist, proceed with the insert
                pst = Con.prepareStatement(sql);
                pst.setString(1, firstName);
                pst.setString(2, lastName);
                pst.setString(3, email);
                pst.setString(4, password);
                pst.setString(5, address);
                pst.setString(6, street);
                pst.setString(7, gender);
                pst.setString(8, phone);

                int rowsInserted = pst.executeUpdate();
                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(null, "Registration successful!");
                    this.dispose();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Enter valid credentials");
        }
        
        
        /*try {
            pst = Con.prepareStatement(sql);
            pst.setString(1, firstName);
            pst.setString(2, lastName);
            pst.setString(3, email);
            pst.setString(4, password);
            pst.setString(5, address);
            pst.setString(6, street);
            pst.setString(7, gender);
            pst.setString(8, phone);
            //pst.setString(9, allergy);

            int rowsInserted = pst.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Registration successful!");
                this.dispose();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }*/
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Register().setVisible(true);
                
            }
        });
    }

    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelHeading;
    private javax.swing.JTextField firstNameField;
    private javax.swing.JTextField lastNameField;
    private javax.swing.JTextField emailField;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JTextField addressField;
    private javax.swing.JTextField streetField;
    //private javax.swing.JTextField genderField;
    private javax.swing.JTextField phoneField;
    //private javax.swing.JTextField allergyField;
    private javax.swing.JButton registerButton;
    private javax.swing.JRadioButton jradioFemale;
    private javax.swing.JRadioButton jradioMale;
   // public String gender;
    ButtonGroup BG = new ButtonGroup();
    
    
}
