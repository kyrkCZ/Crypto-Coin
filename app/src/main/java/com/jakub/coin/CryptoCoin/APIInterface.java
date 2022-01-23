package com.jakub.coin.CryptoCoin;

import com.jakub.coin.CryptoCoin.data.CryptoList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

interface APIInterface {

    //API klic + top 100 crypto
    @Headers("X-CMC_PRO_API_KEY:ad0e6601-96b8-4290-8d62-53953916616b")
    @GET("/v1/cryptocurrency/listings/latest?")
    Call<CryptoList> doGetUserList(@Query("limit") String page);

}
