package com.example.praktikumprognet17;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tvResultName;
    String resultName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
        // untuk mendapatkan data dari activity sebelumnya, yaitu activity login.
        Bundle extras = getIntent().getExtras();
        if (extras != null)
            resultName = extras.getString("result_name");
        tvResultName.setText(resultName);
    }

    private void initComponents() {
        tvResultName = (TextView) findViewById(R.id.tvResultName);
    }
}
