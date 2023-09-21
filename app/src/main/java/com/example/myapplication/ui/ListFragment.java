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
import com.example.myapplication.dto.ListItemOrder;
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
    private ListItemOrder listItemOrder;
    private String keyword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentListBinding.inflate(inflater);
        //NavController 얻기
        navController = NavHostFragment.findNavController(this);

        listItemOrder = ListItemOrder.PRICE_ASC;

        initBtnMain();
        initBtnCart();
        initBtnBack();
        initSearch();
        initChangeItemOrderBtn();

        Bundle bundle = getArguments();
        keyword = bundle.getString("keyword");
        if (keyword == null) {
            keyword = "";
        }
        initSearchText();
        initList();

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

    private void initSearch() {
        binding.search.setOnClickListener(v -> {
            navController.navigate(R.id.action_list_to_search);
        });
    }

    private void initChangeItemOrderBtn() {
        binding.changeItemOrderBtn.setOnClickListener(v -> {
            ListBottomSheetDialogFragment bottomSheet = new ListBottomSheetDialogFragment(this, listItemOrder);
            bottomSheet.show(getActivity().getSupportFragmentManager(), bottomSheet.getTag());
        });
    }

    public void onDialogClosed(ListItemOrder result) {
        Log.i(TAG, result.toString());
        if (result == ListItemOrder.PRICE_DESC) {
            binding.itemOrderText.setText("높은 가격순");
        } else if (result == ListItemOrder.PRICE_ASC) {
            binding.itemOrderText.setText("낮은 가격순");
        }

        listItemOrder = result;
        initList();
    }

    private void initSearchText() {
        binding.search.setText(keyword);
    }

    private void initList() {
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
        Call<List<MobileProductForList>> call;
        //가격 비싼 순 정렬
        if (listItemOrder == ListItemOrder.PRICE_DESC) {
            call = listService.getMobileProductsForListPriceDesc(keyword);
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
        //가격 저렴한 순 정렬
        } else if (listItemOrder == ListItemOrder.PRICE_ASC) {
            call = listService.getMobileProductsForListPriceAsc(keyword);
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
        }


        //항목을 클릭했을 때 콜백 객체를 등록
        listAdapter.setOnItemClickListener(new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                MobileProductForList mobileProductForList = listAdapter.getItem(position);
                Bundle args = new Bundle();
                args.putInt("board_no", mobileProductForList.getProduct_no());
                navController.navigate(R.id.action_list_to_detail, args);
            }
        });

    }
}