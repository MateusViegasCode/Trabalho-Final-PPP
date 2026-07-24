package quiz.framework.observer;

import java.util.ArrayList;
import java.util.List;

// Sujeito observável: notifica os observadores a cada mudança de pontuação/acerto/erro
public class Jogador {

    private String name;
    private int score;
    private int hits;
    private int errors;
    private List<ObservadorQuiz> observers;

    public Jogador(String name) {
        this.name = name;
        this.score = 0;
        this.hits = 0;
        this.errors = 0;
        this.observers = new ArrayList<>();
    }

    public void addObserver(ObservadorQuiz observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void removeObserver(ObservadorQuiz observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (ObservadorQuiz observer : observers) {
            observer.update(this);
        }
    }

    public void updateScore(int points) {
        this.score += points;
        notifyObservers();
    }

    public void recordHit() {
        this.hits++;
        notifyObservers();
    }

    public void recordError() {
        this.errors++;
        notifyObservers();
    }

    public String getName() {
        return name;
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

    public int getTotalAnswers() {
        return hits + errors;
    }

    public double getAccuracyPercentage() {
        if (getTotalAnswers() == 0) {
            return 0.0;
        }
        return (double) hits / getTotalAnswers() * 100;
    }

    @Override
    public String toString() {
        return String.format("%s | Pontos: %d | Acertos: %d | Erros: %d | Taxa: %.1f%%",
                name, score, hits, errors, getAccuracyPercentage());
    }
}
