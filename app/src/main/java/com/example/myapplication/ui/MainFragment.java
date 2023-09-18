package com.example.myapplication.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.adapter.ListAdapter;
import com.example.myapplication.adapter.SpecialPriceAdapter;
import com.example.myapplication.adapter.ViewPagerMainPagerAdapter;
import com.example.myapplication.databinding.FragmentListBinding;
import com.example.myapplication.databinding.FragmentMainBinding;
import com.example.myapplication.dto.MobileProductForList;
import com.example.myapplication.service.ListService;
import com.example.myapplication.service.ServiceProvider;
import com.example.myapplication.viewHolder.SpecialPriceViewHolder;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        initSpecialPrice();
        initViewPagerMain();
        initCategoryBtn();
        initScrollToTopBtn();


        return binding.getRoot();
    }


    public void initCategoryBtn() {
        Bundle args = new Bundle();

        binding.uglyCtgryBtn.setOnClickListener(v -> {
            args.putSerializable("keyword", "못난이");
            navController.navigate(R.id.action_main_to_list, args);
        });

        binding.watermelonCtgryBtn.setOnClickListener(v -> {
            args.putSerializable("keyword", "수박");
            navController.navigate(R.id.action_main_to_list, args);
        });

        binding.kWatermelonCtgryBtn.setOnClickListener(v -> {
            args.putSerializable("keyword", "참외");
            navController.navigate(R.id.action_main_to_list, args);
        });

        binding.cherryCtgryBtn.setOnClickListener(v -> {
            args.putSerializable("keyword", "체리");
            navController.navigate(R.id.action_main_to_list, args);
        });

        binding.avocadoCtgryBtn.setOnClickListener(v -> {
            args.putSerializable("keyword", "아보카도");
            navController.navigate(R.id.action_main_to_list, args);
        });

        binding.mangoCtgryBtn.setOnClickListener(v -> {
            args.putSerializable("keyword", "망고");
            navController.navigate(R.id.action_main_to_list, args);
        });

        binding.bananaCtgryBtn.setOnClickListener(v -> {
            args.putSerializable("keyword", "바나나");
            navController.navigate(R.id.action_main_to_list, args);
        });

        binding.orangeCtgryBtn.setOnClickListener(v -> {
            args.putSerializable("keyword", "오렌지");
            navController.navigate(R.id.action_main_to_list, args);
        });
    }

    private void initSpecialPrice() {
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        binding.specialPrice.setLayoutManager(layoutManager);

        SpecialPriceAdapter specialPriceAdapter = new SpecialPriceAdapter();

        //API 서버에서 JSON 목록 받기
        ListService listService = ServiceProvider.getListService(getContext());
        Call<List<MobileProductForList>> call = listService.getMobileProductsForList("");

        call.enqueue(new Callback<List<MobileProductForList>>() {
            @Override
            public void onResponse(Call<List<MobileProductForList>> call, Response<List<MobileProductForList>> response) {
                List<MobileProductForList> list = response.body();
                specialPriceAdapter.setList(list);
                binding.specialPrice.setAdapter(specialPriceAdapter);
                Log.i(TAG, "size: " + specialPriceAdapter.getItemCount());
            }

            @Override
            public void onFailure(Call<List<MobileProductForList>> call, Throwable t) {

            }
        });

        //항목을 클릭했을 때 콜백 객체를 등록
        /*specialPriceAdapter.setOnItemClickListener(new SpecialPriceViewHolder.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Log.i(TAG, position + "번 항목 클릭됨");
                MobileProductForList mobileProductForList = specialPriceAdapter.getItem(position);

                Bundle args = new Bundle();
                args.putSerializable("mobileProductForList", mobileProductForList);
                navController.navigate(R.id.action_main_to_detail, args);
            }
        });*/

    }

    private void initAd() {
        /*FragmentManager fragmentManager = getChildFragmentManager(); // 또는 getChildFragmentManager()를 사용할 수 있습니다.
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        CherryAdFragment cherryAdFragment = new CherryAdFragment();
        if (cherryAdFragment != null) {
            transaction.add(R.id.cherry_ad_container, cherryAdFragment);
            transaction.commit();
        }*/
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initViewPagerMain() {
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
        ((MainActivity) requireActivity()).updateBottomNavigationView(R.id.main);
    }

    private void initScrollToTopBtn() {
        binding.nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY > 0) {
                    binding.scrollToTopBtn.show();
                } else {
                    binding.scrollToTopBtn.hide();
                }
            }
        });

        binding.scrollToTopBtn.setOnClickListener(v -> {
            binding.nestedScrollView.smoothScrollTo(0, 0);
        });
    }

}