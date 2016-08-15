package com.owed.nobug.net;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BaseService {

    @GET("version_android.json")
    Call<Objects> getVersion();

    //  ========================================================================================

    @GET("owed/login")
    Call<Objects> login();

}
