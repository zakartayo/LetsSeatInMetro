package com.example.letsseatinmetro.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 이승헌 on 2018-05-28.
 */

public class ServiceGenerator {
    private static final String SW_API_ROOT_URL = "http://swopenapi.seoul.go.kr/";
    /**
     * Get Retrofit Instance
     */
//baseUrl은 /로 끝나야 한다.
    private static Retrofit getStationListInstance() {
        return new Retrofit.Builder()
                .baseUrl(SW_API_ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * Get API Service
     *
     * @return API Service
     */
    public static SubwayApiService getListApiService() {
        return getStationListInstance().create(SubwayApiService.class);
    }

}

