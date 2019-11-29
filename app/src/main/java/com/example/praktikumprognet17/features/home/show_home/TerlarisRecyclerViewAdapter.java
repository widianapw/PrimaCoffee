package com.example.praktikumprognet17.features.home.show_home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.praktikumprognet17.R;
import com.example.praktikumprognet17.dao.ReportDAO;
import com.example.praktikumprognet17.database.ReportAppDatabase;
import com.example.praktikumprognet17.database.entity.Report;
import com.example.praktikumprognet17.database.entity.Terlaris;

import java.util.ArrayList;
import java.util.List;

public class TerlarisRecyclerViewAdapter extends RecyclerView.Adapter<TerlarisRecyclerViewAdapter.ViewHolder> {

    private List<Terlaris> mDaftarTerlaris;
    private ReportAppDatabase reportAppDatabase;


    Context context;
    List<ResultReport> results;
//    public TerlarisRecyclerViewAdapter(Context context, List<ResultReport> results ){
//        this.results = results;
//        this.context = context;
//    }

    public TerlarisRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_terlaris, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String getNama = mDaftarTerlaris.get(position).getNama_produk();
        String getJumlah = String.valueOf(mDaftarTerlaris.get(position).getTotal());

        holder.nama.setText(getNama);
        holder.jumlah.setText(getJumlah);
    }

    @Override
    public int getItemCount() {
        if (mDaftarTerlaris == null) {
            return 0;
        }
        return mDaftarTerlaris.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nama, jumlah;
        //Deklarasi View yang akan digunakan

        ViewHolder(View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.nama_terlaris);
            jumlah = itemView.findViewById(R.id.jumlah_terlaris);
        }

    }

    public void setTasks(List<Terlaris> daftarTerlaris) {
        mDaftarTerlaris = daftarTerlaris;
        notifyDataSetChanged();
    }
}
