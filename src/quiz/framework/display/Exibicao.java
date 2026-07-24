package quiz.framework.display;

import quiz.framework.core.Pergunta;
import quiz.framework.core.QuizResult;

// Abstrai a apresentação do quiz, sem depender de Swing/console/web específico
public interface Exibicao {

    void showQuestion(Pergunta question);

    int getUserAnswer();

    void showResult(QuizResult result);

    void showMessage(String message);

    void waitForNext();

    void clear();

    default void updateStats(int score, int hits, int errors) {
        
    }
}
