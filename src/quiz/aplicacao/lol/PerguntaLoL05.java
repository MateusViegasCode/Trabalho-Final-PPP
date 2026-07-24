package quiz.aplicacao.lol;

import quiz.framework.annotation.PerguntaQuiz;

@PerguntaQuiz(
    statement = "Qual monstro neutro concede o buff conhecido como 'Baron', fortalecendo todo o time?",
    alternatives = {"Dragão Ancião", "Arauto do Vale", "Barão Nashor", "Grump"},
    correct = 2,
    category = "League of Legends",
    difficulty = "MÉDIO"
)
public class PerguntaLoL05 {
}
