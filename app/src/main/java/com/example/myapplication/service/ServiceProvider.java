package com.example.myapplication.service;

import android.content.Context;

import com.example.myapplication.datastore.AppKeyValueStore;
import com.example.myapplication.dto.MobileProductForList;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceProvider {
    private static final String TAG = "ServiceProvider";

    public static Retrofit getRetrofit(Context context) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    // 오리지널 요청 내용을 가지고 있는 요청 객체
                    Request request = chain.request();
                    //공통 파라미터 설정---------------------------------
                /*HttpUrl newUrl = request.url().newBuilder()
                        .addQueryParameter("param1", "value1")
                        .addQueryParameter("param2", "value2")
                        .build();*/

                    HttpUrl.Builder httpUrlBuilder = request.url().newBuilder();
                    String shopperId = AppKeyValueStore.getValue(context, "shopperId");
                    String shopperPw = AppKeyValueStore.getValue(context, "shopperPw");
                    if (shopperId != null && shopperPw != null) {
                        //mid로 이름을 주었을 경우에 게시물 쓰기의 mid와 중복 발생
                        //인증 정보를 보낼 때 이름을 userId로 변경
                        httpUrlBuilder.addQueryParameter("userId", shopperId);
                        httpUrlBuilder.addQueryParameter("userPassword", shopperPw);
                    }
                    HttpUrl newUrl = httpUrlBuilder.build();

                    // 공통 헤더 설정
                    Request updatedRequest = request.newBuilder()
                            .url(newUrl)
                            //.addHeader("name1", "value1")
                            //.addHeader("name2", "value2")
                            .build();

                    return chain.proceed(updatedRequest);
                }
            })
            .build();

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS) // 연결 타임아웃
                .readTimeout(30, TimeUnit.SECONDS)    // 읽기 타임아웃
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                //BASE_URL의 끝에 /가 붙어있어야 한다
                .baseUrl(NetworkInfo.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        return retrofit;
    }

    public static ListService getListService(Context context) {
        ListService listService = getRetrofit(context).create(ListService.class);
        return listService;
    }

    public static AddressBookService getAddressBookService(Context context) {
        AddressBookService addressBookService = getRetrofit(context).create(AddressBookService.class);
        return addressBookService;
    }

    public static CartService getCartService(Context context) {
        CartService cartService = getRetrofit(context).create(CartService.class);
        return cartService;
    }

    public static ReviewService getReviewService(Context context) {
        ReviewService reviewService = getRetrofit(context).create(ReviewService.class);
        return reviewService;
    }

    public static MyPageCouponService getMyPageCouponService(Context context) {
        MyPageCouponService myPageCouponService = getRetrofit(context).create(MyPageCouponService.class);
        return myPageCouponService;
    }

    public static MyPageOrderedService getMyPageOrderedService(Context context) {
        MyPageOrderedService myPageOrderedService = getRetrofit(context).create(MyPageOrderedService.class);
        return myPageOrderedService;
    }

    public static ShopperService getShopperService(Context context) {
        ShopperService shopperService = getRetrofit(context).create(ShopperService.class);
        return shopperService;
    }

    public static DetailViewService getDetailViewService(Context context) {
        DetailViewService detailViewService = getRetrofit(context).create(DetailViewService.class);
        return detailViewService;
    }

    public static MyPageShopperInquiryService getMyPageShopperInquiryService(Context context) {
        MyPageShopperInquiryService myPageShopperInquiryService = getRetrofit(context).create(MyPageShopperInquiryService.class);
        return myPageShopperInquiryService;
    }
}
