package nerdcastle.datatheft;

import com.firebase.jobdispatcher.JobTrigger;
import com.firebase.jobdispatcher.Trigger;

/**
 * Created by Nazmul on 10-Sep-17.
 */

public class JobDispatcherUtils {
    public static JobTrigger periodicTrigger(int frequency, int tolerance) {
        return Trigger.executionWindow(frequency - tolerance, frequency);
    }
}
