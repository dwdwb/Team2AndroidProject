package com.example.myapplication.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.AddressAdapter;
import com.example.myapplication.adapter.MyPageOrderedAdapter;
import com.example.myapplication.databinding.FragmentListBinding;
import com.example.myapplication.databinding.FragmentOrderHistoryBinding;
import com.example.myapplication.datastore.AppKeyValueStore;
import com.example.myapplication.dto.AddressList;
import com.example.myapplication.dto.OrderHistory;
import com.example.myapplication.dto.Shopper;
import com.example.myapplication.service.AddressBookService;
import com.example.myapplication.service.ListService;
import com.example.myapplication.service.MyPageOrderedService;
import com.example.myapplication.service.ServiceProvider;
import com.example.myapplication.service.ShopperService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderHistoryFragment extends Fragment {
    private static final String TAG = "OrderHistoryFragment";
    private FragmentOrderHistoryBinding binding;
    private NavController navController;
    private SearchView searchView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentOrderHistoryBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // NavController 얻기
        navController = NavHostFragment.findNavController(this);


        // XML에서 정의한 SearchView를 참조합니다.
        searchView = view.findViewById(R.id.searchView);

        // SearchView의 이벤트 리스너를 설정합니다.
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String searchKeyword) {
                // 사용자가 검색 버튼을 누르면 호출됩니다.
                // 이곳에서 서버로 검색 쿼리를 보내고 결과를 처리합니다.
                // query 변수에 검색어가 포함됩니다.
                Log.i(TAG, "나검색어임"+searchKeyword);
                performSearch(searchKeyword);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // 검색어가 변경될 때마다 호출됩니다.
                // 이곳에서 검색어를 가지고 동적으로 검색 결과를 업데이트할 수 있습니다.
                return false;
            }
        });

        initRecyclerView();
        initScrollToTopBtn();

        initBtnBack();

        return view;
    }

    private void initScrollToTopBtn() {
        binding.recyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if(scrollY > 0) {
                    binding.historyScrollToTopBtn.show();
                }
            }
        });

        binding.historyScrollToTopBtn.setOnClickListener(v -> {
            binding.recyclerView.smoothScrollToPosition(0);
        });
    }

    private void performSearch(String searchKeyword) {
        // 서버로 검색 쿼리를 보내고 결과를 처리하는 코드를 작성합니다.
        // query 변수에 검색어가 포함됩니다.
        String shopperId = AppKeyValueStore.getValue(getContext(), "shopperId");
        ShopperService shopperService = ServiceProvider.getShopperService(getContext());
        Log.i(TAG, "나쇼퍼아이디임"+shopperId);
        Call<Shopper> call =shopperService.getShopper(shopperId);


        call.enqueue(new Callback<Shopper>() {
            @Override
            public void onResponse(Call<Shopper> call, Response<Shopper> response) {
                Shopper shopper = response.body();
                int shopperNo = shopper.getShopperNo();
                MyPageOrderedService myPageOrderedService = ServiceProvider.getMyPageOrderedService(getContext());
                Log.i(TAG, "나검색어임"+searchKeyword);
                Log.i(TAG, "나쇼퍼넘버임"+shopperNo);
                Call<List<OrderHistory>> call2 = myPageOrderedService.searchOrderedList(shopperNo, searchKeyword);

                call2.enqueue(new Callback<List<OrderHistory>>() {
                    @Override
                    public void onResponse(Call<List<OrderHistory>> call2, Response<List<OrderHistory>> response2) {
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                                getContext(), LinearLayoutManager.VERTICAL, false
                        );
                        binding.recyclerView
                                .setLayoutManager(linearLayoutManager);

                        //어댑터 생성
                        MyPageOrderedAdapter myPageOrderedAdapter = new MyPageOrderedAdapter();

                        List<OrderHistory> list = response2.body();
                        Log.i(TAG, "나검색된리스트임"+list.toString());
                        myPageOrderedAdapter.setList(list);
                        binding.recyclerView.setAdapter(myPageOrderedAdapter);

                    }

                    @Override
                    public void onFailure(Call<List<OrderHistory>> call2, Throwable t2) {

                    }
                });


            }

            @Override
            public void onFailure(Call<Shopper> call, Throwable t) {

            }
        });


    }

    // 나머지 코드 및 멤버 변수들도 필요에 따라 추가하세요.






    private void initRecyclerView() {
        //RecyclerView에서 항목을 수직으로 배치하도록 설정
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getContext(), LinearLayoutManager.VERTICAL, false
        );
        binding.recyclerView
                .setLayoutManager(linearLayoutManager);

        //어댑터 생성
        MyPageOrderedAdapter myPageOrderedAdapter = new MyPageOrderedAdapter();
        //Log.i(TAG, "size: " + addressAdapter.getItemCount());

        //API 서버에서 JSON 목록 받기
        MyPageOrderedService myPageOrderedService = ServiceProvider.getMyPageOrderedService(getContext());
        Call<List<OrderHistory>> call = myPageOrderedService.getOrderedList();
        call.enqueue(new Callback<List<OrderHistory>>() {
            @Override
            public void onResponse(Call<List<OrderHistory>> call, Response<List<OrderHistory>> response) {
                List<OrderHistory> list = response.body();

                // onResponse 콜백 내에서 어댑터에 데이터 설정 및 리사이클러뷰에 연결
                myPageOrderedAdapter.setList(list);
                binding.recyclerView.setAdapter(myPageOrderedAdapter);

                Log.i(TAG, "size: " + myPageOrderedAdapter.getItemCount());
            }

            @Override
            public void onFailure(Call<List<OrderHistory>> call, Throwable t) {
                Log.i(TAG, "나너무슬퍼");
                t.printStackTrace();
            }
        });

        //항목을 클릭했을 때 콜백 객체를 등록
        myPageOrderedAdapter.setOnItemClickListener(new MyPageOrderedAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Log.i(TAG, position + "번 항목 클릭됨");
                OrderHistory orderHistory = myPageOrderedAdapter.getItem(position);

                int clickProductNo = orderHistory.getProduct_no();
                Log.i(TAG, clickProductNo+"번 항목 클릭됨");

                Bundle args = new Bundle();
                args.putInt("order_no", orderHistory.getOrder_no());
                args.putInt("product_no", orderHistory.getProduct_no());

                navController.navigate(R.id.action_orderHistory_to_writeReview, args);

            }
        });

    }

    private void initBtnBack() {
        binding.btnBack.setOnClickListener(v -> {
            navController.popBackStack();
        });
    }

}