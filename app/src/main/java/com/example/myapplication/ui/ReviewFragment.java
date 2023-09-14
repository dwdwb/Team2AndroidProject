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
import com.example.myapplication.adapter.ReviewAdapter;
import com.example.myapplication.databinding.FragmentListBinding;
import com.example.myapplication.databinding.FragmentReviewBinding;
import com.example.myapplication.dto.MobileProductForList;
import com.example.myapplication.dto.ReviewListItem;
import com.example.myapplication.service.ListService;
import com.example.myapplication.service.ReviewService;
import com.example.myapplication.service.ServiceProvider;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewFragment extends Fragment {
    private static final String TAG = "ReviewFragment";
    private FragmentReviewBinding binding;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentReviewBinding.inflate(inflater);
        //NavController 얻기
        navController = NavHostFragment.findNavController(this);

        initRecyclerView();

        initBtnBack();

        return binding.getRoot();
    }

    private void initRecyclerView() {
        //RecyclerView에서 항목을 수직으로 배치하도록 설정
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getContext(), LinearLayoutManager.VERTICAL, false
        );
        binding.recyclerView.setLayoutManager(linearLayoutManager);

        //어댑터 생성
        ReviewAdapter reviewAdapter = new ReviewAdapter();

        //API 서버에서 JSON 목록 받기
        ReviewService reviewService = ServiceProvider.getReviewService(getContext());
        Call<List<ReviewListItem>> call = reviewService.getReviewList();
        call.enqueue(new Callback<List<ReviewListItem>>() {
            @Override
            public void onResponse(Call<List<ReviewListItem>> call, Response<List<ReviewListItem>> response) {
                List<ReviewListItem> list = response.body();
                reviewAdapter.setList(list);
                binding.recyclerView.setAdapter(reviewAdapter);
            }

            @Override
            public void onFailure(Call<List<ReviewListItem>> call, Throwable t) {
                Log.i(TAG, "FAIL");
                t.printStackTrace();
            }
        });
    }

    private void initBtnBack() {
        binding.btnBack.setOnClickListener(v -> {
            navController.navigate(R.id.action_review_to_editReview);
        });
    }
}