package fr.utbm.appstud.appstudtest;

import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;

import static android.app.Activity.RESULT_OK;


public class CardFragment extends Fragment implements OnMapReadyCallback {

    private UiSettings mapUIsettings;
    private View view;
    private GoogleMap map;


    public CardFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_card_layout, container, false);
        setupmap();
        return view;
    }
    //geting map fragment
    private void setupmap() {
        MapFragment mapView = (MapFragment) this.getChildFragmentManager().findFragmentById(R.id.MapHere);
        mapView.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        mapUIsettings = googleMap.getUiSettings();
        if (Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP_MR1)
        if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions( new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION
                    , android.Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
        }
        else
            googleMap.setMyLocationEnabled(true);

        mapUIsettings.setZoomControlsEnabled(true);
    }
    //after accepting persmissions
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) {
            if (resultCode != RESULT_OK)
                Toast.makeText(getActivity(), "GPS est déactivé", Toast.LENGTH_LONG).show();
            else {
                if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                        ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    setupmap();
                    map.setMyLocationEnabled(true);
                }
            }
        }
    }
}
