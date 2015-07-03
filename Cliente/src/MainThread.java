
import java.awt.Color;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author Freddy Duque f80021351
 */
public class MainThread {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    JProgressBar ProgressBar = null;
    int k = 0;
    ObjectInputStream obj_input = null;
    DataOutputStream obj_output = null;
    NodeInfo[] rm_bypass_btns = null;
    JLabel[][] module_info_lbls = null;

    MainThread(String AppName, JProgressBar jProgressBar1, DataOutputStream output, ObjectInputStream input, NodeInfo[] rm_bypass, JLabel[][] module_info) {
        ProgressBar = jProgressBar1;
        obj_input = input;
        obj_output = output;
        rm_bypass_btns = rm_bypass;
        module_info_lbls = module_info;
    }

    public void runMainThread() {
        final Runnable MainThread = new Runnable() {

            public void run() {
                if (k == 0) {
                    try {
                        ProgressBar.setString("Consultando Módulos...");
                        for (int i = 0; i < rm_bypass_btns.length; i++) {
                            rm_bypass_btns[i].setVisible(false);
                            module_info_lbls[i][0].setIcon(new ImageIcon(getClass().getResource("/gray.png")));
                        }
                        obj_output.writeInt(0);
                        String[] info_returned = (String[]) obj_input.readObject();
                        check_module(info_returned);
                        for (int i = 0; i < rm_bypass_btns.length; i++) {
                            if (rm_bypass_btns[i].getValue() == 0) {
                                rm_bypass_btns[i].setVisible(false);
                                module_info_lbls[i][0].setIcon(new ImageIcon(getClass().getResource("/green.png")));
                            } else {
                                if (rm_bypass_btns[i].getValue() == 1) {
                                    rm_bypass_btns[i].setVisible(true);
                                    module_info_lbls[i][0].setIcon(new ImageIcon(getClass().getResource("/red.png")));
                                } else {
                                    rm_bypass_btns[i].setVisible(false);
                                    module_info_lbls[i][0].setIcon(new ImageIcon(getClass().getResource("/gray.png")));
                                }
                            }
                        }
                    } catch (ClassNotFoundException ex) {
                        System.out.println("error por aqui 1");
                        Logger.getLogger(MainThread.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        //JOptionPane.showMessageDialog(null, "No se logró establecer conexión con los módulos", "Error de conexión", JOptionPane.WARNING_MESSAGE);
                        Logger.getLogger(MainThread.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                ProgressBar.setValue(k);
                ProgressBar.setString(k + " seg");
                if (k == 59) {
                    k = 0;
                } else {
                    k++;
                }
            }

            private void check_module(String[] info_returned) {
                String mod_name;
                String[] values;
                int flag;
                for (int i = 0; i < info_returned.length - 1; i++) {
                    values = info_returned[i].split("\\=");
                    flag = Integer.parseInt(values[1]);
                    mod_name = values[0].replace("_", " ");
                    for (int j = 0; j < rm_bypass_btns.length; j++) {
                        if (rm_bypass_btns[j].getModName().compareTo(mod_name) == 0) {
                            rm_bypass_btns[j].setValue(flag);
                            break;
                        }
                    }
                }
            }
        };
        scheduler.scheduleAtFixedRate(MainThread, 0, 1L, TimeUnit.SECONDS);
    }
}