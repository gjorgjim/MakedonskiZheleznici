package mk.com.mztransportad.makedonskizheleznici.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import mk.com.mztransportad.makedonskizheleznici.Helpers.ParentTrain;
import mk.com.mztransportad.makedonskizheleznici.Helpers.Train;
import mk.com.mztransportad.makedonskizheleznici.R;

/**
 * Created by gjorgjim on 10/20/16.
 */
public class TimetableAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<ParentTrain> list;

    public TimetableAdapter(Context context, List<ParentTrain> list) {
        this.context = context;
        this.list = new ArrayList<>();
        this.list = list;
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).getList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition).getList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.parent_train_layout, null);
        }

        ImageView groupHolder = (ImageView)convertView.findViewById(R.id.imageholder);
        if (isExpanded) {
            groupHolder.setImageResource(R.drawable.arrow_down);
        } else {
            groupHolder.setImageResource(R.drawable.arrow_up);
        }

        TextView startEnd = (TextView) convertView.findViewById(R.id.startEnd);
        startEnd.setText(list.get(groupPosition).getStart().getName() + " - " + list.get(groupPosition).getEnd().getName());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.child_train_layout, null);
        }

        TextView startingHour = (TextView)convertView.findViewById(R.id.startingHourTextView);
        TextView endingHour = (TextView)convertView.findViewById(R.id.endingHourTextView);
        int startinghours = list.get(groupPosition).getList().get(childPosition).getStart().getTime().get(Calendar.HOUR_OF_DAY);
        int startingminutes = list.get(groupPosition).getList().get(childPosition).getStart().getTime().get(Calendar.MINUTE);
        if(startinghours < 10) {
            if(startingminutes<10) {
                startingHour.setText("0" + startinghours + ":0" + startingminutes+ "h.");
            } else {
                startingHour.setText("0" + startinghours + ":" + startingminutes+ "h.");
            }
        } else {
            if(startingminutes < 10) {
                startingHour.setText(startinghours + ":0" + startingminutes+ "h.");
            } else {
                startingHour.setText(startinghours + ":" + startingminutes+ "h.");
            }
        }
        int endinghours = list.get(groupPosition).getList().get(childPosition).getEnd().getTime().get(Calendar.HOUR_OF_DAY);
        int endingminutes = list.get(groupPosition).getList().get(childPosition).getEnd().getTime().get(Calendar.MINUTE);
        if(endinghours < 10) {
            if(endingminutes < 10) {
                endingHour.setText("0" + endinghours + ":0" + endingminutes+ "h.");
            } else {
                endingHour.setText("0" + endinghours + ":" + endingminutes+ "h.");
            }
        } else {
            if(endingminutes < 10) {
                endingHour.setText(endinghours + ":0" + endingminutes+ "h.");
            } else {
                endingHour.setText(endinghours + ":" + endingminutes+ "h.");
            }
        }

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
