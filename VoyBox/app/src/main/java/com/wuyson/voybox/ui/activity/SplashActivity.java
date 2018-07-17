package com.wuyson.voybox.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.wuyson.common.base.BaseActivity;
import com.wuyson.voybox.R;
import com.wuyson.voybox.net.Api;
import com.wuyson.voybox.net.ApiService;
import com.wuyson.voybox.net.base.ApiManager;
import com.wuyson.voybox.net.base.BaseModel;
import com.wuyson.voybox.net.base.ImageModel;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author : Wuyson
 * @date : 2018/7/4-16:11
 */

public class SplashActivity extends BaseActivity {
    private static final String TAG = "SplashActivity";
    private ImageView imgSplash;

    @Override
    protected int setContentViewId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        imgSplash = findViewById(R.id.img_splash);

        getData();
    }

    private void getData() {

//        ApiService apiService = Api.getService();
//        getData1(apiService);
//        getData2(apiService);

//        getRxData();

        ApiService apiService = Api.getRxService();
        ApiManager.post("gg");
    }

    private void getRxData() {
        ApiService rxService = Api.getRxService();
        Observable<BaseModel> observable = rxService.listRxRepos();
        observable.map(new Function<BaseModel, List<ImageModel>>() {
            @Override
            public List<ImageModel> apply(BaseModel baseModel) throws Exception {
                return baseModel.getResults();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ImageModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(List<ImageModel> imageModels) {
                        Log.e(TAG, "onNext: ");
                        Glide.with(SplashActivity.this).load(imageModels.get(0).getUrl()).into(imgSplash);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: ");
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete: ");
                    }
                });
    }

    /**
     * Retrofit直接装换成model使用
     *
     * @param apiService
     */
    private void getData1(ApiService apiService) {
        Call<BaseModel> baseModelCall = apiService.listRepos();
        baseModelCall.enqueue(new Callback<BaseModel>() {
            @Override
            public void onResponse(Call<BaseModel> call, Response<BaseModel> response) {
                BaseModel body = response.body();
                Glide.with(SplashActivity.this).load(body.getResults().get(0).getUrl()).into(imgSplash);
            }

            @Override
            public void onFailure(Call<BaseModel> call, Throwable t) {

            }
        });
    }

    /**
     * 获取原始的json
     *
     * @param apiService
     */
    private void getData2(ApiService apiService) {
        Call<ResponseBody> baseModelCall = apiService.listRepos2("data/福利/10/1");

        baseModelCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    String string = "";
                    ResponseBody body = response.body();
                    if (body != null) {
                        string = body.string();
                        Log.e(TAG, "onResponse: " + string);
                    }
                    BaseModel model = new Gson().fromJson(string, BaseModel.class);
                    Glide.with(SplashActivity.this).load(model.getResults().get(0).getUrl()).into(imgSplash);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
