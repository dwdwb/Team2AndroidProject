package com.example.myapplication.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentWriteInquiryBinding;
import com.example.myapplication.dto.ProductInquiry;
import com.example.myapplication.service.DetailViewService;
import com.example.myapplication.service.MyPageShopperInquiryService;
import com.example.myapplication.service.ServiceProvider;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WriteInquiryFragment extends Fragment {
    private static final String TAG = "WriteInquiryFragment";
    private FragmentWriteInquiryBinding binding;
    private NavController navController;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentWriteInquiryBinding.inflate(inflater);

        navController = NavHostFragment.findNavController(this);

        Bundle bundle = getArguments();
        int bno = bundle.getInt("bno");

        initBtnWriteInquiry(bno);

        return binding.getRoot();
    }

    private void initBtnWriteInquiry(int bno) {
        binding.btnWrite.setOnClickListener(v -> {
            String content = binding.content.getText().toString();
            ProductInquiry productInquiry = new ProductInquiry();
            productInquiry.setBoard_NO(bno);
            productInquiry.setShopper_NO(1);    //이것도 받기
            productInquiry.setInquiry_CONTENT(content);

            Log.i(TAG, content);

            //API 서버에서 JSON 목록 받기
            DetailViewService detailViewService = ServiceProvider.getDetailViewService(getContext());

            Call<Void> call = detailViewService.writeInquiry(bno, 1, content);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    //navController.popBackStack();
                    Bundle bundle = new Bundle();
                    bundle.putInt("board_no", bno);
                    NavOptions navOptions = new NavOptions.Builder()
                            .setPopUpTo(R.id.detail,false)
                            .setLaunchSingleTop(true)
                            .build();
                    navController.navigate(R.id.detail,bundle, navOptions);
                    /*NavOptions navOptions = new NavOptions.Builder()
                            .setPopUpTo(R.id.inquiry,false)
                            .setLaunchSingleTop(true)
                            .build();
                    navController.navigate(R.id.inquiry, null, navOptions);*/
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {

                }
            });
        });
    }
}