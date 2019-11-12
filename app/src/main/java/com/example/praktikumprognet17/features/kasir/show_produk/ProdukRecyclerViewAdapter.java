package com.example.praktikumprognet17.features.kasir.show_produk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.praktikumprognet17.R;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProdukRecyclerViewAdapter extends RecyclerView.Adapter<ProdukRecyclerViewAdapter.ViewHolder>{
    private Context context;
    private List<ResultProduk> results;
    public ProdukRecyclerViewAdapter(Context context, List<ResultProduk> results) {
        this.context = context;
        this.results = results;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.kasir, parent, false);
        ProdukRecyclerViewAdapter.ViewHolder holder = new ProdukRecyclerViewAdapter.ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ResultProduk result = results.get(position);
        holder.textViewNamaProduk.setText(result.getNama_produk());
        holder.textViewHarga.setText(result.getHarga());
    }


    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.nama_produk)
        TextView textViewNamaProduk;

        @BindView(R.id.harga) TextView textViewHarga;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
