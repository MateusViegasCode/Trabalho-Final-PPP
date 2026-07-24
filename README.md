# Trabalho de Princípios e Padrões de Projeto

**Alunos:** Leandro e Mateus

Framework em Java para construir diferentes tipos de quiz, reaproveitando a mesma base de código (interfaces, pontuação, factories, anotações e processamento automático das perguntas) em duas aplicações distintas, sem duplicar ou alterar o código do framework.

## Quizzes

| Quiz | Interface | Modo |
|---|---|---|
| **Quiz de Programação** | Java Swing | 1 jogador |
| **Quiz de League of Legends** | Console | 2 jogadores simultâneos |

## Funcionalidades

- **Template Method** — o fluxo do quiz (iniciar, perguntar, corrigir, exibir resultado) é fixo no framework; cada aplicação só define suas perguntas, pontuação e exibição.
- **Factory Method** — cada quiz é criado por sua própria fábrica (`FabricaQuizProgramacao`, `FabricaQuizLoL`), sem o cliente conhecer a classe concreta.
- **Strategy** — 4 políticas de pontuação intercambiáveis, escolhidas em tempo de execução no quiz de LoL.
- **Annotation + Reflection** — as perguntas são declaradas com `@PerguntaQuiz` e montadas automaticamente pelo `ProcessadorPerguntas`.
- **Observer** — no quiz de LoL, o `Scoreboard` acompanha a pontuação dos 2 jogadores em tempo real e apura o vencedor ao final.
- **Logging** — o resultado de cada partida é gravado em um arquivo de log próprio (`quiz-programacao.log` / `quiz-lol.log`).

## Tipos de Pontuação (Strategy)

O framework possui 4 estratégias de pontuação prontas, todas implementando `EstrategiaPontuacao`:

| Estratégia | Regra |
|---|---|
| `EstrategiaDezPontos` | +10 pontos por acerto, sem penalidade |
| `EstrategiaCincoPontos` | +5 pontos por acerto, sem penalidade |
| `EstrategiaPenalizacao` | +10 pontos por acerto, -5 por erro (nunca fica negativa) |
| `EstrategiaPorTempo` | pontuação decrescente conforme o tempo de resposta |

## Estrutura do Projeto

```
TrabalhoPPP/
├── logs/
│   ├── quiz-programacao.log
│   └── quiz-lol.log
├── src/
│   └── quiz/
│       ├── framework/
│       │   ├── annotation/            (@PerguntaQuiz)
│       │   ├── core/                  (Template Method: QuizTemplate, QuizMultijogadorTemplate)
│       │   ├── display/               (interface Exibicao)
│       │   ├── factory/               (FabricaQuiz, FabricaQuizMultijogador)
│       │   ├── logging/               (QuizLogger)
│       │   ├── observer/              (Jogador, ObservadorQuiz, Scoreboard)
│       │   ├── processor/             (ProcessadorPerguntas)
│       │   └── strategy/              (4 políticas de pontuação)
│       │
│       └── aplicacao/
│           ├── programacao/           (Quiz de Programação: Swing)
│           │   ├── ExibicaoSwingProgramacao.java
│           │   ├── FabricaQuizProgramacao.java
│           │   ├── Main.java
│           │   ├── PerguntaProgramacao01.java ... 15.java
│           │   └── QuizProgramacao.java
│           │
│           └── lol/                   (Quiz de LoL: console, 2 jogadores)
│               ├── ExibicaoConsoleLoL.java
│               ├── FabricaQuizLoL.java
│               ├── Main.java
│               ├── PerguntaLoL01.java ... 08.java
│               └── QuizLoL.java
│
├── README.md
└── Relatorio Técnico.pdf
```
