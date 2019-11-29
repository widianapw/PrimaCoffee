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

import java.util.List;

public class ReportTahunRecyclerViewAdapter extends RecyclerView.Adapter<ReportTahunRecyclerViewAdapter.ViewHolder>{
    private List<ReportDAO.ReportTahun> mDaftarReportTahun;
    Context context;

    public ReportTahunRecyclerViewAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_report_tahun, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String getBulan = mDaftarReportTahun.get(position).getBulan();
        String getJumlah = String.valueOf(mDaftarReportTahun.get(position).getTotal());

        holder.bulan.setText(getBulan);
        holder.jumlah.setText(getJumlah);

    }

    @Override
    public int getItemCount() {
        if (mDaftarReportTahun == null){
            return 0;
        }
        return mDaftarReportTahun.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView bulan, jumlah;
        //Deklarasi View yang akan digunakan

        ViewHolder(View itemView) {
            super(itemView);
            bulan = itemView.findViewById(R.id.bulan_report_tahun);
            jumlah = itemView.findViewById(R.id.jumlah_report_tahun);
        }

    }

    public void setTasksReport(List<ReportDAO.ReportTahun> daftarReportTahun){
        mDaftarReportTahun = daftarReportTahun;
        notifyDataSetChanged();
    }
}
