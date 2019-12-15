package com.example.praktikumprognet17.features.kasir.keranjang.show_keranjang;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.praktikumprognet17.R;
import com.example.praktikumprognet17.apihelper.BaseApiService;
import com.example.praktikumprognet17.apihelper.UtilsApi;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class KeranjangRecyclerViewAdapter extends RecyclerView.Adapter<KeranjangRecyclerViewAdapter.ViewHolder> {
    Context context;
    List<ResultKeranjang> results;


    BaseApiService mApiService;
    private LinearLayout layout_keranjang;
    public static final String URL = "http://10.0.2.2:8000/api/";
    OnItemEditClickListener listener;

    public KeranjangRecyclerViewAdapter(Context context, List<ResultKeranjang> results, OnItemEditClickListener listener) {
        this.listener = listener;
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

        ResultKeranjang result = results.get(position);

        int totHarga;
        totHarga = result.getHarga() * result.getQty();
        Log.e("as", "" + getItemCount());
        holder.textViewNamaKeranjang.setText(result.getNama_produk());
        holder.textViewHargaKeranjang.setText("Rp "+ result.getHarga());
        holder.textViewQtyKeranjang.setText(Integer.toString(result.getQty()));
        holder.textViewSubtotalKeranjang.setText(Integer.toString(totHarga));
        holder.btnEditKeranjang.setOnClickListener(v -> {
            Log.e("AS1J", result.getId() + "");
            Bundle args = new Bundle();
            args.putInt("id", result.getId());
            args.putString("nama", result.getNama_produk());
            args.putInt("qty", result.getQty());
            listener.onItemClicked(v, args);
        });

        holder.btnDeleteKeranjang.setOnClickListener(v -> {
            Log.e("ASJ", result.getId() + "");
            mApiService = UtilsApi.getAPIService();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            BaseApiService api = retrofit.create(BaseApiService.class);
            final SweetAlertDialog sDialog = new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE);
            sDialog.setTitle("Hapus Data");
            sDialog.setContentText("Ingin menghapus data " + result.getNama_produk() + " ?");
            sDialog.setConfirmButton("Ya", new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    Call<ResponseBody> call = api.deleteKeranjang(result.getId());
                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            results.remove(position);
                            notifyDataSetChanged();
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                        }
                    });
                    sDialog.dismissWithAnimation();
                }
            });
            sDialog.setCancelButton("Tidak", new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {

                    sDialog.dismissWithAnimation();
                }
            });
            sDialog.show();

        });

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.nama_keranjang)
        TextView textViewNamaKeranjang;

        @BindView(R.id.harga_keranjang)
        TextView textViewHargaKeranjang;

        @BindView(R.id.qty_keranjang)
        TextView textViewQtyKeranjang;

        @BindView(R.id.subtotal_keranjang)
        TextView textViewSubtotalKeranjang;

        @BindView(R.id.button_delete_keranjang)
        ImageButton btnDeleteKeranjang;

        @BindView(R.id.button_edit_keranjang)
        ImageButton btnEditKeranjang;

        RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            parentLayout = itemView.findViewById(R.id.list_keranjang);
        }

    }

    public ResultKeranjang getKeranjangAt(int position) {
        return results.get(position);
    }

//    private void onDeleteData(int position){
//        database.keranjangDAO().delete(results.get(position));
//        results.remove(position);
//        notifyItemRemoved(position);
//        notifyItemRangeChanged(position, results.size());
//
//    }
}