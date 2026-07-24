package quiz.framework.factory;

import quiz.framework.core.QuizTemplate;

// Factory Method para criação dos quizzes
public interface FabricaQuiz {

    QuizTemplate createQuiz();

    String getQuizName();

    String getQuizDescription();
}
