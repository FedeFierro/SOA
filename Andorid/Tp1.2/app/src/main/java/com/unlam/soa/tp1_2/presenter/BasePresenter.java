package com.unlam.soa.tp1_2.presenter;

import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.snackbar.Snackbar;
import com.unlam.soa.tp1_2.R;
import com.unlam.soa.tp1_2.contract.Contract;

public abstract class BasePresenter implements Contract.Presenter {
    ConstraintLayout constraintLayout;
    Contract.View view;
    Contract.Model model;
    public BasePresenter(Contract.View view,ConstraintLayout constraintLayout){
        this.view = view;
        this.constraintLayout =constraintLayout;
    }

    public void showSuccess(String msg){
        Snackbar.make(constraintLayout, msg, Snackbar.LENGTH_SHORT)
                .setBackgroundTint(view.getResourceColor(R.color.success))
                .setTextColor(view.getResourceColor(R.color.white))
                .setAction(R.string.action_success,null)
                .show();
    }
    public void showError(String msg){
        Snackbar.make(constraintLayout, "msg", Snackbar.LENGTH_SHORT)
                .setBackgroundTint(view.getResourceColor(R.color.error))
                .setTextColor(view.getResourceColor(R.color.white))
                .setAction(R.string.action_error, null)
                .show();
    }
}
