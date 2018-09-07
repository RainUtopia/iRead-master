package com.yang.iread.http;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Date：2018/5/25 on 16:35
 * Description:
 *
 * @author jianhong
 */
public class HttpManager {
    private static final int HTTP_CONNECT_TIMEOUT = 10;
    private static final int HTTP_WRITE_TIMEOUT = 10;
    private static final int HTTP_READ_TIMEOUT = 10;

    private static final String mDefaultUrl = "https://api.douban.com/";
    public static final String videoUrl = "http://2449.vod.myqcloud.com/";

    private static volatile HttpManager mHttpManager;

    private HttpManager() {
    }

    public static HttpManager getInstance() {
        if (mHttpManager == null) {
            synchronized (HttpManager.class) {
                mHttpManager = new HttpManager();
            }
        }
        return mHttpManager;
    }

    private OkHttpClient createOkHttpClient() {
        OkHttpClient okHttpClient;
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel( HttpLoggingInterceptor.Level.BODY );

        OkHttpClient.Builder client = new OkHttpClient.Builder()
                //.addInterceptor( getHeaderInterceptor() )
                .addInterceptor( logging )
                .connectTimeout( HTTP_CONNECT_TIMEOUT, TimeUnit.SECONDS )
                .writeTimeout( HTTP_WRITE_TIMEOUT, TimeUnit.SECONDS )
                .readTimeout( HTTP_READ_TIMEOUT, TimeUnit.SECONDS );

        okHttpClient = client.build();

        return okHttpClient;
    }

    public HttpApi getHttpApi(String... url) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl( url == null ? mDefaultUrl : url[0] )
                .client( createOkHttpClient() )
                //.addConverterFactory( new EmptyConverterFactory() )
                .addConverterFactory( GsonConverterFactory.create() )
                .addCallAdapterFactory( RxJava2CallAdapterFactory.create() )
                .build();

        return retrofit.create( HttpApi.class );
    }
}
