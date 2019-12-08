package com.example.praktikumprognet17.features.setting.edit_profil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.praktikumprognet17.R;
import com.example.praktikumprognet17.apihelper.BaseApiService;
import com.example.praktikumprognet17.apihelper.UtilsApi;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserProfile extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    boolean session = false;
    String token;

    final String SHARED_PREFERENCES_NAME = "shared_preferences";
    final String SESSION_STATUS = "session";
    public final static String TAG_TOKEN = "token";
    public final static int ID_USER = 0;

    public static final String URL = "http://10.0.2.2:8000/api/";
//    private List<ResultProfile> results = new ArrayList<>();


    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        int id_user;
        sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        session = sharedPreferences.getBoolean(SESSION_STATUS, false);
        token = sharedPreferences.getString(TAG_TOKEN, null);
        id_user = sharedPreferences.getInt(String.valueOf(ID_USER), 0);
        Log.e("ID_USER", id_user + "");

        mApiService = UtilsApi.getAPIService();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        BaseApiService api = retrofit.create(BaseApiService.class);
        Call<ValueUser> call = api.viewUser(id_user);
        call.enqueue(new Callback<ValueUser>() {
            @Override
            public void onResponse(Call<ValueUser> call, Response<ValueUser> response) {

                String nama_user = response.body().getName();
                String email_user = response.body().getEmail();
                EditText etEmail = findViewById(R.id.etEmail);
                etEmail.setText(email_user);

                EditText etName = findViewById(R.id.etNama);
                etName.setText(nama_user);

            }

            @Override
            public void onFailure(Call<ValueUser> call, Throwable t) {

            }
        });

        Button btnSaveUser = findViewById(R.id.btnSave);
        btnSaveUser.setOnClickListener(v -> {
            EditText etEmail = findViewById(R.id.etEmail);
            EditText etName = findViewById(R.id.etNama);
//            mApiService = UtilsApi.getAPIService();

            String newEmail = etEmail.getText().toString();
            String newName = etName.getText().toString();
            Log.e("email", newEmail);
            mApiService.updateProfile(id_user, newEmail, newName).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    Log.e("BERHASIL", "DATA BERHASIL DIUPDATE");
                    finish();
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });

        });

        initComponents();
    }

    private void initComponents() {


        TextView btnEditPass = findViewById(R.id.btnEditPass);
        btnEditPass.setOnClickListener(v -> {
            Intent i = new Intent(this, EditPassword.class);
            startActivity(i);
        });


    }
}
