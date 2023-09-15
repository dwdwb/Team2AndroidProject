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
import com.example.myapplication.adapter.InquiryAdapter;
import com.example.myapplication.adapter.InquiryProductAdapter;
import com.example.myapplication.databinding.FragmentInquiryBinding;
import com.example.myapplication.databinding.FragmentShippingBinding;
import com.example.myapplication.dto.CartProduct;
import com.example.myapplication.dto.ProductInquiry;
import com.example.myapplication.service.CartService;
import com.example.myapplication.service.MyPageShopperInquiryService;
import com.example.myapplication.service.ServiceProvider;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InquiryFragment extends Fragment {
    private static final String TAG = "InquiryFragment";
    private FragmentInquiryBinding binding;
    private NavController navController;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentInquiryBinding.inflate(inflater);

        navController = NavHostFragment.findNavController(this);

        //RecyclerView 초기화
        initRecyclerView();

        return binding.getRoot();
    }

    /*private void initRecyclerView() {
        //RecyclerView에서 항목을 수직으로 배치하도록 설정
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getContext(), LinearLayoutManager.VERTICAL, false
        );
        binding.recyclerView.setLayoutManager(linearLayoutManager);

        //어댑터 생성
        InquiryAdapter inquiryAdapter = new InquiryAdapter();

        //API 서버에서 JSON 목록 받기
        MyPageShopperInquiryService myPageShopperInquiryService = ServiceProvider.getMyPageShopperInquiryService(getContext());
        Call<List<ProductInquiry>> call = myPageShopperInquiryService.getInquiryList(1);
        call.enqueue(new Callback<List<ProductInquiry>>() {
            @Override
            public void onResponse(Call<List<ProductInquiry>> call, Response<List<ProductInquiry>> response) {
                //JSON -> List<Board> 변환
                List<ProductInquiry> list = response.body();
                Log.i(TAG, list + "");
                //어댑터 데이터 세팅
                inquiryAdapter.setList(list);
                //RecyclerView에 어댑터 세팅
                binding.recyclerView.setAdapter(inquiryAdapter);
            }

            @Override
            public void onFailure(Call<List<ProductInquiry>> call, Throwable t) {
                Log.i(TAG, "안됨......");
            }
        });

        //항목을 클릭했을 때 콜백 객체를 등록
        inquiryAdapter.setOnItemClickListener(new InquiryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Log.i(TAG, position + "항목 클릭됨");
                ProductInquiry productInquiry = inquiryAdapter.getItem(position);

                Bundle args = new Bundle();
                args.putSerializable("productInquiry", productInquiry);
                //navController.navigate(R.id.action_dest_list_to_dest_detail, args);
            }
        });
    }*/

    private void initRecyclerView() {
        //RecyclerView에서 항목을 수직으로 배치하도록 설정
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getContext(), LinearLayoutManager.VERTICAL, false
        );
        binding.recyclerView.setLayoutManager(linearLayoutManager);

        //어댑터 생성
        InquiryProductAdapter inquiryProductAdapter = new InquiryProductAdapter();

        //API 서버에서 JSON 목록 받기
        MyPageShopperInquiryService myPageShopperInquiryService = ServiceProvider.getMyPageShopperInquiryService(getContext());

        Call<List<String>> call = myPageShopperInquiryService.getInquiryProductList(1);

        /*Call<List<ProductInquiry>> call = myPageShopperInquiryService.getInquiryList(1);*/
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                //JSON -> List<Board> 변환
                List<String> list = response.body();
                Log.i(TAG, list + "");
                //어댑터 데이터 세팅
                inquiryProductAdapter.setList(list);
                //RecyclerView에 어댑터 세팅
                binding.recyclerView.setAdapter(inquiryProductAdapter);
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Log.i(TAG, "안됨......");
            }
        });

        //항목을 클릭했을 때 콜백 객체를 등록
        inquiryProductAdapter.setOnItemClickListener(new InquiryProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Log.i(TAG, position + "항목 클릭됨");
                String productName = inquiryProductAdapter.getItem(position);

                Bundle args = new Bundle();
                args.putSerializable("productName", productName);
                //navController.navigate(R.id.action_dest_list_to_dest_detail, args);
            }
        });
    }
}