package com.example.myapplication.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.adapter.ListAdapter;
import com.example.myapplication.databinding.FragmentListBinding;
import com.example.myapplication.databinding.FragmentSearchBinding;
import com.example.myapplication.dto.MobileProductForList;
import com.example.myapplication.service.ListService;
import com.example.myapplication.service.ServiceProvider;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListFragment extends Fragment {
    private static final String TAG = "ListFragment";
    private FragmentListBinding binding;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentListBinding.inflate(inflater);
        //NavController 얻기
        navController = NavHostFragment.findNavController(this);

        initBtnMain();
        initBtnCart();
        initBtnBack();

        Bundle bundle = getArguments();
        String keyword = bundle.getString("keyword");
        if (keyword == null) {
            keyword = "";
        }
        initSearchText(keyword);
        initList(keyword);

        return binding.getRoot();
    }

    private void initBtnMain() {
        binding.btnMain.setOnClickListener(v -> {
            //대상으로 이동, 백스택의 위쪽 대상으로 모두 제거
            navController.popBackStack(R.id.main, false);
        });
    }

    private void initBtnCart() {
        binding.btnCart.setOnClickListener(v -> {
            navController.navigate(R.id.action_list_to_cart);
        });
    }

    private void initBtnBack() {
        binding.btnBack.setOnClickListener(v -> {
            navController.popBackStack();
        });
    }

    private void initSearchText(String keyword) {
        binding.search.setText(keyword);
    }

    private void initList(String keyword) {
        //RecyclerView에서 항목을 수직으로 배치하도록 설정
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getContext(), LinearLayoutManager.VERTICAL, false
        );
        binding.recyclerView.setLayoutManager(linearLayoutManager);

        //어댑터 생성
        ListAdapter listAdapter = new ListAdapter();
        Log.i(TAG, "size: " + listAdapter.getItemCount());

        //API 서버에서 JSON 목록 받기
        ListService listService = ServiceProvider.getListService(getContext());
        Call<List<MobileProductForList>> call = listService.getMobileProductsForList(keyword);
        call.enqueue(new Callback<List<MobileProductForList>>() {
            @Override
            public void onResponse(Call<List<MobileProductForList>> call, Response<List<MobileProductForList>> response) {
                List<MobileProductForList> list = response.body();
                listAdapter.setList(list);
                binding.recyclerView.setAdapter(listAdapter);
                Log.i(TAG, "size: " + listAdapter.getItemCount());
            }

            @Override
            public void onFailure(Call<List<MobileProductForList>> call, Throwable t) {

            }
        });

        //항목을 클릭했을 때 콜백 객체를 등록
        listAdapter.setOnItemClickListener(new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Log.i(TAG, position + "번 항목 클릭됨");
                MobileProductForList mobileProductForList = listAdapter.getItem(position);

                Bundle args = new Bundle();
                args.putSerializable("mobileProductForList", mobileProductForList);
                navController.navigate(R.id.action_list_to_detail, args);
            }
        });

    }
}