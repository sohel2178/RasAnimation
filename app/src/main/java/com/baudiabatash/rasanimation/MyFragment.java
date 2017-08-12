package com.baudiabatash.rasanimation;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.TextView;

import com.baudiabatash.rasanimation.ModelView.EarthBead;
import com.baudiabatash.rasanimation.ModelView.HeavenBead;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends Fragment implements EarthBead.SohelButtonListener,
        HeavenBead.HeavenBeadListener{

    private TextView tvValue;
    private EarthBead btn1,btn2,btn3,btn4;
    private HeavenBead heavenBead;


    private int buttonWidth, buttonHeight;

    private List<EarthBead> earthBeadList;


    public MyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        initView(view);

        ViewTreeObserver viewTreeObserver = btn1.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    btn1.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    btn1.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
                buttonWidth  = btn1.getMeasuredWidth();
                buttonHeight = btn1.getMeasuredHeight();

                int margin = toPx((int) getResources().getDimension(R.dimen.margin));
                float textViewHeight =getResources().getDimension(R.dimen.text_size);

                int screenHeight = getScreenHeight();
                float displacement = (float) (screenHeight-4*buttonHeight-margin-textViewHeight);

                float heavenBeadDisplacement = displacement/3;
                float earthBeadDisplacement = displacement-heavenBeadDisplacement;

                btn1.setDisplacement(-(earthBeadDisplacement-buttonHeight/2));
                btn2.setDisplacement(-(earthBeadDisplacement-buttonHeight/2));
                btn3.setDisplacement(-(earthBeadDisplacement-buttonHeight/2));
                btn4.setDisplacement(-(earthBeadDisplacement-buttonHeight/2));
                heavenBead.setDisplacement(heavenBeadDisplacement-buttonHeight/2);

                //Log.d("KKK",btn1.getValue()+"");





               // Log.d("KKK",buttonHeight+"");
            }
        });

       // Log.d("YYY",btn1.getWidth()+"");
       // Log.d("YYY",btn1.getHeight()+"");
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void initView(View view) {
        tvValue = (TextView) view.findViewById(R.id.value);
        heavenBead = (HeavenBead) view.findViewById(R.id.heaven_bead);
        heavenBead.setHeavenBeadListener(this);

        btn1 = (EarthBead) view.findViewById(R.id.btn1);
        btn2 = (EarthBead) view.findViewById(R.id.btn2);
        btn3 = (EarthBead) view.findViewById(R.id.btn3);
        btn4 = (EarthBead) view.findViewById(R.id.btn4);

        btn1.setSohelButtonListener(this);
        btn2.setSohelButtonListener(this);
        btn3.setSohelButtonListener(this);
        btn4.setSohelButtonListener(this);

        earthBeadList = new ArrayList<>();
        earthBeadList.add(btn1);
        earthBeadList.add(btn2);
        earthBeadList.add(btn3);
        earthBeadList.add(btn4);

    }

    @Override
    public void onClick(int id) {

        if(id==heavenBead.getId()){
            if(heavenBead.getMoveState()==HeavenBead.MOVE_DOWN){
                heavenBead.moveUp();
            }else{
                heavenBead.moveDown();
            }


            //return;
        }else{
            int touchIndex= getIndex(id);

            EarthBead touchButton = earthBeadList.get(touchIndex);

            if(touchButton.getMoveState()== EarthBead.MOVE_DOWN){
                for(EarthBead x: earthBeadList){
                    if(x.getId()<=id){
                        x.moveUp();
                    }
                }
            }else{
                for(EarthBead x: earthBeadList){
                    if(x.getId()>=id){
                        x.moveDown();
                    }
                }
            }


        }

        setValue();




    }

    private void setValue(){
        int value = 0;
        for (EarthBead x: earthBeadList){
            value = value+x.getValue();
        }
        value = value+heavenBead.getValue();
        tvValue.setText(String.valueOf(value));
    }

    private int getIndex(int id) {
        int index=-1;
        for(EarthBead x: earthBeadList){
            if(x.getId()==id){
                index = earthBeadList.indexOf(x);
                break;
            }
        }
        return index;
    }


    private int getScreenHeight() {
        WindowManager wm = (WindowManager)getActivity().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int height = metrics.heightPixels;
        return height;
    }

    private int toPx(int dp){
        return (int)((getResources().getDisplayMetrics().density)*dp);
    }
}
