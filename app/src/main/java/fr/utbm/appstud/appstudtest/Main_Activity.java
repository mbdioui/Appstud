package fr.utbm.appstud.appstudtest;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class Main_Activity extends AppCompatActivity {

    private Fragment fragment;
    private ActionBar actionBar;
    private FragmentManager fragmentManager;
    private Menu menu;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            return selectFragment(item);
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        actionBar=getSupportActionBar();
        actionBar.hide();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation_bottom);
        menu = navigation.getMenu();
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        selectFragment(menu.getItem(0));

    }


    private boolean selectFragment(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_map:
                fragment = new CardFragment();
                break;
            case R.id.navigation_list:
                fragment = new ListFragment();
                break;
        }
        fragmentManager = getFragmentManager();
        if (fragmentManager != null) {
            FragmentTransaction ft = fragmentManager.beginTransaction();
            if (ft != null) {
                ft.replace(R.id.main_activity_fragment, fragment);
                ft.commit();
            }
        }
        return false;
    }


}
