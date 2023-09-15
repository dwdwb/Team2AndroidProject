package com.example.myapplication;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.datastore.AppKeyValueStore;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private NavController navController;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host);
        navController = navHostFragment.getNavController();

        //상태바를 투명
        //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        initBottomNavigationBar();
        initAppBar();


        initDestinationChangedListener();
    }

    public void updateBottomNavigationView(int itemId) {
        binding.bottomNavigationView.setSelectedItemId(itemId);
    }

    public void reloadBottomNavigationView() {
        initBottomNavigationBar();
    }

    private void initBottomNavigationBar() {
        String shopperId = AppKeyValueStore.getValue(this, "shopperId");
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.main) {
                navController.popBackStack(R.id.main, false);
                return true;
            } else if (item.getItemId() == R.id.search) {
                navController.popBackStack(R.id.main, false);
                navController.navigate(R.id.action_main_to_search);
                return true;
            } else if (item.getItemId() == R.id.cart) {
                if (shopperId == null) {
                    navController.navigate(R.id.login);
                } else {
                    navController.popBackStack(R.id.main, false);
                    navController.navigate(R.id.action_main_to_cart);
                }
                return true;
            } else if (item.getItemId() == R.id.myPage) {
                if (shopperId == null) {
                    navController.navigate(R.id.login);
                } else {
                    navController.popBackStack(R.id.main, false);
                    navController.navigate(R.id.action_main_to_myPage);
                }
                return true;
            }
            return false;
        });
    }



    private void initAppBar() {
        //Toolbar를 Appbar로 설정
        setSupportActionBar(binding.toolbar);
        //그래프 상에 있는 이동 경로를 참고
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        //Toolbar의 타이틀 및 탐색 버튼(목록 또는 백버튼)을 navController 탐색 그래프를 참고해서 처리
        // 1) Toolbar의 제목은 대상의 label 속성을 이용해서 표시됨
        // 2) 백버튼(<-)이 표시되며, 클릭시 이전 대상으로 이동

        //Toolbar만 단독으로 사용할 경우
        NavigationUI.setupWithNavController(binding.toolbar,navController, appBarConfiguration);

        //CollapsingToolbarLayout을 사용할 경우
        /*NavigationUI.setupWithNavController(binding.collapsingToolbarLayout, binding.toolbar,navController, appBarConfiguration);*/

        //하단 탐색 뷰와 navController 연동
        //조건: 아이템 id = 대상 id
        //NavigationUI.setupWithNavController(binding.bottomNavigationView, navController);

        /*
        getSupportActionBar().setDisplayHomeAsUpEnabled(false); // 백 버튼 활성화
        getSupportActionBar().setDisplayShowHomeEnabled(true); // 홈 아이콘 표시
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back_24dp);*/
    }

    private void initDestinationChangedListener() {
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
                //이동한 대상에 따른 처리
                if(navDestination.getId() == R.id.detail || navDestination.getId() == R.id.cart) {
                    getSupportActionBar().show();
                } else  {
                    getSupportActionBar().hide();
                }
            }
        });
    }
}