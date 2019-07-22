package com.example.hopupanimation;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.widget.Button;

import com.example.hopupanimation.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.fl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPriceDetails();
            }
        });
        setAnimationToTextView();
    }

    private void setAnimationToTextView() {
        final Handler animationHandler = new Handler();
        animationHandler.post(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void run() {
                ViewPropertyAnimator viewPropertyAnimator =
                        mBinding.fl.animate().translationYBy(-40f).setDuration(200);
                viewPropertyAnimator.withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        mBinding.fl.animate().translationYBy(40f).setDuration(200);
                    }
                });
                animationHandler.postDelayed(this, 1000);
            }
        });
    }

    private void displayPriceDetails() {
        final Dialog priceDetailDialog = new Dialog(this);
        priceDetailDialog.setContentView(R.layout.pricing_detail_dialog);
        Button dialogButton = (Button) priceDetailDialog.findViewById(R.id.btn_done);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                priceDetailDialog.dismiss();
            }
        });
        priceDetailDialog.getWindow().getAttributes().windowAnimations = R.style.CustomDialogAnimation;
        Window window = priceDetailDialog.getWindow();
        window.setLayout(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        priceDetailDialog.show();
    }
}
