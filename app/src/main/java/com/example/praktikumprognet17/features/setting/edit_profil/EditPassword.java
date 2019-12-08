package com.example.praktikumprognet17.features.setting.edit_profil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.praktikumprognet17.MainActivity;
import com.example.praktikumprognet17.R;
import com.example.praktikumprognet17.apihelper.BaseApiService;
import com.example.praktikumprognet17.apihelper.UtilsApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditPassword extends AppCompatActivity {
    Context mContext;
    BaseApiService mApiService;
    SharedPreferences sharedPreferences;
    boolean session = false;
    String token;

    public static final String URL = "http://10.0.2.2:8000/api/";
    final String SHARED_PREFERENCES_NAME = "shared_preferences";
    final String SESSION_STATUS = "session";
    public final static String TAG_TOKEN = "token";
    public final static int ID_USER = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_password);

        int id_user;
        sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        session = sharedPreferences.getBoolean(SESSION_STATUS, false);
        token = sharedPreferences.getString(TAG_TOKEN, null);
        id_user = sharedPreferences.getInt(String.valueOf(ID_USER), 0);

        Button btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(v -> {
            EditText etPassLama = findViewById(R.id.etPassLama);
            EditText etPassBaru = findViewById(R.id.etPassBaru);

            String pass_lama = etPassLama.getText().toString();
            String pass_baru = etPassBaru.getText().toString();
            mApiService = UtilsApi.getAPIService();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            BaseApiService api = retrofit.create(BaseApiService.class);
            Call<ResponseBody> call = api.updatePassword(id_user, pass_lama, pass_baru);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            JSONObject jsonRESULTS = new JSONObject(response.body().string());
                            Log.e("ANJ", jsonRESULTS.getString("status"));
                            if (jsonRESULTS.getString("status").equals("false")) {
                                Log.e("ASD", pass_lama );
                                Log.e("GAGAL", "PASSWORD LAMA SALAH");
                            }
                            else {
                                Log.e("BERHASIL", "PASSWORD BERHASIL DIUPDATE");
                                Intent i = new Intent(EditPassword.this, MainActivity.class);
                                i.putExtra("objek","setting");
                                startActivity(i);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });

        });
    }
}
