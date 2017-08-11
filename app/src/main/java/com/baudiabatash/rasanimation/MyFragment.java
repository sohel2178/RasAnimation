package com.baudiabatash.rasanimation;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;

import com.baudiabatash.rasanimation.ModelView.SohelButton;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends Fragment implements SohelButton.SohelButtonListener {
    private SohelButton btn1,btn2,btn3,btn4;

    private int buttonWidth, buttonHeight;

    private List<SohelButton> sohelButtonList;


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

                int screenHeight = getScreenHeight();
                float displacement = (float) (screenHeight-4.5*buttonHeight-56*2);

                btn1.setDisplacement(-displacement);
                btn2.setDisplacement(-displacement);
                btn3.setDisplacement(-displacement);
                btn4.setDisplacement(-displacement);





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

        Log.d("KKK",buttonHeight+"");
    }

    private void initView(View view) {
        btn1 = (SohelButton) view.findViewById(R.id.btn1);
        btn2 = (SohelButton) view.findViewById(R.id.btn2);
        btn3 = (SohelButton) view.findViewById(R.id.btn3);
        btn4 = (SohelButton) view.findViewById(R.id.btn4);



        btn1.setSohelButtonListener(this);
        btn2.setSohelButtonListener(this);
        btn3.setSohelButtonListener(this);
        btn4.setSohelButtonListener(this);

        sohelButtonList = new ArrayList<>();
        sohelButtonList.add(btn1);
        sohelButtonList.add(btn2);
        sohelButtonList.add(btn3);
        sohelButtonList.add(btn4);

    }

    @Override
    public void onClick(int id) {
        //Log.d("KKK",id+"");

        int touchIndex= getIndex(id);

        SohelButton touchButton = sohelButtonList.get(touchIndex);

        if(touchButton.getMoveState()==SohelButton.MOVE_DOWN){
            for(SohelButton x: sohelButtonList){
                if(x.getId()<=id){
                    x.moveUp();
                }
            }
        }else{
            for(SohelButton x: sohelButtonList){
                if(x.getId()>=id){
                    x.moveDown();
                }
            }
        }


    }

    private int getIndex(int id) {
        int index=-1;
        for(SohelButton x: sohelButtonList){
            if(x.getId()==id){
                index = sohelButtonList.indexOf(x);
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
}
