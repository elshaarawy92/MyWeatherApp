package com.example.myweatherapp.view;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myweatherapp.R;
import com.example.myweatherapp.bean.Weather;
import com.example.myweatherapp.contract.MainContract;
import com.example.myweatherapp.presenter.MainPresenter;

import java.util.List;

/**
 * Created by esliang on 2018/8/13
 */
public class MainActivity extends AppCompatActivity implements MainContract.View{

    private final static String TAG = "MainActivity";

    private MainContract.Presenter presenter;

    TextInputEditText editText;

    TextView tLocation;

    TextView tParentCity;

    TextView tAdminArea;

    TextView tCnty;

    TextView tLat;

    TextView tLon;

    TextView tTmp;

    TextView tFl;

    TextView tCondTxt;

    TextView tWindDir;

    TextView tWindSc;

    TextView tWindSpd;

    TextView tHum;

    TextView tLoc;

    Button bSearch;

    private String sCity;

    LinearLayout background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       init();
       onClick();
    }

    public void init(){
        presenter = new MainPresenter(this);
        editText = findViewById(R.id.et_city);
        tAdminArea = findViewById(R.id.t_admin_area);
        tCnty = findViewById(R.id.t_cnty);
        tCondTxt = findViewById(R.id.t_cond_txt);
        tFl = findViewById(R.id.t_fl);
        tHum = findViewById(R.id.t_hum);
        tLat = findViewById(R.id.t_lat);
        tLoc = findViewById(R.id.t_loc);
        tLocation = findViewById(R.id.t_location);
        tLon = findViewById(R.id.t_lon);
        tParentCity = findViewById(R.id.t_parent_city);
        tTmp = findViewById(R.id.t_tmp);
        tWindDir = findViewById(R.id.t_wind_dir);
        tWindSc = findViewById(R.id.t_wind_sc);
        tWindSpd = findViewById(R.id.t_wind_spd);
        bSearch = findViewById(R.id.b_search);
        background = findViewById(R.id.background);
        //隐藏标题栏
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
    }

    public void onClick(){
        bSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sCity = editText.getText().toString();
                presenter.getWeather(sCity);
                Log.d(TAG, "onClick: " + sCity);
                //隐藏软键盘
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
            }
        });
        //回车键的监听
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_UNSPECIFIED) {
                    sCity = editText.getText().toString();
                    presenter.getWeather(sCity);
                    Log.d(TAG, "onClick: " + sCity);
                    //隐藏软键盘
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
                }
                return false;
            }
        });
    }

    @Override
    public void getSuccess() {
        Toast.makeText(getApplicationContext(),"获取数据成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getFailed() {
        Toast.makeText(getApplicationContext(),"获取数据失败",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setWeather(List<Weather> weathers) {
        tWindSpd.setText("风速: " + weathers.get(0).getHeWeather6().get(0).getNow().getWindSpd() + "公里/小时");
        tWindSc.setText("风力: " + weathers.get(0).getHeWeather6().get(0).getNow().getWindSc() + "级");
        tWindDir.setText("风向: " + weathers.get(0).getHeWeather6().get(0).getNow().getWindDir());
        tTmp.setText("温度: " + weathers.get(0).getHeWeather6().get(0).getNow().getTmp() + "℃");
        tParentCity.setText("市: " + weathers.get(0).getHeWeather6().get(0).getBasic().getParentCity());
        tLon.setText("经度: " + weathers.get(0).getHeWeather6().get(0).getBasic().getLon());
        tLocation.setText("地区: " + weathers.get(0).getHeWeather6().get(0).getBasic().getLocation());
        tLat.setText("纬度: " + weathers.get(0).getHeWeather6().get(0).getBasic().getLat());
        tHum.setText("相对湿度: " + weathers.get(0).getHeWeather6().get(0).getNow().getHum());
        tFl.setText("体感温度: " + weathers.get(0).getHeWeather6().get(0).getNow().getFl() + "℃");
        tCondTxt.setText("天气状况: " + weathers.get(0).getHeWeather6().get(0).getNow().getCondTxt());
        tCnty.setText("国家: " + weathers.get(0).getHeWeather6().get(0).getBasic().getCnty());
        tAdminArea.setText("省: " + weathers.get(0).getHeWeather6().get(0).getBasic().getAdminArea());
        addWeatherPhoto();
    }

    public void addWeatherPhoto(){
        if (tCondTxt.getText().toString().equals("天气状况: 多云")){
            background.setBackgroundResource(R.drawable.cloud);
        }else if (tCondTxt.getText().toString().equals("天气状况: 晴")){
            background.setBackgroundResource(R.drawable.sun);
        }else if (tCondTxt.getText().toString().equals("天气状况: 少云")){
            background.setBackgroundResource(R.drawable.little_cloud);
        }else if (tCondTxt.getText().toString().equals("天气状况: 晴间多云")){
            background.setBackgroundResource(R.drawable.sun_and_cloud);
        }else if (tCondTxt.getText().toString().equals("天气状况: 阴")){
            background.setBackgroundResource(R.drawable.cloudy);
        }else if (tCondTxt.getText().toString().equals("天气状况: 有风")){
            background.setBackgroundResource(R.drawable.windy);
        }else if (tCondTxt.getText().toString().equals("天气状况: 平静")){
            background.setBackgroundResource(R.drawable.calm);
        }else if (tCondTxt.getText().toString().equals("天气状况: 微风")){
            background.setBackgroundResource(R.drawable.light_breeze);
        }else if (tCondTxt.getText().toString().equals("天气状况: 和风")){
            background.setBackgroundResource(R.drawable.moderate);
        }else if (tCondTxt.getText().toString().equals("天气状况: 清风")){
            background.setBackgroundResource(R.drawable.fresh_breeze);
        }else if (tCondTxt.getText().toString().equals("天气状况: 强风/劲风")){
            background.setBackgroundResource(R.drawable.strong_breeze);
        }else if (tCondTxt.getText().toString().equals("天气状况: 疾风")){
            background.setBackgroundResource(R.drawable.high_wind);
        }else if (tCondTxt.getText().toString().equals("天气状况: 大风")){
            background.setBackgroundResource(R.drawable.gale);
        }else if (tCondTxt.getText().toString().equals("天气状况: 烈风")){
            background.setBackgroundResource(R.drawable.strong_gale);
        }else if (tCondTxt.getText().toString().equals("天气状况: 风暴")){
            background.setBackgroundResource(R.drawable.storm);
        }else if (tCondTxt.getText().toString().equals("天气状况: 狂爆风")){
            background.setBackgroundResource(R.drawable.violent_storm);
        }else if (tCondTxt.getText().toString().equals("天气状况: 飓风")){
            background.setBackgroundResource(R.drawable.hurricane);
        }else if (tCondTxt.getText().toString().equals("天气状况: 龙卷风")){
            background.setBackgroundResource(R.drawable.tornado);
        }else if (tCondTxt.getText().toString().equals("天气状况: 热带风暴")){
            background.setBackgroundResource(R.drawable.tropical_storm);
        }else if (tCondTxt.getText().toString().equals("天气状况: 阵雨")){
            background.setBackgroundResource(R.drawable.shower_rain);
        }else if (tCondTxt.getText().toString().equals("天气状况: 强阵雨")){
            background.setBackgroundResource(R.drawable.heavy_shower_rain);
        }else if (tCondTxt.getText().toString().equals("天气状况: 雷阵雨")){
            background.setBackgroundResource(R.drawable.thundershower);
        }else if (tCondTxt.getText().toString().equals("天气状况: 强雷阵雨")){
            background.setBackgroundResource(R.drawable.heavy_thundershower);
        }else if (tCondTxt.getText().toString().equals("天气状况: 雷阵雨伴有冰雹")){
            background.setBackgroundResource(R.drawable.thundershower_with_hail);
        }else if (tCondTxt.getText().toString().equals("天气状况: 小雨")){
            background.setBackgroundResource(R.drawable.light_rain);
        }else if (tCondTxt.getText().toString().equals("天气状况: 中雨")){
            background.setBackgroundResource(R.drawable.moderate_rain);
        }else if (tCondTxt.getText().toString().equals("天气状况: 大雨")){
            background.setBackgroundResource(R.drawable.heavy_rain);
        }else if (tCondTxt.getText().toString().equals("天气状况: 极端降雨")){
            background.setBackgroundResource(R.drawable.extreme_rain);
        }else if (tCondTxt.getText().toString().equals("天气状况: 毛毛雨/细雨")){
            background.setBackgroundResource(R.drawable.drizzle_rain);
        }else if (tCondTxt.getText().toString().equals("天气状况: 暴雨")){
            background.setBackgroundResource(R.drawable.storm2);
        }else if (tCondTxt.getText().toString().equals("天气状况: 大暴雨")){
            background.setBackgroundResource(R.drawable.heavy_storm);
        }else if (tCondTxt.getText().toString().equals("天气状况: 特大暴雨")){
            background.setBackgroundResource(R.drawable.severe_storm);
        }else if (tCondTxt.getText().toString().equals("天气状况: 冻雨")){
            background.setBackgroundResource(R.drawable.freezing_rain);
        }else if (tCondTxt.getText().toString().equals("天气状况: 小到中雨")){
            background.setBackgroundResource(R.drawable.light_to_moderate_rain);
        }else if (tCondTxt.getText().toString().equals("天气状况: 中到大雨")){
            background.setBackgroundResource(R.drawable.moderate_to_heavy_rain);
        }else if (tCondTxt.getText().toString().equals("天气状况: 大到暴雨")){
            background.setBackgroundResource(R.drawable.heavy_rain_to_storm);
        }else if (tCondTxt.getText().toString().equals("天气状况: 暴雨到大暴雨")){
            background.setBackgroundResource(R.drawable.storm_to_heavy_storm);
        }else if (tCondTxt.getText().toString().equals("天气状况: 大暴雨到特大暴雨")){
            background.setBackgroundResource(R.drawable.heavy_to_severe_storm);
        }else if (tCondTxt.getText().toString().equals("天气状况: 雨")){
            background.setBackgroundResource(R.drawable.rain);
        }else if (tCondTxt.getText().toString().equals("天气状况: 小雪")){
            background.setBackgroundResource(R.drawable.light_snow);
        }else if (tCondTxt.getText().toString().equals("天气状况: 中雪")){
            background.setBackgroundResource(R.drawable.moderate_snow);
        }else if (tCondTxt.getText().toString().equals("天气状况: 大雪")){
            background.setBackgroundResource(R.drawable.heavy_snow);
        }else if (tCondTxt.getText().toString().equals("天气状况: 暴雪")){
            background.setBackgroundResource(R.drawable.snowstorm);
        }else if (tCondTxt.getText().toString().equals("天气状况: 雨夹雪")){
            background.setBackgroundResource(R.drawable.sleet);
        }else if (tCondTxt.getText().toString().equals("天气状况: 雨雪天气")){
            background.setBackgroundResource(R.drawable.rain_and_snow);
        }else if (tCondTxt.getText().toString().equals("天气状况: 阵雨夹雪")){
            background.setBackgroundResource(R.drawable.shower_snow);
        }else if (tCondTxt.getText().toString().equals("天气状况: 阵雪")){
            background.setBackgroundResource(R.drawable.shower_flurry);
        }else if (tCondTxt.getText().toString().equals("天气状况: 小到中雪")){
            background.setBackgroundResource(R.drawable.light_to_moderate_snow);
        }else if (tCondTxt.getText().toString().equals("天气状况: 中到大雪")){
            background.setBackgroundResource(R.drawable.moderate_to_heavy_snow);
        }else if (tCondTxt.getText().toString().equals("天气状况: 大到暴雪")){
            background.setBackgroundResource(R.drawable.heavy_snow_to_storm);
        }else if (tCondTxt.getText().toString().equals("天气状况: 雪")){
            background.setBackgroundResource(R.drawable.snow);
        }else if (tCondTxt.getText().toString().equals("天气状况: 薄雾")){
            background.setBackgroundResource(R.drawable.mist);
        }else if (tCondTxt.getText().toString().equals("天气状况: 雾")){
            background.setBackgroundResource(R.drawable.foggy);
        }else if (tCondTxt.getText().toString().equals("天气状况: 霾")){
            background.setBackgroundResource(R.drawable.haze);
        }else if (tCondTxt.getText().toString().equals("天气状况: 扬沙")){
            background.setBackgroundResource(R.drawable.sand);
        }else if (tCondTxt.getText().toString().equals("天气状况: 浮尘")){
            background.setBackgroundResource(R.drawable.dust);
        }else if (tCondTxt.getText().toString().equals("天气状况: 沙尘暴")){
            background.setBackgroundResource(R.drawable.duststorm);
        }else if (tCondTxt.getText().toString().equals("天气状况: 强沙尘暴")){
            background.setBackgroundResource(R.drawable.heavy_duststorm);
        }else if (tCondTxt.getText().toString().equals("天气状况: 浓雾")){
            background.setBackgroundResource(R.drawable.dense_fog);
        }else if (tCondTxt.getText().toString().equals("天气状况: 强浓雾")){
            background.setBackgroundResource(R.drawable.heavy_dense_fog);
        }else if (tCondTxt.getText().toString().equals("天气状况: 中度霾")){
            background.setBackgroundResource(R.drawable.moderate_haze);
        }else if (tCondTxt.getText().toString().equals("天气状况: 重度霾")){
            background.setBackgroundResource(R.drawable.heavy_haze);
        }else if (tCondTxt.getText().toString().equals("天气状况: 严重霾")){
            background.setBackgroundResource(R.drawable.severe_haze);
        }else if (tCondTxt.getText().toString().equals("天气状况: 大雾")){
            background.setBackgroundResource(R.drawable.heavy_fog);
        }else if (tCondTxt.getText().toString().equals("天气状况: 特别浓雾")){
            background.setBackgroundResource(R.drawable.extra_heavy_fog);
        }else if (tCondTxt.getText().toString().equals("天气状况: 热")){
            background.setBackgroundResource(R.drawable.hot);
        }else if (tCondTxt.getText().toString().equals("天气状况: 冷")){
            background.setBackgroundResource(R.drawable.cold);
        }else if (tCondTxt.getText().toString().equals("天气状况: 未知")){
            background.setBackgroundResource(R.drawable.unknown);
        }
    }

}
