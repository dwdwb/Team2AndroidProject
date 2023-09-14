package com.example.myapplication.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import com.example.myapplication.R;
import com.example.myapplication.adapter.DetailOptionAdapter;
import com.example.myapplication.databinding.FragmentCouponBinding;
import com.example.myapplication.databinding.FragmentDetailBottomSheetDialogBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class DetailBottomSheetDialogFragment extends BottomSheetDialogFragment {
    private FragmentDetailBottomSheetDialogBinding binding;
    private List<String> list = new ArrayList<>();
    private Spinner spinner;
    private DetailOptionAdapter detailOptionAdapter;
    private String selectedItem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDetailBottomSheetDialogBinding.inflate(inflater);

        initOptionSpinner();

        initBtnCart();

        return binding.getRoot();
    }

    public void initOptionSpinner() {
        spinner = binding.spinner;

        // 스피너 안에 넣을 데이터 임의 생성
        for (int i = 1; i < 6; i++) {
            list.add("아이템" + i);
        }

        // 스피너에 붙일 어댑터 초기화
        detailOptionAdapter = new DetailOptionAdapter(getContext(), list);
        spinner.setAdapter(detailOptionAdapter);

        // 스피너 클릭 리스너
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // 어댑터에서 정의한 메서드를 통해 스피너에서 선택한 아이템의 이름을 받아온다
                selectedItem = (String) detailOptionAdapter.getItem(position);
                //Toast.makeText(BottomSheet.this, "선택한 아이템 : " + selectedItem, Toast.LENGTH_SHORT).show();
                // 어댑터에서 정의하는 게 귀찮다면 아래처럼 구할 수도 있다
                // getItemAtPosition()의 리턴형은 Object이므로 String 캐스팅이 필요하다
                //String otherItem = (String) spinner.getItemAtPosition(position);
                //Log.e(TAG, "getItemAtPosition() - 선택한 아이템 : " + otherItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //
            }
        });
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