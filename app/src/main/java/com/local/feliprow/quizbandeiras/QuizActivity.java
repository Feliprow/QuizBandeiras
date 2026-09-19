package com.local.feliprow.quizbandeiras;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private static final int TOTAL_RODADAS = 10;

    private ImageView ivVoltar;
    private ImageView ivBandeiraAtual;
    private TextView tvContadorRodada;
    private RadioGroup rgOpcoes;
    private RadioButton[] rbOpcoes;
    private Button btnConfirmar;

    private String nomeJogador;
    private int iconeJogador;

    private List<Pais> todosPaises;
    private List<Pais> paisesDaPartida;
    private Pais paisCorreto;
    private int rodadaAtual = 0;
    private int pontos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        ivVoltar = findViewById(R.id.iv_voltar);
        ivBandeiraAtual = findViewById(R.id.iv_bandeira_atual);
        tvContadorRodada = findViewById(R.id.tv_contador_rodada);
        rgOpcoes = findViewById(R.id.rg_opcoes);
        rbOpcoes = new RadioButton[]{
                findViewById(R.id.rb_opcao_1),
                findViewById(R.id.rb_opcao_2),
                findViewById(R.id.rb_opcao_3),
                findViewById(R.id.rb_opcao_4)
        };
        btnConfirmar = findViewById(R.id.btn_confirmar);

        // Dados que vieram da MainActivity
        nomeJogador = getIntent().getStringExtra("nome");
        iconeJogador = getIntent().getIntExtra("icone", R.drawable.icon_masculino);

        // Voltar (seta da tela ou botão do celular): pergunta antes de desistir
        ivVoltar.setOnClickListener(v -> mostrarDialogSair());
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                mostrarDialogSair();
            }
        });

        // Confirmar só fica habilitado quando há uma opção marcada
        rgOpcoes.setOnCheckedChangeListener((group, checkedId) ->
                btnConfirmar.setEnabled(checkedId != -1));
        btnConfirmar.setOnClickListener(v -> confirmarResposta());

        sortearPartida();
        mostrarRodada();
    }

    // Sorteia os 10 países da partida entre os 84 disponíveis
    private void sortearPartida() {
        todosPaises = BancoPaises.getTodos();
        List<Pais> embaralhados = new ArrayList<>(todosPaises);
        Collections.shuffle(embaralhados);
        paisesDaPartida = embaralhados.subList(0, TOTAL_RODADAS);
    }

    // Mostra a bandeira da rodada e as 4 opções (1 certa + 3 erradas)
    private void mostrarRodada() {
        paisCorreto = paisesDaPartida.get(rodadaAtual);

        List<Pais> erradas = new ArrayList<>(todosPaises);
        erradas.remove(paisCorreto);
        Collections.shuffle(erradas);

        List<Pais> opcoes = new ArrayList<>();
        opcoes.add(paisCorreto);
        opcoes.addAll(erradas.subList(0, 3));
        Collections.shuffle(opcoes);

        tvContadorRodada.setText(getString(R.string.rodada_formato, rodadaAtual + 1, TOTAL_RODADAS));
        ivBandeiraAtual.setImageResource(paisCorreto.getBandeira());
        rgOpcoes.clearCheck();
        for (int i = 0; i < rbOpcoes.length; i++) {
            rbOpcoes[i].setText(opcoes.get(i).getNome());
        }
    }

    private void confirmarResposta() {
        RadioButton marcada = findViewById(rgOpcoes.getCheckedRadioButtonId());
        if (marcada.getText().toString().equals(paisCorreto.getNome())) {
            pontos++;
        }

        rodadaAtual++;
        if (rodadaAtual < TOTAL_RODADAS) {
            mostrarRodada();
        } else {
            abrirResultado();
        }
    }

    private void abrirResultado() {
        Intent intent = new Intent(this, ResultadoActivity.class);
        intent.putExtra("nome", nomeJogador);
        intent.putExtra("icone", iconeJogador);
        intent.putExtra("pontos", pontos);
        startActivity(intent);
        finish();
    }

    private void mostrarDialogSair() {
        View view = getLayoutInflater().inflate(R.layout.dialog_sair, null);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(view)
                .create();
        // Fundo transparente para aparecerem os cantos arredondados do bg_dialog
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        view.findViewById(R.id.btn_dialog_sim).setOnClickListener(v -> {
            dialog.dismiss();
            finish();
        });
        view.findViewById(R.id.btn_dialog_nao).setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }
}
