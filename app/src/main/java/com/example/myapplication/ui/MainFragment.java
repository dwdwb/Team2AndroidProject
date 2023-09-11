package com.example.myapplication.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.adapter.ViewPagerMainPagerAdapter;
import com.example.myapplication.databinding.FragmentListBinding;
import com.example.myapplication.databinding.FragmentMainBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainFragment extends Fragment {
    private static final String TAG = "MainFragment";
    private FragmentMainBinding binding;
    private NavController navController;
    private Handler sliderHandler = new Handler();
    private float startX;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater);

        //NavController 얻기
        navController = NavHostFragment.findNavController(this);

        initPagerView();

        return binding.getRoot();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initPagerView() {
        ViewPagerMainPagerAdapter viewPagerMainPagerAdapter = new ViewPagerMainPagerAdapter(this);
        binding.viewPagerMain.setAdapter(viewPagerMainPagerAdapter);

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(
                binding.tabLayoutMain, binding.viewPagerMain, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

            }
        });
        tabLayoutMediator.attach();



        // 5초 간격으로 자동 슬라이딩
        binding.viewPagerMain.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable, 5000);
            }
        });


        /*
        binding.viewPagerMain.setOnTouchLister가 아니라
        binding.viewPagerMain.getChildAt(0).setOnTouchListener를 사용한 이유:
        Because ViewPager2 is a ViewGroup, the final target is the recyclerview in it.
        The setOnTouchListener not called is because recyclerview intercepts the event
        and calls the onTouchEvent first.
        The right way to add customised onTouch logic is to call mViewPager.
        getChildAt(0).setOnTouchListener{...}
         */
        binding.viewPagerMain.getChildAt(0).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = event.getX();
                        //Log.i(TAG, "startX: "+startX);
                        break;
                    case MotionEvent.ACTION_UP:
                        float endX = event.getX();
                        //Log.i(TAG, "endX: "+endX);
                        float deltaX = endX - startX;
                        //Log.i(TAG, "deltaX: "+deltaX);
                        //Log.i(TAG, "position: "+binding.viewPagerMain.getCurrentItem());

                        // 슬라이드 방향 확인 및 액션 처리
                        if (deltaX > 0) {
                            // 왼쪽에서 오른쪽으로 슬라이드
                            handleSlideRightAction();
                        } else if (deltaX < 0) {
                            // 오른쪽에서 왼쪽으로 슬라이드
                            handleSlideLeftAction();
                        }
                        break;
                }
                return false;
            }
        });
    }

    private void handleSlideRightAction() {
        if (binding.viewPagerMain.getCurrentItem() == 0) {
            //Log.i(TAG, "position: "+binding.viewPagerMain.getCurrentItem());
            sliderHandler.post(new Runnable() {
                @Override
                public void run() {
                    binding.viewPagerMain.setCurrentItem(2);
                }
            });
        }
    }

    private void handleSlideLeftAction() {
        if (binding.viewPagerMain.getCurrentItem() == 2) {
            //Log.i(TAG, "position: "+binding.viewPagerMain.getCurrentItem());
            sliderHandler.post(new Runnable() {
                @Override
                public void run() {
                    binding.viewPagerMain.setCurrentItem(0);
                }
            });
        }
    }

    private Runnable sliderRunnable = new Runnable() {
        // 자동으로 다음 슬라이드로 슬라이딩. 마지막 장일경우 첫 번째 장으로 슬라이딩
        @Override
        public void run() {
            if (binding.viewPagerMain.getCurrentItem() == 2) {
                binding.viewPagerMain.setCurrentItem(0);
            } else {
                binding.viewPagerMain.setCurrentItem(binding.viewPagerMain.getCurrentItem() + 1);
            }
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        sliderHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        sliderHandler.postDelayed(sliderRunnable, 5000);
    }

}