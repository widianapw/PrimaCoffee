package com.example.praktikumprognet17;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.praktikumprognet17.apihelper.BaseApiService;
import com.example.praktikumprognet17.apihelper.UtilsApi;
//import com.example.praktikumprognet17.features.kategori_crud.show_kategori.KategoriList;
import com.example.praktikumprognet17.util.Connectivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText etEmail;
    EditText etPassword;
    Button btnLogin;
//    Button btnRegister;
    ProgressDialog loading;


    Context mContext;
    BaseApiService mApiService;
    SharedPreferences sharedPreferences;
    boolean session = false;
    String token;
    int id_user;
    final String SHARED_PREFERENCES_NAME = "shared_preferences";
    final String SESSION_STATUS = "session";
    public final static String TAG_TOKEN = "token";
    final int ID_USER = 0;
    Connectivity cm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext = this;
        mApiService = UtilsApi.getAPIService(); // meng-init yang ada di package apihelper
        sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        session = sharedPreferences.getBoolean(SESSION_STATUS, false);
        token = sharedPreferences.getString(TAG_TOKEN, null);
        id_user = sharedPreferences.getInt(String.valueOf(ID_USER),0);
        cm = new Connectivity();
        if(cm.isConnected(this)){
            Toast.makeText(mContext, "TERKONEKSI", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(mContext, "TIDAK ADA INTERNET", Toast.LENGTH_SHORT).show();
        }

        if (session){
            Log.e("as",""+id_user);
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra(TAG_TOKEN, token);
            intent.putExtra(String.valueOf(ID_USER), id_user);
            finish();
            startActivity(intent);
        }
        initComponents();
    }

    private void initComponents() {
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
//        btnRegister = (Button) findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
                requestLogin();
            }
        });

//        btnRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(mContext, RegisterActivity.class));
//            }
//        });
    }


    private void requestLogin() {
        mApiService.loginRequest(etEmail.getText().toString(), etPassword.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            loading.dismiss();
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getString("status").equals("true")) {

                                    String name = jsonRESULTS.getJSONObject("data").getString("name");
                                    Toast.makeText(mContext, "BERHASIL LOGIN "+name, Toast.LENGTH_SHORT).show();

                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putBoolean(SESSION_STATUS, true);
                                    editor.putString(TAG_TOKEN, jsonRESULTS.getString("token"));
                                    editor.putInt(String.valueOf(ID_USER), jsonRESULTS.getJSONObject("data").getInt("id"));
                                    editor.commit();

                                    Intent intent = new Intent(mContext, MainActivity.class);
                                    startActivity(intent);
                                } else {
                                    // Jika login gagal
                                    String error_message = jsonRESULTS.getString("error_msg");
                                    Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            loading.dismiss();
                        }
                    }
                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                        loading.dismiss();
                    }

                });
    }
}

