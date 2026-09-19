package com.local.feliprow.quizbandeiras;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView ivSair;
    private TextView tvSair;
    private ImageView ivIconeJogador;
    private ImageView ivTrocarIcone;
    private EditText etNomeJogador;
    private Button btnJogar;
    private Button btnDesenvolvedores;

    private boolean iconeMasculino = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivSair = findViewById(R.id.iv_sair);
        tvSair = findViewById(R.id.tv_sair);
        ivIconeJogador = findViewById(R.id.iv_icone_jogador);
        ivTrocarIcone = findViewById(R.id.iv_trocar_icone);
        etNomeJogador = findViewById(R.id.et_nome_jogador);
        btnJogar = findViewById(R.id.btn_jogar);
        btnDesenvolvedores = findViewById(R.id.btn_desenvolvedores);

        // Sair: fecha o app
        ivSair.setOnClickListener(v -> finish());
        tvSair.setOnClickListener(v -> finish());

        // Troca o ícone entre masculino e feminino
        ivTrocarIcone.setOnClickListener(v -> {
            iconeMasculino = !iconeMasculino;
            ivIconeJogador.setImageResource(iconeSelecionado());
        });

        // Jogar só fica habilitado quando o nome não está vazio
        etNomeJogador.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                btnJogar.setEnabled(!s.toString().trim().isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        // Jogar: abre o quiz levando o nome e o ícone escolhidos
        btnJogar.setOnClickListener(v -> {
            Intent intent = new Intent(this, QuizActivity.class);
            intent.putExtra("nome", etNomeJogador.getText().toString().trim());
            intent.putExtra("icone", iconeSelecionado());
            startActivity(intent);
        });

        // Desenvolvedores: abre a tela de créditos
        btnDesenvolvedores.setOnClickListener(v ->
                startActivity(new Intent(this, DevsActivity.class)));
    }


    private int iconeSelecionado() {
        return iconeMasculino ? R.drawable.icon_masculino : R.drawable.icon_feminino;
    }
}
