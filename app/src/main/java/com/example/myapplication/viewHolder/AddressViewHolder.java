package com.example.myapplication.viewHolder;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.AddressAdapter;
import com.example.myapplication.dto.AddressList;

public class AddressViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "AddressViewHolder";
    private int address_no;

    private AddressList addressList;
    private TextView shipping_name;
    private TextView shipping_address;
    private TextView receiver_tel;
    private TextView shipping_preference;
    private Button btnDel;

    private Button btnSel;

    public AddressViewHolder(@NonNull View itemView, AddressAdapter.OnItemClickListener onItemClickListener) {
        super(itemView);

        //아이템 UI 얻기

        shipping_name = (TextView) itemView.findViewById(R.id.shipping_name);
        shipping_address = (TextView) itemView.findViewById(R.id.add_shipping_address);
        receiver_tel = (TextView) itemView.findViewById(R.id.receiver_tel);
        shipping_preference = (TextView) itemView.findViewById(R.id.shipping_preference);
        btnDel = itemView.findViewById(R.id.btnDel);
        btnSel = itemView.findViewById(R.id.btnSelect);



        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 버튼 클릭 시 해당 아이템의 address_no를 전달
                Log.i(TAG, "address_no선택됨?"+address_no);

                onItemClickListener.onDeleteClick(btnDel, getAdapterPosition());

            }
        });

        btnSel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "address가 선택됨" + address_no);
                onItemClickListener.onSelectClick(btnSel, getAdapterPosition()); // 시그니처 변경
            }
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
