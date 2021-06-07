package com.moringaschool.kenyatravelguide;

import retrofit2.Call;
import retrofit2.http.GET;

public interface OpenTripMapApi {
    @GET("places/radius?radius=10000000&lon=37.9062&lat=0.0236&format=json&apikey=5ae2e3f221c38a28845f05b6ee7d87e85387dcaff9194900e52e074a")
    Call<KenyaSightingsClass> getKenyaSightings();

    @GET("places/radius?radius=10000000&lon=37.9062&lat=0.0236&kinds=tourist_facilities&format=json&apikey=5ae2e3f221c38a28845f05b6ee7d87e85387dcaff9194900e52e074a")
    Call<TouristFacilitiesModelClass> getTouristFacilitiesKenya();

}
