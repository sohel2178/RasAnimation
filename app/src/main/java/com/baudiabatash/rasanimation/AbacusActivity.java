package com.baudiabatash.rasanimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.baudiabatash.rasanimation.ModelView.RashinView;

public class AbacusActivity extends AppCompatActivity {

    private MyFragment fragment;

    private LinearLayout contaner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new RashinView(this));



    }
}
