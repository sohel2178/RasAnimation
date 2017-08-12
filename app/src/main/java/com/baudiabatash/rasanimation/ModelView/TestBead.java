package com.baudiabatash.rasanimation.ModelView;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.baudiabatash.rasanimation.MyAnimation;

/**
 * Created by Sohel on 8/12/2017.
 */

public class TestBead {
    private float cX,cY,radius;
    private float iniTialCy;

    private Paint paint;

    private boolean isTouch;

    private MyAnimation myAnimation;

    public TestBead(float cX, float cY, float radius) {
        this.cX = cX;
        this.cY = cY;
        this.radius = radius;
        this.iniTialCy= cY;

        isTouch = false;

        myAnimation = new MyAnimation();

        initPaint();
    }

    private void initPaint() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
    }

    public void moveUp(float displacement){
        cY = iniTialCy-displacement;
    }

    public void moveDown(float displacement){
        cY = iniTialCy+displacement;
    }

    public float getIniTialCy() {
        return iniTialCy;
    }

    public void isTouched(float x, float y){
        float dist = (float) Math.sqrt(Math.pow(cX-x,2)+Math.pow(cY-y,2));

        if(dist<radius){

            // Touch
            if(isTouch){
                myAnimation.move();
                isTouch=false;
            }else{
                myAnimation.move();
                isTouch=true;
            }



        }

    }

    public MyAnimation getAnimator(){
        return myAnimation;
    }

    public boolean isTouch() {
        return isTouch;
    }

    public void setTouch(boolean touch) {
        isTouch = touch;
    }

    public void draw(Canvas canvas){
        canvas.drawCircle(cX,cY,radius,paint);
    }


    public float getcX() {
        return cX;
    }

    public void setcX(float cX) {
        this.cX = cX;
    }

    public float getcY() {
        return cY;
    }

    public void setcY(float cY) {
        this.cY = cY;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }
}
