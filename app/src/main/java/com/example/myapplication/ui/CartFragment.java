package com.example.myapplication.ui;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.CartCouponAdapter;
import com.example.myapplication.adapter.CartProductAdapter;
import com.example.myapplication.databinding.FragmentCartBinding;
import com.example.myapplication.databinding.FragmentDetailBinding;
import com.example.myapplication.dto.CartProduct;
import com.example.myapplication.dto.Coupon;
import com.example.myapplication.dto.ProductBoard;
import com.example.myapplication.service.CartService;
import com.example.myapplication.service.ServiceProvider;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartFragment extends Fragment {
    private static final String TAG = "CartFragment";
    private FragmentCartBinding binding;
    private NavController navController;
    private List<CartProduct> cartProductList = new ArrayList<>();
    private List<Coupon> couponList = new ArrayList<>();
    private List<Boolean> checkList = new ArrayList<>();
    private List<Boolean> checkCouponList = new ArrayList<>();
    private CartProductAdapter cartProductAdapter;
    private CartCouponAdapter cartCouponAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCartBinding.inflate(inflater);

        //NavController 얻기
        navController = NavHostFragment.findNavController(this);

        //RecyclerView 초기화
        initCartProductRecyclerView();
        initCartCouponRecyclerView();

        initBtnOrder();
        initCheckAll();

        //하단바 숨기기
        hideBottomNavigation(true);

        return binding.getRoot();
    }

    //하단바 설정
    public void hideBottomNavigation(Boolean bool) {
        BottomNavigationView bottomNavigation = getActivity().findViewById(R.id.bottomNavigationView);
        if (bool == true)
            bottomNavigation.setVisibility(View.GONE);
        else
            bottomNavigation.setVisibility(View.VISIBLE);
    }

    //상품 불러오기
    private void initCartProductRecyclerView() {
        //RecyclerView에서 항목을 수직으로 배치하도록 설정
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getContext(), LinearLayoutManager.VERTICAL, false
        );
        binding.cartProductRecyclerView.setLayoutManager(linearLayoutManager);

        //어댑터 생성
        //CartProductAdapter cartProductAdapter = new CartProductAdapter();
        cartProductAdapter = new CartProductAdapter();

        //API 서버에서 JSON 목록 받기
        CartService cartService = ServiceProvider.getCartService(getContext());
        Call<List<CartProduct>> call = cartService.getCartProductList();
        call.enqueue(new Callback<List<CartProduct>>() {
            @Override
            public void onResponse(Call<List<CartProduct>> call, Response<List<CartProduct>> response) {
                //JSON -> List<Board> 변환
                cartProductList = response.body();

                for(CartProduct cartProduct : cartProductList) {
                    cartProduct.setShopper_NO(1);
                    checkList.add(false);
                }
                binding.totalCartCountTxt.setText(String.valueOf(checkList.size()));

                Log.i(TAG, "서비스에서 가져온 cartProductList: " + cartProductList);
                //어댑터 데이터 세팅
                cartProductAdapter.setList(cartProductList);
                cartProductAdapter.setCheckList(checkList);
                //RecyclerView에 어댑터 세팅
                binding.cartProductRecyclerView.setAdapter(cartProductAdapter);
            }

            @Override
            public void onFailure(Call<List<CartProduct>> call, Throwable t) {
                Log.i(TAG, "안됨......");
            }
        });

        //항목을 클릭했을 때 콜백 객체를 등록
        cartProductAdapter.setOnItemClickListener(new CartProductAdapter.OnItemClickListener() {
            @Override
            public void onBtnCheckClick(CheckBox checkBox, int position) {
                checkList.set(position, checkBox.isChecked());
                Log.i(TAG, "체크하면? checkList? " + checkList);

                if(!checkList.contains(false)) {
                    binding.btnCheckAll.setChecked(true);
                } else {
                    binding.btnCheckAll.setChecked(false);
                }

                initCheckedCountTxt();
                calculatePrice("onBtnCheckClick");
            }

            @Override
            public void onBtnDeleteClick(View itemView, int position) {
                Log.i(TAG, position + "항목 클릭됨");
                //CartProduct cartProduct = cartProductAdapter.getItem(position);
                CartProduct cartProduct = cartProductList.get(position);

                //Log.i(TAG, "삭제될 cartProduct: " + cartProduct);
                Log.i(TAG, "삭제될 cartProductFromList: " + cartProduct);

                cartProductList.remove(position);
                checkList.remove(position);
                //어댑터 데이터 세팅
                cartProductAdapter.setList(cartProductList);
                cartProductAdapter.setCheckList(checkList);
                //RecyclerView에 어댑터 세팅
                binding.cartProductRecyclerView.setAdapter(cartProductAdapter);

                deleteCartProduct(cartProduct);

                binding.totalCartCountTxt.setText(String.valueOf(cartProductList.size()));
                initCheckedCountTxt();

                calculatePrice("onBtnDeleteClick");
            }

            @Override
            public void onBtnPlusClick(TextView stock, TextView price, int position) {
                int updateStock = Integer.parseInt(stock.getText().toString()) + 1;
                int updatePrice = updateStock * cartProductList.get(position).getDiscount_PRICE();
                stock.setText(String.valueOf(updateStock));
                DecimalFormat df = new DecimalFormat("#,###,###");
                price.setText(df.format(updatePrice) + "원");

                CartProduct cartProduct = cartProductList.get(position);
                cartProduct.setCart_PRODUCT_STOCK(updateStock);

                updateCartProductStock(cartProduct);

                calculatePrice("onBtnPlusClick");
            }

            @Override
            public void onBtnMinusClick(TextView stock, TextView price, int position) {
                int updateStock = Integer.parseInt(stock.getText().toString());
                if(updateStock > 1) {
                    updateStock--;
                    stock.setText(String.valueOf(updateStock));
                    int updatePrice = updateStock * cartProductList.get(position).getDiscount_PRICE();
                    DecimalFormat df = new DecimalFormat("#,###,###");
                    price.setText(df.format(updatePrice) + "원");

                    CartProduct cartProduct = cartProductList.get(position);
                    cartProduct.setCart_PRODUCT_STOCK(updateStock);

                    updateCartProductStock(cartProduct);

                    calculatePrice("onBtnMinusClick");
                }
            }
        });
    }

    //쿠폰 불러오기
    private void initCartCouponRecyclerView() {
        //RecyclerView에서 항목을 수직으로 배치하도록 설정
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getContext(), LinearLayoutManager.VERTICAL, false
        );
        binding.cartCouponRecyclerView.setLayoutManager(linearLayoutManager);

        //어댑터 생성
        cartCouponAdapter = new CartCouponAdapter();

        //API 서버에서 JSON 목록 받기
        CartService cartService = ServiceProvider.getCartService(getContext());
        Call<List<Coupon>> call = cartService.getCartCouponList();
        call.enqueue(new Callback<List<Coupon>>() {
            @Override
            public void onResponse(Call<List<Coupon>> call, Response<List<Coupon>> response) {
                //JSON -> List<Board> 변환
                couponList = response.body();
                for(Coupon coupon : couponList) {
                    checkCouponList.add(false);
                }
                //어댑터 데이터 세팅
                cartCouponAdapter.setCouponList(couponList);
                cartCouponAdapter.setCheckList(checkCouponList);
                //RecyclerView에 어댑터 세팅
                binding.cartCouponRecyclerView.setAdapter(cartCouponAdapter);
            }

            @Override
            public void onFailure(Call<List<Coupon>> call, Throwable t) {
                Log.i(TAG, "안됨......");
            }
        });

        //항목을 클릭했을 때 콜백 객체를 등록
        cartCouponAdapter.setOnItemClickListener(new CartCouponAdapter.OnItemClickListener() {
            @Override
            public void onBtnCheckClick(CheckBox checkBox, int position) {
                Log.i(TAG, position + "항목 클릭됨");
                checkCouponList.set(position, checkBox.isChecked());
                calculatePrice("onBtnCheckClick");

                if(binding.totalProductPrice.getText().equals("0원")) {
                    checkCouponList.set(position, false);

                    cartCouponAdapter.setCheckList(checkCouponList);
                    //RecyclerView에 어댑터 세팅
                    binding.cartCouponRecyclerView.setAdapter(cartCouponAdapter);
                }
            }
        });
    }

    //장바구니 상품 삭제
    private void deleteCartProduct(CartProduct cartProduct) {
        //API 서버에서 JSON 목록 받기
        CartService cartService = ServiceProvider.getCartService(getContext());
        Call<Void> call = cartService.deleteCartProduct(cartProduct);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    //장바구니 상품 수량 변경
    private void updateCartProductStock(CartProduct cartProduct) {
        //API 서버에서 JSON 목록 받기
        CartService cartService = ServiceProvider.getCartService(getContext());
        Call<Void> call = cartService.updateStockCartProduct(cartProduct);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    //장바구니 전체 선택
    private void initCheckAll() {
        binding.btnCheckAll.setOnClickListener(v -> {
            for(int i=0; i<checkList.size(); i++) {
                checkList.set(i, binding.btnCheckAll.isChecked());
            }

            //어댑터 데이터 세팅
            cartProductAdapter.setCheckList(checkList);
            //RecyclerView에 어댑터 세팅
            binding.cartProductRecyclerView.setAdapter(cartProductAdapter);

            initCheckedCountTxt();
            calculatePrice("initCheckAll");
        });
    }

    //선택된 상품수 초기화
    private void initCheckedCountTxt() {
        int checkedCount = 0;
        for(boolean isChecked : checkList) {
            if(isChecked) {
                checkedCount++;
            }
        }
        binding.checkedCartCountTxt.setText(String.valueOf(checkedCount));
    }

    //주문금액 동작
    private void calculatePrice(String method) {
        Log.i(TAG, "실행되는 메소드: " + method);
        int totalPrice = 0;
        int totalDelivery = 0;
        //총 상품금액 계산
        for(int i=0; i<checkList.size(); i++) {
            if(checkList.get(i)) {
                totalPrice += (cartProductList.get(i).getDiscount_PRICE() * cartProductList.get(i).getCart_PRODUCT_STOCK());
            }
        }

        Log.i(TAG, "totalPrice가 얼마길래 왜 안되는 거야 ㅡㅡ " + totalPrice);

        if(totalPrice != 0) {
            Log.i(TAG, "totalPrice가 얼마길래 왜 안되는 거야 ㅡㅡ " + totalPrice);
            //총 배송비 계산
            if(totalPrice >= 30000) {
                totalDelivery = 0;
            } else {
                totalDelivery = 3000;
            }

            //쿠폰 적용
            int[] returnArray = calculateCoupon(totalPrice, totalDelivery);
            /*var coupon = calculateCoupon(totalPrice, totalDelivery);
            var discountProduct = coupon.Pdis;
            var discountDelivery = coupon.Ddis;*/
            int discountProduct = returnArray[0];
            int discountDelivery = returnArray[1];
            Log.i(TAG, "상품할인되니? " + discountProduct);
            Log.i(TAG, "배송비할인되니? " + discountDelivery);

            int priceFinal = totalPrice;
            int deliveryFinal = totalDelivery - discountDelivery;
            int discountFinal = discountProduct + discountDelivery;
            int orderPriceFinal = priceFinal + deliveryFinal - discountProduct;

            DecimalFormat df = new DecimalFormat("#,###,###");
            binding.totalProductPrice.setText(df.format(priceFinal) + "원");
            binding.totalShippingPrice.setText(df.format(deliveryFinal) + "원");
            if(discountFinal == 0) {
                binding.totalDiscountPrice.setText("0원");
            } else {
                binding.totalDiscountPrice.setText("-" + df.format(discountFinal) + "원");
            }
            binding.finalProductPrice.setText(df.format(orderPriceFinal) + "원");
            binding.cartOrderPrice.setText("총 " + df.format(orderPriceFinal) + "원");

        } else {
            binding.totalProductPrice.setText("0원");
            binding.totalShippingPrice.setText("0원");
            binding.totalDiscountPrice.setText("0원");
            binding.finalProductPrice.setText("0원");
            binding.cartOrderPrice.setText("총 0원");
        }
    }

    private int[] calculateCoupon(int price, int delivery) {
        Log.i(TAG, "calculateCoupon의 쿠폰 체크리스트1: " + checkCouponList);
        //현재 총상품금액
        int priceCurrent = price;
        //현재 총배송비
        int deliveryCurrent = delivery;

        //적용할 상품할인가
        int discountProduct = 0;
        //적용할 배송비할인가
        int discountDelivery = 0;

        for(int i=0; i<checkCouponList.size(); i++) {
            if(checkCouponList.get(i)) {
                Log.i(TAG, "calculateCoupon의 체크된 애?: " + checkCouponList.get(i));
                Log.i(TAG, "너 뉘기야: " + couponList.get(i));
                //couponType: ####[원|%]
                String couponType = couponList.get(i).getCoupon_TYPE();
                //couponCondition: [금액제한없음|####원 이상 구매 시 ~~~]
                int couponCondition = couponList.get(i).getDiscount_RULE();
                //isDlivery: 배송비 할인쿠폰인지 아닌지
                String isDelivery = couponList.get(i).getCoupon_KIND();
                //couponPrice: 쿠폰할인가격
                int couponPrice = couponList.get(i).getDiscount_PRICE();

                //쿠폰작용가능여부
                boolean isUsable = false;

                //#### '원' 일 경우
                if(couponType.equals("원")) {
                    //조건적합여부
                    boolean isOk = true;

                    //금액제한없음이 아니고 조건에 맞지 않는다면 isOk = false
                    if(couponCondition != 0) {
                        if(priceCurrent < couponCondition) { isOk = false; }
                    }

                    if(isOk) {
                        //'배송비' 할인일 경우
                        if(isDelivery.equals("배송비")) {
                            if(deliveryCurrent >= couponPrice) {
                                isUsable = true;
                                discountDelivery += couponPrice;
                            }
                        } else if(isDelivery.equals("상품")) {
                            //'배송비' 할인이 아닐 경우
                            if(priceCurrent >= couponPrice) {
                                isUsable = true;
                                discountProduct += couponPrice;
                            }
                        }
                    }

                }
                //#### '%' 일 경우
                else if(couponType.equals("%")) {
                    Log.i(TAG, "혹시 %조차 안되는거니??? ");
                    //조건적합여부
                    boolean isOk = true;

                    //금액제한없음이 아니고 조건에 맞지 않는다면 isOk = false
                    if(couponCondition != 0) {
                        if(priceCurrent < couponCondition) { isOk = false; }
                    }

                    if(isOk) {
                        if(priceCurrent * (1-couponPrice/100) > 0) {
                            isUsable = true;
                            discountProduct += Math.round(priceCurrent * (couponPrice/100.0));

                            Log.i(TAG, "상품할인되니?? " + discountProduct);
                            Log.i(TAG, "배송비할인되니?? " + discountDelivery);

                            Log.i(TAG, "혹시 현재 상품금액이 0원이니? " + priceCurrent);
                            Log.i(TAG, "아니면 할인금액이 0원????" + couponPrice);
                            Log.i(TAG, "그것도 아니면 계산된 할인금액이 0원??????" + Math.round(priceCurrent * (couponPrice/100.0)));
                        }
                    }
                }

                if(!isUsable) {
                    checkCouponList.set(i, false);
                    //$(item).prop("checked", false);
                    Log.i(TAG, "calculateCoupon의 쿠폰 체크리스트2: " + checkCouponList);
                    Log.i(TAG, "calculateCoupon의 체크 false된 애?2: " + checkCouponList.get(i));
                }
                //checkCouponList.set(i, !isUsable);
            }
        }
        Log.i(TAG, "calculateCoupon의 쿠폰 체크리스트3 - 어댑터 설정: " + checkCouponList);
        cartCouponAdapter.setCheckList(checkCouponList);
        //RecyclerView에 어댑터 세팅
        binding.cartCouponRecyclerView.setAdapter(cartCouponAdapter);

        Log.i(TAG, "상품할인되니??? " + discountProduct);
        Log.i(TAG, "배송비할인되니??? " + discountDelivery);

        int[] returnArray = {discountProduct, discountDelivery};
        return returnArray;
    }


    private void initBtnOrder() {
        binding.btnOrder.setOnClickListener(v -> {
            Bundle bundle = new Bundle();

            //주문할 상품 리스트
            List<CartProduct> orderedCartProductList = new ArrayList<>();
            for(int i=0; i<checkList.size(); i++) {
                if(checkList.get(i)) {
                    orderedCartProductList.add(cartProductList.get(i));
                }
            }
            Log.i(TAG, "혹시 아무것도 안골랐니????? " + orderedCartProductList.size());
            if(orderedCartProductList.size() != 0) {
                bundle.putSerializable("cartProductList", (Serializable) orderedCartProductList);

                //사용할 쿠폰 리스트
                List<Coupon> orderedCouponList = new ArrayList<>();
                for(int i=0; i<checkCouponList.size(); i++) {
                    if(checkCouponList.get(i)) {
                        orderedCouponList.add(couponList.get(i));
                    }
                }
                bundle.putSerializable("couponList", (Serializable) orderedCouponList);

                //총 상품금액
                String totalPrice = binding.totalProductPrice.getText().toString();
                bundle.putString("totalPrice", totalPrice);

                //총 할인금액
                String totalDiscountPrice = binding.totalDiscountPrice.getText().toString();
                bundle.putString("totalDiscountPrice", totalDiscountPrice);

                //총 배송비
                String totalShippingPrice = binding.totalShippingPrice.getText().toString();
                bundle.putString("totalShippingPrice", totalShippingPrice);

                //총 주문금액
                String orderPrice = binding.finalProductPrice.getText().toString();
                bundle.putString("orderPrice", orderPrice);

                Log.i(TAG, "주문할 상품들: " + bundle.getSerializable("cartProductList"));
                Log.i(TAG, "사용할 쿠폰들: " + bundle.getSerializable("couponList"));
                Log.i(TAG, "총 상품금액: " + bundle.getString("totalPrice"));
                Log.i(TAG, "총 할인금액: " + bundle.getString("totalDiscountPrice"));
                Log.i(TAG, "총 배송비: " + bundle.getString("totalShippingPrice"));
                Log.i(TAG, "총 주문금액: " + bundle.getString("orderPrice"));

                navController.navigate(R.id.action_cart_to_order, bundle);
            } else {
                AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                        //.setTitle("주문할 상품을 선택해주세요.")
                        .setMessage("주문할 상품을 선택해주세요.")
                        .setPositiveButton("확인", null)
                        .create();
                alertDialog.show();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        //하단바 보이기
        hideBottomNavigation(false);
    }
}