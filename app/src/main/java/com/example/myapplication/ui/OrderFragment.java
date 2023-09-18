package com.example.myapplication.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentOrderBinding;
import com.example.myapplication.datastore.AppKeyValueStore;
import com.example.myapplication.dto.AddressList;
import com.example.myapplication.dto.Shopper;
import com.example.myapplication.service.ServiceProvider;
import com.example.myapplication.service.ShopperService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OrderFragment extends Fragment {

    private FragmentOrderBinding binding;

    private NavController navController;

    private static final String TAG = "OrderFragment";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOrderBinding.inflate(inflater);

        //NavController 얻기
        navController = NavHostFragment.findNavController(this);

        initBtnOrderHistory();
        initBtnOrderShipping();

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String shopperId = AppKeyValueStore.getValue(getContext(), "shopperId");
        ShopperService shopperService = ServiceProvider.getShopperService(getContext());
        Log.i(TAG, "나쇼퍼아이디임"+shopperId);
        Call<Shopper> call = shopperService.getShopper(shopperId);

        call.enqueue(new Callback<Shopper>() {
            @Override
            public void onResponse(Call<Shopper> call, Response<Shopper> response) {
                Shopper shopper = response.body();
                Log.i(TAG, "나쇼퍼임"+shopper.toString());
                TextView shopperName = view.findViewById(R.id.order_shopper_name);
                shopperName.setText(shopper.getShopperName());
                TextView shopperTel = view.findViewById(R.id.order_shopper_tel);
                shopperTel.setText(shopper.getShopperTel());

            }

            @Override
            public void onFailure(Call<Shopper> call, Throwable t) {

            }
        });


        // Fragment에서 FragmentResultListener 구현
        getParentFragmentManager().setFragmentResultListener("selectedAddress", getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                if (requestKey.equals("selectedAddress")) {
                    AddressList selectedAddress = (AddressList) result.getSerializable("selectedAddress");
                    Log.i(TAG, "나SELECTED된 주소임"+selectedAddress.toString());
                    // AddressList 객체를 사용하여 뷰에 데이터 설정
                    if (selectedAddress != null) {
                        // 예시: TextView에 데이터 설정
                        TextView shipping_name = view.findViewById(R.id.order_shipping_name);
                        shipping_name.setText(selectedAddress.getShipping_name());

                        TextView shipping_address = view.findViewById(R.id.order_shipping_address);
                        shipping_address.setText(selectedAddress.getShipping_address());

                        TextView receiver_tel = view.findViewById(R.id.order_receiver_tel);
                        receiver_tel.setText(selectedAddress.getReceiver_tel());

                        TextView shipping_preference = view.findViewById(R.id.order_shipping_preference);
                        shipping_preference.setText(selectedAddress.getShipping_preference());

                    }

                }
            }
        });


    }



    private void initBtnOrderShipping() {
        binding.btnOrderShipping.setOnClickListener(v -> {
            navController.navigate(R.id.action_order_to_orderShipping);

        });
    }

    private void  initBtnOrderHistory() {
        binding.btnOrderHistory.setOnClickListener(v -> {
            navController.navigate(R.id.action_order_to_orderHistory);

        });

    }









}