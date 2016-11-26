package mk.com.mztransportad.makedonskizheleznici.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import mk.com.mztransportad.makedonskizheleznici.Adapters.SeeTrainsAdapter;
import mk.com.mztransportad.makedonskizheleznici.Helpers.Data;
import mk.com.mztransportad.makedonskizheleznici.Helpers.Price;
import mk.com.mztransportad.makedonskizheleznici.Helpers.Station;
import mk.com.mztransportad.makedonskizheleznici.Helpers.Train;
import mk.com.mztransportad.makedonskizheleznici.R;

/**
 * Created by gjorgjim on 7/4/16.
 */
public class PlanTravelFragment extends Fragment {
    private Spinner spinner1;
    private Spinner spinner2;
    private ArrayAdapter<CharSequence> adapter;
    private Button seeTrains;
    private CheckBox nextTrains;
    private CheckBox allTrains;
    private boolean noNextTrains = false;
    private RelativeLayout mainLayout;
    private RelativeLayout firstLayout;
    private RelativeLayout secondLayout;
    private RelativeLayout footer;
    private ListView nextTrainsListView;
    private ListView allTrainsListView;
    private TextView nextTrainsTextView;
    private TextView allTrainsTextView;
    private ImageView back;
    private TextView pricetext;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.plan_travel, container, false);

        mainLayout = (RelativeLayout)view.findViewById(R.id.mainLayout);
        firstLayout =(RelativeLayout)view.findViewById(R.id.firstLayout);
        secondLayout = (RelativeLayout)view.findViewById(R.id.secondLayout);
        footer = (RelativeLayout)view.findViewById(R.id.footer);
        secondLayout.setVisibility(RelativeLayout.GONE);
        footer.setVisibility(RelativeLayout.GONE);

        back = (ImageView)view.findViewById(R.id.backImg);

        pricetext = (TextView)view.findViewById(R.id.priceText);

        nextTrainsListView = (ListView)view.findViewById(R.id.listViewNextTrains);
        allTrainsListView = (ListView)view.findViewById(R.id.listViewAllTrains);
        nextTrainsTextView = (TextView)view.findViewById(R.id.textViewNextTrains);
        allTrainsTextView = (TextView)view.findViewById(R.id.textViewAllTrains);

        spinner1 = (Spinner)view.findViewById(R.id.spinner);
        spinner2 = (Spinner)view.findViewById(R.id.spinner2);

        adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.stations_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);

        nextTrains = (CheckBox)view.findViewById(R.id.checkBox1);
        allTrains = (CheckBox)view.findViewById(R.id.checkBox2);

        seeTrains = (Button)view.findViewById(R.id.seeTrains);
        seeTrains.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String start = spinner1.getSelectedItem().toString().toLowerCase();
                String end = spinner2.getSelectedItem().toString().toLowerCase();

                List<Train> planList = new ArrayList<Train>();
                List<Train> nextTrainlist = new ArrayList<Train>();

                if (start.equals(end)) {
                    Toast.makeText(getActivity(), "Ве молиме одберете различна почетна и последна станица",
                            Toast.LENGTH_LONG).show();
                } else {
                    if (nextTrains.isChecked() || allTrains.isChecked()) {
                        List<Train> trainList = Data.getTrainList();
                        for (int i = 0; i < trainList.size(); i++) {
                            for (int j = 0; j < trainList.get(i).getStationList().size(); j++) {
                                if (trainList.get(i).getStationList().get(j).getName().toLowerCase().equals(start)) {
                                    Station startStation = trainList.get(i).getStationList().get(j);
                                    for (int k = j + 1; k < trainList.get(i).getStationList().size(); k++) {
                                        if (trainList.get(i).getStationList().get(k).getName().toLowerCase().equals(end)) {
                                            planList.add(new Train(null, startStation, trainList.get(i).getStationList().get(k)));
                                        }
                                    }
                                }
                            }
                        }
                        if (nextTrains.isChecked()) {
                            Calendar c = Calendar.getInstance();
                            int hours = c.get(Calendar.HOUR_OF_DAY);
                            int minutes = c.get(Calendar.MINUTE);
                            for (int i = 0; i < planList.size(); i++) {
                                if (planList.get(i).getStart().getTime().get(Calendar.HOUR_OF_DAY) > hours) {
                                    nextTrainlist.add(planList.get(i));
                                } else if (planList.get(i).getStart().getTime().get(Calendar.HOUR_OF_DAY) == hours) {
                                    if (planList.get(i).getStart().getTime().get(Calendar.MINUTE) > minutes) {
                                        nextTrainlist.add(planList.get(i));
                                    }
                                }
                            }
                        }
                        firstLayout.setVisibility(RelativeLayout.GONE);
                        if (nextTrains.isChecked() && allTrains.isChecked()) {
                            if (planList.isEmpty()) {
                                nextTrainsTextView.setText("Нема возни линии.");
                                nextTrainsTextView.setVisibility(TextView.VISIBLE);
                                nextTrainsListView.setVisibility(ListView.GONE);
                                allTrainsListView.setVisibility(ListView.GONE);
                                allTrainsTextView.setVisibility(TextView.GONE);
                            } else {
                                if (nextTrainlist.isEmpty()) {
                                    nextTrainsTextView.setText("Нема наредни возни линии.");
                                    nextTrainsTextView.setVisibility(TextView.VISIBLE);
                                    nextTrainsListView.setVisibility(ListView.GONE);
                                    allTrainsTextView.setText("Возни линии во текот на денот:");
                                    allTrainsTextView.setVisibility(TextView.VISIBLE);
                                    SeeTrainsAdapter allAdapter = new SeeTrainsAdapter(getContext(), planList);
                                    allTrainsListView.setAdapter(allAdapter);
                                    allTrainsListView.setVisibility(ListView.VISIBLE);
                                } else {
                                    nextTrainsTextView.setText("Наредни возни линии:");
                                    nextTrainsTextView.setVisibility(TextView.VISIBLE);
                                    SeeTrainsAdapter nextAdapter = new SeeTrainsAdapter(getContext(), nextTrainlist);
                                    nextTrainsListView.setAdapter(nextAdapter);
                                    nextTrainsListView.setVisibility(ListView.VISIBLE);
                                    allTrainsTextView.setText("Возни линии во текот на денот:");
                                    allTrainsTextView.setVisibility(TextView.VISIBLE);
                                    SeeTrainsAdapter allAdapter = new SeeTrainsAdapter(getContext(), planList);
                                    allTrainsListView.setAdapter(allAdapter);
                                    allTrainsListView.setVisibility(ListView.VISIBLE);
                                }
                            }
                        } else {
                            if (nextTrains.isChecked() && !allTrains.isChecked()) {
                                allTrainsListView.setVisibility(ListView.GONE);
                                allTrainsTextView.setVisibility(TextView.GONE);
                                if (nextTrainlist.isEmpty()) {
                                    nextTrainsTextView.setText("Нема наредни возни линии.");
                                    nextTrainsTextView.setVisibility(TextView.VISIBLE);
                                    nextTrainsListView.setVisibility(ListView.GONE);
                                } else {
                                    nextTrainsTextView.setText("Наредни возни линии:");
                                    nextTrainsTextView.setVisibility(TextView.VISIBLE);
                                    SeeTrainsAdapter nextAdapter = new SeeTrainsAdapter(getContext(), nextTrainlist);
                                    nextTrainsListView.setAdapter(nextAdapter);
                                    nextTrainsListView.setVisibility(ListView.VISIBLE);
                                }
                            }
                            if (!nextTrains.isChecked() && allTrains.isChecked()) {
                                nextTrainsTextView.setVisibility(TextView.GONE);
                                nextTrainsListView.setVisibility(ListView.GONE);
                                if (planList.isEmpty()) {
                                    allTrainsTextView.setText("Нема возни линии.");
                                    allTrainsTextView.setVisibility(TextView.VISIBLE);
                                    allTrainsListView.setVisibility(ListView.GONE);
                                } else {
                                    allTrainsTextView.setText("Возни линии во текот на денот:");
                                    allTrainsTextView.setVisibility(TextView.VISIBLE);
                                    SeeTrainsAdapter allAdapter = new SeeTrainsAdapter(getContext(), planList);
                                    allTrainsListView.setAdapter(allAdapter);
                                    allTrainsListView.setVisibility(ListView.VISIBLE);
                                }
                            }
                        }
                        int oneprice = 0;
                        int twoprice = 0;
                        List<Price> priceList = Data.getPriceList();
                        for(int i=0; i<priceList.size(); i++) {
                            if(priceList.get(i).getStart().toLowerCase().equals(start.toLowerCase())) {
                                if(priceList.get(i).getEnd().toLowerCase().equals(end.toLowerCase())) {
                                    oneprice = priceList.get(i).getOneway();
                                    twoprice = priceList.get(i).getTwoway();
                                    break;
                                }
                            }
                            if(priceList.get(i).getEnd().toLowerCase().equals(start.toLowerCase())) {
                                if(priceList.get(i).getStart().toLowerCase().equals(end.toLowerCase())) {
                                    oneprice = priceList.get(i).getOneway();
                                    twoprice = priceList.get(i).getTwoway();
                                    break;
                                }
                            }
                        }
                        if(!planList.isEmpty()) {
                            pricetext.setText("Цена: " + oneprice + ",00/" + twoprice + ",00 ден.");
                        }
                        if(nextTrainlist.size()==1) {
                            nextTrainsListView.setMinimumHeight(50);
                        }
                        secondLayout.setVisibility(RelativeLayout.VISIBLE);
                        footer.setVisibility(RelativeLayout.VISIBLE);
                    } else {
                        Toast.makeText(getActivity(), "Ве молиме селектирајте една од опциите",
                                Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(secondLayout.getVisibility() == RelativeLayout.VISIBLE) {
                    secondLayout.setVisibility(RelativeLayout.GONE);
                    footer.setVisibility(RelativeLayout.GONE);
                    firstLayout.setVisibility(RelativeLayout.VISIBLE);
                }
            }
        });


        return view;
    }

}
