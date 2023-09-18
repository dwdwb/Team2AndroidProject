package com.example.myapplication.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.myapplication.R;

public class DetailOptionLinearLayout extends LinearLayout {
    public DetailOptionLinearLayout(Context context) {
        super(context);

        LayoutInflater inflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.detail_option_layout,this,true);
    }

    public DetailOptionLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
}
