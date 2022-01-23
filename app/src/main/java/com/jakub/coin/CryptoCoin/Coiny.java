package com.jakub.coin.CryptoCoin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.jakub.coin.CryptoCoin.data.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Coiny extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_page);

        Intent intent = getIntent();
        Data data = (Data) intent.getSerializableExtra("coin");
        //Prirazeni podle id
        TextView name = findViewById(R.id.name);
        TextView price = findViewById(R.id.price);
        TextView date = findViewById(R.id.date);

        TextView symbol = findViewById(R.id.symbol);
        TextView fullName = findViewById(R.id.fullName);
        TextView volume24h = findViewById(R.id.volume24h);
        TextView circulating_supply = findViewById(R.id.circulating_supply);
        TextView max_supply = findViewById(R.id.max_supply);
        TextView market_cap = findViewById(R.id.market_cap);

        TextView change1h = findViewById(R.id.change1h);
        TextView change24h = findViewById(R.id.change24h);
        TextView change7d = findViewById(R.id.change7d);
        //Nastavení textu
        name.setText(data.getName() + " (" + data.getSymbol() + ")");
        price.setText("Price: $" + String.format("%,f", data.getQuote().getUSD().getPrice()));
        date.setText("Last Updated: " + parseDateToddMMyyyy(data.getLastUpdated()));

        symbol.setText("Symbol: " + data.getSymbol());
        fullName.setText("Full name: " + data.getfullName());
        volume24h.setText("Volume/24h: $" + String.format("%,d", Math.round(data.getQuote().getUSD().getVolume24h())));

        circulating_supply.setText("Circulating Supply: " + String.format("%.0f", data.getCirculatingSupply()) + " " + data.getSymbol());
        max_supply.setText("Max Supply: " + String.format("%.0f", data.getMaxSupply()) + " " + data.getSymbol());

        market_cap.setText("Market Capitalization: $" + String.format("%,d", Math.round(data.getQuote().getUSD().getMarketCap())));

        change1h.setText(String.format("Change 1h: %.2f", data.getQuote().getUSD().getPercentChange1h()) + "%");
        change24h.setText(String.format("Change 24h: %.2f", data.getQuote().getUSD().getPercentChange24h()) + "%");
        change7d.setText(String.format("Change 7d: %.2f", data.getQuote().getUSD().getPercentChange7d()) + "%");
    }

//datum a čas
    private String parseDateToddMMyyyy(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

//převod na lokální čas
        SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
        output.setTimeZone(TimeZone.getDefault());
//podmínky pro datum a čas
        Date date = null;
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return output.format(date);
    }

}
