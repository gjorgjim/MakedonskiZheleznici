package mk.com.mztransportad.makedonskizheleznici;

import android.app.FragmentManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;


import com.roomorama.caldroid.CaldroidFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import mk.com.mztransportad.makedonskizheleznici.Fragments.FreeDaysFragment;
import mk.com.mztransportad.makedonskizheleznici.Fragments.FreeTravelFragment;
import mk.com.mztransportad.makedonskizheleznici.Fragments.PatnichkiFragment;
import mk.com.mztransportad.makedonskizheleznici.Fragments.TovarenFragment;
import mk.com.mztransportad.makedonskizheleznici.Helpers.HelperFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private ViewPager viewPager = null;
    private NavigationView navigationView = null;
    private RelativeLayout pagerStripLayout;
    private CalendarView calendarView;

    private boolean undo = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pagerStripLayout = (RelativeLayout)findViewById(R.id.pagerStripLayout);

        PagerTabStrip pagerTabStrip = (PagerTabStrip) findViewById(R.id.title);
        pagerTabStrip.setTabIndicatorColor(Color.argb(255, 255, 193, 7));

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager)findViewById(R.id.pager);
        viewPager.setOffscreenPageLimit(3);
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        viewPager.setAdapter(new MyAdapter(fragmentManager));

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    if(pagerStripLayout.getVisibility() == RelativeLayout.GONE) {
                        pagerStripLayout.setVisibility(RelativeLayout.VISIBLE);
                        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_white_48dp);
                        HelperFragment fragment = new HelperFragment();
                        android.support.v4.app.FragmentTransaction fragmentTransaction =
                                getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, fragment);
                        fragmentTransaction.commit();
                    } else {
                        drawer.openDrawer(GravityCompat.START);
                    }
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if(pagerStripLayout.getVisibility() == RelativeLayout.GONE) {
                pagerStripLayout.setVisibility(RelativeLayout.VISIBLE);
                getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_white_48dp);
                HelperFragment fragment = new HelperFragment();
                android.support.v4.app.FragmentTransaction fragmentTransaction =
                        getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.commit();
            } else {
                super.onBackPressed();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.patnichki) {
            PatnichkiFragment fragment = new PatnichkiFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment, "PATNICHKI");
            fragmentTransaction.commit();
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
            pagerStripLayout.setVisibility(RelativeLayout.GONE);
            //frameLayout.setVisibility(FrameLayout.GONE);
        } else if (id == R.id.tovaren) {
            TovarenFragment fragment = new TovarenFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment, "TOVAREN");
            fragmentTransaction.commit();
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
            pagerStripLayout.setVisibility(RelativeLayout.GONE);
            //frameLayout.setVisibility(FrameLayout.GONE);

        } else if (id == R.id.freedays) {
            CaldroidFragment caldroidFragment = new CaldroidFragment();
            Bundle args = new Bundle();
            Calendar cal = Calendar.getInstance();
            args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
            args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
            args.putInt(CaldroidFragment.THEME_RESOURCE, com.caldroid.R.style.CaldroidDefault);
            caldroidFragment.setArguments(args);
            ColorDrawable color = new ColorDrawable(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));
            caldroidFragment.setBackgroundDrawableForDate(color, new Date(cal.get(Calendar.MILLISECOND)));
            caldroidFragment.refreshView();
            FragmentTransaction t = getSupportFragmentManager().beginTransaction();
            t.replace(R.id.fragment_container, caldroidFragment);
            t.commit();
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
            pagerStripLayout.setVisibility(RelativeLayout.GONE);
            //frameLayout.setVisibility(FrameLayout.GONE);

        } else if (id == R.id.freetravels) {
            FreeTravelFragment fragment = new FreeTravelFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment, "FREETRAVEL");
            fragmentTransaction.commit();
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
            pagerStripLayout.setVisibility(RelativeLayout.GONE);
            //frameLayout.setVisibility(FrameLayout.GONE);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
