package com.example.myapplication.adapter;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.dto.ProductInquiry;
import com.example.myapplication.service.MyPageShopperInquiryService;
import com.example.myapplication.service.ServiceProvider;
import com.example.myapplication.viewHolder.InquiryProductViewHolder;
import com.example.myapplication.viewHolder.InquiryViewHolder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InquiryProductAdapter extends RecyclerView.Adapter<InquiryProductViewHolder> {
    private List<String> productNameList = new ArrayList<>();
    private List<ProductInquiry> productInquiryList = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    @NonNull
    @Override
    public InquiryProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.inquiry_product_item, parent, false);
        InquiryProductViewHolder inquiryProductViewHolder = new InquiryProductViewHolder(itemView, onItemClickListener);
        return inquiryProductViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull InquiryProductViewHolder holder, int position) {
        String productName = productNameList.get(position);
        holder.setData(productName);

        //RecyclerView에서 항목을 수직으로 배치하도록 설정
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                holder.itemView.getContext(), LinearLayoutManager.VERTICAL, false
        );
        holder.getRecycler_view().setLayoutManager(linearLayoutManager);

        //어댑터 생성
        InquiryAdapter inquiryAdapter = new InquiryAdapter();

        //API 서버에서 JSON 목록 받기
        MyPageShopperInquiryService myPageShopperInquiryService = ServiceProvider.getMyPageShopperInquiryService(holder.itemView.getContext());

        Call<List<ProductInquiry>> call = myPageShopperInquiryService.getInquiryList(1, productName);
        call.enqueue(new Callback<List<ProductInquiry>>() {
            @Override
            public void onResponse(Call<List<ProductInquiry>> call, Response<List<ProductInquiry>> response) {
                //JSON -> List<Board> 변환
                List<ProductInquiry> list = response.body();
                //Log.i(TAG, list + "");
                //어댑터 데이터 세팅
                inquiryAdapter.setList(list);
                //RecyclerView에 어댑터 세팅
                holder.getRecycler_view().setAdapter(inquiryAdapter);
            }

            @Override
            public void onFailure(Call<List<ProductInquiry>> call, Throwable t) {
                //Log.i(TAG, "안됨......");
            }
        });

        //항목을 클릭했을 때 콜백 객체를 등록
        inquiryAdapter.setOnItemClickListener(new InquiryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                //Log.i(TAG, position + "항목 클릭됨");
                ProductInquiry productInquiry = inquiryAdapter.getItem(position);

                Bundle args = new Bundle();
                args.putSerializable("productInquiry", productInquiry);
                //navController.navigate(R.id.action_dest_list_to_dest_detail, args);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productNameList.size();
    }

    public void setList(List<String> list) {
        this.productNameList = list;
    }

    public String getItem(int position) {
        return productNameList.get(position);
    }

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener(InquiryProductAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
