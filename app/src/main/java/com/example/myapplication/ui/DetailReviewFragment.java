package com.example.myapplication.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.adapter.DetailInquiryAdapter;
import com.example.myapplication.adapter.DetailReviewAdapter;
import com.example.myapplication.databinding.FragmentDetailReviewBinding;
import com.example.myapplication.dto.ProductInquiry;
import com.example.myapplication.dto.Review;
import com.example.myapplication.dto.ReviewInfo;
import com.example.myapplication.service.DetailViewService;
import com.example.myapplication.service.ServiceProvider;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailReviewFragment extends Fragment {
    private static final String TAG = "DetailReviewFragment";
    private FragmentDetailReviewBinding binding;
    private int bno;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDetailReviewBinding.inflate(inflater);

        //화면 초기화
        initView();

        //RecyclerView 초기화
        initRecyclerView("최신순");

        initAlign();

        Log.i(TAG, "review에서 bno: " + bno);

        return binding.getRoot();
    }

    private void initView() {
        DetailViewService detailViewService = ServiceProvider.getDetailViewService(getContext());
        Call<ReviewInfo> call = detailViewService.getReviewInfo(bno);
        call.enqueue(new Callback<ReviewInfo>() {
            @Override
            public void onResponse(Call<ReviewInfo> call, Response<ReviewInfo> response) {
                ReviewInfo reviewInfo = response.body();
                if(reviewInfo != null) {
                    binding.ratingTotal.setRating(reviewInfo.getStarRateAvg()*5/100);
                    binding.ratingTotalTxt.setText(String.valueOf(Math.round((reviewInfo.getStarRateAvg()*5/100.0)*10)/10.0));
                    binding.ratingCount.setText("(" + reviewInfo.getReviewCount() + ")");
                }
            }

            @Override
            public void onFailure(Call<ReviewInfo> call, Throwable t) {

            }
        });
    }

    private void initRecyclerView(String filterKeyword) {
        //RecyclerView에서 항목을 수직으로 배치하도록 설정
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getContext(), LinearLayoutManager.VERTICAL, false
        );
        binding.recyclerView.setLayoutManager(linearLayoutManager);

        //어댑터 생성
        DetailReviewAdapter detailReviewAdapter = new DetailReviewAdapter();

        DetailViewService detailViewService = ServiceProvider.getDetailViewService(getContext());
        if(filterKeyword.equals("최신순")) {
            //API 서버에서 JSON 목록 받기
            Call<List<Review>> call = detailViewService.getRecentReviewList(bno);
            call.enqueue(new Callback<List<Review>>() {
                @Override
                public void onResponse(Call<List<Review>> call, Response<List<Review>> response) {
                    //JSON -> List<Board> 변환
                    List<Review> list = response.body();
                    Log.i(TAG, list + "");
                    if(list != null) {
                        //어댑터 데이터 세팅
                        detailReviewAdapter.setList(list);
                        //RecyclerView에 어댑터 세팅
                        binding.recyclerView.setAdapter(detailReviewAdapter);
                    }
                }

                @Override
                public void onFailure(Call<List<Review>> call, Throwable t) {
                    Log.i(TAG, "최신순 안됨......");
                }
            });
        } else if (filterKeyword.equals("베스트순")) {
            //API 서버에서 JSON 목록 받기
            Call<List<Review>> call = detailViewService.getBestReviewList(bno);
            call.enqueue(new Callback<List<Review>>() {
                @Override
                public void onResponse(Call<List<Review>> call, Response<List<Review>> response) {
                    //JSON -> List<Board> 변환
                    List<Review> list = response.body();
                    Log.i(TAG, list + "");
                    if(list != null) {
                        //어댑터 데이터 세팅
                        detailReviewAdapter.setList(list);
                        //RecyclerView에 어댑터 세팅
                        binding.recyclerView.setAdapter(detailReviewAdapter);
                    }
                }

                @Override
                public void onFailure(Call<List<Review>> call, Throwable t) {
                    Log.i(TAG, "베스트순 안됨......");
                }
            });
        }
        /*
        //API 서버에서 JSON 목록 받기
        DetailViewService detailViewService = ServiceProvider.getDetailViewService(getContext());
        Call<List<Review>> call = detailViewService.getReviewList(bno);
        call.enqueue(new Callback<List<Review>>() {
            @Override
            public void onResponse(Call<List<Review>> call, Response<List<Review>> response) {
                //JSON -> List<Board> 변환
                List<Review> list = response.body();
                Log.i(TAG, list + "");
                if(list != null) {
                    //어댑터 데이터 세팅
                    detailReviewAdapter.setList(list);
                    //RecyclerView에 어댑터 세팅
                    binding.recyclerView.setAdapter(detailReviewAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Review>> call, Throwable t) {
                Log.i(TAG, "안됨......");
            }
        });*/

        //항목을 클릭했을 때 콜백 객체를 등록
        /*detailReviewAdapter.setOnItemClickListener(new DetailReviewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Log.i(TAG, position + "항목 클릭됨");
                Review review = detailReviewAdapter.getItem(position);

                Bundle args = new Bundle();
                args.putSerializable("review", review);
                //navController.navigate(R.id.action_dest_list_to_dest_detail, args);
            }
        });*/
    }

    private void initAlign() {
        binding.alignLayout.setOnClickListener(v -> {
            //PopupMenu 객체 생성
            PopupMenu popup= new PopupMenu(getContext(), binding.alignTxt); //두 번째 파라미터가 팝업메뉴가 붙을 뷰
            //PopupMenu popup= new PopupMenu(MainActivity.this, btn2); //첫번째 버튼을 눌렀지만 팝업메뉴는 btn2에 붙어서 나타남
            popup.getMenuInflater().inflate(R.menu.review_optoin_menu, popup.getMenu());
            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    if (menuItem.getItemId() == R.id.align_recent){
                        Log.i(TAG, "최신순 선택");
                        initRecyclerView("최신순");
                        binding.alignTxt.setText("최신순");

                    }else if (menuItem.getItemId() == R.id.align_best){
                        Log.i(TAG, "베스트순 선택");
                        initRecyclerView("베스트순");
                        binding.alignTxt.setText("베스트순");
                    }

                    return false;
                }
            });
            popup.show();
        });
    }

    public int getBno() {
        return bno;
    }

    public void setBno(int bno) {
        this.bno = bno;
    }
}