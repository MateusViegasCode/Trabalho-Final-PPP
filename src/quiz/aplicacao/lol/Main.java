package quiz.aplicacao.lol;

import quiz.framework.core.QuizMultijogadorTemplate;
import quiz.framework.factory.FabricaQuizMultijogador;
import quiz.framework.observer.Jogador;
import quiz.framework.strategy.EstrategiaCincoPontos;
import quiz.framework.strategy.EstrategiaDezPontos;
import quiz.framework.strategy.EstrategiaPenalizacao;
import quiz.framework.strategy.EstrategiaPontuacao;
import quiz.framework.strategy.EstrategiaPorTempo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Main do quiz de LoL: pega nomes dos 2 jogadores, deixa escolher a estratégia e roda o quiz
public class Main {

    public static void main(String[] args) {
        System.out.println("Quiz de League of Legends - Modo 2 Jogadores \n");
        

        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Nome do Jogador 1: ");
            String nomeJogador1 = scanner.nextLine().trim();
            if (nomeJogador1.isEmpty()) {
                nomeJogador1 = "Jogador 1";
            }

            System.out.print("Nome do Jogador 2: ");
            String nomeJogador2 = scanner.nextLine().trim();
            if (nomeJogador2.isEmpty()) {
                nomeJogador2 = "Jogador 2";
            }

            EstrategiaPontuacao strategy = escolherEstrategia(scanner);

            List<Jogador> jogadores = new ArrayList<>();
            jogadores.add(new Jogador(nomeJogador1));
            jogadores.add(new Jogador(nomeJogador2));

            FabricaQuizMultijogador factory = new FabricaQuizLoL(strategy, scanner);

            System.out.println(factory.getQuizName());
            System.out.println(factory.getQuizDescription());
            System.out.println("\nIniciando...\n");

            QuizMultijogadorTemplate quiz = factory.createQuiz();
            quiz.execute(jogadores);

            System.out.println("\n✓ Quiz finalizado com sucesso!");
            scanner.close();
            System.exit(0);

        } catch (Exception e) {
            System.err.println("Erro ao executar o quiz: " + e.getMessage());
            e.printStackTrace();
            scanner.close();
            System.exit(1);
        }
    }

    // menu pra escolher a estratégia de pontuação em runtime
    private static EstrategiaPontuacao escolherEstrategia(Scanner scanner) {
        System.out.println("\nEscolha a política de pontuação:");
        System.out.println("[1] Dez Pontos por Acerto");
        System.out.println("[2] Cinco Pontos por Acerto");
        System.out.println("[3] Penalização por Erro (+10 / -5)");
        System.out.println("[4] Pontuação Baseada em Tempo");

        while (true) {
            System.out.print("Opção: ");
            String opcao = scanner.nextLine().trim();

            switch (opcao) {
                case "1":
                    return new EstrategiaDezPontos();
                case "2":
                    return new EstrategiaCincoPontos();
                case "3":
                    return new EstrategiaPenalizacao();
                case "4":
                    return new EstrategiaPorTempo();
                default:
                    System.out.println("Opção inválida! Digite um número de 1 a 4.");
            }
        }
    }
}
