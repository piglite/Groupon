package com.tarena.groupon.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by pjy on 2017/6/21.
 */

@DatabaseTable
public class CitynameBean {
    @DatabaseField(id = true)
    String cityName;//城市的中文名称
    @DatabaseField
    String pyName;//城市的中文的名称拼音
    @DatabaseField
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
