package mk.com.mztransportad.makedonskizheleznici.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.List;

import mk.com.mztransportad.makedonskizheleznici.Adapters.TimetableAdapter;
import mk.com.mztransportad.makedonskizheleznici.Helpers.Data;
import mk.com.mztransportad.makedonskizheleznici.Helpers.ParentTrain;
import mk.com.mztransportad.makedonskizheleznici.R;

/**
 * Created by gjorgjim on 7/4/16.
 */
public class TimetableFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.timetable, container, false);

        ExpandableListView listView = (ExpandableListView)view.findViewById(R.id.timetableListView);
        List<ParentTrain> list = Data.getParentTrain();
        TimetableAdapter adapter = new TimetableAdapter(getContext(), list);
        listView.setAdapter(adapter);

        return view;
    }
}
