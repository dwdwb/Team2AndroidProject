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
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS) // 연결 타임아웃
                .readTimeout(30, TimeUnit.SECONDS)    // 읽기 타임아웃
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                //BASE_URL의 끝에 /가 붙어있어야 한다
                .baseUrl(NetworkInfo.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
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
}
