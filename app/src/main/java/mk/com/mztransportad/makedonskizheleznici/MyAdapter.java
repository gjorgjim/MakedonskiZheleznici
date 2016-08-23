package mk.com.mztransportad.makedonskizheleznici;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import mk.com.mztransportad.makedonskizheleznici.Fragments.PlanTravelFragment;
import mk.com.mztransportad.makedonskizheleznici.Fragments.PricelistFragment;
import mk.com.mztransportad.makedonskizheleznici.Fragments.TimetableFragment;

/**
 * Created by gjorgjim on 7/4/16.
 */
public class MyAdapter extends FragmentStatePagerAdapter {
    public MyAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if(position == 0 ) {
             fragment = new PlanTravelFragment();
        } else if(position == 1) {
            fragment = new TimetableFragment();
        } else {
            fragment = new PricelistFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0 ) {
            return "Планирај патување";
        } else if(position == 1) {
            return "Возен ред";
        } else {
            return "Ценовник";
        }
    }

}
