package com.local.feliprow.quizbandeiras;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class DevsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devs);

        // Voltar: fecha esta tela e volta para a MainActivity
        ImageView ivVoltar = findViewById(R.id.iv_voltar);
        ivVoltar.setOnClickListener(v -> finish());
    }
}
