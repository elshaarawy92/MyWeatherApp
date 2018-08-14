package com.example.myweatherapp.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by esliang on 2018/8/13
 */
public class Weather {

    @SerializedName("HeWeather6")
    private List<HeWeather6> heWeather6;

    public List<HeWeather6> getHeWeather6() {
        return heWeather6;
    }

    public void setHeWeather6(List<HeWeather6> heWeather6) {
        this.heWeather6 = heWeather6;
    }

    public class HeWeather6{

        //城市信息
        private Basic basic;
        //天气信息
        private Now now;
        //获取状态
        private String status;
        //更新时间
        private Update update;

        public Basic getBasic() {
            return basic;
        }

        public void setBasic(Basic basic) {
            this.basic = basic;
        }

        public Now getNow() {
            return now;
        }

        public void setNow(Now now) {
            this.now = now;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Update getUpdate() {
            return update;
        }

        public void setUpdate(Update update) {
            this.update = update;
        }

        @Override
        public String toString() {
            return "HeWeather6{" +
                    "status='" + status + '\'' +
                    '}';
        }
    }

    public class Basic{

        //地区id
        private String cid;
        //地区名称
        private String location;
        //地区所在的市级单位
        @SerializedName(value = "parentCity",alternate = "parent_city")
        private String parentCity;
        //地区所在的省级单位
        @SerializedName(value = "adminArea",alternate = "admin_area")
        private String adminArea;
        //地区所在的国家
        private String cnty;
        //经度
        private String lat;
        //纬度
        private String lon;
        //时区
        private String tz;

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getParentCity() {
            return parentCity;
        }

        public void setParentCity(String parentCity) {
            this.parentCity = parentCity;
        }

        public String getAdminArea() {
            return adminArea;
        }

        public void setAdminArea(String adminArea) {
            this.adminArea = adminArea;
        }

        public String getCnty() {
            return cnty;
        }

        public void setCnty(String cnty) {
            this.cnty = cnty;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLon() {
            return lon;
        }

        public void setLon(String lon) {
            this.lon = lon;
        }

        public String getTz() {
            return tz;
        }

        public void setTz(String tz) {
            this.tz = tz;
        }

        @Override
        public String toString() {
            return "Basic{" +
                    "cid='" + cid + '\'' +
                    ", location='" + location + '\'' +
                    ", parentCity='" + parentCity + '\'' +
                    ", adminArea='" + adminArea + '\'' +
                    ", cnty='" + cnty + '\'' +
                    ", lat='" + lat + '\'' +
                    ", lon='" + lon + '\'' +
                    ", tz='" + tz + '\'' +
                    '}';
        }
    }

    public class Now{

        //体感温度
        private String fl;
        //温度
        private String tmp;
        //实况天气状况代码
        @SerializedName(value = "condCode",alternate = "cond_code")
        private String condCode;
        //实况天气状况描述
        @SerializedName(value = "condTxt",alternate = "cond_txt")
        private String condTxt;
        //风向360角度
        @SerializedName(value = "windDeg",alternate = "wind_deg")
        private String windDeg;
        //风向
        @SerializedName(value = "windDir",alternate = "wind_dir")
        private String windDir;
        //风力
        @SerializedName(value = "windSc",alternate = "wind_sc")
        private String windSc;
        //风速
        @SerializedName(value = "windSpd",alternate = "wind_spd")
        private String windSpd;
        //相对湿度
        private String hum;
        //降水量
        private String pcpn;
        //大气压强
        private String pres;
        //能见度
        private String vis;
        //云量
        private String cloud;

        public String getFl() {
            return fl;
        }

        public void setFl(String fl) {
            this.fl = fl;
        }

        public String getTmp() {
            return tmp;
        }

        public void setTmp(String tmp) {
            this.tmp = tmp;
        }

        public String getCondCode() {
            return condCode;
        }

        public void setCondCode(String condCode) {
            this.condCode = condCode;
        }

        public String getCondTxt() {
            return condTxt;
        }

        public void setCondTxt(String condTxt) {
            this.condTxt = condTxt;
        }

        public String getWindDeg() {
            return windDeg;
        }

        public void setWindDeg(String windDeg) {
            this.windDeg = windDeg;
        }

        public String getWindDir() {
            return windDir;
        }

        public void setWindDir(String windDir) {
            this.windDir = windDir;
        }

        public String getWindSc() {
            return windSc;
        }

        public void setWindSc(String windSc) {
            this.windSc = windSc;
        }

        public String getWindSpd() {
            return windSpd;
        }

        public void setWindSpd(String windSpd) {
            this.windSpd = windSpd;
        }

        public String getHum() {
            return hum;
        }

        public void setHum(String hum) {
            this.hum = hum;
        }

        public String getPcpn() {
            return pcpn;
        }

        public void setPcpn(String pcpn) {
            this.pcpn = pcpn;
        }

        public String getPres() {
            return pres;
        }

        public void setPres(String pres) {
            this.pres = pres;
        }

        public String getVis() {
            return vis;
        }

        public void setVis(String vis) {
            this.vis = vis;
        }

        public String getCloud() {
            return cloud;
        }

        public void setCloud(String cloud) {
            this.cloud = cloud;
        }

        @Override
        public String toString() {
            return "Now{" +
                    "fl='" + fl + '\'' +
                    ", tmp='" + tmp + '\'' +
                    ", condCode='" + condCode + '\'' +
                    ", condTxt='" + condTxt + '\'' +
                    ", windDeg='" + windDeg + '\'' +
                    ", windDir='" + windDir + '\'' +
                    ", windSc='" + windSc + '\'' +
                    ", windSpd='" + windSpd + '\'' +
                    ", hum='" + hum + '\'' +
                    ", pcpn='" + pcpn + '\'' +
                    ", pres='" + pres + '\'' +
                    ", vis='" + vis + '\'' +
                    ", cloud='" + cloud + '\'' +
                    '}';
        }
    }

    public class Update{

        //当地时间
        private String loc;
        //UTC时间
        private String utc;

        public String getLoc() {
            return loc;
        }

        public void setLoc(String loc) {
            this.loc = loc;
        }

        public String getUtc() {
            return utc;
        }

        public void setUtc(String utc) {
            this.utc = utc;
        }

        @Override
        public String toString() {
            return "Update{" +
                    "loc='" + loc + '\'' +
                    ", utc='" + utc + '\'' +
                    '}';
        }
    }

}
