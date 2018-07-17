package com.wuyson.voybox.net.base;

import android.util.Log;

import com.google.gson.Gson;
import com.wuyson.common.net.okhttp.OkHttpHola;
import com.wuyson.voybox.net.AESOperator;
import com.wuyson.voybox.net.Api;
import com.wuyson.voybox.net.ApiService;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;


public class ApiManager {
    private static final String TAG = "ApiManager";
    public static void post(final String serviceName, String sessionId, BaseRequestBody.Content content) {
        Gson gson = new Gson();
        BaseRequestBody baseRequestBody = new BaseRequestBody(serviceName,sessionId, content);

        String jsonStr = gson.toJson(baseRequestBody);
        try {
            String encrypt = AESOperator.encrypt(jsonStr);
            RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), encrypt);

            Log.e(TAG, "post: "+jsonStr );
            Api.getRxService().post(body).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ResponseBody>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(ResponseBody responseBody) {
                    try {
                        String encryptStr = responseBody.string();
                        String json = AESOperator.decrypt(encryptStr);


                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onComplete() {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
