package quiz.aplicacao.lol;

import quiz.framework.core.Pergunta;
import quiz.framework.core.QuizMultijogadorTemplate;
import quiz.framework.core.QuizResult;
import quiz.framework.display.Exibicao;
import quiz.framework.logging.QuizLogger;
import quiz.framework.observer.Jogador;
import quiz.framework.processor.ProcessadorPerguntas;
import quiz.framework.strategy.EstrategiaPontuacao;

import java.util.List;
import java.util.Scanner;

// Quiz de LoL para 2 jogadores, reaproveita Template Method + Strategy + Observer do framework
public class QuizLoL extends QuizMultijogadorTemplate {

    private final EstrategiaPontuacao strategy;
    private final Scanner scanner;
    private ExibicaoConsoleLoL consoleDisplay;
    private List<Jogador> jogadoresFinalizados;

    public QuizLoL(EstrategiaPontuacao strategy, Scanner scanner) {
        this.strategy = strategy;
        this.scanner = scanner;
    }

    @Override
    protected List<Pergunta> createQuestions() {
        Class<?>[] questionClasses = {
            PerguntaLoL01.class,
            PerguntaLoL02.class,
            PerguntaLoL03.class,
            PerguntaLoL04.class,
            PerguntaLoL05.class,
            PerguntaLoL06.class,
            PerguntaLoL07.class,
            PerguntaLoL08.class
        };

        return ProcessadorPerguntas.processQuestions(questionClasses);
    }

    @Override
    protected EstrategiaPontuacao getScoreStrategy() {
        return strategy;
    }

    @Override
    protected Exibicao getDisplay() {
        this.consoleDisplay = new ExibicaoConsoleLoL(scanner);
        return consoleDisplay;
    }

    @Override
    protected String getQuizName() {
        return "Quiz de League of Legends (Multiplayer)";
    }

    @Override
    protected void finalizeQuiz() {
        this.jogadoresFinalizados = players;

        // Registra o resultado individual de cada jogador em log
        for (Jogador jogador : jogadoresFinalizados) {
            QuizResult result = new QuizResult(
                jogador.getHits(),
                jogador.getErrors(),
                jogador.getScore()
            );

            QuizLogger.appendResult(
                "quiz-lol.log",
                "Quiz de LoL - " + jogador.getName(),
                strategy.getClass().getSimpleName(),
                result
            );
        }
    }
}
