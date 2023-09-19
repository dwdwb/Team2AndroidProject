package com.example.myapplication.ui;

import static android.app.Activity.RESULT_OK;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.DaumAddressActivity;
import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentAddShippingBinding;
import com.example.myapplication.dto.AddressResult;
import com.example.myapplication.service.AddressBookService;
import com.example.myapplication.service.ServiceProvider;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddShippingFragment extends Fragment {
    private static final String TAG = "AddShippingFragment";
    private FragmentAddShippingBinding binding;
    private NavController navController;

    private Button edit_addr;
    private static final int SEARCH_ADDRESS_ACTIVITY = 1002;
    private TextView et_order_zipcode;
    private TextView et_order_address1;
    private ActivityResultLauncher<Intent> addressLauncher;

    //private Button edit_addr;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAddShippingBinding.inflate(inflater);
        navController = NavHostFragment.findNavController(this);

        initBtnAddShipping();
        initBtnBack();


        et_order_zipcode = binding.addShippingAddress;
        et_order_address1 = binding.addShippingAddressDetail;
        edit_addr = binding.editAddr;

        addressLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            Intent intent = result.getData();
                            if (intent != null) {
                                String data = intent.getStringExtra("data");
                                if (data != null) {
                                    // data의 정보를 각각 우편번호와 실주소로 나누어 EditText에 표시
                                    et_order_zipcode.setText(data.substring(0, 5));
                                    et_order_address1.setText(data.substring(7));
                                }
                            }
                        }
                    }
                });

        edit_addr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), DaumAddressActivity.class);
                addressLauncher.launch(intent);
            }
        });


        return binding.getRoot();
    }


    private void initBtnAddShipping() {
        binding.btnAddShipping.setOnClickListener(v -> {
            AddressBookService addressBookService = ServiceProvider.getAddressBookService(getContext());
            // 주소 추가에 필요한 파라미터 설정

            int shopper_no = 1; // 쇼퍼 번호
            String shipping_name = binding.addShippingName.getText().toString(); // 배송지 이름
            String shipping_address = binding.addShippingAddress.getText().toString() + " " + binding.addShippingAddressDetail.getText().toString(); // 배송 주소
            String receiver_tel = binding.addReceiverTel.getText().toString(); // 수령자 전화번호
            String shipping_preference = binding.addShippingPreference.getText().toString(); // 배송 선호도

            // 주소 추가 요청 만들기
            Call<AddressResult> call = addressBookService.addAddress(
                    shopper_no,
                    shipping_name,
                    shipping_address,
                    receiver_tel,
                    shipping_preference
            );

            // 요청 보내기
            call.enqueue(new Callback<AddressResult>() {
                @Override
                public void onResponse(Call<AddressResult> call, Response<AddressResult> response) {
                    AddressResult addressResult = response.body();
                    Log.i(TAG, addressResult.toString());
                    AlertDialog alertDialog;
                    if (addressResult.getAddress_result().equals("success")) {
                        alertDialog = new AlertDialog.Builder(getContext())
                                .setTitle("주소지가 성공적으로 추가되었습니다.")
                                .setMessage(addressResult.getAddress_result())
                                .setPositiveButton("확인", null)
                                .create();

                        navController.popBackStack();


                        // 주소 추가 성공 시 처리
                        // 주소 목록 화면으로 이동하거나 다른 작업 수행
                    } else {
                        // 주소 추가 실패 시 처리
                        alertDialog = new AlertDialog.Builder(getContext())
                                .setTitle("주소 추가 실패")
                                .setMessage(addressResult.getAddress_result())
                                .setPositiveButton("확인", null)
                                .create();
                    }
                    alertDialog.show();
                }

                @Override
                public void onFailure(Call<AddressResult> call, Throwable t) {
                    // 네트워크 오류 등의 실패 처리
                    t.printStackTrace();
                }
            });

        });
    }


    private void initBtnBack() {
        binding.btnBack.setOnClickListener(v -> {
            navController.popBackStack();
        });
    }
}