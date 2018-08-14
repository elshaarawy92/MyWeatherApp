package com.example.myweatherapp.model;

import android.util.Log;

import com.example.myweatherapp.api.DataRequest;
import com.example.myweatherapp.bean.Weather;
import com.example.myweatherapp.contract.MainContract;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by esliang on 2018/8/13。
 */
public class MainRepository {

    private final static String KEY = "你自己的key";

    private final static String TAG = "MainRepository";

    private final static String BASE_URL = "https://free-api.heweather.com/s6/weather/";

    private List<Weather> weathers = new ArrayList<>();

    private MainContract.Presenter presenter;

    public MainRepository(MainContract.Presenter presenter) {
        this.presenter = presenter;
    }

    /**
     * 获取天气数据
     * @param location 城市
     * @return Weather类的数据
     */
    public void getWeather(String location){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        DataRequest dataRequest = retrofit.create(DataRequest.class);
        dataRequest.getNowWeather(location,KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Weather>() {
                    @Override
                    public void onCompleted() {
                        presenter.setWeather(weathers);
                        Log.d(TAG, "onCompleted: " + weathers.get(0).getHeWeather6().get(0).getBasic().getLocation());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onNext(Weather weather) {
                        weathers.add(weather);
                        Log.d(TAG, "onNext: " + weather.getHeWeather6().get(0).getBasic().getLocation());
                    }
                });
        weathers.clear();
    }

}
