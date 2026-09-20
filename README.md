# Quiz Bandeiras

Aplicativo Android de quiz sobre bandeiras de países, feito em Java como trabalho de faculdade.

O jogador escolhe um ícone, digita o nome e responde 10 perguntas do tipo "Que país é esse?", com 4 alternativas cada. No final, vê a pontuação e pode jogar de novo.

## Telas

- **Tela inicial:** escolha do ícone (masculino ou feminino), campo para o nome e os botões Jogar, Desenvolvedores e Sair. O botão Jogar só fica habilitado depois que o nome é digitado.
- **Quiz:** mostra uma bandeira e 4 alternativas. O botão Confirmar só fica habilitado depois de marcar uma opção. O contador mostra a rodada atual (de 1 a 10).
- **Resultado:** mostra o nome, o ícone e a pontuação (acertos em 10), com os botões Tentar novamente e Menu.
- **Desenvolvedores:** créditos de quem fez o app.

Ao tentar sair do quiz no meio da partida, o app pergunta se o jogador quer mesmo desistir.

## Como funciona

- Existem 60 países no jogo. A cada partida, 10 são sorteados.
- Em cada rodada, a opção certa é misturada com 3 países errados sorteados.
- O app usa uma Activity para cada tela, e os dados (nome, ícone e pontos) passam de uma para outra por `Intent`.

## Tecnologias

- Java
- Android (minSdk 24, ou seja, Android 7.0 ou mais novo)
- Android Studio, Gradle, Material Components e ConstraintLayout

## Baixar o app

Dá para instalar direto pelo celular (Android 7.0 ou mais novo), sem precisar do Android Studio:

**[Baixar o QuizBandeiras.apk](https://github.com/Feliprow/QuizBandeiras/releases/latest/download/QuizBandeiras.apk)**

1. Abra este link no navegador do celular e baixe o arquivo.
2. Toque no arquivo baixado para instalar.
3. Se o Android avisar que a instalação de fontes desconhecidas está bloqueada, toque em **Configurações** e permita para o navegador que você usou. Depois volte e toque em **Instalar**.
4. Se aparecer um aviso do Play Protect, toque em **Instalar mesmo assim**. O aviso aparece porque o app não é da Play Store.

Todas as versões estão na página de [Releases](https://github.com/Feliprow/QuizBandeiras/releases).

## Como rodar

1. Clone o repositório e abra a pasta no Android Studio.
2. Espere o Gradle terminar de sincronizar.
3. Escolha um emulador ou um celular com Android 7.0 ou mais novo e clique em **Run**.

## Estrutura do código

Em `app/src/main/java/com/local/feliprow/quizbandeiras/`:

| Arquivo | O que faz |
|---|---|
| `MainActivity.java` | Tela inicial |
| `QuizActivity.java` | Rodadas do quiz |
| `ResultadoActivity.java` | Tela de resultado |
| `DevsActivity.java` | Tela dos desenvolvedores |
| `Pais.java` | Guarda o nome e a bandeira de um país |
| `BancoPaises.java` | Lista com os 60 países do quiz |

Os layouts ficam em `app/src/main/res/layout/` e as bandeiras em `app/src/main/res/drawable/` (arquivos `flag_xx.png`).

## Desenvolvedores

- Felipe Neres Vieira
- Victor Oliveira
