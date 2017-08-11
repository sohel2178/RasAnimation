package com.baudiabatash.rasanimation;

import android.animation.ValueAnimator;
import android.widget.Button;

/**
 * Created by Sohel on 8/11/2017.
 */

public class MyAnimation {
    private ValueAnimator valueAnimator;

    public MyAnimation() {
        valueAnimator = new ValueAnimator().ofFloat(0f,100f);
        valueAnimator.setDuration(700);
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
}
