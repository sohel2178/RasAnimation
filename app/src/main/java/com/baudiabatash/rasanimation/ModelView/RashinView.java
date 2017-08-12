package com.baudiabatash.rasanimation.ModelView;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.baudiabatash.rasanimation.Listener.TestListener;
import com.baudiabatash.rasanimation.MyAnimation;

/**
 * Created by Sohel on 8/12/2017.
 */

public class RashinView extends View implements TestListener {
    private Context context;



    private TestBead testBead;

    public RashinView(Context context) {
        super(context);
        this.context = context;


        testBead = new TestBead(200,500,50);

        testBead.getAnimator().setTestListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        testBead.draw(canvas);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean value = super.onTouchEvent(event);

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:{

                float x= event.getX();
                float y = event.getY();

                testBead.isTouched(x,y);

                return true;
            }

        }

        return value;
    }

    @Override
    public void click(float displacement) {
        Log.d("FFF","get Data");

        if(testBead.isTouch()){
            testBead.setcY(testBead.getIniTialCy()-200*displacement);

        }else{
            testBead.setcY(testBead.getIniTialCy()-200+200*displacement);
        }

        postInvalidate();
    }
}
