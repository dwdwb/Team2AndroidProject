package com.example.myapplication.ui;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adapter.ReviewAdapter;
import com.example.myapplication.adapter.WishAdapter;
import com.example.myapplication.databinding.FragmentReviewBinding;
import com.example.myapplication.databinding.FragmentWishBinding;
import com.example.myapplication.datastore.AppKeyValueStore;
import com.example.myapplication.dto.ReviewListItem;
import com.example.myapplication.dto.WishListItem;
import com.example.myapplication.dto.WriteReviewResult;
import com.example.myapplication.service.ReviewService;
import com.example.myapplication.service.ServiceProvider;
import com.example.myapplication.service.WishService;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class WishFragment extends Fragment {
    private static final String TAG = "WishFragment";
    private FragmentWishBinding binding;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentWishBinding.inflate(inflater);
        //NavController 얻기
        navController = NavHostFragment.findNavController(this);

        initRecyclerView();

        return binding.getRoot();
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getContext(), LinearLayoutManager.VERTICAL, false
        );
        binding.recyclerView.setLayoutManager(linearLayoutManager);

        WishAdapter wishAdapter = new WishAdapter();

        //API 서버에서 JSON 목록 받기
        String shopperId = AppKeyValueStore.getValue(getContext(), "shopperId");
        WishService wishService = ServiceProvider.getWishService(getContext());
        Call<List<WishListItem>> call = wishService.getShopperWishList(shopperId);
        call.enqueue(new Callback<List<WishListItem>>() {
            @Override
            public void onResponse(Call<List<WishListItem>> call, Response<List<WishListItem>> response) {
                List<WishListItem> list = response.body();
                wishAdapter.setList(list);
                binding.recyclerView.setAdapter(wishAdapter);
            }

            @Override
            public void onFailure(Call<List<WishListItem>> call, Throwable t) {
                Log.i(TAG, "FAIL");
                t.printStackTrace();
            }
        });

        wishAdapter.setOnItemClickListener1(new WishAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Log.i(TAG, "setOnItemClickListener1");
                WishListItem wishListItem = wishAdapter.getItem(position);

                WishService wishService = ServiceProvider.getWishService(getContext());
                Call<WriteReviewResult> call = wishService.deleteWish(wishListItem.getProduct_no(), wishListItem.getShopper_no());
                call.enqueue(new Callback<WriteReviewResult>() {
                    @Override
                    public void onResponse(Call<WriteReviewResult> call, Response<WriteReviewResult> response) {
                        WriteReviewResult writeReviewResult = response.body();
                        if (writeReviewResult.getResult().equals("success")) {
                            navController.popBackStack(R.id.myPage, false);
                            navController.navigate(R.id.action_myPage_to_wish);
                        } else {
                            AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                                    .setTitle("삭제 실패")
                                    .setPositiveButton("확인", null)
                                    .create();
                            alertDialog.show();
                        }
                    }

                    @Override
                    public void onFailure(Call<WriteReviewResult> call, Throwable t) {

                    }
                });
            }
        });

        wishAdapter.setOnItemClickListener2(new WishAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Log.i(TAG, "setOnItemClickListener2");
                WishListItem wishListItem = wishAdapter.getItem(position);

                WishService wishService = ServiceProvider.getWishService(getContext());
                Call<WriteReviewResult> call = wishService.addToCart(wishListItem.getProduct_no(), wishListItem.getShopper_no());
                call.enqueue(new Callback<WriteReviewResult>() {
                    @Override
                    public void onResponse(Call<WriteReviewResult> call, Response<WriteReviewResult> response) {
                        WriteReviewResult writeReviewResult = response.body();
                        if (writeReviewResult.getResult().equals("success")) {
                            Toast toast = Toast.makeText(getContext(), "상품이 장바구니에 추가되었습니다.", Toast.LENGTH_SHORT);
                            toast.show();
                        } else {
                            Toast toast = Toast.makeText(getContext(), "상품이 이미 장바구니에 존재합니다.", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }

                    @Override
                    public void onFailure(Call<WriteReviewResult> call, Throwable t) {

                    }
                });
            }
        });
    }


}