package com.example.myapplication.ui;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adapter.OrderAdapter;
import com.example.myapplication.adapter.OrderCartAdapter;
import com.example.myapplication.databinding.FragmentOrderBinding;
import com.example.myapplication.datastore.AppKeyValueStore;
import com.example.myapplication.dto.AddressList;
import com.example.myapplication.dto.CartProduct;
import com.example.myapplication.dto.ProductBoard;
import com.example.myapplication.dto.Shopper;
import com.example.myapplication.service.ServiceProvider;
import com.example.myapplication.service.ShopperService;

import java.text.DecimalFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OrderFragment extends Fragment {

    private FragmentOrderBinding binding;

    private NavController navController;

    private Button payButtonCard;
    private Button payButtonAccount;
    private Button payButtonNoBank;
    private Button payButtonPhone;
    private boolean isCardToggled;
    private boolean isAccountToggled;
    private boolean isPhoneToggled;
    private boolean isNoBankToggled;
    private Spinner spinner;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;
    private static final String TAG = "OrderFragment";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOrderBinding.inflate(inflater);

        //NavController 얻기
        navController = NavHostFragment.findNavController(this);

        spinner = binding.orderSpinner;
        arrayList = new ArrayList<>();
        arrayList.add("휴대폰번호");
        arrayList.add("현금영수증카드");

        arrayAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item, arrayList);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), arrayList.get(position)+"가 선택 되었습니다.",
                        Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        payButtonCard = binding.card;
        payButtonAccount = binding.account;
        payButtonNoBank = binding.noBank;
        payButtonPhone = binding.phone;
        isCardToggled = false;
        isAccountToggled = false;
        isPhoneToggled = false;
        isNoBankToggled = false;

        initBtnPhone();
        initBtnNoBank();
        initBtnCard();
        initBtnAccount();
        initBtnOrderHistory();
        initBtnOrderShipping();


        Bundle bundle = getArguments();
        if (bundle.getSerializable("productList") != null) {
            initRecyclerView();
            ArrayList<ProductBoard> productList = (ArrayList<ProductBoard>) bundle.getSerializable("productList");
            Log.i(TAG, productList.toString());

            int totalPrice = 0;
            for(ProductBoard productBoard : productList) {
                int stock = productBoard.getStock();
                int price = productBoard.getDiscountPrice();
                int orderPrice = stock * price;

                totalPrice += orderPrice;
            }
            DecimalFormat decimalFormat = new DecimalFormat("#,###");
            binding.orderTotalPrice.setText(decimalFormat.format(totalPrice)+"원");
            binding.discountPrice.setText("-"+0+"원");
            if(totalPrice > 30000) {
                binding.delFee.setText(0+"원");
                binding.finalPrice.setText(decimalFormat.format(totalPrice)+"원");
            } else {
                binding.delFee.setText(3000+"원");
                binding.finalPrice.setText(decimalFormat.format(totalPrice+3000)+"원");
            }
        } else if(bundle.getSerializable("cartProductList") != null) {
            initRecyclerView();
            ArrayList<CartProduct> cartProductList = (ArrayList<CartProduct>) bundle.getSerializable("cartProductList");
            String totalPrice = bundle.getString("totalPrice");
            String couponList = bundle.getString("couponList");
            String totalDiscountPrice = bundle.getString("totalDiscountPrice");
            String totalShippingPrice = bundle.getString("totalShippingPrice");
            String orderPrice = bundle.getString("orderPrice");

            binding.orderTotalPrice.setText(totalPrice);
            binding.discountPrice.setText(totalDiscountPrice);
            binding.delFee.setText(totalShippingPrice);
            binding.finalPrice.setText(orderPrice);
        }


        return binding.getRoot();
    }

    private void initBtnAccount() {

        payButtonAccount.setOnClickListener(v -> {
            if(isCardToggled || isNoBankToggled || isPhoneToggled) {
                payButtonNoBank.setTextColor(Color.parseColor("#A0DE5F"));
                ViewCompat.setBackgroundTintList(payButtonNoBank, ColorStateList.valueOf(Color.parseColor("#ffffff")));
                payButtonCard.setTextColor(Color.parseColor("#A0DE5F"));
                ViewCompat.setBackgroundTintList(payButtonCard, ColorStateList.valueOf(Color.parseColor("#ffffff")));
                payButtonPhone.setTextColor(Color.parseColor("#A0DE5F"));
                ViewCompat.setBackgroundTintList(payButtonPhone, ColorStateList.valueOf(Color.parseColor("#ffffff")));
            }
            isCardToggled = false;
            isNoBankToggled = false;
            isPhoneToggled = false;
            isAccountToggled = true;

            payButtonAccount.setTextColor(Color.parseColor("#ffffff"));
            ViewCompat.setBackgroundTintList(payButtonAccount, ColorStateList.valueOf(Color.parseColor("#A0DE5F")));

        });
    }

    private void initBtnCard() {

        payButtonCard.setOnClickListener(v -> {
            if(isAccountToggled || isNoBankToggled || isPhoneToggled) {
                payButtonNoBank.setTextColor(Color.parseColor("#A0DE5F"));
                ViewCompat.setBackgroundTintList(payButtonNoBank, ColorStateList.valueOf(Color.parseColor("#ffffff")));
                payButtonAccount.setTextColor(Color.parseColor("#A0DE5F"));
                ViewCompat.setBackgroundTintList(payButtonAccount, ColorStateList.valueOf(Color.parseColor("#ffffff")));
                payButtonPhone.setTextColor(Color.parseColor("#A0DE5F"));
                ViewCompat.setBackgroundTintList(payButtonPhone, ColorStateList.valueOf(Color.parseColor("#ffffff")));
            }

            isCardToggled = true;
            isNoBankToggled = false;
            isPhoneToggled = false;
            isAccountToggled = false;

            payButtonCard.setTextColor(Color.parseColor("#ffffff"));
            ViewCompat.setBackgroundTintList(payButtonCard, ColorStateList.valueOf(Color.parseColor("#A0DE5F")));


        });
    }

    private void initBtnNoBank() {

        payButtonNoBank.setOnClickListener(v -> {
            if(isCardToggled || isAccountToggled || isPhoneToggled) {
                payButtonAccount.setTextColor(Color.parseColor("#A0DE5F"));
                ViewCompat.setBackgroundTintList(payButtonAccount, ColorStateList.valueOf(Color.parseColor("#ffffff")));
                payButtonCard.setTextColor(Color.parseColor("#A0DE5F"));
                ViewCompat.setBackgroundTintList(payButtonCard, ColorStateList.valueOf(Color.parseColor("#ffffff")));
                payButtonPhone.setTextColor(Color.parseColor("#A0DE5F"));
                ViewCompat.setBackgroundTintList(payButtonPhone, ColorStateList.valueOf(Color.parseColor("#ffffff")));
            }
            isCardToggled = false;
            isNoBankToggled = true;
            isPhoneToggled = false;
            isAccountToggled = false;
            payButtonNoBank.setTextColor(Color.parseColor("#ffffff"));
            ViewCompat.setBackgroundTintList(payButtonNoBank, ColorStateList.valueOf(Color.parseColor("#A0DE5F")));

        });
    }

    private void initBtnPhone() {
        payButtonPhone.setOnClickListener(v -> {
            if(isCardToggled || isAccountToggled || isNoBankToggled) {
                payButtonAccount.setTextColor(Color.parseColor("#A0DE5F"));
                ViewCompat.setBackgroundTintList(payButtonAccount, ColorStateList.valueOf(Color.parseColor("#ffffff")));
                payButtonCard.setTextColor(Color.parseColor("#A0DE5F"));
                ViewCompat.setBackgroundTintList(payButtonCard, ColorStateList.valueOf(Color.parseColor("#ffffff")));
                payButtonNoBank.setTextColor(Color.parseColor("#A0DE5F"));
                ViewCompat.setBackgroundTintList(payButtonNoBank, ColorStateList.valueOf(Color.parseColor("#ffffff")));
            }
            isCardToggled = false;
            isNoBankToggled = false;
            isPhoneToggled = true;
            isAccountToggled = false;
            payButtonPhone.setTextColor(Color.parseColor("#ffffff"));
            ViewCompat.setBackgroundTintList(payButtonPhone, ColorStateList.valueOf(Color.parseColor("#A0DE5F")));

        });
    }


    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getContext(), LinearLayoutManager.VERTICAL, false
        );
        binding.recyclerView.setLayoutManager(linearLayoutManager);


        Bundle bundle = getArguments();
        if (bundle.getSerializable("productList") != null) {
            OrderAdapter orderAdapter = new OrderAdapter();
            ArrayList<ProductBoard> productList = (ArrayList<ProductBoard>) bundle.getSerializable("productList");
            Log.i(TAG, productList.toString());
            orderAdapter.setList(productList);
            binding.recyclerView.setAdapter(orderAdapter);
        } else if (bundle.getSerializable("cartProductList") != null) {
            OrderCartAdapter orderCartAdapter = new OrderCartAdapter();
            ArrayList<CartProduct> cartProductList = (ArrayList<CartProduct>) bundle.getSerializable("cartProductList");
            orderCartAdapter.setList(cartProductList);
            binding.recyclerView.setAdapter(orderCartAdapter);
        }
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