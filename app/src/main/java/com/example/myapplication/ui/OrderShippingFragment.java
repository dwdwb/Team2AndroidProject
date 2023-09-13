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
import com.example.myapplication.adapter.ListAdapter;
import com.example.myapplication.databinding.FragmentOrderBinding;
import com.example.myapplication.databinding.FragmentOrderShippingBinding;
import com.example.myapplication.dto.AddressList;
import com.example.myapplication.dto.MobileProductForList;
import com.example.myapplication.service.AddressBookService;
import com.example.myapplication.service.ListService;
import com.example.myapplication.service.ServiceProvider;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OrderShippingFragment extends Fragment {

    private static final String TAG = "OrderShippingFragment";
    private FragmentOrderShippingBinding binding;
    private NavController navController;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentOrderShippingBinding.inflate(inflater);
        //NavController 얻기
        navController = NavHostFragment.findNavController(this);

        initRecyclerView();
        initBtnShippingAdd();
        return binding.getRoot();
    }

    private void initBtnShippingAdd() {
        binding.btnOrderShippingAdd.setOnClickListener(v -> {
            navController.navigate(R.id.action_orderShipping_to_addShipping);

        });
    }

    private void initRecyclerView() {
        //RecyclerView에서 항목을 수직으로 배치하도록 설정
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getContext(), LinearLayoutManager.VERTICAL, false
        );
        binding.recyclerView2
                .setLayoutManager(linearLayoutManager);

        //어댑터 생성
        AddressAdapter addressAdapter = new AddressAdapter();
        //Log.i(TAG, "size: " + addressAdapter.getItemCount());

        //API 서버에서 JSON 목록 받기
        AddressBookService addressBookService = ServiceProvider.getAddressBookService(getContext());
        Call<List<AddressList>> call = addressBookService.getAddressList();
        call.enqueue(new Callback<List<AddressList>>() {
            @Override
            public void onResponse(Call<List<AddressList>> call, Response<List<AddressList>> response) {
                List<AddressList> list = response.body();

                // onResponse 콜백 내에서 어댑터에 데이터 설정 및 리사이클러뷰에 연결
                addressAdapter.setList(list);
                binding.recyclerView2.setAdapter(addressAdapter);

                Log.i(TAG, "size: " + addressAdapter.getItemCount());
            }

            @Override
            public void onFailure(Call<List<AddressList>> call, Throwable t) {
                Log.i(TAG, "나너무슬퍼");
                t.printStackTrace();
            }
        });

        //항목을 클릭했을 때 콜백 객체를 등록
       addressAdapter.setOnItemClickListener(new AddressAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Log.i(TAG, position + "번 항목 클릭됨");
                AddressList addressList = addressAdapter.getItem(position);

                Bundle args = new Bundle();
                args.putSerializable("addressList", addressList);

            }
        });

    }

}