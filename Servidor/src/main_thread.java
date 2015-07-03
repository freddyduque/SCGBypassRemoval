
import com.jcraft.jsch.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.util.logging.Level;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author f80021351
 */
public class main_thread extends Thread {

    String msg, RetLine;
    Connection con;
    String[][] ModulesInfo = null;
    Socket check_sock;
    ServerSocket check_serv = null;
    DataInputStream check_input = null;
    ObjectOutputStream check_output = null;
    JSch SSH = null;
    Session Conex = null;

    public void run() {
        try {
            check_serv = new ServerSocket(1234);
            check_sock = check_serv.accept();
            check_output = new ObjectOutputStream(check_sock.getOutputStream());
            check_input = new DataInputStream(check_sock.getInputStream());
            String User = "oam", IP = "10.164.48.45", Pass = "oam", ModName = "OAM";

            try {
                SSH = new JSch();
                Conex = SSH.getSession(User, IP, 22);
                Conex.setPassword(Pass);
                Conex.setConfig("StrictHostKeyChecking", "no");
                Conex.setTimeout(10000);
            } catch (JSchException ex) {
                System.out.println("error intentando asignar los valores para la conexion");
            }
            while (true) {
                try {
                    if (Conex.isConnected() != true) {
                        Conex.connect();
                    }
                    if (check_input.readInt() == 0) {
                        RetLine = ExecCmd(Conex, "./SCGMS/MAIN_BYPASS.sh");
                        String[] result = RetLine.split("\\|");
                        check_output.writeObject(result);
                    }
                } catch (Exception sd) {
                    check_sock.close();
                    check_serv.close();
                    break;
                }
            }
        } catch (Exception e) {
            try {
                check_sock.close();
                check_serv.close();
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(main_thread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private String ExecCmd(Session Conex, String cmd) throws IOException {
        String OutputText = "";
        try {
            Channel ConexChan = Conex.openChannel("exec");
            ((ChannelExec) ConexChan).setCommand(cmd);
            ConexChan.setInputStream(null);

            ((ChannelExec) ConexChan).setErrStream(System.err);
            InputStream InnerStream = ConexChan.getInputStream();

            ConexChan.connect();

            OutputText = StreamToString(InnerStream);
            ConexChan.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
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
