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
import android.widget.TextView;

import com.example.praktikumprognet17.LoginActivity;
import com.example.praktikumprognet17.R;
import com.example.praktikumprognet17.features.setting.edit_profil.UserProfile;


public class SettingFragment extends Fragment {
    SharedPreferences sharedPreferences;
    boolean session = false;
    String token;
    int id_user;
    final String SHARED_PREFERENCES_NAME = "shared_preferences";
    final String SESSION_STATUS = "session";
    public final static String TAG_TOKEN = "token";
    public final static int ID_USER = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_setting_fragment, container, false);
        sharedPreferences = requireContext().getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        session = sharedPreferences.getBoolean(SESSION_STATUS, false);
        token = sharedPreferences.getString(TAG_TOKEN, null);
        id_user = sharedPreferences.getInt(String.valueOf(ID_USER),0);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView username = view.findViewById(R.id.username);
        username.setText(Integer.toString(id_user));

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
}
