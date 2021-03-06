package com.baudiabatash.rasanimation.ModelView;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.baudiabatash.rasanimation.MyAnimation;
import com.baudiabatash.rasanimation.R;

/**
 * Created by Sohel on 8/12/2017.
 */

public class HeavenBead extends AppCompatButton {
    public static final int MOVE_UP=0;
    public static final int MOVE_DOWN=1;

    private MyAnimation myAnimation;
    private int moveState;
    private float displacement;
    private HeavenBeadListener listener;

    public HeavenBead(Context context) {
        super(context);
        init(context,null,0);
    }

    public HeavenBead(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs,0);
    }

    public HeavenBead(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr){
        setBackground(ContextCompat.getDrawable(getContext(), R.drawable.heaven_bead_back));
        moveState = MOVE_DOWN;
        myAnimation = new MyAnimation();

        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch(action) {
                    case (MotionEvent.ACTION_DOWN) :
                        if(listener!=null){
                            listener.onClick(getId());
                        }
                        return true;
                    case (MotionEvent.ACTION_MOVE) :
                        return true;
                }
                return true;
            }
        });

    }

    public int getMoveState(){
        return moveState;
    }


    public void moveUp(){
        if(moveState==MOVE_DOWN){
            myAnimation.moveDown(this,displacement);
            moveState= MOVE_UP;
        }

    }

    public void moveDown(){
        if(moveState==MOVE_UP){
            myAnimation.moveUp(this,displacement);
            moveState= MOVE_DOWN;
        }


    }

    public void setDisplacement(float displacement){
        this.displacement = displacement;
    }

    public void setHeavenBeadListener(HeavenBeadListener listener){
        this.listener= listener;
    }

    public int getValue(){
        int value = 0;
        if(moveState==MOVE_UP){
            value = 5;
        }

        return value;
    }


    public interface HeavenBeadListener{
        public void onClick(int id);
    }
}
