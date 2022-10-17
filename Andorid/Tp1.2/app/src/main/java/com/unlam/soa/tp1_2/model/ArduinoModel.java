package com.unlam.soa.tp1_2.model;

import android.os.Handler;

import com.unlam.soa.tp1_2.contract.Contract;

import java.util.Random;


public class ArduinoModel implements Contract.Model{
    private static final int MAX_POWER = 1024;
    private static final int INTERVAL_TASK = 5000;
    private Runnable randomNumberTask;
    private Handler handlerTask;

    Contract.Presenter presenter;
    public ArduinoModel(Contract.Presenter presenter) {
        this.presenter = presenter;
        handlerTask = new android.os.Handler();
        randomNumberTask = new Runnable() {
            @Override
            public void run() {
                String data = getRandomNumber();
                presenter.sendMessage(data);
                handlerTask.postDelayed(randomNumberTask,INTERVAL_TASK);
            }
        };
    }


    private String getRandomNumber(){
        Random random = new Random();
        int power = random.nextInt(MAX_POWER);
        return Integer.toString(power);
    }

    @Override
    public void doInBackground() {
        randomNumberTask.run();
    }
}
