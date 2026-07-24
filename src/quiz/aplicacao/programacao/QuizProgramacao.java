package quiz.aplicacao.programacao;

import quiz.framework.core.Pergunta;
import quiz.framework.core.QuizTemplate;
import quiz.framework.display.Exibicao;
import quiz.framework.processor.ProcessadorPerguntas;
import quiz.framework.logging.QuizLogger;
import quiz.framework.strategy.EstrategiaPontuacao;
import quiz.framework.strategy.EstrategiaDezPontos;

import java.util.List;

// Quiz de Programação: implementa os hooks do QuizTemplate com perguntas de PPP,
// 10 pontos por acerto e interface Swing
public class QuizProgramacao extends QuizTemplate {

    private ExibicaoSwingProgramacao swingDisplay;

    @Override
    protected List<Pergunta> createQuestions() {
        Class<?>[] questionClasses = {
            PerguntaProgramacao01.class,
            PerguntaProgramacao02.class,
            PerguntaProgramacao03.class,
            PerguntaProgramacao04.class,
            PerguntaProgramacao05.class,
            PerguntaProgramacao06.class,
            PerguntaProgramacao07.class,
            PerguntaProgramacao08.class,
            PerguntaProgramacao09.class,
            PerguntaProgramacao10.class,
            PerguntaProgramacao11.class,
            PerguntaProgramacao12.class,
            PerguntaProgramacao13.class,
            PerguntaProgramacao14.class,
            PerguntaProgramacao15.class
        };

        return ProcessadorPerguntas.processQuestions(questionClasses);
    }

    @Override
    protected EstrategiaPontuacao getScoreStrategy() {
        return new EstrategiaDezPontos();
    }

    @Override
    protected Exibicao getDisplay() {
        this.swingDisplay = new ExibicaoSwingProgramacao();
        return swingDisplay;
    }

    @Override
    protected void finalizeQuiz() {
        if (swingDisplay != null && session != null) {
            swingDisplay.updateStats(
                session.getScore(),
                session.getHits(),
                session.getErrors()
            );

            QuizLogger.appendResult(
                "quiz-programacao.log",
                "Quiz de Programação",
                getScoreStrategy().getClass().getSimpleName(),
                session.getResult()
            );
        }
    }
}
