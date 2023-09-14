package com.example.myapplication.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.adapter.ViewPagerMangoAdapter;
import com.example.myapplication.adapter.ViewPagerWatermelonAdapter;
import com.example.myapplication.databinding.FragmentMangoAdBinding;
import com.example.myapplication.databinding.FragmentWatermelonAdBinding;
import com.example.myapplication.dto.MobileProductForList;
import com.example.myapplication.service.ListService;
import com.example.myapplication.service.ServiceProvider;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MangoAdFragment extends Fragment {
    private static final String TAG = "MangoAdFragment";
    private FragmentMangoAdBinding binding;
    //private NavController navController;
    private Handler sliderHandler = new Handler();
    private float startX;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMangoAdBinding.inflate(inflater);

        //NavController 얻기
        //navController = NavHostFragment.findNavController(new MainFragment());

        initViewPager();

        return binding.getRoot();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initViewPager() {
        ViewPagerMangoAdapter viewPagerMangoAdapter = new ViewPagerMangoAdapter(this);

        ListService listService = ServiceProvider.getListService(getContext());
        Call<List<MobileProductForList>> call = listService.getMangoAdList();
        call.enqueue(new Callback<List<MobileProductForList>>() {
            @Override
            public void onResponse(Call<List<MobileProductForList>> call, Response<List<MobileProductForList>> response) {
                Log.i(TAG, "성공");
                List<MobileProductForList> list = list = response.body();
                viewPagerMangoAdapter.setList(list);
                binding.viewPagerMango.setAdapter(viewPagerMangoAdapter);
                Log.i(TAG, "" + list.size());

                TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(
                        binding.tabLayoutMango, binding.viewPagerMango, new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

                    }
                });
                tabLayoutMediator.attach();
            }

            @Override
            public void onFailure(Call<List<MobileProductForList>> call, Throwable t) {
                Log.i(TAG, "실패");
                t.printStackTrace();
            }
        });
    }
}