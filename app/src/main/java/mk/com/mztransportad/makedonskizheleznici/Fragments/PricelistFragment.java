package mk.com.mztransportad.makedonskizheleznici.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import mk.com.mztransportad.makedonskizheleznici.Adapters.SeePricesAdapter;
import mk.com.mztransportad.makedonskizheleznici.Helpers.Data;
import mk.com.mztransportad.makedonskizheleznici.MyAdapter;
import mk.com.mztransportad.makedonskizheleznici.R;

/**
 * Created by gjorgjim on 7/4/16.
 */
public class PricelistFragment extends Fragment {

    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pricelist, container, false);
        listView = (ListView)view.findViewById(R.id.listView);
        SeePricesAdapter adapter = new SeePricesAdapter(getContext(), Data.getPriceList());
        listView.setAdapter(adapter);


        return view;
    }

}
