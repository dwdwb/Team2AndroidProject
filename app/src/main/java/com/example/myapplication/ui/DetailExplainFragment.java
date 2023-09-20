package com.example.myapplication.ui;

import android.graphics.drawable.Drawable;
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
import com.example.myapplication.dto.ReviewInfo;
import com.example.myapplication.service.CartService;
import com.example.myapplication.service.DetailViewService;
import com.example.myapplication.service.ServiceProvider;

import java.text.DecimalFormat;
import java.util.Currency;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailExplainFragment extends Fragment {
    private static final String TAG = "DetailExplainFragment";
    private FragmentDetailExplainBinding binding;
    private boolean isWishToggled;
    private int bno;
    private String bname;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDetailExplainBinding.inflate(inflater);

        //Bundle bundle = getArguments();
        //int bno = bundle.getInt("bno");

        //화면 초기화
        initView();

        //이미지 불러오기
        initContentImageRecyclerView();

        initBtnOrder();

        isWishToggled = false;
        initBtnWish();

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    //화면 초기화
    public void initView() {
        //DetailViewService.loadProductImage(1, binding.productImage);

        DetailViewService.loadMainImage(bno, binding.productImage);

        //API 서버에서 JSON 목록 받기
        DetailViewService detailViewService = ServiceProvider.getDetailViewService(getContext());
        Call<ProductBoard> call = detailViewService.getBoard(bno);
        call.enqueue(new Callback<ProductBoard>() {
            @Override
            public void onResponse(Call<ProductBoard> call, Response<ProductBoard> response) {
                //JSON -> List<Board> 변환
                ProductBoard productBoard = response.body();

                DecimalFormat df = new DecimalFormat("#,###,###");

                bname = productBoard.getProductName();
                binding.productName.setText(bname);
                if(productBoard.getDiscountRate() == 0) {
                    binding.productDiscountRate.setVisibility(View.GONE);
                    binding.productOriginalPrice.setVisibility(View.GONE);
                } else {
                    binding.productDiscountRate.setText(productBoard.getDiscountRate() + "%");
                    binding.productOriginalPrice.setText(df.format(productBoard.getProductPrice()) + "원");
                }
                binding.productDiscountPrice.setText(df.format(productBoard.getDiscountPrice()) + "원");

                Log.i(TAG, productBoard + "");
            }

            @Override
            public void onFailure(Call<ProductBoard> call, Throwable t) {
                Log.i(TAG, "안됨......");
            }
        });

        Call<ReviewInfo> callRating = detailViewService.getReviewInfo(bno);
        callRating.enqueue(new Callback<ReviewInfo>() {
            @Override
            public void onResponse(Call<ReviewInfo> call, Response<ReviewInfo> response) {
                ReviewInfo reviewInfo = response.body();
                Log.i(TAG, "reviewInfo가 왜 null이지....?" + reviewInfo);
                binding.rating.setRating(reviewInfo.getStarRateAvg()*5/100);
                binding.reviewCount.setText(reviewInfo.getReviewCount() + "개 상품평");
            }

            @Override
            public void onFailure(Call<ReviewInfo> call, Throwable t) {

            }
        });


        //찜 버튼 활성화 여부 결정

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
        Call<List<Integer>> call = detailViewService.getMediaNoList(bno);
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

    private void initBtnOrder() {
        binding.btnBuy.setOnClickListener(v -> {
            DetailBottomSheetDialogFragment bottomSheet = new DetailBottomSheetDialogFragment();
            bottomSheet.setBname(bname);
            bottomSheet.show(getActivity().getSupportFragmentManager(), bottomSheet.getTag());
            /*bottomSheet.show(getSupportFragmentManager(), bottomSheet.getTag());*/
        });
    }

    private void initBtnWish() {
        binding.wishBtn.setOnClickListener(v -> {
            if (isWishToggled == false) {
                binding.wishBtn.setImageResource(R.drawable.ic_wish_filled_36dp);
                isWishToggled = true;
            } else {
                binding.wishBtn.setImageResource(R.drawable.ic_wish_36dp);
                isWishToggled = false;
            }
        });
    }


    public int getBno() {
        return bno;
    }

    public void setBno(int bno) {
        this.bno = bno;
    }
}