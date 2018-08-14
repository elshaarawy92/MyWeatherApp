package com.example.myweatherapp.api;

import com.example.myweatherapp.bean.Weather;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by esliang on 2018/8/13
 */
public interface DataRequest {

    //获取天气数据
    @GET("now")
    Observable<Weather> getNowWeather(@Query("location")String location,@Query("key")String key);

}
