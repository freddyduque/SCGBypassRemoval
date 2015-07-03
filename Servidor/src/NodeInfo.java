
import javax.swing.JButton;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author Freddy Duque FWX53681
 */
public class NodeInfo extends JButton {

    String ModuleName, ModulePass, ModuleIP;
    int Index, flag; //0=NO BYPASS, 1=BYPASS, 2=NONE

    public NodeInfo() {
    }

    public NodeInfo(String[] IncModuleInfo, int ind) {
        ModuleName = IncModuleInfo[3];
        ModuleIP = IncModuleInfo[2];
        ModulePass = IncModuleInfo[7];
        Index = ind;
    }

    public void setValue(int val) {
        flag = val;
    }

    public int getValue() {
        return flag;
    }

    public String getModName() {
        return ModuleName;
    }
}