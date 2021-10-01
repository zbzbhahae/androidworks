package com.zb.reviewjava.gson.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReadLineBean {
    @SerializedName("4G")
    public TypedReadLineBean Type4G;
    @SerializedName("5G")
    public TypedReadLineBean Type5G;

    public boolean isPower;

    @SerializedName(value = "RedLineCity", alternate = {"cmccRedLineCity", "cuccRedLineCity", "ctccRedLineCity"})
    public List<RedLineCity> redLineCities;
    @SerializedName(value = "InsightConclusion", alternate = {"cmccPhoneInsightConclusion",
            "cuccPhoneInsightConclusion", "ctccPhoneInsightConclusion"})
    public InsightConclusion conclusion;

    public class TypedReadLineBean {
        @SerializedName("cmccData")
        public ReadLineBean cmcc;
        @SerializedName("ctccData")
        public ReadLineBean ctcc;
        @SerializedName("cuccData")
        public ReadLineBean cucc;
    }
    public class RedLineCity {
        @SerializedName("phase")
        public String updateTime;
        public int no;
        @SerializedName("province")
        public String provinceNames;
        @SerializedName("citys")
        public String cityNames;
        public String type;
        public String operator;
        @SerializedName("provinceCity")
        List<ProvinceCity> provinceCitiyList;
    }
    public class ProvinceCity {
        public String province;
        public String city;
    }
    public class InsightConclusion {
        @SerializedName("phase")
        public String updateTime;
        public int no;
        public String message;
        public String operator;
    }
}
