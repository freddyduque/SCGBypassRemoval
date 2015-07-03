
import java.io.BufferedWriter;
import java.io.IOException;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author Freddy Duque FWX53681
 */
public class LogInformation {

    DateTimeNow Timer = new DateTimeNow();
    BufferedWriter BuffWri;

    public LogInformation(BufferedWriter out) {
        BuffWri = out;
    }

    public void information(String Message) throws IOException {
        BuffWri.write("[" + Timer.getCurrentTime() + "] [INFO] " + Message + "\n");
        BuffWri.flush();
    }

    public void warning(String Message) throws IOException {
        BuffWri.write("[" + Timer.getCurrentTime() + "] [WARN] " + Message + "\n");
        BuffWri.flush();
    }

    public void critical(String Message) throws IOException {
        BuffWri.write("[" + Timer.getCurrentTime() + "] [CRIT] " + Message + "\n");
        BuffWri.flush();
    }

    public void successful(String Message) throws IOException {
        BuffWri.write("[" + Timer.getCurrentTime() + "] [SUCC] " + Message + "\n");
        BuffWri.flush();
    }

    public void newline() throws IOException {
        BuffWri.write("\n");
        BuffWri.flush();
    }

    public void welcome(String ModName, String IP) throws IOException {
        BuffWri.write("================= [" + Timer.getCurrentTime() + " Starting Log File for Module " + ModName + " IP: " + IP + "] =================\n");
        BuffWri.flush();
    }
}