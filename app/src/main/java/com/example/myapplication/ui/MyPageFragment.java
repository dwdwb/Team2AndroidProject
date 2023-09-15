package com.example.myapplication.ui;

import android.net.Uri;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.adapter.MyPageMenuItemAdapter;
import com.example.myapplication.databinding.FragmentMyPageBinding;
import com.example.myapplication.datastore.AppKeyValueStore;
import com.example.myapplication.dto.MyPageMenuItem;
import com.example.myapplication.viewHolder.MyPageMenuItemViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MyPageFragment extends Fragment {
    private static final String TAG = "MyPageFragment";
    private FragmentMyPageBinding binding;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMyPageBinding.inflate(inflater);

        //NavController 얻기
        navController = NavHostFragment.findNavController(this);

        initRecyclerView();

        initLogoutBtn();

        return binding.getRoot();
    }

    private void initLogoutBtn() {
        binding.btnLogout.setOnClickListener(v -> {
            AppKeyValueStore.remove(getContext(), "shopperId");
            AppKeyValueStore.remove(getContext(), "shopperPw");
            ((MainActivity) requireActivity()).reloadBottomNavigationView();
            navController.popBackStack(R.id.main, false);
        });
    }

    public void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getContext(), LinearLayoutManager.VERTICAL, false
        );
        binding.recyclerView.setLayoutManager(linearLayoutManager);

        //어댑터 생성
        MyPageMenuItemAdapter myPageMenuItemAdapter = new MyPageMenuItemAdapter();

        //메뉴 구성
        List<MyPageMenuItem> list = new ArrayList<MyPageMenuItem>();
        //주문 내역
        list.add(new MyPageMenuItem(R.drawable.ic_order_list_36dp, "주문 내역"));
        //찜 리스트
        list.add(new MyPageMenuItem(R.drawable.ic_wish_list_36dp, "찜 리스트"));
        //쿠폰함
        list.add(new MyPageMenuItem(R.drawable.ic_loyalty_36dp, "쿠폰함"));
        //상품 문의
        list.add(new MyPageMenuItem(R.drawable.ic_inquiry_36dp, "상품 문의"));
        /*//내 정보 관리
        list.add(new MyPageMenuItem(R.drawable.ic_person_36dp, "내 정보 관리"));*/
        //리뷰 관리
        list.add(new MyPageMenuItem(R.drawable.ic_review_36dp, "리뷰 관리"));
        //배송지 관리
        list.add(new MyPageMenuItem(R.drawable.ic_shipping_36dp, "배송지 관리"));
        //회원 탈퇴
        list.add(new MyPageMenuItem(R.drawable.ic_close_36dp, "회원 탈퇴"));

        myPageMenuItemAdapter.setList(list);
        binding.recyclerView.setAdapter(myPageMenuItemAdapter);

        myPageMenuItemAdapter.setOnItemClickListener(new MyPageMenuItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View viewItem, int position) {
                MyPageMenuItem myPageMenuItem = myPageMenuItemAdapter.getItem(position);
                if (myPageMenuItem.getMenuText().equals("주문 내역")) {
                    navController.navigate(R.id.action_myPage_to_orderHistory);
                } else if (myPageMenuItem.getMenuText().equals("찜 리스트")) {
                    navController.navigate(R.id.action_myPage_to_wish);
                } else if (myPageMenuItem.getMenuText().equals("쿠폰함")) {
                    navController.navigate(R.id.action_myPage_to_coupon);
                } else if (myPageMenuItem.getMenuText().equals("상품 문의")) {
                    navController.navigate(R.id.action_myPage_to_inquiry);
                } /*else if (myPageMenuItem.getMenuText().equals("내 정보 관리")) {
                    navController.navigate(R.id.action_myPage_to_orderHistory);*/
                else if (myPageMenuItem.getMenuText().equals("리뷰 관리")) {
                    navController.navigate(R.id.action_myPage_to_review);
                } else if (myPageMenuItem.getMenuText().equals("배송지 관리")) {
                    navController.navigate(R.id.action_myPage_to_shipping);
                } else if (myPageMenuItem.getMenuText().equals("회원 탈퇴")) {

                }
            }
        });
    }


}