package com.unlam.soa.tp1_2.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.unlam.soa.tp1_2.R;
import com.unlam.soa.tp1_2.contract.Contract;
import com.unlam.soa.tp1_2.presenter.ArduinoPresenter;

public class ArduinoActivity extends AppCompatActivity implements Contract.View {
    ConstraintLayout constraintLayout;
    Contract.Presenter presenter;
    TextInputEditText textInput;
    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arduino);

        constraintLayout = findViewById(R.id.constraintLayout);
        presenter = new ArduinoPresenter(this,constraintLayout);
        Button actionBtn = findViewById(R.id.btnAction);
        textInput = findViewById(R.id.text_power);
        presenter.starBackgroundTask();

        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setSelectedItemId(R.id.arduino);
        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.arduino:
                        return true;
                    case R.id.android:
                        startActivity(new Intent(getApplicationContext(), AndroidActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
        actionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { presenter.doAction(); }
        });

    }

    @Override
    public int getResourceColor(int id){
        return getResources().getColor(id);
    }
    @Override
    public void setInfo(String text){
        textInput.setText(text);
    }

}