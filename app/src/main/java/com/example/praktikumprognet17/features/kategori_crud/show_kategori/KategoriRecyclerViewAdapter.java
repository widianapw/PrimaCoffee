package com.example.praktikumprognet17.features.kategori_crud.show_kategori;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.praktikumprognet17.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KategoriRecyclerViewAdapter extends RecyclerView.Adapter<KategoriRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<ResultKategori> results;

    public KategoriRecyclerViewAdapter(Context context, List<ResultKategori> results) {
        this.context = context;
        this.results = results;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kategori, parent, false);
        ViewHolder holder = new ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ResultKategori result = results.get(position);
        holder.textViewKategori.setText(result.getKategori());
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.kategori) TextView textViewKategori;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
