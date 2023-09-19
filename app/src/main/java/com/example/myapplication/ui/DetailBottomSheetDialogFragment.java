package com.example.myapplication.ui;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.CartProductAdapter;
import com.example.myapplication.adapter.DetailOptionAdapter;
import com.example.myapplication.adapter.DetailOptionProductAdapter;
import com.example.myapplication.databinding.FragmentCouponBinding;
import com.example.myapplication.databinding.FragmentDetailBottomSheetDialogBinding;
import com.example.myapplication.datastore.AppKeyValueStore;
import com.example.myapplication.dto.CartProduct;
import com.example.myapplication.dto.ProductBoard;
import com.example.myapplication.dto.Result;
import com.example.myapplication.service.CartService;
import com.example.myapplication.service.DetailViewService;
import com.example.myapplication.service.ServiceProvider;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailBottomSheetDialogFragment extends BottomSheetDialogFragment {
    private static final String TAG = "DetailBottomSheetDialog";
    private FragmentDetailBottomSheetDialogBinding binding;
    private DetailOptionProductAdapter detailOptionProductAdapter = new DetailOptionProductAdapter();
    private List<ProductBoard> optionProductList = new ArrayList<>();
    //private List<Integer> selectOptionProductNoList = new ArrayList<>();
    private List<ProductBoard> selectedOptionProductList = new ArrayList<>();
    private DetailOptionAdapter detailOptionAdapter;
    private ProductBoard selectedItem;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDetailBottomSheetDialogBinding.inflate(inflater);

        //NavController 얻기
        navController = NavHostFragment.findNavController(this);

        initOption();

        initBtnCart();
        initBtnOrder();

        return binding.getRoot();
    }

    private void initOption() {
        //API 서버에서 JSON 목록 받기
        DetailViewService detailViewService = ServiceProvider.getDetailViewService(getContext());
        Call<List<ProductBoard>> call = detailViewService.getOptionProductList("고재승 꿀수박");
        call.enqueue(new Callback<List<ProductBoard>>() {
            @Override
            public void onResponse(Call<List<ProductBoard>> call, Response<List<ProductBoard>> response) {
                //JSON -> List<Board> 변환
                optionProductList = response.body();

                for(ProductBoard productBoard : optionProductList) {
                    productBoard.setShopperNo(1);
                    productBoard.setStock(1);
                }

                initOptionSpinner();
            }

            @Override
            public void onFailure(Call<List<ProductBoard>> call, Throwable t) {
                Log.i(TAG, "안됨......");
            }
        });
    }

    public void initOptionSpinner() {

        // 스피너에 붙일 어댑터 초기화
        detailOptionAdapter = new DetailOptionAdapter(getContext(), optionProductList);
        binding.spinner.setAdapter(detailOptionAdapter);

        // 스피너 클릭 리스너
        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // 어댑터에서 정의한 메서드를 통해 스피너에서 선택한 아이템의 이름을 받아온다
                if(position != 0) {
                    selectedItem = (ProductBoard) detailOptionAdapter.getItem(position);
                    selectOption(selectedItem);
                }
                //binding.spinner.setSelection(0);
                //Toast.makeText(BottomSheet.this, "선택한 아이템 : " + selectedItem, Toast.LENGTH_SHORT).show();
                // 어댑터에서 정의하는 게 귀찮다면 아래처럼 구할 수도 있다
                // getItemAtPosition()의 리턴형은 Object이므로 String 캐스팅이 필요하다
                //String otherItem = (String) spinner.getItemAtPosition(position);
                //Log.e(TAG, "getItemAtPosition() - 선택한 아이템 : " + otherItem);
                binding.spinner.setSelection(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                binding.spinner.setSelection(0);
            }
        });
    }

    private void selectOption(ProductBoard selectedItem) {
        if(!selectedOptionProductList.contains(selectedItem)) {
            selectedOptionProductList.add(selectedItem);

            //RecyclerView에서 항목을 수직으로 배치하도록 설정
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                    getContext(), LinearLayoutManager.VERTICAL, false
            );
            binding.recyclerView.setLayoutManager(linearLayoutManager);
            //어댑터 데이터 세팅
            detailOptionProductAdapter.setList(selectedOptionProductList);
            //RecyclerView에 어댑터 세팅
            binding.recyclerView.setAdapter(detailOptionProductAdapter);


            //항목을 클릭했을 때 콜백 객체를 등록
            detailOptionProductAdapter.setOnItemClickListener(new DetailOptionProductAdapter.OnItemClickListener() {
                @Override
                public void onBtnDeleteClick(View itemView, int position) {
                    Log.i(TAG, position + "항목 클릭됨");
                    Log.i(TAG, "선택된 상품 리스트들에서 해당 포지션: " + selectedOptionProductList.get(position));
                    ProductBoard productBoard = detailOptionProductAdapter.getItem(position);
                    Log.i(TAG, "어댑터 리스트에서 해당 포지션: " + productBoard.toString());

                    selectedOptionProductList.remove(position);
                    detailOptionProductAdapter.setList(selectedOptionProductList);
                    binding.recyclerView.setAdapter(detailOptionProductAdapter);
                }

                @Override
                public void onBtnPlusClick(TextView optionStock, TextView optionPrice, int position) {
                    int updateStock = Integer.parseInt(optionStock.getText().toString()) + 1;
                    int updatePrice = updateStock * selectedOptionProductList.get(position).getDiscountPrice();
                    optionStock.setText(String.valueOf(updateStock));
                    DecimalFormat df = new DecimalFormat("#,###,###");
                    optionPrice.setText(df.format(updatePrice) + "원");

                    ProductBoard productBoard = selectedOptionProductList.get(position);
                    productBoard.setStock(updateStock);
                }

                @Override
                public void onBtnMinusClick(TextView optionStock, TextView optionPrice, int position) {
                    int updateStock = Integer.parseInt(optionStock.getText().toString());
                    int updatePrice = updateStock * selectedOptionProductList.get(position).getDiscountPrice();
                    if(updateStock > 1) {
                        updateStock--;
                        optionStock.setText(String.valueOf(updateStock));
                        DecimalFormat df = new DecimalFormat("#,###,###");
                        optionPrice.setText(df.format(updatePrice) + "원");

                        ProductBoard productBoard = selectedOptionProductList.get(position);
                        productBoard.setStock(updateStock);
                    }
                }
            });
        }
    }

    public void initBtnCart() {
        binding.btnCart.setOnClickListener(v -> {
            for(ProductBoard productBoard : selectedOptionProductList) {
                Log.i(TAG, productBoard.toString());
            }

            String shopperId = AppKeyValueStore.getValue(this.getContext(), "shopperId");
            //dismiss();
            if (shopperId == null) {
                dismiss();
                navController.navigate(R.id.login);
            } else {
                if(selectedOptionProductList.size() != 0) {
                    dismiss();
                    Log.i(TAG, selectedOptionProductList.toString());
                    DetailViewService detailViewService = ServiceProvider.getDetailViewService(getContext());
                    Call<Void> call = detailViewService.addCart(selectedOptionProductList);
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            navController.navigate(R.id.action_detail_to_cart);
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {

                        }
                    });
                } else {
                    AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                            .setTitle("옵션을 선택해주세요.")
                            //.setMessage(joinResult.getResult())
                            .setPositiveButton("확인", null)
                            .create();
                    alertDialog.show();
                }
            }
        });
    }

    private void initBtnOrder() {
        binding.btnOrder.setOnClickListener(v -> {
            String shopperId = AppKeyValueStore.getValue(this.getContext(), "shopperId");
            //dismiss();
            if (shopperId == null) {
                dismiss();
                navController.navigate(R.id.login);
            } else {
                if(selectedOptionProductList.size() != 0) {
                    dismiss();

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("productList", (Serializable) selectedOptionProductList);

                    navController.navigate(R.id.action_detail_to_orderFragment, bundle);
                } else {
                    AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                            .setTitle("옵션을 선택해주세요.")
                            //.setMessage(joinResult.getResult())
                            .setPositiveButton("확인", null)
                            .create();
                    alertDialog.show();
                }
            }
        });
    }

    /*@Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.btnCart.setOnClickListener(v -> {
            dismiss();
        });
    }*/
}