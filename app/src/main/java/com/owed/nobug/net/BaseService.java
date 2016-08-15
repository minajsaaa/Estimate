package com.owed.nobug.net;

import com.owed.estimate.model.cast.CastGroup;
import com.owed.estimate.model.cast.CastItem;
import com.owed.estimate.model.company.detail.CompanyDetailItem;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BaseService {

    @GET("version_android.json")
    Call<Objects> getVersion();

    //  ========================================================================================

    @GET("owed/login")
    Call<Objects> login();

    //  ========================================================================================

    @GET("company/lists")
    Call<Com> getCompanyList(@Query("page") int page, @Query("limit") int limit);

    @GET("company/detail")
    Call<CompanyDetailItem> getCompanyDetail(@Query("no") int no);

    //  ========================================================================================

    @GET("cast/lists")
    Call<CastGroup> getCastList(@Query("page") int page, @Query("limit") int limit);

    @GET("cast/detail")
    Call<CastItem> getCastDetail(@Query("no") int no);

    //  ========================================================================================

    @GET("deal")
    Call<CastItem> get(@Query("no") int no);

    //  ========================================================================================

}
