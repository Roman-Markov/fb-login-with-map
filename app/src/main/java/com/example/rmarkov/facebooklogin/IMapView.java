package com.example.rmarkov.facebooklogin;

import com.google.android.gms.maps.GoogleMap;

public interface IMapView {

    void setMap(GoogleMap googleMap);

    void updateLocationUi();
}
