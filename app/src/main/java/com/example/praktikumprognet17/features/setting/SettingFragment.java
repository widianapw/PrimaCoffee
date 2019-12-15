package com.example.praktikumprognet17.features.setting;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.praktikumprognet17.Firebase;
import com.example.praktikumprognet17.LoginActivity;
import com.example.praktikumprognet17.MySingleton;
import com.example.praktikumprognet17.R;
import com.example.praktikumprognet17.apihelper.BaseApiService;
import com.example.praktikumprognet17.apihelper.UtilsApi;
import com.example.praktikumprognet17.features.setting.edit_profil.UserProfile;
import com.example.praktikumprognet17.features.setting.edit_profil.ValueUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.android.volley.VolleyLog.TAG;


public class SettingFragment extends Fragment {
    SharedPreferences sharedPreferences;
    boolean session = false;
    String token;
    int id_user;
    View mView;
    final String SHARED_PREFERENCES_NAME = "shared_preferences";
    final String SESSION_STATUS = "session";
    public final static String TAG_TOKEN = "token";
    public final static int ID_USER = 0;
    BaseApiService mApiService;
    public static final String URL = UtilsApi.BASE_URL_API;

    Firebase firebase;

//    final private String FCM_API = "https://fcm.googleapis.com/fcm/send";
//    final private String serverKey = "key=" + "AAAASmSliLo:APA91bE_L8amA2ht6jyWFudkg65YiV5dYbicOMSYzzvWUr9ziKxYiRTyGK1dIz00bH5M8Fl9W2u6oE-XWz-0wlg4vjJ-1d6eNB9ULheJnQxTigFM6N-ZQTMtx0qeCAgii7SH_K3slt8f";
//    final private String contentType = "application/json";
//    final String TAG = "NOTIFICATION TAG";
//
//    String NOTIFICATION_TITLE;
//    String NOTIFICATION_MESSAGE;
//    String TOPIC;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_setting_fragment, container, false);
        mView = view;
        sharedPreferences = requireContext().getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        id_user = sharedPreferences.getInt(String.valueOf(ID_USER),0);
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadUserProfile();

        Button btnEditProfile = view.findViewById(R.id.btnEditProfil);
        btnEditProfile.setOnClickListener(v->{
            startActivity(new Intent(getContext(), UserProfile.class));
        });

        Button btnLogout = view.findViewById(R.id.button_logoutMain);
        btnLogout.setOnClickListener(v -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove(SESSION_STATUS);
            editor.remove(TAG_TOKEN);
            editor.apply();
            startActivity(new Intent(getContext(), LoginActivity.class));

        });
    }

    public void loadUserProfile(){
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
                TextView username = mView.findViewById(R.id.username);
                username.setText(response.body().getName());

                TextView email = mView.findViewById(R.id.email);
                email.setText(response.body().getEmail());

                TextView namaLengkap = mView.findViewById(R.id.nama_lengkap);
                namaLengkap.setText(response.body().getName());
            }

            @Override
            public void onFailure(Call<ValueUser> call, Throwable t) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        loadUserProfile();
    }
}

