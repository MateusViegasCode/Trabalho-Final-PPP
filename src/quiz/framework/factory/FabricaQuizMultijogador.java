package quiz.framework.factory;

import quiz.framework.core.QuizMultijogadorTemplate;

// Factory para quizzes multiplayer, espelha a FabricaQuiz
public interface FabricaQuizMultijogador {

    QuizMultijogadorTemplate createQuiz();

    String getQuizName();

    String getQuizDescription();
}
