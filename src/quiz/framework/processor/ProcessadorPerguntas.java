package quiz.framework.processor;

import quiz.framework.annotation.PerguntaQuiz;
import quiz.framework.core.Pergunta;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Lê classes anotadas com @PerguntaQuiz e gera os objetos Pergunta via Reflection
public class ProcessadorPerguntas {

    public static List<Pergunta> processQuestions(Class<?>[] classes) {
        List<Pergunta> questions = new ArrayList<>();

        for (Class<?> clazz : classes) {
            Pergunta question = processClass(clazz);
            if (question != null) {
                questions.add(question);
            }
        }

        return questions;
    }

    private static Pergunta processClass(Class<?> clazz) {
        if (!clazz.isAnnotationPresent(PerguntaQuiz.class)) {
            return null;
        }

        try {
            PerguntaQuiz annotation = clazz.getAnnotation(PerguntaQuiz.class);

            String statement = annotation.statement();
            String[] alternatives = annotation.alternatives();
            int correct = annotation.correct();

            validateQuestion(statement, alternatives, correct, clazz.getSimpleName());

            return new Pergunta(statement, Arrays.asList(alternatives), correct);

        } catch (Exception e) {
            throw new IllegalArgumentException(
                "Erro ao processar annotation em " + clazz.getName() + ": " + e.getMessage(),
                e
            );
        }
    }

    // validações básicas dos dados vindos da annotation
    private static void validateQuestion(String statement, String[] alternatives,
                                        int correct, String className) {

        if (statement == null || statement.trim().isEmpty()) {
            throw new IllegalArgumentException(
                className + ": 'statement' não pode ser vazio"
            );
        }

        if (alternatives == null || alternatives.length == 0) {
            throw new IllegalArgumentException(
                className + ": 'alternatives' não pode estar vazio"
            );
        }

        if (alternatives.length < 2) {
            throw new IllegalArgumentException(
                className + ": 'alternatives' deve ter pelo menos 2 opções"
            );
        }

        if (correct < 0 || correct >= alternatives.length) {
            throw new IllegalArgumentException(
                className + ": 'correct' deve estar entre 0 e " + (alternatives.length - 1)
            );
        }

        for (int i = 0; i < alternatives.length; i++) {
            if (alternatives[i] == null || alternatives[i].trim().isEmpty()) {
                throw new IllegalArgumentException(
                    className + ": alternativa[" + i + "] não pode ser vazia"
                );
            }
        }
    }

    public static List<Pergunta> processPackage(String packageName) {
        System.out.println("Dica: Use processQuestions(Class<?>[] classes) passando " +
                          "explicitamente as classes do pacote " + packageName);
        return new ArrayList<>();
    }
}
