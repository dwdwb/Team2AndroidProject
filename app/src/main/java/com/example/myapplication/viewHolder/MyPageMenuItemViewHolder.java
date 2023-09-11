package com.example.myapplication.viewHolder;

import android.graphics.drawable.Icon;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.MyPageMenuItemAdapter;
import com.example.myapplication.dto.MyPageMenuItem;

public class MyPageMenuItemViewHolder extends RecyclerView.ViewHolder {
    private ImageView icon;
    private TextView text;

    public MyPageMenuItemViewHolder(@NonNull View itemView, MyPageMenuItemAdapter.OnItemClickListener onItemClickListener) {
        super(itemView);

        icon = (ImageView) itemView.findViewById(R.id.menu_icon);
        text = (TextView) itemView.findViewById(R.id.menu_text);

        itemView.setOnClickListener(v -> {
            onItemClickListener.onItemClick(v, getAdapterPosition());
        });
    }

    public void setData(MyPageMenuItem myPageMenuItem) {
        icon.setImageResource(myPageMenuItem.getIcon());
        text.setText(myPageMenuItem.getMenuText());
    }
}
