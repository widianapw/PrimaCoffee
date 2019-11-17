package com.example.praktikumprognet17.features.kasir.keranjang.show_keranjang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.praktikumprognet17.R;
import com.example.praktikumprognet17.features.kategori_crud.show_kategori.KategoriRecyclerViewAdapter;
import com.example.praktikumprognet17.features.kategori_crud.show_kategori.ResultKategori;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KeranjangRecyclerViewAdapter extends RecyclerView.Adapter<KeranjangRecyclerViewAdapter.ViewHolder>{
    private Context context;
    List<ResultKeranjang> results;

    public KeranjangRecyclerViewAdapter(Context context, List<ResultKeranjang> results) {
        this.context = context;
        this.results = results;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_keranjang, parent, false);
        KeranjangRecyclerViewAdapter.ViewHolder holder = new KeranjangRecyclerViewAdapter.ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return results.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.kategori)
        TextView textViewKategori;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
