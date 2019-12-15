package com.example.praktikumprognet17.features.kasir.show_produk;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.praktikumprognet17.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProdukRecyclerViewAdapter extends RecyclerView.Adapter<ProdukRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<ResultProduk> results;
    private OnItemClickListener listener;



    public ProdukRecyclerViewAdapter(Context context, List<ResultProduk> results, OnItemClickListener listener) {
        this.listener = listener;
        this.context = context;
        this.results = results;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kasir, parent, false);
        ProdukRecyclerViewAdapter.ViewHolder holder = new ProdukRecyclerViewAdapter.ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ResultProduk result = results.get(position);
        holder.textViewNamaProduk.setText(result.getNama_produk());
        holder.textViewHarga.setText("Rp "+result.getHarga());
        holder.parentLayout.setOnClickListener(v ->{
            Bundle args = new Bundle();
            Log.e("id_produkk",""+result.getId());
            Log.e("harga",""+result.getHarga());
            args.putInt("id",result.getId());
            args.putString("nama",result.getNama_produk());
            args.putInt("harga",result.getHarga());
            listener.onItemClicked(v,args);
        });
    }


    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.nama_produk)
        TextView textViewNamaProduk;

        @BindView(R.id.harga)
        TextView textViewHarga;

        RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            parentLayout = itemView.findViewById(R.id.list);

        }
    }


}
