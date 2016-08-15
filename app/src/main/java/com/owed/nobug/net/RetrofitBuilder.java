package com.owed.nobug.net;

import android.content.Context;

import com.owed.estimate.BuildConfig;
import com.owed.nobug.constant.HeaderProperty;

import java.io.IOException;
import java.security.cert.CertificateException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {

    // OkHttp Client
    private static OkHttpClient client, unsafeClient;

    //  =====================================================================================

/**
     * Create OkHttpClient Instance
     * @param context Context
     * @return OkHttpClient Instance
     */
    public static OkHttpClient getOkHttpClient(final Context context) {
        client = new OkHttpClient.Builder()
                .addInterceptor(getInterceptor(context))
                .build();
        return client;
    }

    //  =========================================================================================

    public static BaseService with(final Context context) {
        BaseService baseService = new Retrofit.Builder()
                .client(getOkHttpClient(context))
                .baseUrl(BuildConfig.API_HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(BaseService.class);
        return baseService;
    }

    public static BaseService withUnsafe(final Context context) {
        BaseService baseService = new Retrofit.Builder()
                .client(getUnsafeOkHttpClient(context))
                .baseUrl(BuildConfig.API_HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(BaseService.class);
        return baseService;
    }

    private static Interceptor getInterceptor(final Context context) {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Request original = chain.request();
                Request request = original.newBuilder()
                        .header(HeaderProperty.AUTH_TOKEN, "get data")
                        .header(HeaderProperty.USER_AGENT, HeaderProperty.getUserAgent(context))
                        .method(original.method(), original.body())
                        .build();
                return chain.proceed(request);
            }
        };
    }

    private static OkHttpClient getUnsafeOkHttpClient(final Context context) {
        try {
            final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                @Override
                public void checkClientTrusted(
                        java.security.cert.X509Certificate[] chain,
                        String authType) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(
                        java.security.cert.X509Certificate[] chain,
                        String authType) throws CertificateException {
                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[0];
                }
            }};

            final SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            unsafeClient = new OkHttpClient.Builder().sslSocketFactory(sslContext.getSocketFactory())
                    .hostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER)
                    .addInterceptor(getInterceptor(context))
                    .build();
            return unsafeClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}

