package com.unlam.soa.tp1_2.contract;

import com.google.android.gms.maps.model.LatLng;

public interface Contract {
    interface View{
        int getResourceColor(int id);
        void setInfo(String text);
    }
    interface Model{
        void doInBackground();
    }
    interface Presenter{
        void doAction();
        void sendMessage(String text);
        void starBackgroundTask();
    }
    interface LocationCallBack{
        void callBackLocation(LatLng latLng);
    }

}
