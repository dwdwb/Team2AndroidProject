package com.example.myapplication.ui;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentLoginBinding;
import com.example.myapplication.datastore.AppKeyValueStore;
import com.example.myapplication.dto.LoginResult;
import com.example.myapplication.service.ServiceProvider;
import com.example.myapplication.service.ShopperService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginFragment extends Fragment {
    private static final String TAG = "LoginFragment";

    private FragmentLoginBinding binding;

    private NavController navController;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater);

        //NavController 얻기
        navController = NavHostFragment.findNavController(this);

        initBtnLogin();

        return binding.getRoot();
    }

    private void  initBtnLogin() {
        binding.btnLogin.setOnClickListener(v -> {
            String shopperId = binding.shopperId.getText().toString();
            String shopperPw = binding.shopperPw.getText().toString();

            //MemberService 구현 객체 얻기
            ShopperService shopperService = ServiceProvider.getShopperService(getContext());
            //login 요청 객체 얻기
            Call<LoginResult> call = shopperService.tryLogin(shopperId, shopperPw);
            call.enqueue(new Callback<LoginResult>() {
                @Override
                public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                    LoginResult loginResult = response.body();
                    if(loginResult.getResult().equals("success")) {
                        Log.i(TAG, "로그인 성공");

                        //로그인 성공시 mid와 mpassword를 공유 저장소에 저장
                        AppKeyValueStore.put(getContext(), "shopperId", loginResult.getShopperId());
                        AppKeyValueStore.put(getContext(), "shopperPw", loginResult.getShopperPw());
                        ((MainActivity) requireActivity()).reloadBottomNavigationView();

                        //메인으로 이동
                        navController.popBackStack(R.id.main, false);

                    } else {
                        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                                .setTitle("로그인 실패")
                                .setMessage(loginResult.getResult())
                                .setPositiveButton("확인", null)
                                .create();
                        alertDialog.show();
                    }
                }
                //네트워크 통신이 안될때 실행
                @Override
                public void onFailure(Call<LoginResult> call, Throwable t) {
                }
            });
        });

    }
}