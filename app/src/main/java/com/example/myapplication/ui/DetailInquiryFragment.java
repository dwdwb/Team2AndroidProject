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
import com.example.myapplication.adapter.CartProductAdapter;
import com.example.myapplication.adapter.DetailInquiryAdapter;
import com.example.myapplication.databinding.FragmentDetailInquiryBinding;
import com.example.myapplication.dto.CartProduct;
import com.example.myapplication.dto.ProductInquiry;
import com.example.myapplication.service.CartService;
import com.example.myapplication.service.DetailViewService;
import com.example.myapplication.service.ServiceProvider;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailInquiryFragment extends Fragment {
    private static final String TAG = "DetailInquiryFragment";
    private FragmentDetailInquiryBinding binding;
    private NavController navController;
    private int bno;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDetailInquiryBinding.inflate(inflater);

        //NavController 얻기
        navController = NavHostFragment.findNavController(this);

        //RecyclerView 초기화
        initRecyclerView();

        //버튼 초기화
        initWriteInquiry();

        return binding.getRoot();
    }

    private void initWriteInquiry() {
        binding.btnWriteInquiry.setOnClickListener(v -> {
            navController.navigate(R.id.action_detail_to_writeInquiry);
        });
    }

    private void initRecyclerView() {
        //RecyclerView에서 항목을 수직으로 배치하도록 설정
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getContext(), LinearLayoutManager.VERTICAL, false
        );
        binding.recyclerView.setLayoutManager(linearLayoutManager);

        //어댑터 생성
        DetailInquiryAdapter detailInquiryAdapter = new DetailInquiryAdapter();

        //API 서버에서 JSON 목록 받기
        DetailViewService detailViewService = ServiceProvider.getDetailViewService(getContext());
        Call<List<ProductInquiry>> call = detailViewService.getInquiryList(bno);
        call.enqueue(new Callback<List<ProductInquiry>>() {
            @Override
            public void onResponse(Call<List<ProductInquiry>> call, Response<List<ProductInquiry>> response) {
                //JSON -> List<Board> 변환
                List<ProductInquiry> list = response.body();
                Log.i(TAG, list + "");
                //어댑터 데이터 세팅
                detailInquiryAdapter.setList(list);
                //RecyclerView에 어댑터 세팅
                binding.recyclerView.setAdapter(detailInquiryAdapter);
            }

            @Override
            public void onFailure(Call<List<ProductInquiry>> call, Throwable t) {
                Log.i(TAG, "안됨......");
            }
        });

        //항목을 클릭했을 때 콜백 객체를 등록
        detailInquiryAdapter.setOnItemClickListener(new DetailInquiryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Log.i(TAG, position + "항목 클릭됨");
                ProductInquiry productInquiry = detailInquiryAdapter.getItem(position);

                Bundle args = new Bundle();
                args.putSerializable("productInquiry", productInquiry);
                //navController.navigate(R.id.action_dest_list_to_dest_detail, args);
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