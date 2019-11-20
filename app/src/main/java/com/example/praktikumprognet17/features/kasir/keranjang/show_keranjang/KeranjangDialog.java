package com.example.praktikumprognet17.features.kasir.keranjang.show_keranjang;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.praktikumprognet17.R;
import com.example.praktikumprognet17.apihelper.BaseApiService;
import com.example.praktikumprognet17.apihelper.UtilsApi;
import com.example.praktikumprognet17.features.kasir.show_produk.ProdukRecyclerViewAdapter;
import com.example.praktikumprognet17.features.kasir.show_produk.ResultProduk;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class KeranjangDialog extends DialogFragment {
    BaseApiService mApiService;
    public static final String URL = "http://10.0.2.2:8000/api/";
    private List<ResultKeranjang> results = new ArrayList<>();
    private KeranjangRecyclerViewAdapter viewAdapter;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.activity_keranjang_dialog, null);
        loadDataKeranjang(v);
        builder.setView(v)
                // Add action buttons

                .setPositiveButton(R.string.save, (dialog, id) -> {

                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        KeranjangDialog.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }



    public void loadDataKeranjang(View view){
        mApiService = UtilsApi.getAPIService();
        viewAdapter = new KeranjangRecyclerViewAdapter(getActivity(),results);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_keranjang);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(viewAdapter);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        BaseApiService api = retrofit.create(BaseApiService.class);
        Call<ValueKeranjang> call = api.viewKeranjang();
        call.enqueue(new Callback<ValueKeranjang>() {
            @Override
            public void onResponse(Call<ValueKeranjang> call, Response<ValueKeranjang> response) {
                results = response.body().getResult();
                viewAdapter = new KeranjangRecyclerViewAdapter(getActivity(),results);
                recyclerView.setAdapter(viewAdapter);
            }

            @Override
            public void onFailure(Call<ValueKeranjang> call, Throwable t) {

            }
        });

    }
}
