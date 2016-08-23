package mk.com.mztransportad.makedonskizheleznici.Helpers;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by gjorgjim on 7/6/16.
 */
public class Station {
    private String name;
    private Calendar time;

    public Station(Calendar time, String name) {
        this.time = time;
        this.name = name;
    }

    public Calendar getTime() {
        return time;
    }

    public String getName() {
        return name;
    }
}
