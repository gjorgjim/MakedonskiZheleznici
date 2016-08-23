package mk.com.mztransportad.makedonskizheleznici.Helpers;

/**
 * Created by gjorgjim on 8/23/16.
 */
public class Price {
    private String start;
    private String end;
    private int oneway;
    private int twoway;

    public Price(String start, String end, int oneway, int twoway) {
        this.start = start;
        this.end = end;
        this.oneway = oneway;
        this.twoway = twoway;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public int getOneway() {
        return oneway;
    }

    public void setOneway(int oneway) {
        this.oneway = oneway;
    }

    public int getTwoway() {
        return twoway;
    }

    public void setTwoway(int twoway) {
        this.twoway = twoway;
    }
}
