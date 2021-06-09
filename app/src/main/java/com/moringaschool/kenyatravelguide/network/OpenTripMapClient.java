package com.moringaschool.kenyatravelguide.network;

import com.moringaschool.kenyatravelguide.BuildConfig;
import com.moringaschool.kenyatravelguide.network.OpenTripMapApi;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.moringaschool.kenyatravelguide.Constants.OPENTRIPMAP_API_KEY;
import static com.moringaschool.kenyatravelguide.Constants.OPENTRIPMAP_BASE_URL;

public class OpenTripMapClient {
    private static Retrofit retrofit = null;

    public static OpenTripMapApi getClient() {

        if (retrofit == null) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request newRequest  = chain.request().newBuilder()
                                    .addHeader("Authorization", OPENTRIPMAP_API_KEY)
                                    .build();
                            return chain.proceed(newRequest);
                        }
                    })
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(OPENTRIPMAP_BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

//        OkHttpClient.Builder httpClient =
//                new OkHttpClient.Builder();
//        httpClient.addInterceptor(new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request original = chain.request();
//                HttpUrl originalHttpUrl = original.url();
//
//                HttpUrl url = originalHttpUrl.newBuilder()
//                        .addQueryParameter("&apikey=", BuildConfig.OPENTRIPMAP_API_KEY)
//                        .build();
//
//                // Request customization: add request headers
//                Request.Builder requestBuilder = original.newBuilder()
//                        .url(url);
//
//                Request request = requestBuilder.build();
//                return chain.proceed(request);
//            }
//        });

        return retrofit.create(OpenTripMapApi.class);
    }
}
