package com.example.myapplication.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.adapter.CartCouponAdapter;
import com.example.myapplication.adapter.MyPageCouponAdapter;
import com.example.myapplication.databinding.FragmentCouponBinding;
import com.example.myapplication.databinding.FragmentShippingBinding;
import com.example.myapplication.dto.Coupon;
import com.example.myapplication.service.CartService;
import com.example.myapplication.service.MyPageCouponService;
import com.example.myapplication.service.ServiceProvider;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CouponFragment extends Fragment {
    private static final String TAG = "CouponFragment";
    private FragmentCouponBinding binding;
    private NavController navController;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCouponBinding.inflate(inflater);

        navController = NavHostFragment.findNavController(this);

        initCouponList();

        initBtnBack();

        return binding.getRoot();
    }


    private void initCouponList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getContext(), LinearLayoutManager.VERTICAL, false
        );
        binding.recyclerView.setLayoutManager(linearLayoutManager);

        //어댑터 생성
        MyPageCouponAdapter myPageCouponAdapter = new MyPageCouponAdapter();

        //API 서버에서 JSON 목록 받기
        MyPageCouponService myPageCouponService = ServiceProvider.getMyPageCouponService(getContext());
        Call<List<Coupon>> call = myPageCouponService.getCartCouponList();
        call.enqueue(new Callback<List<Coupon>>() {
            @Override
            public void onResponse(Call<List<Coupon>> call, Response<List<Coupon>> response) {
                //JSON -> List<Board> 변환
                List<Coupon> list = response.body();
                //어댑터 데이터 세팅
                myPageCouponAdapter.setCouponList(list);
                //RecyclerView에 어댑터 세팅
                binding.recyclerView.setAdapter(myPageCouponAdapter);

                //보유쿠폰 개수 띄우기
                int couponSize = myPageCouponAdapter.getItemCount();
                binding.couponSize.setText("보유 쿠폰 " + couponSize + "장");
            }

            @Override
            public void onFailure(Call<List<Coupon>> call, Throwable t) {
                Log.i(TAG, "안됨......");
            }
        });

        //항목을 클릭했을 때 콜백 객체를 등록
        myPageCouponAdapter.setOnItemClickListener(new MyPageCouponAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Log.i(TAG, position + "항목 클릭됨");
                Coupon coupon = myPageCouponAdapter.getItem(position);

                Bundle args = new Bundle();
                args.putSerializable("coupon", coupon);
                //navController.navigate(R.id.action_dest_list_to_dest_detail, args);
            }
        });
    }

    private void initBtnBack() {
        binding.btnBack.setOnClickListener(v -> {
            navController.popBackStack();
        });
    }
}