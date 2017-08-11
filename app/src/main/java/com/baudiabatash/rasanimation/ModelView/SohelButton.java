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
 * Created by Sohel on 8/11/2017.
 */

public class SohelButton extends AppCompatButton {
    public static final int MOVE_UP=0;
    public static final int MOVE_DOWN=1;
    private static final String TAG= "SohelButton";
    private MyAnimation myAnimation;
    private int moveState;
    private float displacement;
    private SohelButtonListener listener;
    public SohelButton(Context context) {
        super(context);
        init(context,null,0);

    }

    public void setSohelButtonListener(SohelButtonListener listener){
        this.listener = listener;
    }

    public void setDisplacement(float displacement){
        this.displacement = displacement;
    }


    public SohelButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs,0);
    }

    public SohelButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    private void init(Context context,AttributeSet attrs, int defStyleAttr){
        setBackground(ContextCompat.getDrawable(getContext(),R.drawable.button_back));
        moveState = MOVE_UP;
        myAnimation = new MyAnimation();
        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch(action) {
                    case (MotionEvent.ACTION_DOWN) :
                        Log.d(TAG,"Action was DOWN");
                        if(listener!=null){
                            listener.onClick(getId());
                        }
                        return true;
                    case (MotionEvent.ACTION_MOVE) :
                        Log.d(TAG,"Action was MOVE");
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
            myAnimation.moveUp(this,displacement);
            moveState= MOVE_UP;
        }

    }

    public void moveDown(){
        if(moveState==MOVE_UP){
            myAnimation.moveDown(this,displacement);
            moveState= MOVE_DOWN;
        }


    }

    public interface SohelButtonListener{
        public void onClick(int id);
    }

}
