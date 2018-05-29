package com.example.letsseatinmetro.Network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by 이승헌 on 2018-05-28.
 */

public interface SubwayApiService {
    @GET("api/subway/{apikey}/json/realtimePosition/1/150/{linenum}")
    Call<Station> getStationList(
            @Path("apikey") String apikey,
            @Path("linenum") String linenum
    );

}
