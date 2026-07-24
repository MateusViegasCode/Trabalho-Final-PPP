package quiz.aplicacao.programacao;

import quiz.framework.core.QuizTemplate;
import quiz.framework.factory.FabricaQuiz;

// Factory do quiz de Programação
public class FabricaQuizProgramacao implements FabricaQuiz {

    @Override
    public QuizTemplate createQuiz() {
        return new QuizProgramacao();
    }

    @Override
    public String getQuizName() {
        return "Quiz de Programação";
    }

    @Override
    public String getQuizDescription() {
        return "Teste seus conhecimentos em Príncipios e Padrões de Projeto.";
    }
}
