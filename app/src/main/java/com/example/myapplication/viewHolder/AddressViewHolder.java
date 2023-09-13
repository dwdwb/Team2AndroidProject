package com.example.myapplication.viewHolder;

import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.AddressAdapter;
import com.example.myapplication.adapter.ListAdapter;
import com.example.myapplication.dto.AddressList;
import com.example.myapplication.dto.MobileProductForList;
import com.example.myapplication.service.ListService;
import com.google.android.material.card.MaterialCardView;

public class AddressViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "AddressViewHolder";
    private int address_no;
    private TextView shipping_name;
    private TextView shipping_address;
    private TextView receiver_tel;
    private TextView shipping_preference;

    public AddressViewHolder(@NonNull View itemView, AddressAdapter.OnItemClickListener onItemClickListener) {
        super(itemView);

        //아이템 UI 얻기

        shipping_name = (TextView) itemView.findViewById(R.id.shipping_name);
        shipping_address = (TextView) itemView.findViewById(R.id.shipping_address);
        receiver_tel = (TextView) itemView.findViewById(R.id.receiver_tel);
        shipping_preference = (TextView) itemView.findViewById(R.id.shipping_preference);


        //클릭 이벤트 처리
        itemView.setOnClickListener(v -> {
            Log.i(TAG, address_no + " 항목이 클릭됨");
            onItemClickListener.onItemClick(v, getAdapterPosition());
        });
    }

    public void setData(AddressList addressList) {
        address_no = addressList.getAddress_no();
        shipping_name.setText(addressList.getShipping_name());
        shipping_address.setText(addressList.getShipping_address());
        receiver_tel.setText(addressList.getReceiver_tel());
        shipping_preference.setText(addressList.getShipping_preference());

    }
}
