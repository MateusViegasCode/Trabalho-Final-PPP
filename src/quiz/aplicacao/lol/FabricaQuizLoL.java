package quiz.aplicacao.lol;

import quiz.framework.core.QuizMultijogadorTemplate;
import quiz.framework.factory.FabricaQuizMultijogador;
import quiz.framework.strategy.EstrategiaPontuacao;

import java.util.Scanner;

// Factory do quiz de LoL; a estratégia é escolhida em runtime e injetada aqui
public class FabricaQuizLoL implements FabricaQuizMultijogador {

    private final EstrategiaPontuacao strategy;
    private final Scanner scanner;

    public FabricaQuizLoL(EstrategiaPontuacao strategy, Scanner scanner) {
        this.strategy = strategy;
        this.scanner = scanner;
    }

    @Override
    public QuizMultijogadorTemplate createQuiz() {
        return new QuizLoL(strategy, scanner);
    }

    @Override
    public String getQuizName() {
        return "Quiz de League of Legends (2 jogadores)";
    }

    @Override
    public String getQuizDescription() {
        return "Teste seus conhecimentos sobre League of Legends contra um amigo, "
             + "respondendo em turnos alternados. Estratégia de pontuação: "
             + strategy.getName() + ".";
    }
}
