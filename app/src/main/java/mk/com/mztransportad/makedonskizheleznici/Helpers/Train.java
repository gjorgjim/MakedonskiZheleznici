package mk.com.mztransportad.makedonskizheleznici.Helpers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gjorgjim on 7/6/16.
 */
public class Train {
    private Station start;
    private Station end;
    private List<Station> stationList;

    public Train(List<Station> stationList, Station start, Station end) {
        this.stationList = new ArrayList<Station>();
        this.stationList = stationList;
        this.start = start;
        this.end = end;
    }

    public Station getEnd() {
        return end;
    }

    public List<Station> getStationList() {
        return stationList;
    }

    public Station getStart() {
        return start;
    }
}
