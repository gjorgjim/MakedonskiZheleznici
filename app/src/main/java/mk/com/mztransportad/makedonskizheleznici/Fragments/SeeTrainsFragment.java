package mk.com.mztransportad.makedonskizheleznici.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import mk.com.mztransportad.makedonskizheleznici.Adapters.SeeTrainsAdapter;
import mk.com.mztransportad.makedonskizheleznici.Helpers.Train;
import mk.com.mztransportad.makedonskizheleznici.R;

/**
 * Created by gjorgjim on 7/10/16.
 */
public class SeeTrainsFragment extends Fragment {
    private List<Train> planList = null;
    private List<Train> nextTrainList;

    private ListView nextTrainsListView;
    private ListView allTrainsListView;
    private TextView nextTrainsTextView;
    private TextView allTrainsTextView;
    private boolean noNextTrains;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.see_trains_layout, container, false);

        nextTrainsListView = (ListView)view.findViewById(R.id.listViewNextTrains);
        allTrainsListView = (ListView)view.findViewById(R.id.listViewAllTrains);
        nextTrainsTextView = (TextView)view.findViewById(R.id.textViewNextTrains);
        allTrainsTextView = (TextView)view.findViewById(R.id.textViewAllTrains);
        Bundle bundle = getArguments();
        if(bundle.getSerializable("planList")!=null && bundle.getSerializable("nextTrainList")!=null) {
            planList = (List<Train>) bundle.getSerializable("planList");
            nextTrainList = (List<Train>) bundle.getSerializable("nextTrainList");
            noNextTrains = bundle.getBoolean("noNextTrains");
        }

        if(!nextTrainList.isEmpty()) {
            SeeTrainsAdapter nextAdapter = new SeeTrainsAdapter(getContext(), nextTrainList);
            nextTrainsListView.setAdapter(nextAdapter);
        } else {
            if(noNextTrains == true) {
                nextTrainsTextView.setText("Нема наредни возни линии.");
            } else {
                nextTrainsTextView.setText("");
            }
        }
        if(!planList.isEmpty()) {
            SeeTrainsAdapter allAdapter = new SeeTrainsAdapter(getContext(), planList);
            allTrainsListView.setAdapter(allAdapter);
        } else {
            allTrainsTextView.setText("");
        }

        return view;
    }
}
