package com.example.myapplication.ui;

import android.app.LauncherActivity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentListBottomSheetDialogBinding;
import com.example.myapplication.dto.ListItemOrder;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class ListBottomSheetDialogFragment extends BottomSheetDialogFragment {
    private static final String TAG = "ListBottomSheetDialog";
    FragmentListBottomSheetDialogBinding binding;
    private NavController navController;

    private ListFragment listFragment;

    private ListItemOrder listItemOrder;

    public ListBottomSheetDialogFragment(ListFragment listFragment, ListItemOrder listItemOrder) {
        this.listFragment = listFragment;
        this.listItemOrder = listItemOrder;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentListBottomSheetDialogBinding.inflate(inflater);

        navController = NavHostFragment.findNavController(this);

        setRadioGroup();
        initBtn();

        return binding.getRoot();
    }


    private void setRadioGroup() {
        if (listItemOrder == ListItemOrder.PRICE_DESC) {
            //Log.i(TAG, listItemOrder.toString());
            binding.priceDescBtn.setChecked(true);
        } else if (listItemOrder == ListItemOrder.PRICE_ASC) {
            binding.priceAscBtn.setChecked(true);
        }

        binding.closeBtn.setOnClickListener(v -> {
            dismiss();
        });
    }

    private void initBtn() {
        binding.priceDescBtn.setOnClickListener(v -> {
            binding.priceDescBtn.setChecked(true);
            listFragment.onDialogClosed(ListItemOrder.PRICE_DESC);
            dismiss();
        });

        binding.priceAscBtn.setOnClickListener(v -> {
            binding.priceAscBtn.setChecked(true);
            listFragment.onDialogClosed(ListItemOrder.PRICE_ASC);
            dismiss();
        });
    }
}