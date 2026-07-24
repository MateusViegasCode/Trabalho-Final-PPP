package quiz.aplicacao.lol;

import quiz.framework.display.Exibicao;
import quiz.framework.core.Pergunta;
import quiz.framework.core.QuizResult;
import java.util.Scanner;
import java.util.List;

// Exibição em console do quiz de LoL (igual a ExibicaoConsole do quiz de Conhecimentos)
public class ExibicaoConsoleLoL implements Exibicao {

    private final Scanner scanner;
    private int alternativesCount;

    // recebe o Scanner do Main pra não disputar o mesmo System.in com outro Scanner
    public ExibicaoConsoleLoL(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void showQuestion(Pergunta question) {
        System.out.println("\n" + "═".repeat(70));
        System.out.println("PERGUNTA");
        System.out.println("═".repeat(70));
        System.out.println(question.getStatement());
        System.out.println("\n" + "─".repeat(70));
        System.out.println("ALTERNATIVAS:");
        System.out.println("─".repeat(70));

        List<String> alternatives = question.getAlternatives();
        this.alternativesCount = alternatives.size();
        for (int i = 0; i < alternatives.size(); i++) {
            System.out.printf("[%d] %s%n", i, alternatives.get(i));
        }

        System.out.println();
    }

    @Override
    public int getUserAnswer() {
        int maxIndex = alternativesCount - 1;
        while (true) {
            try {
                System.out.print("Digite o número da sua resposta: ");
                int answer = scanner.nextInt();

                if (answer >= 0 && answer <= maxIndex) {
                    return answer;
                }
                System.out.println("Resposta inválida! Digite um número entre 0 e " + maxIndex + ".");
            } catch (java.util.InputMismatchException e) {
                System.out.println("Resposta inválida! Digite um número.");
                scanner.nextLine();
            }
        }
    }

    @Override
    public void showResult(QuizResult result) {
        System.out.printf("Acertos: %-58d%n", result.getHits());
        System.out.printf("Erros: %-60d%n", result.getErrors());
        System.out.printf("Pontuação final: %-50d%n", result.getScore());
    }

    @Override
    public void showMessage(String message) {
        System.out.println("\n" + message);
    }

    @Override
    public void waitForNext() {
        System.out.print("\nPressione ENTER para continuar");
        scanner.nextLine();
        scanner.nextLine();
    }

    @Override
    public void clear() {
        for (int i = 0; i < 3; i++) {
            System.out.println();
        }
    }

    // scanner é compartilhado com o Main, então não fecha aqui
}
