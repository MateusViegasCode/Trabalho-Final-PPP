package quiz.framework.core;

import quiz.framework.display.Exibicao;
import quiz.framework.observer.Jogador;
import quiz.framework.observer.Scoreboard;
import quiz.framework.strategy.EstrategiaPontuacao;

import java.util.List;

// Template Method para quiz multiplayer: N jogadores, mesmas perguntas, Scoreboard (Observer) acompanha o placar
public abstract class QuizMultijogadorTemplate {

    protected List<Pergunta> questions;
    protected Exibicao display;
    protected EstrategiaPontuacao strategy;
    protected List<Jogador> players;
    protected Scoreboard scoreboard;

    // fluxo fixo do quiz multiplayer
    public final void execute(List<Jogador> players) {
        this.players = players;
        initializeQuiz();
        runQuizLoop();
        displayFinalResult();
        finalizeQuiz();
    }

    private void initializeQuiz() {
        this.strategy = getScoreStrategy();
        this.display = getDisplay();
        this.questions = createQuestions();

        this.scoreboard = new Scoreboard(getQuizName());
        for (Jogador player : players) {
            scoreboard.addPlayer(player);
        }

        display.showMessage("Quiz multiplayer iniciado! Jogadores: "
                + describePlayers() + " | Perguntas: " + questions.size()
                + " | Estratégia: " + strategy.getName());
    }

    private void runQuizLoop() {
        int total = questions.size();

        for (int i = 0; i < total; i++) {
            Pergunta question = questions.get(i);

            for (Jogador player : players) {
                display.clear();
                display.showMessage(String.format("Pergunta %d de %d — Vez de %s",
                        i + 1, total, player.getName()));
                display.showQuestion(question);

                int answer = display.getUserAnswer();
                boolean correct = question.isCorrect(answer);

                // pontos calculados pela Strategy, jogador notifica o Scoreboard
                int points = strategy.calculate(player.getScore(), correct);

                player.updateScore(points);
                if (correct) {
                    player.recordHit();
                } else {
                    player.recordError();
                }

                showAnswerFeedback(player, correct, question, answer);
            }
        }
    }

    private void showAnswerFeedback(Jogador player, boolean correct, Pergunta question, int answer) {
        display.clear();

        StringBuilder feedback = new StringBuilder();
        feedback.append(correct ? "✓ " : "✗ ")
                .append(player.getName())
                .append(correct ? " acertou!\n" : " errou!\n");

        feedback.append("Resposta marcada: ")
                .append(question.getAlternatives().get(answer))
                .append("\n");

        if (!correct) {
            feedback.append("Resposta correta: ")
                    .append(question.getAlternatives().get(question.getCorrectAnswer()))
                    .append("\n");
        }

        feedback.append("Pontuação de ").append(player.getName())
                .append(": ").append(player.getScore());

        display.showMessage(feedback.toString());
        display.waitForNext();
    }

    private void displayFinalResult() {
        display.clear();
        display.showMessage("===== QUIZ MULTIPLAYER FINALIZADO =====");
        display.showMessage(scoreboard.render());

        Jogador winner = scoreboard.getWinner();
        if (winner == null) {
            return;
        }

        long empatados = players.stream()
                .filter(p -> p.getScore() == winner.getScore())
                .count();

        if (empatados > 1) {
            display.showMessage("Resultado empatado entre " + empatados + " jogador(es)!");
        } else {
            display.showMessage("🏆 Vencedor: " + winner.getName() + "!");
        }
    }

    private String describePlayers() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < players.size(); i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(players.get(i).getName());
        }
        return sb.toString();
    }

    // hook opcional (ex: logging)
    protected void finalizeQuiz() {
    }

    // hooks obrigatórios das subclasses

    protected abstract List<Pergunta> createQuestions();

    protected abstract EstrategiaPontuacao getScoreStrategy();

    protected abstract Exibicao getDisplay();

    protected abstract String getQuizName();
}
