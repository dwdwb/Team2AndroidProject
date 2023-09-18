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
import com.example.myapplication.adapter.AddressAdapter;
import com.example.myapplication.adapter.MyPageOrderedAdapter;
import com.example.myapplication.databinding.FragmentListBinding;
import com.example.myapplication.databinding.FragmentOrderHistoryBinding;
import com.example.myapplication.dto.AddressList;
import com.example.myapplication.dto.OrderHistory;
import com.example.myapplication.service.AddressBookService;
import com.example.myapplication.service.ListService;
import com.example.myapplication.service.MyPageOrderedService;
import com.example.myapplication.service.ServiceProvider;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderHistoryFragment extends Fragment {
    private static final String TAG = "OrderHistoryFragment";
    private FragmentOrderHistoryBinding binding;
    private NavController navController;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentOrderHistoryBinding.inflate(inflater);
        //NavController 얻기
        navController = NavHostFragment.findNavController(this);

        initRecyclerView();


        return binding.getRoot();
    }



    private void initRecyclerView() {
        //RecyclerView에서 항목을 수직으로 배치하도록 설정
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getContext(), LinearLayoutManager.VERTICAL, false
        );
        binding.recyclerView
                .setLayoutManager(linearLayoutManager);

        //어댑터 생성
        MyPageOrderedAdapter myPageOrderedAdapter = new MyPageOrderedAdapter();
        //Log.i(TAG, "size: " + addressAdapter.getItemCount());

        //API 서버에서 JSON 목록 받기
        MyPageOrderedService myPageOrderedService = ServiceProvider.getMyPageOrderedService(getContext());
        Call<List<OrderHistory>> call = myPageOrderedService.getOrderedList();
        call.enqueue(new Callback<List<OrderHistory>>() {
            @Override
            public void onResponse(Call<List<OrderHistory>> call, Response<List<OrderHistory>> response) {
                List<OrderHistory> list = response.body();

                // onResponse 콜백 내에서 어댑터에 데이터 설정 및 리사이클러뷰에 연결
                myPageOrderedAdapter.setList(list);
                binding.recyclerView.setAdapter(myPageOrderedAdapter);

                Log.i(TAG, "size: " + myPageOrderedAdapter.getItemCount());
            }

            @Override
            public void onFailure(Call<List<OrderHistory>> call, Throwable t) {
                Log.i(TAG, "나너무슬퍼");
                t.printStackTrace();
            }
        });

        //항목을 클릭했을 때 콜백 객체를 등록
        myPageOrderedAdapter.setOnItemClickListener(new MyPageOrderedAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Log.i(TAG, position + "번 항목 클릭됨");
                OrderHistory orderHistory = myPageOrderedAdapter.getItem(position);

                int clickProductNo = orderHistory.getProduct_no();
                Log.i(TAG, clickProductNo+"번 항목 클릭됨");

                navController.navigate(R.id.action_orderHistory_to_writeReview);

            }
        });

    }


}