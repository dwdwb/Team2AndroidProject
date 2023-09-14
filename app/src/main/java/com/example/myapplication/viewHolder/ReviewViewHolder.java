package com.example.myapplication.viewHolder;

import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.ListAdapter;
import com.example.myapplication.dto.MobileProductForList;
import com.example.myapplication.dto.ReviewListItem;
import com.example.myapplication.service.ListService;
import com.example.myapplication.service.ReviewService;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ReviewViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "ReviewViewHolder";
    private int review_no;
    private int product_no;
    private TextView write_date;
    private ImageView product_image;
    private TextView product_name_and_option;
    private TextView content;
    private Button review_delete_btn;
    private Button review_edit_btn;

    public ReviewViewHolder(@NonNull View itemView) {
        super(itemView);

        //아이템 UI 얻기
        write_date = (TextView) itemView.findViewById(R.id.write_date);
        product_image = (ImageView) itemView.findViewById(R.id.product_image);
        product_name_and_option = (TextView) itemView.findViewById(R.id.product_name_and_option);
        content = (TextView) itemView.findViewById(R.id.content);
    }

    public void setData(ReviewListItem reviewListItem) {
        Date date = new Date(reviewListItem.getWrite_date());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        String strDate = sdf.format(date);
        write_date.setText(strDate);
        review_no = reviewListItem.getReview_no();
        product_no = reviewListItem.getProduct_no();
        ReviewService.loadThumbnailImage(product_no, product_image);
        String str = "" + reviewListItem.getProduct_name() + ", " + reviewListItem.getProduct_option();
        product_name_and_option.setText(str);
        content.setText(reviewListItem.getContent());
    }

}
