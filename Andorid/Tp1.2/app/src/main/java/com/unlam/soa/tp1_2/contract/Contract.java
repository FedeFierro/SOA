package com.unlam.soa.tp1_2.contract;

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
}
