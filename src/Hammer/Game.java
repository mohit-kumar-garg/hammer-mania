/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hammer;

import java.awt.Color;
import java.awt.event.*;
import java.io.FileInputStream;
import java.net.URL;
import javax.swing.*;
import java.sql.*;
import javax.sound.sampled.*;
import sun.audio.*;

/**
 *
 * @author Mohit
 */
public class Game extends javax.swing.JFrame implements ActionListener , Runnable{

    private Thread myThread = null;
    String Level ,Name ;
    int score=0 , games_played;
    int temp;
    JButton jbutton[][] = new JButton[3][9];
    
    AudioPlayer mgp = AudioPlayer.player;
     AudioStream bgm;
     AudioData md;
     ContinuousAudioDataStream loop = null;
    /**
     * Creates new form Level1
     */
    public Game() {
        initComponents();           
    }
    
    public void run()
    {
        this.setSize(500,500);
       database();  
        this.setVisible(true);
        this.setLocationRelativeTo(null); 

        jButton1.setVisible(false);
        jButton2.setVisible(false);     
         jLabel1.setText("Welcome "+Name);
        jLabel2.setText("Current Level is : "+Level);
     
        for(int i=5;i>0;i--)
        {
            jLabel3.setText("Your Level will Start in: "+ i +" seconds");
            try
            {    
                Thread.sleep(1000);
            }
            catch(Exception e)
            {
                System.out.println("Error because of: "+e);
            }
        }       
        jLabel3.setText("Game Started");
        
        ButtonDesign();
        
        music();
        
        if(!Level.equals(""))
        {
        switch (Level) { 
            case "Level1":
                level1();
                break;
            case "Level2":
                level2();
                break;
            case "Level3":
                level3();
                break;
            case "Level4":
                level4();
                break;
            case "Level5":
                level5();
                break;
            default:
                new Won().setVisible(true);
                this.setVisible(false);
                break;
            }    
        }
    }
    
    public void setThread(Thread mine)
    {
        myThread = mine;
    }
   
    void database()
    {
        new Database_Connection();
        try
        {
            Statement s = Database_Connection.con.createStatement();
            
            ResultSet rs = s.executeQuery("select games_played , status from user_status where User_No = '"+New_user.User_id+"' ");
            while(rs.next())
            {
                games_played = Integer.parseInt(rs.getString("games_played"));
                Level = rs.getString("status");
            }
            games_played+=1;
            s.executeUpdate("update user_status set games_played = '"+games_played+"' where user_no = '"+New_user.User_id+"' ");
            
            if(Level.equals("Not Started"))
            {
                Level = "Level1";
                s.executeUpdate("update user_status set status = '"+Level+"' where user_no = '"+New_user.User_id+"' ");
            }
            
            ResultSet rs1 = s.executeQuery("select name from user_info where User_No = '"+New_user.User_id+"' ");
            while(rs1.next())
            {
                Name = rs1.getString("name");
            }
        }
        catch(Exception e)
        {
            System.out.println("Error beacuse of : "+e);
        }
    }
    
    void ButtonDesign()
    {
        int x=25,y=320;
        for(int i=0;i<3 ; i++)
        {    
            for(int j=0;j<9;j++)
            {
                jbutton[i][j]=new JButton("");
                jbutton[i][j].addActionListener(this);
                jPanel1.add(jbutton[i][j]);
                jbutton[i][j].setBounds(x,y,50,50);                  
                x+=50;
            }
            x=25;
            y+=50;
        }
    }
    
    void level1()
    {
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hammer/Level1.png")));
        jLabel5.setBounds(0, 0, 500, 500);
        
        for(int x=0;x<40;x++)
        {
            int row = (int)(Math.random()*3) ;
            int column = (int)(Math.random()*9);
            for(int i=0;i<3;i++)
            {
                for(int j=0;j<9;j++)
                {
                    jbutton[i][j].setBackground(Color.gray);
                }
            }
            jbutton[row][column].setBackground(Color.blue);   
            try
            { 
                Thread.sleep(1000);
            }
            catch(Exception e)
            {
                System.out.println("Error because of: "+e);
            }
        } 
        
        stop();
        
        if(score >=200)
        {
            temp = 1;
            sound();
            
            Level = "Level2";
            success();
        }
        else
        {
            temp = 0;
            sound();
            
            JOptionPane.showMessageDialog(null, "You loose this level");
            jButton1.setVisible(true);
            jButton1.setText("Retry");
            jButton2.setVisible(true);
        }
    }
    
    void level2()
    {
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hammer/Level2.png"))); // NOI18N
        getContentPane().add(jLabel5);
        jLabel5.setBounds(0, 0, 500, 500);
        for(int x=0;x<40;x++)
        {
            int row = (int)(Math.random()*3) ;
            int column = (int)(Math.random()*9);
            for(int i=0;i<3;i++)
            {
                for(int j=0;j<9;j++)
                {
                    jbutton[i][j].setBackground(Color.gray);
                }
            }
            jbutton[row][column].setBackground(Color.blue);   
            try
            {
             Thread.sleep(900);
            }
            catch(Exception e)
            {
                System.out.println("Error because of: "+e);
            }
        }
        stop();
        if(score >=200)
        {
            temp = 1;
            sound();
            
            Level = "Level3";
            success();
        }
        else
        {
            temp = 0;
            sound();
            
            JOptionPane.showMessageDialog(null, "You loose this level");
            jButton1.setVisible(true);
            jButton1.setText("Retry");
            jButton2.setVisible(true);
        }
    }
    
    void level3()
    {
        jLabel5.setBackground(java.awt.Color.black);
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hammer/Level3.png"))); // NOI18N
        jLabel5.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel5.setOpaque(true);
        getContentPane().add(jLabel5);
        jLabel5.setBounds(0, 0, 500, 500);
        for(int x=0;x<40;x++)
        {
            int row = (int)(Math.random()*3) ;
            int column = (int)(Math.random()*9);
            for(int i=0;i<3;i++)
            {
                for(int j=0;j<9;j++)
                {
                    jbutton[i][j].setBackground(Color.gray);
                }
            }
            jbutton[row][column].setBackground(Color.blue);   

            try
            {
             Thread.sleep(800);
            }
            catch(Exception e)
            {
                System.out.println("Error because of: "+e);
            }
        }
        stop();
        if(score >=200)
        {
            temp = 1;
            sound();
            
            Level = "Level4";
            success();
        }
        else
        {
            temp = 0;
            sound();
            
            JOptionPane.showMessageDialog(null, "You loose this level");
            jButton1.setVisible(true);
            jButton1.setText("Retry");
            jButton2.setVisible(true);
        }
    }
    
    void level4()
    {
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hammer/Level4.png"))); // NOI18N
        jLabel5.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel5.setOpaque(true);
        getContentPane().add(jLabel5);
        jLabel5.setBounds(0, 0, 500, 500);
        for(int x=0;x<40;x++)
        {
            int row = (int)(Math.random()*3) ;
            int column = (int)(Math.random()*9);
            for(int i=0;i<3;i++)
            {
                for(int j=0;j<9;j++)
                {
                    jbutton[i][j].setBackground(Color.gray);
                }
            }
            jbutton[row][column].setBackground(Color.blue);   
            
            try
            {
             Thread.sleep(700);
            }
            catch(Exception e)
            {
                System.out.println("Error because of: "+e);
            }
        }
        stop();
        if(score >=200)
        {
            temp = 1;
            sound();
            
            Level = "Level5";
            success();
        }
        else
        {
            temp = 0;
            sound();
            
            JOptionPane.showMessageDialog(null, "You loose this level");
            jButton1.setVisible(true);
            jButton1.setText("Retry");
            jButton2.setVisible(true);
        }
    }
    
    void level5()
    {
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hammer/Level5.png"))); // NOI18N
        jLabel5.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel5.setOpaque(true);
        getContentPane().add(jLabel5);
        jLabel5.setBounds(0, 0, 500, 500);
        for(int x=0;x<40;x++)
        {
             int row = (int)(Math.random()*3) ;
             int column = (int)(Math.random()*9);
             for(int i=0;i<3;i++)
             {
                 for(int j=0;j<9;j++)
                 {
                     jbutton[i][j].setBackground(Color.gray);
                 }
             }
             jbutton[row][column].setBackground(Color.blue);   
            
            try
            {
             Thread.sleep(600);
            }
            catch(Exception e)
            {
                System.out.println("Error because of: "+e);
            }
        }
        stop();
        if(score >=200)
        {
            Level = "Game Completed"; 
            temp = 1;
            sound();
            
            success();
            new Won().setVisible(true);
            this.setVisible(false);
        }
        else
        {
            temp = 0;
            sound();
            
            JOptionPane.showMessageDialog(null, "You loose this level");
            jButton1.setVisible(true);
            jButton1.setText("Retry");
            jButton2.setVisible(true);
        }
    }
    
    public void actionPerformed(ActionEvent e)
    {
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<9;j++)
            {
                if(e.getSource().equals(jbutton[i][j]) && jbutton[i][j].getBackground() == Color.BLUE )
                {
                    temp = 2;
                    sound();
                    
                    score+=10;
                    jLabel4.setText("Your Score is: "+score);
                }
                else
                {
                    temp = 3;
                    sound();
                }
            }
        }
    }
    
    void success()
    {
        new Database_Connection();
        try
        {
            Statement s = Database_Connection.con.createStatement();

            s.executeUpdate("update user_status set status = '"+Level+"' where user_no = '"+New_user.User_id+"' ");
        }
        catch(Exception e)
        {
            System.out.println("Error beacuse of : "+e);
        }

        JOptionPane.showMessageDialog(null, "You win this level");
        jButton1.setVisible(true);
        jButton2.setVisible(true);
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hammer_Mania Game");
        setBackground(java.awt.Color.white);
        setForeground(java.awt.Color.white);
        setResizable(false);

        jPanel1.setBackground(java.awt.Color.white);
        jPanel1.setOpaque(false);
        jPanel1.setLayout(null);

        jLabel1.setBackground(java.awt.Color.white);
        jLabel1.setOpaque(true);
        jPanel1.add(jLabel1);
        jLabel1.setBounds(70, 80, 150, 20);

        jLabel2.setBackground(java.awt.Color.white);
        jLabel2.setOpaque(true);
        jPanel1.add(jLabel2);
        jLabel2.setBounds(290, 80, 160, 20);

        jLabel3.setBackground(java.awt.Color.white);
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setOpaque(true);
        jPanel1.add(jLabel3);
        jLabel3.setBounds(80, 150, 330, 20);

        jLabel4.setBackground(java.awt.Color.white);
        jLabel4.setText("Your Score is: ");
        jLabel4.setOpaque(true);
        jPanel1.add(jLabel4);
        jLabel4.setBounds(290, 120, 160, 20);

        jButton1.setBackground(javax.swing.UIManager.getDefaults().getColor("CheckBoxMenuItem.selectionBackground"));
        jButton1.setText("Move to next Level");
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
        jButton1.setBounds(290, 200, 150, 30);

        jButton2.setBackground(javax.swing.UIManager.getDefaults().getColor("CheckBoxMenuItem.selectionBackground"));
        jButton2.setText("Go to Main Menu");
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
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
        jButton2.setBounds(70, 200, 140, 30);

        jLabel5.setBackground(java.awt.Color.white);
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon("C:\\Users\\User\\Documents\\NetBeansProjects\\Games\\src\\Hammer\\working.png")); // NOI18N
        jLabel5.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel5.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        temp = 0;
        mgp.stop(loop);
        sound();

        new Home_Page().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        temp = 1;
        mgp.stop(loop);
        sound();

        New_user.User_id = New_user.User_id;
        Game f1 = new Game();
        Thread t1 = new Thread(f1 , "Thread");
        f1.setThread(t1);
        t1.start();
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyReleased
        temp = 1;
        mgp.stop(loop);
        sound();

        New_user.User_id = New_user.User_id;
        Game f1 = new Game();
        Thread t1 = new Thread(f1 , "Thread");
        f1.setThread(t1);
        t1.start();
        this.dispose();
    }//GEN-LAST:event_jButton1KeyReleased

    private void jButton2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton2KeyReleased
        temp = 0;
        mgp.stop(loop);
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
         else if(temp == 1)
         {
             url = this.getClass().getClassLoader().getResource("Entry.wav");
         }
         else if(temp == 2)
         {
             url = this.getClass().getClassLoader().getResource("Right button.wav");
         }
         else
         {
             url = this.getClass().getClassLoader().getResource("Wrong button.wav");
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
    
    void music()
    {   
    try
     {
         bgm = new AudioStream(new FileInputStream("Win.wav"));
         
         if(Level.equals("Level1"))
         {
             bgm = new AudioStream(new FileInputStream("Level1.wav"));
         }
         else if(Level.equals("Level2"))
         {
             bgm = new AudioStream(new FileInputStream("Level2.wav"));
         }
         else if(Level.equals("Level3"))
         {
             bgm = new AudioStream(new FileInputStream("Level3.wav"));
         }
         else if(Level.equals("Level4"))
         {
             bgm = new AudioStream(new FileInputStream("Level4.wav"));
         }
         else if(Level.equals("Level5"))
         {
             bgm = new AudioStream(new FileInputStream("Level5.wav"));
         }
         else
         {
             
         }
         
         md = bgm.getData();
         loop = new ContinuousAudioDataStream(md);
   }
     catch(Exception e)
     {
         System.err.println("Error"+e);
     }
     mgp.start(loop);
    }
    
    void stop()
    {
        for(int x=0;x<3;x++)
        {
            for(int y=0;y<9;y++)
            {
                jbutton[x][y].setEnabled(false);
            }
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
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        
                new Game().setVisible(true);
         
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
