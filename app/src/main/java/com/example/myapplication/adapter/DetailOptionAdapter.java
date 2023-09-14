package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.List;

public class DetailOptionAdapter extends BaseAdapter {
    private List<String> optionList;
    private final LayoutInflater inflater;
    private String text;

    public DetailOptionAdapter(Context context, List<String> list) {
        this.optionList = list;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        if (optionList != null)
            return optionList.size();
        else
            return 0;
    }

    @Override
    public Object getItem(int position) {
        return optionList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = inflater.inflate(R.layout.detail_option_item, parent, false);
        if (optionList != null) {
            text = optionList.get(position);
            ((TextView) convertView.findViewById(R.id.option_product_txt)).setText(text);
        }
        return convertView;
    }
}
