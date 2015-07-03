
import com.jcraft.jsch.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author Freddy Duque f80021351
 */
public class Servidor extends javax.swing.JFrame {

    public static Socket sock = new Socket();
    public static ServerSocket serv = null;
    public static ObjectInputStream input = null;
    public static ObjectOutputStream output = null;
    JSch SSH = null;
    Session Conex = null;
    String[] the_node = null;
    String RetLine;
    File file;
    FileWriter fstream;
    BufferedWriter out;
    LogInformation log_obj;

    public Servidor(Connection con) throws IOException {
        initComponents();
        serv = new ServerSocket(4321);
        first_thread test = new first_thread(con);
        test.start();

        this.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent evt) {
                int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea Cerrar el Servidor del Bypass Removal Application?", "Seleccione una Opción", 2);
                if (resp == 0) {
                    System.exit(0);
                }
            }
        });
        this.setIconImage(new ImageIcon(getClass().getResource("/SCGico.png")).getImage());
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Bypass Removal Application");
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setText("<html><p align='justify'>Aplicación corriendo...<br><br>Para detener el servicio de Remoción de Bypass presione el botón de cerrar ventana.<p><html>");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(80, 10, 220, 60);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo.png"))); // NOI18N
        jLabel2.setText("jLabel1");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(10, 10, 60, 60);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-318)/2, (screenSize.height-117)/2, 318, 117);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables

    class first_thread extends Thread {

        Connection con;
        String[][] ModulesInfo = null;
        int i = 0, j = 0;

        private first_thread(Connection con2) {
            con = con2;
        }

        public void run() {
            while (true) {
                try {
                    sock = serv.accept();
                    output = new ObjectOutputStream(sock.getOutputStream());
                    input = new ObjectInputStream(sock.getInputStream());
                    Statement st = con.createStatement();

                    ModulesInfo = RetArray(st.executeQuery("select a.*, b.* from scgms.t_module_conf as a, scgms.t_module_type_conf as b where b.`NAME` = 'PPSA' and a.`T_MODULE_TYPE_CONF_ID`=1 and b.`ID`=1 order by a.`USER_TYPE` desc,a.`NAME` asc;"));
                    output.writeObject(ModulesInfo);

                    main_thread test = new main_thread();
                    test.start();
                    second_thread test2 = new second_thread(input, output);
                    test2.start();
                } catch (Exception e) {
                    System.out.println("error 1");
                    System.out.println(e.getMessage());
                    break;
                }
            }
        }
    }

    class second_thread extends Thread {

        ObjectInputStream input;
        ObjectOutputStream output;
        DateTimeNow timer_obj = null;

        private second_thread(ObjectInputStream input2, ObjectOutputStream output2) {
            input = input2;
            output = output2;
        }

        public void run() {
            while (true) {
                try {
                    the_node = (String[]) input.readObject();
                    SSH = new JSch();
                    try {
                        Conex = SSH.getSession(the_node[2], the_node[1], 22);
                        Conex.setPassword(the_node[2]);
                        Conex.setConfig("StrictHostKeyChecking", "no");
                        Conex.setTimeout(10000);
                    } catch (JSchException ex) {
                        log_obj.critical("Error intentando asignar los valores para la conexión");
                    }
                    if (Conex.isConnected() != true) {
                        Conex.connect();
                    }
                    ExecCmd(Conex, "./SCGMS/REMOVE_BYPASS.sh&");
                    output.writeObject("0");
                } catch (Exception sd) {
                    System.out.println("error 2");
                    System.out.println(sd.getMessage());
                    break;
                }
            }
        }
    }

    private String[][] RetArray(ResultSet rs) {
        String[][] Test = null;
        int NumRow = 0, NumCol = 0;
        try {
            rs.last();
            NumRow = rs.getRow();
            rs.beforeFirst();
            NumCol = rs.getMetaData().getColumnCount();
            Test = new String[NumRow][NumCol];
            for (int i = 0; i < NumRow; i++) {
                if (rs.next()) {
                    for (int j = 0; j < NumCol; j++) {
                        Test[i][j] = rs.getString(j + 1);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Test;
    }

    private String ExecCmd(Session Conex, String cmd) throws IOException {
        String OutputText = "";
        try {
            Channel ConexChan = Conex.openChannel("exec");
            ((ChannelExec) ConexChan).setCommand(cmd);
            ConexChan.setInputStream(null);

            //((ChannelExec) ConexChan).setErrStream(System.err);
            //InputStream InnerStream = ConexChan.getInputStream();

            ConexChan.connect();

            //OutputText = StreamToString(InnerStream);
            ConexChan.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            log_obj.warning("ExecCmd exception - Open Channel!");
        }
        return OutputText;
    }

    private String StreamToString(InputStream input) {
        String RetInf = "";
        int data;
        try {
            while ((data = input.read()) != -1) {
                RetInf += (char) data;
            }
            input.close();
        } catch (IOException ex) {
            System.out.println(this.getName() + "error en input.read");
        }
        return RetInf;
    }
}