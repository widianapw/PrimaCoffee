package com.example.praktikumprognet17.features.kasir.keranjang.show_keranjang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.DialogFragment;
import androidx.room.Room;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import com.example.praktikumprognet17.R;
import com.example.praktikumprognet17.apihelper.BaseApiService;
import com.example.praktikumprognet17.apihelper.UtilsApi;
import com.example.praktikumprognet17.database.KeranjangAppDatabase;
import com.example.praktikumprognet17.database.entity.Keranjang;
import com.example.praktikumprognet17.features.kasir.keranjang.bayar_keranjang.KeranjangBayar;
import com.example.praktikumprognet17.features.kasir.keranjang.edit_keranjang.EditQtyDialog;
import com.example.praktikumprognet17.util.Connectivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class KeranjangActivity extends AppCompatActivity implements OnItemEditClickListener{
    RecyclerView recyclerView, recyclerKeranjang;
    BaseApiService mApiService;
    public static final String URL = "http://10.0.2.2:8000/api/";
    private List<ResultKeranjang> results = new ArrayList<>();

    private KeranjangRecyclerViewAdapter viewAdapter;
    Context mContext;

    Connectivity cm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_keranjang);
//        daftarKeranjang = new ArrayList<>();
//        //Inisialisasi RoomDatabase
//        database = Room.databaseBuilder(getApplicationContext(),
//                KeranjangAppDatabase.class, "rest_api").allowMainThreadQueries().build();

//        cm = new Connectivity();
//        if(cm.isConnected(this)){
//            loadDataKeranjang();
//        }
        loadDataKeranjang();

        Button btnBayar = findViewById(R.id.button_bayar);
        btnBayar.setOnClickListener(v -> {
            Intent i = new Intent(this, KeranjangBayar.class);
            startActivity(i);
        });
    }

    public void loadDataKeranjang(){
        recyclerView = findViewById(R.id.recycler_keranjang);
        final KeranjangRecyclerViewAdapter adapter = new KeranjangRecyclerViewAdapter(mContext,results, (v, args) -> onItemClicked(v, args));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        mApiService = UtilsApi.getAPIService();
        viewAdapter = new KeranjangRecyclerViewAdapter(mContext, results, (v, args) -> onItemClicked(v, args));
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
//                int harga_keranjang = response.body().getHarga_keranjang().get(0).getHarga_total();
                viewAdapter = new KeranjangRecyclerViewAdapter(mContext, results, (v, args) -> onItemClicked(v, args));
                recyclerView.setAdapter(viewAdapter);
            }

            @Override
            public void onFailure(Call<ValueKeranjang> call, Throwable t) {

            }
        });
    }


    @Override
    public void onItemClicked(View v, Bundle args) {
        DialogFragment a = new EditQtyDialog(KeranjangActivity.this);
        a.setArguments(args);
        a.show(getSupportFragmentManager(),"qtyDialog");
    }
}
