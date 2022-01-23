package com.jakub.coin.CryptoCoin.data;


import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CryptoList implements Serializable
{

    @SerializedName("status")
    @Expose
    private Errory errory;
    @SerializedName("data")
    @Expose
    private List<Data> data = null;

    public Errory getStatus() {
        return errory;
    }

    public void setStatus(Errory errory) {
        this.errory = errory;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

}