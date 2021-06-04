package com.moringaschool.kenyatravelguide;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenTripMapApi {
    @GET("businesses/search")
    Call<OpenTripMapCitySearchResponse> getDestinations(
//            @Query("location") String location,
//            @Query("term") String term
    );
}
