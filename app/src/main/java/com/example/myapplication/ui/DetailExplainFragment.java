package com.example.myapplication.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.adapter.CartProductAdapter;
import com.example.myapplication.adapter.DetailContentImageAdpater;
import com.example.myapplication.databinding.FragmentDetailExplainBinding;
import com.example.myapplication.dto.CartProduct;
import com.example.myapplication.dto.ProductBoard;
import com.example.myapplication.service.CartService;
import com.example.myapplication.service.DetailViewService;
import com.example.myapplication.service.ServiceProvider;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailExplainFragment extends Fragment {
    private static final String TAG = "DetailExplainFragment";
    private FragmentDetailExplainBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDetailExplainBinding.inflate(inflater);

        //화면 초기화
        initView();

        //이미지 불러오기
        initContentImageRecyclerView();

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    //화면 초기화
    public void initView() {
        //DetailViewService.loadProductImage(1, binding.productImage);

        //API 서버에서 JSON 목록 받기
        DetailViewService detailViewService = ServiceProvider.getDetailViewService(getContext());
        Call<ProductBoard> call = detailViewService.getBoard(1);
        call.enqueue(new Callback<ProductBoard>() {
            @Override
            public void onResponse(Call<ProductBoard> call, Response<ProductBoard> response) {
                //JSON -> List<Board> 변환
                ProductBoard productBoard = response.body();

                binding.productName.setText(productBoard.getProductName());
                binding.productDiscountRate.setText(productBoard.getDiscountRate() + "%");
                binding.productOriginalPrice.setText(productBoard.getProductPrice() + "원");
                binding.productDiscountPrice.setText(productBoard.getDiscountPrice() + "원");

                Log.i(TAG, productBoard + "");
            }

            @Override
            public void onFailure(Call<ProductBoard> call, Throwable t) {
                Log.i(TAG, "안됨......");
            }
        });
    }

    //이미지 불러오기
    private void initContentImageRecyclerView() {
        //RecyclerView에서 항목을 수직으로 배치하도록 설정
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getContext(), LinearLayoutManager.VERTICAL, false
        );
        binding.detailContentImageRecyclerView.setLayoutManager(linearLayoutManager);

        //어댑터 생성
        DetailContentImageAdpater detailContentImageAdpater = new DetailContentImageAdpater();

        //API 서버에서 JSON 목록 받기
        DetailViewService detailViewService = ServiceProvider.getDetailViewService(getContext());
        Call<List<Integer>> call = detailViewService.getMediaNoList(1);
        call.enqueue(new Callback<List<Integer>>() {
            @Override
            public void onResponse(Call<List<Integer>> call, Response<List<Integer>> response) {
                //JSON -> List<Board> 변환
                List<Integer> list = response.body();
                Log.i(TAG, list + "");
                //어댑터 데이터 세팅
                detailContentImageAdpater.setList(list);
                //RecyclerView에 어댑터 세팅
                binding.detailContentImageRecyclerView.setAdapter(detailContentImageAdpater);
            }

            @Override
            public void onFailure(Call<List<Integer>> call, Throwable t) {
                Log.i(TAG, "안됨......");
            }
        });
    }
}