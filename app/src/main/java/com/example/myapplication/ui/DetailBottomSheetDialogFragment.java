package com.example.myapplication.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import com.example.myapplication.R;
import com.example.myapplication.adapter.CartProductAdapter;
import com.example.myapplication.adapter.DetailOptionAdapter;
import com.example.myapplication.adapter.DetailOptionProductAdapter;
import com.example.myapplication.databinding.FragmentCouponBinding;
import com.example.myapplication.databinding.FragmentDetailBottomSheetDialogBinding;
import com.example.myapplication.dto.CartProduct;
import com.example.myapplication.dto.ProductBoard;
import com.example.myapplication.service.CartService;
import com.example.myapplication.service.DetailViewService;
import com.example.myapplication.service.ServiceProvider;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

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
    private List<Integer> selectOptionProductNoList = new ArrayList<>();
    private DetailOptionAdapter detailOptionAdapter;
    private String selectedItem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDetailBottomSheetDialogBinding.inflate(inflater);

        initOption();

        initBtnCart();

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

                initOptionSpinner();
            }

            @Override
            public void onFailure(Call<List<ProductBoard>> call, Throwable t) {
                Log.i(TAG, "안됨......");
            }
        });
    }

    public void initOptionSpinner() {
        List<String> optionList = new ArrayList<>();
        optionList.add("옵션");
        DecimalFormat df = new DecimalFormat("#,###,###");
        for(ProductBoard optionProduct : optionProductList) {
            String option_name = optionProduct.getProductOption() + " : " + df.format(optionProduct.getDiscountPrice()) + "원";
            optionList.add(option_name);
        }

        // 스피너에 붙일 어댑터 초기화
        detailOptionAdapter = new DetailOptionAdapter(getContext(), optionList);
        binding.spinner.setAdapter(detailOptionAdapter);
        binding.spinner.setSelection(AdapterView.INVALID_POSITION);

        // 스피너 클릭 리스너
        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // 어댑터에서 정의한 메서드를 통해 스피너에서 선택한 아이템의 이름을 받아온다
                selectedItem = (String) detailOptionAdapter.getItem(position);

                //selectOptionProductNoList.add(position);
                selectOption(position - 1);

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

    private void selectOption(int position) {
        Log.i(TAG, "selectOption의 selectOptionProductNoList: " + selectOptionProductNoList);
        Log.i(TAG, "selectOption의 position: " + position);
        if(!selectOptionProductNoList.contains(position) && position >= 0) {
            Log.i(TAG, "selectOption의 if문 속 position: " + position);
            List<ProductBoard> updateSelectOptionList = detailOptionProductAdapter.getList();
            updateSelectOptionList.add(optionProductList.get(position));
            Log.i(TAG, "selectOption의 updateSelectOptionList: " + updateSelectOptionList);


            //RecyclerView에서 항목을 수직으로 배치하도록 설정
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                    getContext(), LinearLayoutManager.VERTICAL, false
            );
            binding.recyclerView.setLayoutManager(linearLayoutManager);
            //어댑터 데이터 세팅
            detailOptionProductAdapter.setList(updateSelectOptionList);
            //RecyclerView에 어댑터 세팅
            binding.recyclerView.setAdapter(detailOptionProductAdapter);

            selectOptionProductNoList.add(position);
        }
    }

    public void initBtnCart() {
        binding.btnCart.setOnClickListener(v -> {
            dismiss();
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