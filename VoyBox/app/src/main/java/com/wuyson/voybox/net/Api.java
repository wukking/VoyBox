package com.wuyson.voybox.net;


import com.wuyson.common.net.retrofit.RetrofitHola;

public class Api {
    private static volatile ApiService sApiService;
    public static ApiService getService(){
        if (sApiService == null){
            synchronized (Api.class){
                if (sApiService == null){
                    sApiService = RetrofitHola.getInstance().create(ApiService.class);
                }
            }
        }
        return sApiService;
    }

    public static ApiService getRxService(){
        if (sApiService == null){
            synchronized (Api.class){
                if (sApiService == null){
                    sApiService = RetrofitHola.getRxInstance().create(ApiService.class);
                }
            }
        }
        return sApiService;
    }

}
