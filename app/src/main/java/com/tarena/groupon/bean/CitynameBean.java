package com.tarena.groupon.bean;

/**
 * Created by pjy on 2017/6/21.
 */

public class CitynameBean {

    String cityName;//城市的中文名称
    String pyName;//城市的中文的名称拼音
    char letter;//城市拼音的首字母

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getPyName() {
        return pyName;
    }

    public void setPyName(String pyName) {
        this.pyName = pyName;
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    @Override
    public String toString() {
        return "CitynameBean{" +
                "cityName='" + cityName + '\'' +
                ", pyName='" + pyName + '\'' +
                ", letter=" + letter +
                '}';
    }
}
