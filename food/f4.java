package food;

import java.sql.*;
import javax.swing.JOptionPane;
public class f4 extends javax.swing.JFrame {
Connection Con=null;
PreparedStatement pst=null;

ResultSet rs=null;
    /**
     * Creates new form f4
     */
    public f4() {
        initComponents();
        setTitle("signin");
       
        try{
             Class.forName("com.mysql.cj.jdbc.Driver");
             Con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mini_project","root","");
             JOptionPane.showMessageDialog(null,"connected");
             
             
         }
         catch(Exception e)
         {
            JOptionPane.showMessageDialog(null," not connected");
         }
        

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jToggleButton3 = new javax.swing.JToggleButton();
        jButton3 = new javax.swing.JButton();
        EMAILID = new javax.swing.JTextField();
        jToggleButton2 = new javax.swing.JToggleButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton4 = new javax.swing.JButton();
        jButtonRegister = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(120, 120, 120));

        jPanel1.setBackground(new java.awt.Color(255, 255, 153));
        jPanel1.setLayout(null);

        jToggleButton3.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jToggleButton3.setForeground(new java.awt.Color(51, 0, 255));
        jToggleButton3.setText("SIGN IN");
        jToggleButton3.setBorder(new javax.swing.border.MatteBorder(null));
        jPanel1.add(jToggleButton3);
        jToggleButton3.setBounds(248, 21, 176, 47);

        jButton3.setText("EMAILID");
        jButton3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(jButton3);
        jButton3.setBounds(134, 175, 104, 31);

        EMAILID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(EMAILID);
        EMAILID.setBounds(335, 175, 180, 31);

        jToggleButton2.setText("password");
        jToggleButton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(jToggleButton2);
        jToggleButton2.setBounds(134, 263, 104, 32);

        jButton2.setText("BACK");
        jButton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(400, 350, 73, 28);

        jButton1.setText("submit");
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(220, 350, 66, 28);
        
        
        //register button
        jButtonRegister.setText("Register");
        jButtonRegister.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButtonRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegisterActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonRegister);
        jButtonRegister.setBounds(570, 350, 66, 28);
        

        jPasswordField1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(jPasswordField1);
        jPasswordField1.setBounds(335, 263, 180, 32);

        jButton4.setText(" EMPLOYEE INFO");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4);
        jButton4.setBounds(520, 50, 170, 30);

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Kushal Kumar B P\\Desktop\\mini\\images\\Screenshot_2017-11-16-11-52-14-595.jpe")); // NOI18N
        jPanel1.add(jLabel1);
        jLabel1.setBounds(-6, -10, 720, 550);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 714, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>                        

    
    //register button functionality -- to open register page
    
    private void jButtonRegisterActionPerformed(java.awt.event.ActionEvent evt) {
    	new Register().setVisible(true);
    	this.dispose();
    	
    }
    
    
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        String sql="select * from customer where  emailid=?  and pwd=?";
        try
        {
            pst=Con.prepareStatement(sql);
            pst.setString(1,EMAILID.getText());
            pst.setString(2,jPasswordField1.getText());

            rs=pst.executeQuery();
            if(rs.next()){
            	String custid = rs.getString("custid");
                JOptionPane.showMessageDialog(null,"your customer id is "+rs.getString("custid")+ " thanks for being member to us");
              foodr ee=new foodr(custid);
                ee.setVisible(true);
                this.dispose();
                     }
            else
            {
                JOptionPane.showMessageDialog(null,"could not sign in");
                new Register().setVisible(true);
                this.dispose(); 
            }
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
    }                                        
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        new f1().setVisible(true);
        this.dispose();
        
        // TODO add your handling code here:
    }                                        

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {                                         
    	new EmployeeAuth().setVisible(true); 
    	this.dispose();
    	
    	// TODO add your handling code here:
    }                                        

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(f4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(f4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(f4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(f4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new f4().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JTextField EMAILID;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButtonRegister;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JToggleButton jToggleButton3;
    // End of variables declaration                   
}
