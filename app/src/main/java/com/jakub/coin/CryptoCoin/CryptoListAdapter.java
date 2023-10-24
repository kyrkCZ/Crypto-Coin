package com.jakub.coin.CryptoCoin;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jakub.coin.CryptoCoin.data.Data;

import java.util.List;

public class CryptoListAdapter extends RecyclerView.Adapter<CryptoListAdapter.ViewHolder> {

    private List<Data> mData;
    private ItemClickListener mClickListener;

    // data do konstruktoru
    CryptoListAdapter(List<Data> data) {
        this.mData = data;
    }

    // zahrnuti rozvržení z XML
    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.crypto_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    // vyplnění dat pres holder
    // priradi data do TextView
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // datovy model podle pozice
        Data data = mData.get(position);

        TextView name = holder.name;
        name.setText(data.getName() + " (" + data.getSymbol() + ")");

        TextView price = holder.price;
        price.setText("Price: " + String.format("%,f", data.getQuote().getUSD().getPrice())+ " $");

        TextView marketCap = holder.marketCap;
        marketCap.setText("Market Cap: $" + String.format("%,d", Math.round(data.getQuote().getUSD().getMarketCap())));

        TextView volume24h = holder.volume24h;
        volume24h.setText("Volume/24h: $" + String.format("%,d", Math.round(data.getQuote().getUSD().getVolume24h())));

        TextView textView1h = holder.textView1h;
        textView1h.setText(String.format("1h: %.2f", data.getQuote().getUSD().getPercentChange1h()) + "%");

        TextView textView24h = holder.textView24h;
        textView24h.setText(String.format("24h: %.2f", data.getQuote().getUSD().getPercentChange24h()) + "%");

        TextView textView7d = holder.textView7d;
        textView7d.setText(String.format("7d: %.2f", data.getQuote().getUSD().getPercentChange7d()) + "%");

    }

    //pocet polozek
    @Override
    public int getItemCount() {
        return mData.size();
    }

    //ulozeni do cache
    //recyklace polozek mimo obraz
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        TextView price;
        TextView marketCap;
        TextView volume24h;
        TextView textView1h;
        TextView textView24h;
        TextView textView7d;

        //konstruktor, který přijímá celý řádek
        ViewHolder(View itemView) {

            super(itemView);

            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            marketCap = itemView.findViewById(R.id.marketCap);
            volume24h = itemView.findViewById(R.id.volume24h);
            textView1h = itemView.findViewById(R.id.textView1h);
            textView24h = itemView.findViewById(R.id.textView24h);
            textView7d = itemView.findViewById(R.id.textView7d);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    //získáváni dat na pozici kliknutí
    Data getItem(int id) {
        return mData.get(id);
    }

    //zachyceni události kliknutí
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    //parent odpoved na klik
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}