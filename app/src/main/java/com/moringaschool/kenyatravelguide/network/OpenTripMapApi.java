package com.moringaschool.kenyatravelguide.network;

import com.moringaschool.kenyatravelguide.models.KenyaSightingsClass;
import com.moringaschool.kenyatravelguide.models.TouristFacilitiesModelClass;

import retrofit2.Call;
import retrofit2.http.GET;

public interface OpenTripMapApi {
    @GET("places/radius?radius=10000000&lon=37.9062&lat=0.0236&format=json")
    Call<KenyaSightingsClass> getKenyaSightings();

    @GET("places/radius?radius=10000000&lon=37.9062&lat=0.0236&kinds=tourist_facilities&format=json")
    Call<TouristFacilitiesModelClass> getTouristFacilitiesKenya();

}
