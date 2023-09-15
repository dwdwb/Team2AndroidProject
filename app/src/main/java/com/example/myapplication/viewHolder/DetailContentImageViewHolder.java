package com.example.myapplication.viewHolder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.dto.CartProduct;
import com.example.myapplication.dto.ProductBoard;
import com.example.myapplication.service.CartService;
import com.example.myapplication.service.DetailViewService;

public class DetailContentImageViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "DetailContentImageViewH";
    private int media_no;
    private ImageView content_image;
    public DetailContentImageViewHolder(@NonNull View itemView) {
        super(itemView);

        //아이템 UI 얻기
        content_image = (ImageView) itemView.findViewById(R.id.content_image);
    }

    public void setData(int media_no) {
        this.media_no = media_no;

        DetailViewService.loadContentImage(media_no, content_image);
    }
}
