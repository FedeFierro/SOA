package com.unlam.soa.tp1_2.entities;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.PolyUtil;

import java.util.ArrayList;
import java.util.List;

public class UnlamPolygon {
    private final List<LatLng> polygon = new ArrayList<>();
    private final LatLng east = new LatLng(-34.668405,-58.567452 );
    private final LatLng south =new LatLng(-34.671788,-58.562982);
    private final LatLng west = new LatLng(-34.669175,-58.560067 );
    private final  LatLng north =new LatLng(-34.665801,-58.564541 );
    private double sin;
    private double cos;
    private double distanceLng;
    private double distanceLat;
    public boolean isReady=false;

    public UnlamPolygon(){
        polygon.add(east);
        polygon.add(south);
        polygon.add(west);
        polygon.add(north);
        polygon.add(east);
        this.buildTransformation();
    }

    public boolean pointInside(LatLng point){
        return PolyUtil.containsLocation(point, polygon,false);
    }
    private void buildTransformation(){
        double gradient = (north.latitude-east.latitude) /(north.longitude-east.longitude);
        double angle =Math.atan(gradient);
        this.sin = Math.sin(-angle);
        this.cos = Math.cos(-angle);
        this.distanceLng = Math.sqrt(Math.pow((north.longitude-east.longitude),2) + (Math.pow((north.latitude-east.latitude),2)));
        this.distanceLat = Math.sqrt(Math.pow((south.longitude-east.longitude),2) + (Math.pow((south.latitude-east.latitude),2)));
        this.isReady=true;
    }
    public CustomLatLng transForm(int width,int height,LatLng point){

        double coefLng =width /distanceLng;
        double coefLat =height/distanceLat;
        //Rotacion
        double rotationLat = (point.longitude*this.sin) + (point.latitude*this.cos);
        double rotationLng = (point.longitude*this.cos) - (point.latitude*this.sin);
        double rotationLat0 = (this.east.longitude*this.sin) + (this.east.latitude*this.cos);
        double rotationLng0 = (this.east.longitude*this.cos) - (this.east.latitude*this.sin);
        //Deplazar a punto 0
        double latO = rotationLat -  rotationLat0;
        double lngO = rotationLng - rotationLng0;
        //cambiar estacala;
        double scaleLat = latO * coefLat;
        double scaleLng = lngO * coefLng;
        double latAbs = Math.abs(scaleLat);
        double lngAbs =Math.abs(scaleLng);
        return new CustomLatLng(latAbs,lngAbs);

    }



}
