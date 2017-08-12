package com.baudiabatash.rasanimation;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.util.Log;
import android.widget.Button;

import com.baudiabatash.rasanimation.Listener.TestListener;
import com.baudiabatash.rasanimation.ModelView.RashinView;
import com.baudiabatash.rasanimation.ModelView.TestBead;

/**
 * Created by Sohel on 8/11/2017.
 */

public class MyAnimation {
    private ValueAnimator valueAnimator;
    private TestListener listener;

    public MyAnimation() {
        valueAnimator = new ValueAnimator().ofFloat(0f,100f);
        valueAnimator.setDuration(450);
    }

    public void setTestListener(TestListener listener){
        this.listener = listener;
    }

    public void moveDown(final Button button, final float displacement){

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float updateValue = animation.getAnimatedFraction();
                button.setTranslationY(updateValue*displacement);
            }
        });
        valueAnimator.start();
    }

    public void moveUp(final Button button,final float displacement){
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float updateValue = animation.getAnimatedFraction();
                button.setTranslationY((1-updateValue)*displacement);
            }
        });
        valueAnimator.start();
    }

    public void move(){
        Log.d("FFF","MOve Up Triggered");
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if(listener!= null){
                    Log.d("FFF","Send Data");
                    listener.click(animation.getAnimatedFraction());
                }
            }
        });

        valueAnimator.start();
    }
}
