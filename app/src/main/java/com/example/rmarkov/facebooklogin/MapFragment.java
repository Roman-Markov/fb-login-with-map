package com.example.rmarkov.facebooklogin;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import javax.inject.Inject;

public class MapFragment extends Fragment implements IMapView, OnMapReadyCallback {

    public final static String TAG = "MapFragment";

    private final int REQUEST_CODE_FOR_LOCATION_PERMISSIONS = 1000;

    MapView mMapView;
    private GoogleMap googleMap;
    @Inject
    MapViewPresenter presenter;

    private boolean isPermissionGranted = false;

    public static MapFragment getNewInstance() {
        return new MapFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        ((FbLoginApp) getContext().getApplicationContext()).getAppComponent().inject(this);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.map_layout, container, false);

        mMapView = (MapView) rootView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(presenter);
        return rootView;
    }

    @Override
    public void onStart() {
        presenter.attachView(this);
        super.onStart();
        mMapView.onStart();
    }

    @Override
    public void onStop() {
        presenter.detachView();
        super.onStop();
        mMapView.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    private void getDeviceLocation() {
        Log.d(TAG, "retrieving current location...");
//        try {
//            if (context.checkLocationPermission()) {
//                val locationResult = fusedLocationProviderClient.lastLocation
//                locationResult.addOnCompleteListener(object: OnCompleteListener<Location> {
//                    override fun onComplete(task: Task<Location>) {
//                        if (task.isSuccessful) {
//                            Log.d(TAG, "retrieving current location is success: ${task.result}")
//                            lastKnownLocation = task.result
//                            locationHolder.onDevicePositionChanged(lastKnownLocation!!.toLatLng())
//                        } else {
//                            Log.e(TAG, "Current location is null, exception: ", task.exception)
//                        }
//                    }
//                })
//            }
//        } catch (e: SecurityException) {
//            Log.e(TAG, "Exception: $e")
//        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }

    @Override
    public void setMap(GoogleMap googleMap) {
        this.googleMap = googleMap;
    }

    @Override
    public void updateLocationUi() {
        if (isPermissionGranted) {
            enableLocationUi();
        } else {
            disableLocationUi();
            getLocationPermission();
        }
    }

    @SuppressLint("MissingPermission")
    private void enableLocationUi() {
        if (isPermissionGranted) {
            googleMap.setMyLocationEnabled(true);
            googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        }
    }

    @SuppressLint("MissingPermission")
    private void disableLocationUi() {
        if (isPermissionGranted) {
            googleMap.setMyLocationEnabled(false);
            googleMap.getUiSettings().setMyLocationButtonEnabled(false);
        }
    }

    private void getLocationPermission() {
        if (ContextCompat.checkSelfPermission(
                getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(
                getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            isPermissionGranted = true;
            updateLocationUi();
        } else {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[] {android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION},
                    REQUEST_CODE_FOR_LOCATION_PERMISSIONS);
        }
    }
}
