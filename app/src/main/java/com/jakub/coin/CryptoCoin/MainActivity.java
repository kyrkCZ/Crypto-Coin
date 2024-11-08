package com.jakub.coin.CryptoCoin;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.jakub.coin.CryptoCoin.data.CryptoList;
import com.jakub.coin.CryptoCoin.data.Data;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    CryptoListAdapter adapter;
    APIInterface apiInterface;
    private RecyclerView recyclerView;
    private List<Data> cryptoList = null;
    //Main
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiInterface = APIClient.getClient().create(APIInterface.class);

        initRecyclerView();
        getCoinList();
    }


    private void initRecyclerView() {
        //seznam podle id
        recyclerView = findViewById(R.id.my_recycler_view);

        //inicializace dat
        cryptoList = new ArrayList<>();

        //vytvoreni adapteru
        adapter = new CryptoListAdapter(cryptoList);

        //prirazeni adapteru
        recyclerView.setAdapter(adapter);

        //layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter.setClickListener(new CryptoListAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //klik test
                Toast.makeText(MainActivity.this, "Kliknul jsi " + adapter.getItem(position) + " na řádku " + position + 1, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, Coiny.class);
                intent.putExtra("coin", adapter.getItem(position));
                startActivity(intent);
            }
        });
    }

    private void getCoinList() {
        Call<CryptoList> call2 = apiInterface.doGetUserList("100");
        call2.enqueue(new Callback<CryptoList>() {
            @Override
            public void onResponse(Call<CryptoList> call, Response<CryptoList> response) {
                CryptoList list = response.body();

                cryptoList.clear();
                cryptoList.addAll(list.getData());

                adapter.notifyDataSetChanged();
            }
            //chyba
            @Override
            public void onFailure(Call<CryptoList> call, Throwable t) {
                Toast.makeText(MainActivity.this, "onFailure", Toast.LENGTH_SHORT).show();
                Log.d("XXXX", t.getLocalizedMessage());
                call.cancel();
            }
        });
    }

}
