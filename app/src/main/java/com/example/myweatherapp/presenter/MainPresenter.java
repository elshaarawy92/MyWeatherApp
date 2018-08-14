package com.example.myweatherapp.presenter;

import com.example.myweatherapp.bean.Weather;
import com.example.myweatherapp.contract.MainContract;
import com.example.myweatherapp.model.MainRepository;

import java.util.List;


/**
 * Created by esliang on 2018/8/13
 */
public class MainPresenter implements MainContract.Presenter{

    private MainRepository mainRepository;

    private MainContract.View mainView;

    public MainPresenter(MainContract.View mainView) {
        mainRepository = new MainRepository(this);
        this.mainView = mainView;
    }

    @Override
    public void getWeather(String location) {
        mainRepository.getWeather(location);
    }

    @Override
    public void setWeather(List<Weather> weathers) {
        mainView.setWeather(weathers);
    }
}
