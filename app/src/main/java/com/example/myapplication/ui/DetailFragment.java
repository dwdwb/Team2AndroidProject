package com.example.myapplication.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.DetailPagerAdapter;
import com.example.myapplication.databinding.FragmentDetailBinding;
import com.example.myapplication.dto.CartProduct;
import com.example.myapplication.dto.ProductBoard;
import com.example.myapplication.dto.ReviewInfo;
import com.example.myapplication.service.CartService;
import com.example.myapplication.service.DetailViewService;
import com.example.myapplication.service.ServiceProvider;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailFragment extends Fragment {
    private static final String TAG = "DetailFragment";
    private FragmentDetailBinding binding;
    private NavController navController;
    private int bno;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDetailBinding.inflate(inflater);

        //NavController 얻기
        navController = NavHostFragment.findNavController(this);

        Bundle bundle = getArguments();
        if(bundle != null) {
            bno = bundle.getInt("board_no");
        }

        Log.i(TAG, "bno: " + bno);

        //메뉴 초기화
        initMenu();

        //하단바 숨기기
        hideBottomNavigation(true);

        //뷰페이저
        initPagerView();

        return binding.getRoot();
    }

    //메뉴 초기화
    private void initMenu() {
        MenuProvider menuProvider = new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                menuInflater.inflate(R.menu.top_menu,menu);
            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId() == R.id.main) {
                    navController.popBackStack(R.id.main, false);
                    return true;
                } else if (menuItem.getItemId() == R.id.cart) {
                    navController.navigate(R.id.action_detail_to_cart);
                    return true;
                }
                return false;
            }
        };

        getActivity().addMenuProvider(menuProvider, getViewLifecycleOwner(), Lifecycle.State.RESUMED);
    }

    //하단바 설정
    public void hideBottomNavigation(Boolean bool) {
        BottomNavigationView bottomNavigation = getActivity().findViewById(R.id.bottomNavigationView);
        if (bool == true)
            bottomNavigation.setVisibility(View.GONE);
        else
            bottomNavigation.setVisibility(View.VISIBLE);
    }

    //뷰페이저
    private void initPagerView() {
        DetailPagerAdapter detailPagerAdapter = new DetailPagerAdapter(getActivity().getSupportFragmentManager(), getLifecycle());
        detailPagerAdapter.setBno(bno);
        binding.detailViewPager.setAdapter(detailPagerAdapter);

        //=== TabLayout기능 추가 부분 ============================================
        new TabLayoutMediator(binding.detailTabLayout, binding.detailViewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("상품 상세");
                        break;
                    case 1:
                        tab.setText("상품평");
                        break;
                    case 2:
                        tab.setText("상품 문의");
                        break;
                    default:
                        break;
                }
            }
        }).attach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        //하단바 보이기
        hideBottomNavigation(false);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "이게 실행되는건가여??");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "그럼 이건가??");
    }
}