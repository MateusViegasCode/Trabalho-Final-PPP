package quiz.framework.core;

// Resultado final de um quiz
public class QuizResult {

    private int hits;
    private int errors;
    private int score;

    public QuizResult(int hits, int errors, int score) {
        this.hits = hits;
        this.errors = errors;
        this.score = score;
    }

    public int getHits() {
        return hits;
    }

    public int getErrors() {
        return errors;
    }

    public int getScore() {
        return score;
    }

    public int getTotalQuestions() {
        return hits + errors;
    }

    public double getAccuracyPercentage() {
        if (getTotalQuestions() == 0) {
            return 0.0;
        }
        return (double) hits / getTotalQuestions() * 100;
    }

    @Override
    public String toString() {
        return "QuizResult{" +
                "hits=" + hits +
                ", errors=" + errors +
                ", score=" + score +
                ", accuracy=" + String.format("%.1f", getAccuracyPercentage()) + "%" +
                '}';
    }
}
