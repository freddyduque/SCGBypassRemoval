
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Cliente extends javax.swing.JFrame {

    Socket sock, check_sock;
    ObjectInputStream input = null, obj_input = null;
    DataOutputStream obj_output = null;
    ObjectOutputStream output = null;
    NodeInfo[] rm_bypass = null;
    JLabel[][] module_info = null;
    int pre = 0, post = 0, mms = 0;
    String msg, ServIP;
    MainThread bypass_rm_app = null;

    public Cliente(String ServIP) {

        try {
            sock = new Socket(ServIP, 4321);
            output = new ObjectOutputStream(sock.getOutputStream());
            input = new ObjectInputStream(sock.getInputStream());

            check_sock = new Socket(ServIP, 1234);
            obj_output = new DataOutputStream(check_sock.getOutputStream());
            obj_input = new ObjectInputStream(check_sock.getInputStream());

            initComponents();
            this.getContentPane().setBackground(Color.white);
            String[][] ModulesInfo = (String[][]) input.readObject();

            for (int i = 0; i < ModulesInfo.length; i++) {
                if (ModulesInfo[i][5].compareTo("PREPAID") == 0) {
                    pre++;
                } else {
                    if (ModulesInfo[i][5].compareTo("POSTPAID") == 0) {
                        post++;
                    } else {
                        mms++;
                    }
                }
            }

            jPanel4.setPreferredSize(new java.awt.Dimension(100, height_value(pre)));
            jPanel5.setPreferredSize(new java.awt.Dimension(100, height_value(post)));
            jPanel6.setPreferredSize(new java.awt.Dimension(100, height_value(mms)));

            post = pre + post;
            mms = post + mms;

            rm_bypass = new NodeInfo[ModulesInfo.length];
            module_info = new JLabel[ModulesInfo.length][3];

            fill_button_panel(rm_bypass, ModulesInfo);
            fill_label_panel(module_info, ModulesInfo);

            bypass_rm_app = new MainThread("bypass_rm_app", jProgressBar1, obj_output, obj_input, rm_bypass, module_info);
            bypass_rm_app.runMainThread();
            this.addWindowListener(new WindowAdapter() {

                public void windowClosing(WindowEvent evt) {
                    try {
                        System.out.println("intento cerrar ventana");
                        sock.shutdownInput();
                        sock.shutdownOutput();
                        System.exit(0);
                    } catch (IOException ex) {
                        Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            this.setIconImage(new ImageIcon(getClass().getResource("/SCGico.png")).getImage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            System.out.println("error 1");
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.println("error 2");
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel7 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel21 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel6 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Bypass Removal Application");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(79, 104));
        setResizable(false);

        jPanel7.setPreferredSize(new java.awt.Dimension(10, 100));
        jPanel7.setLayout(null);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("<html><p align='center'>SCG Bypass Removal<br>Application<p><html>");
        jPanel7.add(jLabel14);
        jLabel14.setBounds(130, 10, 230, 50);

        jProgressBar1.setMaximum(59);
        jProgressBar1.setToolTipText("");
        jProgressBar1.setString("0 seg");
        jProgressBar1.setStringPainted(true);
        jPanel7.add(jProgressBar1);
        jProgressBar1.setBounds(170, 61, 150, 20);

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Tiempo restante para la próxima consulta");
        jLabel21.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel7.add(jLabel21);
        jLabel21.setBounds(120, 80, 250, 13);

        getContentPane().add(jPanel7, java.awt.BorderLayout.PAGE_START);

        jTabbedPane1.setBackground(java.awt.Color.white);
        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("MODULO");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel4.add(jLabel1);
        jLabel1.setBounds(220, 10, 90, 30);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("<html><p align='center'>Remover<br>Bypass<p><html>");
        jLabel4.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel4.add(jLabel4);
        jLabel4.setBounds(5, 10, 60, 30);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("FLAG");
        jLabel5.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel4.add(jLabel5);
        jLabel5.setBounds(60, 10, 60, 30);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("IP");
        jLabel6.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel4.add(jLabel6);
        jLabel6.setBounds(120, 10, 90, 30);

        jScrollPane1.setViewportView(jPanel4);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(10, 10, 350, 400);

        jTabbedPane1.addTab("PREPAGO", jPanel1);

        jPanel2.setBackground(java.awt.Color.white);
        jPanel2.setLayout(null);

        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(null);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("<html><p align='center'>Remover<br>Bypass<p><html>");
        jLabel7.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel5.add(jLabel7);
        jLabel7.setBounds(5, 10, 60, 30);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("FLAG");
        jLabel8.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel5.add(jLabel8);
        jLabel8.setBounds(60, 10, 60, 30);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("IP");
        jLabel9.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel5.add(jLabel9);
        jLabel9.setBounds(120, 10, 90, 30);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("MODULO");
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel5.add(jLabel3);
        jLabel3.setBounds(220, 10, 90, 30);

        jScrollPane2.setViewportView(jPanel5);

        jPanel2.add(jScrollPane2);
        jScrollPane2.setBounds(10, 10, 350, 400);

        jTabbedPane1.addTab("POSTPAGO", jPanel2);

        jPanel3.setBackground(java.awt.Color.white);
        jPanel3.setLayout(null);

        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(null);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("<html><p align='center'>Remover<br>Bypass<p><html>");
        jLabel10.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel6.add(jLabel10);
        jLabel10.setBounds(5, 10, 60, 30);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("FLAG");
        jLabel11.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel6.add(jLabel11);
        jLabel11.setBounds(60, 10, 60, 30);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("IP");
        jLabel12.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel6.add(jLabel12);
        jLabel12.setBounds(120, 10, 90, 30);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("MODULO");
        jLabel13.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel6.add(jLabel13);
        jLabel13.setBounds(220, 10, 90, 30);

        jScrollPane3.setViewportView(jPanel6);

        jPanel3.add(jScrollPane3);
        jScrollPane3.setBounds(10, 10, 350, 400);

        jTabbedPane1.addTab("MMS", jPanel3);

        jPanel.setBackground(java.awt.Color.white);
        jPanel.setLayout(null);

        jPanel8.setBackground(java.awt.Color.white);
        jPanel8.setLayout(null);
        jPanel.add(jPanel8);
        jPanel8.setBounds(0, 0, 0, 0);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setLayout(null);

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel15.setText("<html><p align='justify'>La activación de un bypass automático en los módulos PPSA, viene dado por el cambio del color del ícono de la columna \"FLAG\" a ROJO. \n<br><br>Cuando esto ocurra, automáticamente se habilitará un botón el cual deberá ser presionado para proceder con el reinicio y remoción del Bypass. Si la remoción fue satisfactoria, el color del ícono \"FLAG\" cambiará automáticamente a VERDE, y el modulo PPSA estará tasando en línea de forma normal.\n<br><br>Si el intento de remoción de bypass no es exitoso, favor comunicarse con el Ingeniero de guardia del equipo de SCG.</p></html>");
        jPanel11.add(jLabel15);
        jLabel15.setBounds(10, 23, 340, 140);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bad.PNG"))); // NOI18N
        jPanel11.add(jLabel16);
        jLabel16.setBounds(10, 310, 340, 90);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("Remoción de Bypass automático");
        jPanel11.add(jLabel17);
        jLabel17.setBounds(10, 5, 340, 15);

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setText("Modulos PPSA Tasando en Línea");
        jPanel11.add(jLabel18);
        jLabel18.setBounds(10, 170, 340, 15);

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/good.PNG"))); // NOI18N
        jPanel11.add(jLabel19);
        jLabel19.setBounds(10, 185, 340, 100);

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setText("Modulos PPSA en Bypass");
        jPanel11.add(jLabel20);
        jLabel20.setBounds(10, 285, 340, 20);

        jPanel.add(jPanel11);
        jPanel11.setBounds(5, 5, 360, 410);

        jTabbedPane1.addTab("AYUDA", jPanel);

        jPanel9.setBackground(java.awt.Color.white);
        jPanel9.setLayout(null);

        jPanel10.setBackground(java.awt.Color.white);
        jPanel10.setLayout(null);
        jPanel9.add(jPanel10);
        jPanel10.setBounds(0, 0, 0, 0);

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SCG.png"))); // NOI18N
        jPanel9.add(jLabel22);
        jLabel22.setBounds(10, 40, 350, 90);

        jLabel23.setFont(new java.awt.Font("Arial Black", 0, 15)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Realizado por:");
        jPanel9.add(jLabel23);
        jLabel23.setBounds(10, 250, 350, 20);

        jLabel24.setText("<html><p align='center'>Freddy Duque</p></html>");
        jPanel9.add(jLabel24);
        jLabel24.setBounds(150, 270, 75, 20);

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel25.setText("<html><p align='center'>\nVersión: SCG Bypass Removal App 1.0 (Build 20130426)<br>\nJava: 1.7.0_04; Java HotSpot(TM) Client VM 23.0-b21</p></html>");
        jPanel9.add(jLabel25);
        jLabel25.setBounds(60, 160, 260, 40);

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 51, 153));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("SCG Team");
        jPanel9.add(jLabel26);
        jLabel26.setBounds(10, 140, 350, 20);

        jTabbedPane1.addTab("ACERCA DE", jPanel9);

        getContentPane().add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(383, 584));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables

    public void fill_button_panel(NodeInfo[] rm_bypass, String[][] label_info) {
        int Y = 45;
        for (int i = 0; i < rm_bypass.length; i++) {
            rm_bypass[i] = new NodeInfo(label_info[i], i);
            rm_bypass[i].setIcon(new javax.swing.ImageIcon(getClass().getResource("/tools.png")));
            rm_bypass[i].setValue(2);
            rm_bypass[i].setVisible(false);
            rm_bypass[i].addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    try {

                        Object nodeInfo = evt.getSource();
                        NodeInfo the_node = (NodeInfo) nodeInfo;
                        int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea remover el Bypass en el módulo " + the_node.ModuleName + "?", "Seleccione una Opción", 2);
                        if (resp == 0) {
                            String[] node_logging = {the_node.ModuleName, the_node.ModuleIP, the_node.ModulePass};
                            output.writeObject(node_logging);
                            String conf = (String) input.readObject();
                            if (conf.compareTo("0") == 0) {
                                the_node.setVisible(false);
                                the_node.setValue(0);
                                module_info[the_node.Index][0].setIcon(new ImageIcon(getClass().getResource("/green.png")));
                            } else {
                                if (conf.compareTo("1") == 0) {
                                    the_node.setVisible(true);
                                    the_node.setValue(1);
                                    module_info[the_node.Index][0].setIcon(new ImageIcon(getClass().getResource("/red.png")));
                                } else {
                                    the_node.setVisible(false);
                                    the_node.setValue(2);
                                    module_info[the_node.Index][0].setIcon(new ImageIcon(getClass().getResource("/gray.png")));
                                }
                            }
                        }
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            if (i < pre) {
                jPanel4.add(rm_bypass[i]);
                rm_bypass[i].setBounds(20, Y, 30, 25);
                Y = Y + 25;
            } else {
                if (i < post && i >= pre) {
                    if (i == pre) {
                        Y = 45;
                    }
                    jPanel5.add(rm_bypass[i]);
                    rm_bypass[i].setBounds(20, Y, 30, 25);
                    Y = Y + 25;
                } else {
                    if (i == post) {
                        Y = 45;
                    }
                    jPanel6.add(rm_bypass[i]);
                    rm_bypass[i].setBounds(20, Y, 30, 25);
                    Y = Y + 25;
                }
            }
        }
    }

    private void fill_label_panel(JLabel[][] module_label, String[][] module_info) {
        int X2 = 140, Y1 = 50, Y2 = 45;
        for (int i = 0; i < module_label.length; i++) {
            X2 = 140;
            for (int j = 0; j < module_label[i].length; j++) {
                if (j == 0) {
                    module_label[i][j] = new JLabel();
                    module_label[i][j].setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                    module_label[i][j].setIcon(new ImageIcon(getClass().getResource("/gray.png")));
                    module_label[i][j].setToolTipText("<html>Verde: NO BYPASS<br>Rojo: BYPASS<br>Gris: SIN CONSULTAR</html>");
                    if (i < pre) {
                        jPanel4.add(module_label[i][j]);
                        module_label[i][j].setBounds(80, Y1, 15, 15);
                        Y1 = Y1 + 25;
                    } else {
                        if (i < post && i >= pre) {
                            if (i == pre) {
                                Y1 = 50;
                            }
                            jPanel5.add(module_label[i][j]);
                            module_label[i][j].setBounds(80, Y1, 15, 15);
                            Y1 = Y1 + 25;
                        } else {
                            if (i == post) {
                                Y1 = 50;
                            }
                            jPanel6.add(module_label[i][j]);
                            module_label[i][j].setBounds(80, Y1, 15, 15);
                            Y1 = Y1 + 25;
                        }
                    }
                } else {
                    module_label[i][j] = new JLabel();
                    module_label[i][j].setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                    String[] dqweas = module_info[i][j + 1].split("\\.");
                    if (j == 1) {
                        module_label[i][j].setText("*.*.*." + dqweas[3]);
                    } else {
                        module_label[i][j].setText(module_info[i][j + 1]);
                    }
                    if (i < pre) {
                        jPanel4.add(module_label[i][j]);
                        module_label[i][j].setBounds(X2, Y2, 100, 25);
                    } else {
                        if (i < post && i >= pre) {
                            if (i == pre) {
                                Y2 = 45;
                            }
                            jPanel5.add(module_label[i][j]);
                            module_label[i][j].setBounds(X2, Y2, 100, 25);
                        } else {
                            if (i == post) {
                                Y2 = 45;
                            }
                            jPanel6.add(module_label[i][j]);
                            module_label[i][j].setBounds(X2, Y2, 100, 25);
                        }
                    }
                    X2 = X2 + 80;
                }
            }
            Y2 = Y2 + 25;
        }
    }

    private int height_value(int pre1) {
        int value = (pre1 * 25) + 50;
        if (value <= 400) {
            return 0;
        } else {
            return value;
        }
    }
}