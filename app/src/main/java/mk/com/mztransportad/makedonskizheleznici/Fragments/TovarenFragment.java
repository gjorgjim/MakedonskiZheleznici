package mk.com.mztransportad.makedonskizheleznici.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mk.com.mztransportad.makedonskizheleznici.R;

/**
 * Created by gjorgjim on 9/1/16.
 */
public class TovarenFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tovaren, container, false);


        return view;
    }
}
