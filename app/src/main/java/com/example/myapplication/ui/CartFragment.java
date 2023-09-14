package com.example.myapplication.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.adapter.CartCouponAdapter;
import com.example.myapplication.adapter.CartProductAdapter;
import com.example.myapplication.databinding.FragmentCartBinding;
import com.example.myapplication.databinding.FragmentDetailBinding;
import com.example.myapplication.dto.CartProduct;
import com.example.myapplication.dto.Coupon;
import com.example.myapplication.service.CartService;
import com.example.myapplication.service.ServiceProvider;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartFragment extends Fragment {
    private static final String TAG = "CartFragment";
    private FragmentCartBinding binding;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCartBinding.inflate(inflater);

        //NavController 얻기
        navController = NavHostFragment.findNavController(this);

        //RecyclerView 초기화
        initCartProductRecyclerView();
        initCartCouponRecyclerView();

        initBtnOrder();

        //하단바 숨기기
        hideBottomNavigation(true);

        return binding.getRoot();
    }

    //하단바 설정
    public void hideBottomNavigation(Boolean bool) {
        BottomNavigationView bottomNavigation = getActivity().findViewById(R.id.bottomNavigationView);
        if (bool == true)
            bottomNavigation.setVisibility(View.GONE);
        else
            bottomNavigation.setVisibility(View.VISIBLE);
    }

    //상품 불러오기
    private void initCartProductRecyclerView() {
        //RecyclerView에서 항목을 수직으로 배치하도록 설정
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getContext(), LinearLayoutManager.VERTICAL, false
        );
        binding.cartProductRecyclerView.setLayoutManager(linearLayoutManager);

        //어댑터 생성
        CartProductAdapter cartProductAdapter = new CartProductAdapter();

        //API 서버에서 JSON 목록 받기
        CartService cartService = ServiceProvider.getCartService(getContext());
        Call<List<CartProduct>> call = cartService.getCartProductList();
        call.enqueue(new Callback<List<CartProduct>>() {
            @Override
            public void onResponse(Call<List<CartProduct>> call, Response<List<CartProduct>> response) {
                //JSON -> List<Board> 변환
                List<CartProduct> list = response.body();
                Log.i(TAG, list + "");
                //어댑터 데이터 세팅
                cartProductAdapter.setList(list);
                //RecyclerView에 어댑터 세팅
                binding.cartProductRecyclerView.setAdapter(cartProductAdapter);
            }

            @Override
            public void onFailure(Call<List<CartProduct>> call, Throwable t) {
                Log.i(TAG, "안됨......");
            }
        });

        //항목을 클릭했을 때 콜백 객체를 등록
        cartProductAdapter.setOnItemClickListener(new CartProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Log.i(TAG, position + "항목 클릭됨");
                CartProduct cartProduct = cartProductAdapter.getItem(position);

                Bundle args = new Bundle();
                args.putSerializable("cartProduct", cartProduct);
                //navController.navigate(R.id.action_dest_list_to_dest_detail, args);
            }
        });
    }

    //쿠폰 불러오기
    private void initCartCouponRecyclerView() {
        //RecyclerView에서 항목을 수직으로 배치하도록 설정
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getContext(), LinearLayoutManager.VERTICAL, false
        );
        binding.cartCouponRecyclerView.setLayoutManager(linearLayoutManager);

        //어댑터 생성
        CartCouponAdapter cartCouponAdapter = new CartCouponAdapter();

        //API 서버에서 JSON 목록 받기
        CartService cartService = ServiceProvider.getCartService(getContext());
        Call<List<Coupon>> call = cartService.getCartCouponList();
        call.enqueue(new Callback<List<Coupon>>() {
            @Override
            public void onResponse(Call<List<Coupon>> call, Response<List<Coupon>> response) {
                //JSON -> List<Board> 변환
                List<Coupon> list = response.body();
                //어댑터 데이터 세팅
                cartCouponAdapter.setCouponList(list);
                //RecyclerView에 어댑터 세팅
                binding.cartCouponRecyclerView.setAdapter(cartCouponAdapter);
            }

            @Override
            public void onFailure(Call<List<Coupon>> call, Throwable t) {
                Log.i(TAG, "안됨......");
            }
        });

        //항목을 클릭했을 때 콜백 객체를 등록
        cartCouponAdapter.setOnItemClickListener(new CartCouponAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Log.i(TAG, position + "항목 클릭됨");
                Coupon coupon = cartCouponAdapter.getItem(position);

                Bundle args = new Bundle();
                args.putSerializable("coupon", coupon);
                //navController.navigate(R.id.action_dest_list_to_dest_detail, args);
            }
        });
    }

    private void initBtnOrder() {
        binding.btnOrder.setOnClickListener(v -> {
            navController.navigate(R.id.action_cart_to_order);
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        //하단바 보이기
        hideBottomNavigation(false);
    }
}