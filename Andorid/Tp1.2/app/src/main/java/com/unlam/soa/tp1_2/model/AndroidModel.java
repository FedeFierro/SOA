package com.unlam.soa.tp1_2.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.PolyUtil;
import com.unlam.soa.tp1_2.contract.Contract;

import java.util.ArrayList;
import java.util.List;

public class AndroidModel implements Contract.Model{
    private final List<LatLng> unlamPolygon = new ArrayList<>();
    public AndroidModel(){
        unlamPolygon.add(new LatLng(-34.6695529,-58.5615047));
        unlamPolygon.add(new LatLng(-34.6654188,-58.5650747));
        unlamPolygon.add(new LatLng(-34.6671351,-58.5689907));
        unlamPolygon.add(new LatLng(-34.6713088,-58.5634117));
        unlamPolygon.add(new LatLng(-34.6695529,-58.5615047));
    }
    @Override
    public void doInBackground() {

    }

    public boolean pointInside(LatLng point){
        return PolyUtil.containsLocation(point,unlamPolygon,false);
    }
}
