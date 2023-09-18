package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.dto.ProductBoard;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class DetailOptionAdapter extends BaseAdapter {
    //private List<String> optionList;
    private List<ProductBoard> optionList = new ArrayList<>();
    private final LayoutInflater inflater;
    private String text;

    public DetailOptionAdapter(Context context, List<ProductBoard> list) {
        ProductBoard defaultOption = new ProductBoard();
        defaultOption.setProductOption("옵션");
        optionList.add(defaultOption);
        list.stream().forEach(productBoard -> {
            optionList.add(productBoard);
        });
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
            if(position == 0) {
                text = optionList.get(position).getProductOption();
                ((TextView) convertView.findViewById(R.id.option_product_txt)).setText(text);
            } else {
                String option = optionList.get(position).getProductOption();
                int price = optionList.get(position).getDiscountPrice();
                DecimalFormat df = new DecimalFormat("#,###,###");
                text = option + " : " + df.format(price) + "원";
                ((TextView) convertView.findViewById(R.id.option_product_txt)).setText(text);
            }
        }
        return convertView;
    }
}
