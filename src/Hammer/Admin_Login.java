/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hammer;

import java.net.URL;
import java.sql.*;
import javax.sound.sampled.*;
import javax.swing.*;

/**
 *
 * @author Mohit
 */
public class Admin_Login extends javax.swing.JFrame {

    int temp;
    /**
     * Creates new form Admin_Login
     */
    public Admin_Login() {
        initComponents();
        jTextField4.requestFocusInWindow();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hammer Mania-Admin Login Menu");
        setResizable(false);

        jPanel1.setLayout(null);

        jButton1.setBackground(javax.swing.UIManager.getDefaults().getColor("CheckBoxMenuItem.selectionBackground"));
        jButton1.setText("Submit");
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jButton1KeyReleased(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(120, 260, 80, 30);

        jLabel4.setText("Enter your Name");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(40, 140, 110, 20);
        jPanel1.add(jTextField4);
        jTextField4.setBounds(180, 140, 140, 30);
        jPanel1.add(jTextField5);
        jTextField5.setBounds(180, 170, 140, 30);

        jLabel5.setText("Enter User No.");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(40, 170, 100, 20);

        jLabel6.setText("Enter password");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(40, 210, 100, 20);

        jLabel7.setFont(new java.awt.Font("Serif", 1, 36)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Admin Log-in Menu");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(130, 20, 380, 60);

        jButton2.setBackground(java.awt.Color.white);
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hammer/back.png"))); // NOI18N
        jButton2.setToolTipText("Back to Home Page");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jButton2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jButton2KeyReleased(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(0, 0, 40, 30);
        jPanel1.add(jPasswordField1);
        jPasswordField1.setBounds(180, 200, 140, 30);

        jLabel1.setBackground(java.awt.Color.white);
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hammer/an-image-of-a-handyman-who-is-a-jack-of-all-trades_82032268.jpg"))); // NOI18N
        jLabel1.setOpaque(true);
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, 0, 610, 390);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 607, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(jTextField4.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Please enter your Name");
        }
        else if(jTextField5.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Please enter your User No.");
        }
        else if(jPasswordField1.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Please enter your passsword");
        }
        else
        {
            temp = 1;
            sound();

            new Database_Connection();
            
            try
            {
                Statement s = Database_Connection.con.createStatement();
                
                ResultSet rs = s.executeQuery("select * from admin_info");
                while(rs.next())
                {
                    if(rs.getString("Name").equals(jTextField4.getText()) && rs.getString("User_No").equals(jTextField5.getText()) && rs.getString("Password").equals(jPasswordField1.getText()) )
                    {
                        new Admin_view().setVisible(true);
                        this.setVisible(false);
                        break;
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "You are not Register as Admin");
                    }
                }
            }
            catch(Exception e)
            {
                System.out.println("Error because of: "+e);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        temp = 0;
        sound();

        new Home_Page().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyReleased
        if(jTextField4.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Please enter your Name");
        }
        else if(jTextField5.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Please enter your User No.");
        }
        else if(jPasswordField1.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Please enter your passsword");
        }
        else
        {
            temp = 1;
            sound();

            new Database_Connection();
            
            try
            {
                Statement s = Database_Connection.con.createStatement();
                
                ResultSet rs = s.executeQuery("select * from admin_info");
                while(rs.next())
                {
                    if(rs.getString("Name").equals(jTextField4.getText()) && rs.getString("User_No").equals(jTextField5.getText()) && rs.getString("Password").equals(jPasswordField1.getText()) )
                    {
                        new Admin_view().setVisible(true);
                        this.setVisible(false);
                        break;
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "You are not Register as Admin");
                    }
                }
            }
            catch(Exception e)
            {
                System.out.println("Error because of: "+e);
            }
        }
    }//GEN-LAST:event_jButton1KeyReleased

    private void jButton2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton2KeyReleased
        temp = 0;
        sound();

        new Home_Page().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton2KeyReleased

    void sound()
    {
       try {
         // Open an audio input stream.
         URL url;
         if(temp == 0)
         {
             url = this.getClass().getClassLoader().getResource("Exit.wav");
         }
         else
         {
             url = this.getClass().getClassLoader().getResource("Admin.wav");
         }
         AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
         // Get a sound clip resource.
         Clip clip = AudioSystem.getClip();
         // Open audio clip and load samples from the audio input stream.
         clip.open(audioIn);
         clip.start();
      }
       catch(Exception e)
       {
           System.err.println("Error because of: "+e);
       }
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
            java.util.logging.Logger.getLogger(Admin_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin_Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables
}
