package mk.com.mztransportad.makedonskizheleznici.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.zip.Inflater;

import mk.com.mztransportad.makedonskizheleznici.Helpers.Train;
import mk.com.mztransportad.makedonskizheleznici.R;

/**
 * Created by gjorgjim on 7/10/16.
 */
public class SeeTrainsAdapter extends BaseAdapter {
    private List<Train> list = null;
    private Context context;

    public SeeTrainsAdapter(Context context, List<Train> list) {
        this.context = context;
        this.list = new ArrayList<Train>();
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.list_view_layout, null);
        Train currTrain = list.get(position);
        TextView startStation = (TextView)view.findViewById(R.id.textViewStartStation);
        TextView startHour = (TextView)view.findViewById(R.id.textViewStartHour);
        TextView endStation = (TextView)view.findViewById(R.id.textViewEndStation);
        TextView endHour = (TextView)view.findViewById(R.id.textViewEndHour);

        startStation.setText(currTrain.getStart().getName() + " ");
        int startHours = currTrain.getStart().getTime().get(Calendar.HOUR_OF_DAY);
        int startMinutes = currTrain.getStart().getTime().get(Calendar.MINUTE);
        if(startHours <10) {
            if(startMinutes<10) {
                startHour.setText("0" + startHours + ":0" + startMinutes+ "h.");
            } else {
                startHour.setText("0" + startHours + ":" + startMinutes+ "h.");
            }
        } else {
            if(startMinutes < 10) {
                startHour.setText(startHours + ":0" + startMinutes+ "h.");
            } else {
                startHour.setText(startHours + ":" + startMinutes+ "h.");
            }
        }


        endStation.setText(currTrain.getEnd().getName() + " ");
        int endHours = currTrain.getEnd().getTime().get(Calendar.HOUR_OF_DAY);
        int endMinutes = currTrain.getEnd().getTime().get(Calendar.MINUTE);
        if(endHours <10) {
            if(endMinutes<10) {
                endHour.setText("0" + endHours + ":0" + endMinutes+ "h.");
            } else {
                endHour.setText("0" + endHours + ":" + endMinutes+ "h.");
            }
        } else {
            if(endMinutes < 10) {
                endHour.setText(endHours + ":0" + endMinutes+ "h.");
            } else {
                endHour.setText(endHours + ":" + endMinutes+ "h.");
            }
        }

        return view;
    }
}
