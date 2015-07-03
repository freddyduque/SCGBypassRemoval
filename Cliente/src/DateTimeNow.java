
import java.text.SimpleDateFormat;
import java.util.Calendar;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author Freddy Duque f80021351
 */
public class DateTimeNow {

    String CurrentTime;
    Calendar cal;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), sdf2 = new SimpleDateFormat("S");

    public String getCurrentTime() {
        cal = Calendar.getInstance();
        CurrentTime = sdf.format(cal.getTime());
        return CurrentTime;
    }
}
