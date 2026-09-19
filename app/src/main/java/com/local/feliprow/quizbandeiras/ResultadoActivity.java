package com.local.feliprow.quizbandeiras;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultadoActivity extends AppCompatActivity {

    private ImageView ivIconeSelecionado;
    private TextView tvNomeJogador;
    private TextView tvPontuacao;
    private Button btnTentarNovamente;
    private Button btnMenu;

    private String nomeJogador;
    private int iconeJogador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        ivIconeSelecionado = findViewById(R.id.iv_icone_selecionado);
        tvNomeJogador = findViewById(R.id.tv_nome_jogador);
        tvPontuacao = findViewById(R.id.tv_pontuacao);
        btnTentarNovamente = findViewById(R.id.btn_tentar_novamente);
        btnMenu = findViewById(R.id.btn_menu);

        // Dados que vieram da QuizActivity
        nomeJogador = getIntent().getStringExtra("nome");
        iconeJogador = getIntent().getIntExtra("icone", R.drawable.icon_masculino);
        int pontos = getIntent().getIntExtra("pontos", 0);

        ivIconeSelecionado.setImageResource(iconeJogador);
        tvNomeJogador.setText(nomeJogador);
        tvPontuacao.setText(getString(R.string.pontuacao_formato, pontos));

        // Tentar novamente: começa um quiz novo com o mesmo jogador
        btnTentarNovamente.setOnClickListener(v -> {
            Intent intent = new Intent(this, QuizActivity.class);
            intent.putExtra("nome", nomeJogador);
            intent.putExtra("icone", iconeJogador);
            startActivity(intent);
            finish();
        });

        // Menu: fecha esta tela e volta para a MainActivity
        btnMenu.setOnClickListener(v -> finish());
    }
}
