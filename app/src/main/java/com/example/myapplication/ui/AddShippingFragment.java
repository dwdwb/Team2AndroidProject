package com.example.myapplication.ui;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentAddShippingBinding;
import com.example.myapplication.dto.AddressResult;
import com.example.myapplication.service.AddressBookService;
import com.example.myapplication.service.ServiceProvider;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddShippingFragment extends Fragment {
    private static final int SEARCH_ADDRESS_ACTIVITY = 10000;
    private TextView et_address;
    private TextView address_name;
    private TextView address_preference;
    private TextView address_detail;
    private TextView ad_receiver_tel;
    private ActivityResultLauncher<Intent> addressLauncher;
    private static final String TAG = "AddShippingFragment";
    private FragmentAddShippingBinding binding;
    private NavController navController;

    private Button add_Shipping;

    private Button back;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_add_shipping, container, false);
        binding = FragmentAddShippingBinding.inflate(inflater);
        navController = NavHostFragment.findNavController(this);
        et_address = view.findViewById(R.id.add_shipping_address);

        Button btn_search = view.findViewById(R.id.btn_search);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // DaumAddressActivity를 시작하는 인텐트를 생성합니다.
                Intent i = new Intent(getActivity(), DaumAddressActivity.class);
                addressLauncher.launch(i);
            }
        });
        add_Shipping = view.findViewById(R.id.btn_add_shipping);
        back = view.findViewById(R.id.btn_back);
        Log.i(TAG,back+"백버튼 설정은됐니");
        Log.i(TAG,add_Shipping+"배송지추가버튼 설정은됐니");
        address_name = view.findViewById(R.id.add_shipping_name);
        address_preference = view.findViewById(R.id.add_shipping_preference);
        ad_receiver_tel = view.findViewById(R.id.add_receiver_tel);
        address_detail = view.findViewById(R.id.add_shipping_address_detail);
        initBtnAddShipping();
        initBtnBack();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    private void initBtnBack() {
        Log.i(TAG,back+"백버튼 설정은됐니");
        Log.i(TAG,add_Shipping+"배송지추가버튼 설정은됐니");
        back.setOnClickListener(v -> {
            navController.popBackStack();
        });
    }

    private void initBtnAddShipping() {
        add_Shipping.setOnClickListener(v -> {
            AddressBookService addressBookService = ServiceProvider.getAddressBookService(getContext());
            // 주소 추가에 필요한 파라미터 설정

            int shopper_no = 1; // 쇼퍼 번호


            String shipping_name = address_name.getText().toString(); // 배송지 이름
            String shipping_address = et_address.getText().toString() + " " + address_detail.getText().toString(); // 배송 주소
            String receiver_tel = ad_receiver_tel.getText().toString(); // 수령자 전화번호
            String shipping_preference = address_preference.getText().toString(); // 배송 선호도

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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addressLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent intent = result.getData();
                        if (intent != null) {
                            String data = intent.getStringExtra("data");
                            if (data != null) {
                                et_address.setText(data);
                            }
                        }
                    }
                });


    }
}
