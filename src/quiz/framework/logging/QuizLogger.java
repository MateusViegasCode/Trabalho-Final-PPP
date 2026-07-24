package quiz.framework.logging;

import quiz.framework.core.QuizResult;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;

// Grava o resultado final de cada quiz em um arquivo de log
public final class QuizLogger {

    private static final DateTimeFormatter TIMESTAMP_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private QuizLogger() {
    }

    public static void appendResult(String fileName, String quizName, String strategyName, QuizResult result) {
        Path logPath = Paths.get("logs", fileName);
        StringBuilder entry = new StringBuilder();
        entry.append("[")
            .append(LocalDateTime.now(ZoneId.systemDefault()).format(TIMESTAMP_FORMAT))
            .append("]\n");
        entry.append("Nome: ").append(quizName).append("\n");
        entry.append("Acertos: ").append(result.getHits()).append("\n");
        entry.append("Erros: ").append(result.getErrors()).append("\n");
        entry.append("Total de perguntas: ").append(result.getTotalQuestions()).append("\n");
        entry.append("Pontuação final: ").append(result.getScore()).append("\n");
        entry.append(String.format("Taxa de acerto: %.1f%%%n", result.getAccuracyPercentage()));
        entry.append("\n");
        entry.append("------------------------------------------------------------");
        entry.append("\n\n");

        try {
            Files.createDirectories(logPath.getParent());
            Files.writeString(
                logPath,
                entry.toString(),
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            throw new IllegalStateException("Falha ao registrar log do quiz em " + logPath, e);
        }
    }
}
