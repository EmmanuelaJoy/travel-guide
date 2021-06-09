package com.moringaschool.kenyatravelguide.network;

import com.moringaschool.kenyatravelguide.BuildConfig;
import com.moringaschool.kenyatravelguide.models.KenyaSightingsClass;
import com.moringaschool.kenyatravelguide.models.TouristFacilitiesModelClass;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface OpenTripMapApi {
    @GET("places/radius?radius=10000000&lon=37.9062&lat=0.0236&format=json&apikey=")
    Call<KenyaSightingsClass> getKenyaSightings(@Query(BuildConfig.KEY) String apikey);

    @GET("places/radius?radius=10000000&lon=37.9062&lat=0.0236&kinds=tourist_facilities&format=json")
    Call<TouristFacilitiesModelClass> getTouristFacilitiesKenya();

}
