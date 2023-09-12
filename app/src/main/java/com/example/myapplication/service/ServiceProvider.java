package com.example.myapplication.service;

import android.content.Context;

import java.io.IOException;

public class ServiceProvider {
    private static final String TAG = "ServiceProvider";
/*
    public static Retrofit getRetrofit(Context context) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        //오리지널 요청 내용을 가지고 있는 요청 객체
                        Request request = chain.request();
                        //공통 파라미터 설정
               *//* HttpUrl newUrl = request.url().newBuilder()
                        //.addQueryParameter("param1", "value1")
                        //.addQueryParameter("param2", "value2")
                          .build();*//*
                        HttpUrl.Builder httpUrlBuilder = request.url().newBuilder();
                        String mid = AppKeyValueStore.getValue(context, "mid");
                        String mpassword = AppKeyValueStore.getValue(context, "mpassword");
                        if(mid != null && mpassword != null) {
                            //mid로 이름을 주었을 경우 게시물 쓰기의 mid와 중복이 되기 때문에
                            //인증 정보를 보낼때에 이름을 userId로 변경
                            httpUrlBuilder.addQueryParameter("userId", mid);
                            httpUrlBuilder.addQueryParameter("userPassword", mpassword);


                        }
                        HttpUrl newUrl = httpUrlBuilder.build();
                        //공통 헤더 설정
                        Request updatedrequest = request.newBuilder()
                                .url(newUrl)
                                //.addHeader("name1", "value1")
                                //.addHeader("name2", "value2")
                                .build();
                        return chain.proceed(updatedrequest);
                    }
                })
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                // 끝에 /가 있어야 오류나지 않음
                .baseUrl(NetworkInfo.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        return retrofit;
    }

    public static MemberService getMemberService(Context context) {
        MemberService memberService = getRetrofit(context).create(MemberService.class);
        return memberService;
    }

    public static BoardService getBoardService(Context context) {
        BoardService boardService = getRetrofit(context).create(BoardService.class);
        return boardService;
    }*/
}
