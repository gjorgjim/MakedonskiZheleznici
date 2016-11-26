package mk.com.mztransportad.makedonskizheleznici.Helpers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gjorgjim on 10/20/16.
 */
public class ParentTrain {
    private Station start;
    private Station end;
    private List<Train> list;

    public ParentTrain(Station start, Station end, List<Train> list) {
        this.start = start;
        this.end = end;
        this.list = new ArrayList<>();
        this.list = list;
    }

    public Station getStart() {
        return start;
    }

    public void setStart(Station start) {
        this.start = start;
    }

    public Station getEnd() {
        return end;
    }

    public void setEnd(Station end) {
        this.end = end;
    }

    public List<Train> getList() {
        return list;
    }

    public void setList(List<Train> list) {
        this.list = new ArrayList<>();
        this.list = list;
    }
}
