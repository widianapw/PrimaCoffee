package com.example.praktikumprognet17;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.praktikumprognet17.features.setting.SettingFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Firebase extends AppCompatActivity {
    EditText edtTitle;
    EditText edtMessage;
    SettingFragment settingFragment;
    Context context;
    public Firebase(Context context){
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);
        edtTitle = findViewById(R.id.edtTitle);
        edtMessage = findViewById(R.id.edtMessage);

    }

    public void sendNotification() {
        final  String FCM_API = "https://fcm.googleapis.com/fcm/send";
        final  String serverKey = "key=" + "AAAASmSliLo:APA91bE_L8amA2ht6jyWFudkg65YiV5dYbicOMSYzzvWUr9ziKxYiRTyGK1dIz00bH5M8Fl9W2u6oE-XWz-0wlg4vjJ-1d6eNB9ULheJnQxTigFM6N-ZQTMtx0qeCAgii7SH_K3slt8f";
        final  String contentType = "application/json";
        final String TAG = "NOTIFICATION TAG";

        String NOTIFICATION_TITLE;
        String NOTIFICATION_MESSAGE;
        String TOPIC;
        TOPIC = "/topics/userABC"; //topic must match with what the receiver subscribed to
        NOTIFICATION_TITLE = "Target Penjualan Tercapai";
        NOTIFICATION_MESSAGE = "Penjualan Hari Ini Sudah Mencapai Target yaitu diatas Rp 1000.000";

        JSONObject notification = new JSONObject();
        JSONObject notifcationBody = new JSONObject();
        try {
            notifcationBody.put("title", NOTIFICATION_TITLE);
            notifcationBody.put("message", NOTIFICATION_MESSAGE);

            notification.put("to", TOPIC);
            notification.put("data", notifcationBody);
        } catch (JSONException e) {
            Log.e(TAG, "onCreate: " + e.getMessage());
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(FCM_API, notification,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i(TAG, "onResponse: " + response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Firebase.this, "Request error", Toast.LENGTH_LONG).show();
                        Log.i(TAG, "onErrorResponse: Didn't work");
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization", serverKey);
                params.put("Content-Type", contentType);
                return params;
            }
        };
        MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }

}
