package com.baudiabatash.rasanimation;

import android.content.Intent;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.baudiabatash.rasanimation.ModelView.SohelButton;
import com.transitionseverywhere.Slide;
import com.transitionseverywhere.Transition;
import com.transitionseverywhere.TransitionValues;

public class MainActivity extends AppCompatActivity {
    private ViewGroup transitionContainer;
    private Button button;
    private SohelButton sohelButton;
    private TextView textView,textView2;

    private Transition transition;

    private Slide slide;
    private int state;

    private static final String TAG="MainActivity";

    private boolean moveState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        slide = new Slide(Gravity.RIGHT);

        transitionContainer = (ViewGroup) findViewById(R.id.transitions_container);
        button = (Button) findViewById(R.id.button);
        sohelButton = (SohelButton) findViewById(R.id.an_btn);
        textView = (TextView) findViewById(R.id.text);
        textView2 = (TextView) findViewById(R.id.text2);
        state =0;
        moveState = false;

        transition = new Transition() {
            @Override
            public void captureStartValues(TransitionValues transitionValues) {

            }

            @Override
            public void captureEndValues(TransitionValues transitionValues) {

            }
        };

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoTestActivity();


            }
        });

    }

    private void gotoTestActivity() {
        startActivity(new Intent(getApplicationContext(),TestActivity.class));
    }

    /*@Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = MotionEventCompat.getActionMasked(event);

        switch(action) {
            case (MotionEvent.ACTION_DOWN) :
                Log.d(TAG,"Action was DOWN");
                return true;
            case (MotionEvent.ACTION_MOVE) :
                Log.d(TAG,"Action was MOVE");
                return true;
            case (MotionEvent.ACTION_UP) :
                Log.d(TAG,"Action was UP");
                return true;
            case (MotionEvent.ACTION_CANCEL) :
                Log.d(TAG,"Action was CANCEL");
                return true;
            case (MotionEvent.ACTION_OUTSIDE) :
                Log.d(TAG,"Movement occurred outside bounds " +
                        "of current screen element");
                return true;
            default :
                return super.onTouchEvent(event);
        }
    }*/
}
