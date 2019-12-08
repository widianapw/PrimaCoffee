package com.example.praktikumprognet17.features.kasir.keranjang.bayar_keranjang;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.praktikumprognet17.MainActivity;
import com.example.praktikumprognet17.R;
import com.example.praktikumprognet17.apihelper.BaseApiService;
import com.example.praktikumprognet17.apihelper.UtilsApi;
import com.example.praktikumprognet17.database.AppDatabase;
import com.example.praktikumprognet17.database.entity.Report;
import com.example.praktikumprognet17.features.kasir.keranjang.show_keranjang.ResultKeranjang;
import com.example.praktikumprognet17.features.kasir.keranjang.show_keranjang.ValueKeranjang;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class KeranjangBayar extends AppCompatActivity {
    BaseApiService mApiService;
    AppDatabase database;
    final String SHARED_PREFERENCES_NAME = "shared_preferences";
    public final static int ID_USER = 0;
    int harga_keranjang;
    public static final String URL = "http://10.0.2.2:8000/api/";
    private List<ResultKeranjang> results = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keranjang_bayar);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        int id_user = sharedPreferences.getInt(String.valueOf(ID_USER), 0);
        database = AppDatabase.getDatabase(this);

        Button btnBayarFinal = findViewById(R.id.button_bayar_final);
        EditText etUang = findViewById(R.id.etUang);
        TextView tvKembalian = findViewById(R.id.tv_uang_kembalian);
        TextView tvTotalBayar = findViewById(R.id.tv_total_belanja);

        mApiService = UtilsApi.getAPIService();
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

                Log.e("result 0",""+results.size());
                harga_keranjang = response.body().getHarga_keranjang().get(0).getHarga_total();
                tvTotalBayar.setText(Integer.toString(harga_keranjang));
            }

            @Override
            public void onFailure(Call<ValueKeranjang> call, Throwable t) {

            }
        });


        etUang.addTextChangedListener( new TextWatcher(){

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int a = Integer.parseInt(s.toString());
                int b = Integer.parseInt(tvTotalBayar.getText().toString());
                int c = a-b;
                tvKembalian.setText(Integer.toString(c));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnBayarFinal.setOnClickListener(v->{
            int n = results.size();
            Log.e("SIZE N", ""+results.get(0).getHarga());

            int i;
            for (i=0; i<n; i++){
                Report data = new Report();
                data.setId_produk(results.get(i).getId_produk());
                data.setNama_produk(results.get(i).getNama_produk());
                data.setHarga(results.get(i).getHarga());
                data.setQty(results.get(i).getQty());

//                SimpleDateFormat formatter =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                Date time = null;
//                try {
//                    time = formatter.parse(results.get(i).getCreated_at());
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
                data.setTime(results.get(i).getCreated_at());
                insert(data);
            }
            mApiService.insertTransaksi(id_user).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    Log.e("BERHASIL", "Transaksi Berhasil" );
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle("MANTAP");
            alertDialog.setMessage("Cetak Struk?");
            alertDialog.setPositiveButton("YA", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent i = new Intent(KeranjangBayar.this, MainActivity.class);
                    startActivity(i);
                }
            });
            alertDialog.setNegativeButton("TIDAK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    // DO SOMETHING HERE

                }
            });

            AlertDialog dialog = alertDialog.create();
            dialog.show();

        });

    }

    @SuppressLint("StaticFieldLeak")private void insert(final Report report) {
        new AsyncTask<Void, Void, Long>() {
            @Override
            protected Long doInBackground(Void... voids) {
                //Menjalankan proses insert data
                return database.reportDAO().insert(report);
            }

            @Override
            protected void onPostExecute(Long status) {
                //Menandakan bahwa data berhasil disimpan

            }
        }.execute();
    }
}
