package com.example.myweatherapp.contract;

import com.example.myweatherapp.bean.Weather;

import java.util.List;

/**
 * Created by esliang on 2018/8/13
 */
public class MainContract {

    public interface Presenter{

        //获取天气数据
        void getWeather(String location);

        //设置天气到view上
        void setWeather(List<Weather> weathers);
    }

    public interface View{

        //获取数据成功
        void getSuccess();

        //获取失败
        void getFailed();

        //把数据显示到view上
        void setWeather(List<Weather> weathers);

    }

}
