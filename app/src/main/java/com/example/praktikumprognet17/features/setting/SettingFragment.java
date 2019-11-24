package com.example.praktikumprognet17.features.setting;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.praktikumprognet17.LoginActivity;
import com.example.praktikumprognet17.R;
import com.example.praktikumprognet17.apihelper.BaseApiService;
import com.example.praktikumprognet17.apihelper.UtilsApi;
import com.example.praktikumprognet17.features.setting.edit_profil.UserProfile;
import com.example.praktikumprognet17.features.setting.edit_profil.ValueUser;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


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
    public static final String URL = "http://10.0.2.2:8000/api/";

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

