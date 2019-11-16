package com.example.praktikumprognet17.features.kategori_crud.show_kategori;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.praktikumprognet17.R;
import com.example.praktikumprognet17.apihelper.BaseApiService;
import com.example.praktikumprognet17.apihelper.UtilsApi;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class KategoriList extends AppCompatActivity {

    public static final String URL = "http://10.0.2.2:8000/api/";
    private List<ResultKategori> results = new ArrayList<>();
    private KategoriRecyclerViewAdapter viewAdapter;
    BaseApiService mApiService;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
//    @BindView(R.id.progress_bar)
//    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori_list);
        ButterKnife.bind(this);

        mApiService = UtilsApi.getAPIService();
        viewAdapter = new KategoriRecyclerViewAdapter(this, results);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(viewAdapter);
        Log.e("m", "anjing");
        loadDataKategori();
    }

    private void loadDataKategori() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        BaseApiService api = retrofit.create(BaseApiService.class);
        Call<ValueKategori> call = api.view();
        Log.e("PROGRESSSS", "SUDAH SAMPAI SINI");
        call.enqueue(new Callback<ValueKategori>() {
            @Override
            public void onResponse(Call<ValueKategori> call, Response<ValueKategori> response) {
                Log.e("PROGRESSSS", "SUDAH SAMPAI SINI2");
//                progressBar.setVisibility(View.GONE);
                String value = response.body().getValue();
                Log.e("ERROR", "asa" + results);

                results = response.body().getResult();

                viewAdapter = new KategoriRecyclerViewAdapter(KategoriList.this, results);
                recyclerView.setAdapter(viewAdapter);

            }

            @Override
            public void onFailure(Call<ValueKategori> call, Throwable t) {

            }
        });
    }
}
