package com.unlam.soa.tp1_2.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.unlam.soa.tp1_2.R;
import com.unlam.soa.tp1_2.contract.Contract;
import com.unlam.soa.tp1_2.entities.CustomLatLng;
import com.unlam.soa.tp1_2.entities.CustomLocationListener;
import com.unlam.soa.tp1_2.entities.UnlamPolygon;

public class AndroidActivity extends AppCompatActivity implements Contract.LocationCallBack {
    ConstraintLayout layoutMap;
    UnlamPolygon unlamPolygon;
    int width;
    int height;
    int orientation;
    CustomLocationListener locationListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android);
        checkImage();
        this.unlamPolygon =new UnlamPolygon();

        locationListener = new CustomLocationListener(this);
        layoutMap = findViewById(R.id.layoutMap);
        this.width = layoutMap.getWidth();
        this.height = layoutMap.getHeight();
        //import android.location.LocationManager;
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setSelectedItemId(R.id.android);
        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.arduino:
                        startActivity(new Intent(getApplicationContext(), ArduinoActivity.class));
                        overridePendingTransition(0,0);

                        return true;
                    case R.id.android:
                        return true;
                }
                return false;
            }
        });

    }
    private void checkImage() {
        this.orientation = getResources().getConfiguration().orientation;
        ImageView imgMaps =findViewById(R.id.imgView);
        switch (this.orientation){
            case Configuration.ORIENTATION_LANDSCAPE:
                imgMaps.setImageResource(R.drawable.mapa_unlam_landscape);
                break;
            case Configuration.ORIENTATION_PORTRAIT:
                imgMaps.setImageResource(R.drawable.mapa_unlam_portrait);
                break;
        }
    }

    @Override
    public void callBackLocation(LatLng latLng) {
        if(!unlamPolygon.isReady){
            return;
        }
        boolean inside = unlamPolygon.pointInside(latLng);
        if(inside){
            this.width = layoutMap.getWidth();
            this.height = layoutMap.getHeight();
            CustomLatLng newLatLng = unlamPolygon.transForm(this.width,this.height,latLng);
            updateLocation(newLatLng);
        }
    }
    private void updateLocation(CustomLatLng latLng){
        int x = (int) latLng.longitude;
        int y = (int) latLng.latitude;
        ImageView imgPin = findViewById(R.id.imgPin);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) imgPin.getLayoutParams();
        layoutParams.leftMargin=x;
        layoutParams.topMargin=y;
    }
}