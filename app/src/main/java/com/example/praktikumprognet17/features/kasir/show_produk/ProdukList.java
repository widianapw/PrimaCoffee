package com.example.praktikumprognet17.features.kasir.show_produk;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.praktikumprognet17.R;
import com.example.praktikumprognet17.apihelper.BaseApiService;
import com.example.praktikumprognet17.apihelper.UtilsApi;
import com.example.praktikumprognet17.features.kategori_crud.show_kategori.KategoriList;
import com.example.praktikumprognet17.features.kategori_crud.show_kategori.KategoriRecyclerViewAdapter;
import com.example.praktikumprognet17.features.kategori_crud.show_kategori.ValueKategori;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProdukList extends AppCompatActivity {
    public static final String URL = "http://10.0.2.2:8000/api/";
    private List<ResultProduk> results = new ArrayList<>();
    private ProdukRecyclerViewAdapter viewAdapter;
    BaseApiService mApiService;

    @BindView(R.id.recycler_produk)
    RecyclerView recycler_produk;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kasir_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Kasir");
        mApiService = UtilsApi.getAPIService();
        viewAdapter = new ProdukRecyclerViewAdapter(this, results);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recycler_produk.setLayoutManager(mLayoutManager);
        recycler_produk.setItemAnimator(new DefaultItemAnimator());
        recycler_produk.setAdapter(viewAdapter);
        Log.e("m", "anjing");
        loadDataProduk();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadDataProduk() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        BaseApiService api = retrofit.create(BaseApiService.class);
        Call<ValueProduk> call = api.viewProduk();
        Log.e("PROGRESSSS", "SUDAH SAMPAI SINI");
        call.enqueue(new Callback<ValueProduk>() {
            @Override
            public void onResponse(Call<ValueProduk> call, Response<ValueProduk> response) {
                Log.e("PROGRESSSS", "SUDAH SAMPAI SINI2");
                String value = response.body().getValue();
                Log.e("ERROR", "asa" + results);
                results = response.body().getResult();

                viewAdapter = new ProdukRecyclerViewAdapter(ProdukList.this, results);
                recycler_produk.setAdapter(viewAdapter);
            }
            @Override
            public void onFailure(Call<ValueProduk> call, Throwable t) {

            }
        });
    }
}
