
import java.awt.Color;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author Freddy Duque f80021351
 */
public class DBRequest extends javax.swing.JFrame {

    /**
     * Creates new form DBRequest
     */
    Connection con = null;

    public DBRequest() {
        initComponents();
        this.setIconImage(new ImageIcon(getClass().getResource("/SCGico.png")).getImage());
        //getContentPane().setBackground(Color.white);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DB Information");
        setResizable(false);
        getContentPane().setLayout(null);

        jTextField2.setText("scgms");
        jTextField2.setBorder(javax.swing.BorderFactory.createTitledBorder("DB Name"));
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField2KeyPressed(evt);
            }
        });
        getContentPane().add(jTextField2);
        jTextField2.setBounds(10, 60, 170, 40);

        jTextField3.setText("root");
        jTextField3.setBorder(javax.swing.BorderFactory.createTitledBorder("Username"));
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField3KeyPressed(evt);
            }
        });
        getContentPane().add(jTextField3);
        jTextField3.setBounds(10, 110, 170, 40);

        jPasswordField1.setText("rootscg");
        jPasswordField1.setBorder(javax.swing.BorderFactory.createTitledBorder("Password"));
        jPasswordField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPasswordField1KeyPressed(evt);
            }
        });
        getContentPane().add(jPasswordField1);
        jPasswordField1.setBounds(10, 160, 130, 40);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/play.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(140, 160, 40, 40);

        jTextField4.setText("localhost");
        jTextField4.setBorder(javax.swing.BorderFactory.createTitledBorder("Host"));
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField4KeyPressed(evt);
            }
        });
        getContentPane().add(jTextField4);
        jTextField4.setBounds(10, 10, 100, 40);

        jTextField5.setText("3306");
        jTextField5.setBorder(javax.swing.BorderFactory.createTitledBorder("Port"));
        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField5KeyPressed(evt);
            }
        });
        getContentPane().add(jTextField5);
        jTextField5.setBounds(110, 10, 70, 40);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-199)/2, (screenSize.height-241)/2, 199, 241);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        EvalToken();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyPressed
        if (evt.getKeyCode() == 10) {
            EvalToken();
        }
    }//GEN-LAST:event_jTextField2KeyPressed

    private void jTextField3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyPressed
        if (evt.getKeyCode() == 10) {
            EvalToken();
        }
    }//GEN-LAST:event_jTextField3KeyPressed

    private void jPasswordField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordField1KeyPressed
        if (evt.getKeyCode() == 10) {
            EvalToken();
        }
    }//GEN-LAST:event_jPasswordField1KeyPressed

    private void jTextField4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyPressed
        if (evt.getKeyCode() == 10) {
            EvalToken();
        }
    }//GEN-LAST:event_jTextField4KeyPressed

    private void jTextField5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5KeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new DBRequest().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables

    private void EvalToken() {
        String Token = "";
        char[] test = jPasswordField1.getPassword();
        if (jTextField4.getText().compareTo("") == 0) {
            Token += "* Field \"Host\" is Empty\n";
        } else if (jTextField4.getText().contains(" ") == true) {
            Token += "* Field \"Host\" contain spaces\n";
        }
        if (jTextField5.getText().compareTo("") == 0) {
            Token += "* Field \"Port\" is Empty\n";
        } else if (jTextField5.getText().contains(" ") == true) {
            Token += "* Field \"Port\" contain spaces\n";
        }
        if (jTextField2.getText().compareTo("") == 0) {
            Token += "* Field \"DB Name\" is Empty\n";
        } else if (jTextField2.getText().contains(" ") == true) {
            Token += "* Field \"DB Name\" contain spaces\n";
        }
        if (jTextField3.getText().compareTo("") == 0) {
            Token += "* Field \"Username\" is Empty\n";
        } else if (jTextField3.getText().contains(" ") == true) {
            Token += "* Field \"Username\" contain spaces\n";
        }
        if (jPasswordField1.getPassword().length == 0) {
            Token += "* Field \"Password\" is Empty\n";
        } else {
            for (int i = 0; i < test.length; i++) {
                if (test[i] == ' ') {
                    Token += "* Field \"Password\" contain spaces\n";
                    break;
                }
            }
        }
        if (Token.compareTo("") == 0) {
            TryConnection();
        } else {
            JOptionPane.showMessageDialog(this, "The following Errors were found\n" + Token, "Error found", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void TryConnection() {
        char[] test = jPasswordField1.getPassword();
        try {
            String url = "jdbc:mysql://" + jTextField4.getText() + ":" + jTextField5.getText() + "/" + jTextField2.getText(), user = jTextField3.getText(), password = "";
            for (int i = 0; i < test.length; i++) {
                password += test[i];
            }
            con = DriverManager.getConnection(url, user, password);
            this.setVisible(false);
            new Servidor(con).setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(DBRequest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "The following Errors were found\n" + ex.getMessage(), "Error found", JOptionPane.ERROR_MESSAGE);
        }
    }
}
