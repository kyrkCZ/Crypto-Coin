package com.jakub.coin.CryptoCoin.data.corrency.USD;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.jakub.coin.CryptoCoin.data.corrency.USD.USD;

public class uSDtoUSD implements Serializable
{

    @SerializedName("USD")
    @Expose
    private USD uSD;
    private final static long serialVersionUID = -5780538494495942860L;

    public USD getUSD() {
        return uSD;
    }

    public void setUSD(USD uSD) {
        this.uSD = uSD;
    }

}