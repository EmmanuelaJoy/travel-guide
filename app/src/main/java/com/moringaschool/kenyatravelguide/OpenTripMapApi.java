package com.moringaschool.kenyatravelguide;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenTripMapApi {
    @GET("/places/radius?radius=10000000&lon=37.9062&lat=0.0236&kinds=tourist_facilities&format=json&apikey="+BuildConfig.OPENTRIPMAP_API_KEY)
    Call<TouristFacilitiesModelClass> getTouristFacilitiesKenya();

}
