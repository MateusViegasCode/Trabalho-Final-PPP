package quiz.framework.core;

import java.util.ArrayList;
import java.util.List;
import quiz.framework.strategy.EstrategiaPontuacao;

// Controla o estado de uma sessão de quiz (pergunta atual, pontuação, acertos e erros)
public class SessaoQuiz {

    private List<Pergunta> questions;
    private int currentQuestion;
    private int score;
    private int hits;
    private int errors;
    private EstrategiaPontuacao strategy;

    public SessaoQuiz(List<Pergunta> questions, EstrategiaPontuacao strategy) {
        this.questions = new ArrayList<>(questions);
        this.currentQuestion = 0;
        this.score = 0;
        this.hits = 0;
        this.errors = 0;
        this.strategy = strategy;
    }

    public Pergunta getCurrentQuestion() {
        if (isFinished()) {
            return null;
        }
        return questions.get(currentQuestion);
    }

    // registra a resposta, calcula os pontos via strategy e avança a pergunta
    public boolean registerAnswer(int answer) {
        if (isFinished()) {
            return false;
        }

        Pergunta question = getCurrentQuestion();
        boolean isCorrect = question.isCorrect(answer);

        if (isCorrect) {
            hits++;
        } else {
            errors++;
        }

        int points = strategy.calculate(score, isCorrect);
        updateScore(points);

        nextQuestion();
        return isCorrect;
    }

    public void nextQuestion() {
        if (!isFinished()) {
            currentQuestion++;
        }
    }

    public boolean isFinished() {
        return currentQuestion >= questions.size();
    }

    public void updateScore(int points) {
        this.score += points;
    }

    public int getScore() {
        return score;
    }

    public int getHits() {
        return hits;
    }

    public int getErrors() {
        return errors;
    }

    public int getCurrentQuestionNumber() {
        return currentQuestion + 1;
    }

    public int getTotalQuestions() {
        return questions.size();
    }

    public QuizResult getResult() {
        return new QuizResult(hits, errors, score);
    }
}
