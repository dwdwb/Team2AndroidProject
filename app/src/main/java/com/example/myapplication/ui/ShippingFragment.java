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

import com.example.myapplication.R;
import com.example.myapplication.adapter.AddressAdapter;
import com.example.myapplication.databinding.FragmentOrderHistoryBinding;
import com.example.myapplication.databinding.FragmentShippingBinding;
import com.example.myapplication.dto.AddressList;
import com.example.myapplication.dto.AddressResult;
import com.example.myapplication.service.AddressBookService;
import com.example.myapplication.service.ServiceProvider;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShippingFragment extends Fragment {
    private static final String TAG = "ShippingFragment";
    private FragmentShippingBinding binding;
    private NavController navController;
    AddressAdapter addressAdapter = new AddressAdapter();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentShippingBinding.inflate(inflater);

        navController = NavHostFragment.findNavController(this);

        initBtnAddShipping();
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
                binding.recyclerView.setAdapter(addressAdapter);


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

                AddressList addressList = addressAdapter.getItem(position);

              /*  Bundle args = new Bundle();
                args.putSerializable("addressList", addressList);*/

                // 클릭한 항목의 address_no를 받아옴
                int clickedAddressNo = addressList.getAddress_no();
                Log.i(TAG, clickedAddressNo + "번 항목 클릭됨");

                // 삭제 처리 메서드 호출
                deleteAddress(clickedAddressNo);

            }
        });

    }

    // 삭제 처리 메서드
    private void deleteAddress(int addressNo) {
        AddressBookService addressBookService = ServiceProvider.getAddressBookService(getContext());
        Call<AddressResult> call = addressBookService.deleteAddress(addressNo);
        call.enqueue(new Callback<AddressResult>() {
            @Override
            public void onResponse(Call<AddressResult> call, Response<AddressResult> response) {

                if (response.isSuccessful()) {
                    AddressResult addressResult = response.body();
                    Log.i(TAG, "addressResult: " + addressResult);
                    if (addressResult != null && "success".equals(addressResult.getAddress_result())) {
                        reloadAddressList();


                    } else {
                        // 삭제 실패 시 처리
                        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                                .setTitle("삭제 실패")
                                .setMessage(addressResult != null ? addressResult.getAddress_result() : "Unknown error")
                                .setPositiveButton("확인", null)
                                .create();
                        alertDialog.show();
                    }
                } else {
                    // 응답이 성공적이지 않은 경우 처리
                    // 여기에 필요한 오류 처리 코드를 추가하세요.
                }
            }

            @Override
            public void onFailure(Call<AddressResult> call, Throwable t) {
                // 네트워크 오류 등의 실패 처리
                t.printStackTrace();
            }
        });
    }

    private void reloadAddressList() {
        AddressBookService addressBookService = ServiceProvider.getAddressBookService(getContext());
        Call<List<AddressList>> call = addressBookService.getAddressList();
        call.enqueue(new Callback<List<AddressList>>() {
            @Override
            public void onResponse(Call<List<AddressList>> call, Response<List<AddressList>> response) {
                if (response.isSuccessful()) {
                    List<AddressList> list = response.body();
                    // 어댑터에 새로운 목록 설정
                    addressAdapter.setList(list);
                    // 어댑터 갱신
                    addressAdapter.notifyDataSetChanged();
                } else {
                    // 응답이 성공적이지 않은 경우 처리

                }
            }

            @Override
            public void onFailure(Call<List<AddressList>> call, Throwable t) {
                // 네트워크 오류 등의 실패 처리
                t.printStackTrace();
            }
        });
    }

    private void initBtnAddShipping() {
        binding.btnAddShipping.setOnClickListener(v -> {
            navController.navigate(R.id.action_shipping_to_addShipping);
        });
    }


}