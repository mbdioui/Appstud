package fr.utbm.appstud.appstudtest;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class NavigationlistFragment extends Fragment {

   private View view;

    public NavigationlistFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setuplist();
        view =inflater.inflate(R.layout.fragment_list_layout, container, false);
        return view;
    }

    private void setuplist() {

    }


}
