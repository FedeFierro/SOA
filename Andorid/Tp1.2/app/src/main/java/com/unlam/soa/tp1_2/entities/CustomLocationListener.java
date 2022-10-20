package com.unlam.soa.tp1_2.entities;

import android.location.Location;
import android.location.LocationListener;

import androidx.annotation.NonNull;

import com.google.android.gms.maps.model.LatLng;
import com.unlam.soa.tp1_2.contract.Contract;

public class CustomLocationListener implements LocationListener {
    private final Contract.LocationCallBack locationCallBack;
    public CustomLocationListener(Contract.LocationCallBack locationCallBack) {
        this.locationCallBack =locationCallBack;
    }
    @Override
    public void onLocationChanged(@NonNull Location location) {
        LatLng latLong = new LatLng(location.getLatitude(),location.getLongitude());
        locationCallBack.callBackLocation(latLong);
    }
}
