package quiz.framework.core;

import java.util.ArrayList;
import java.util.List;

// Representa uma pergunta de múltipla escolha
public class Pergunta {

    private String statement;
    private List<String> alternatives;
    private int correctAnswer;

    public Pergunta(String statement, List<String> alternatives, int correctAnswer) {
        this.statement = statement;
        this.alternatives = new ArrayList<>(alternatives);
        this.correctAnswer = correctAnswer;
    }

    public boolean isCorrect(int answer) {
        return answer == this.correctAnswer;
    }

    public String getStatement() {
        return statement;
    }

    public List<String> getAlternatives() {
        return new ArrayList<>(alternatives);
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }
}
