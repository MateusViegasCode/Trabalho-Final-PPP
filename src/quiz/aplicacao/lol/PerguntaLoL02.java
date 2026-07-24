package quiz.aplicacao.lol;

import quiz.framework.annotation.PerguntaQuiz;

@PerguntaQuiz(
    statement = "Como se chama o competitivo Brasileiro de League of Legends?",
    alternatives = {"LPL", "LCS", "LCK", "CBLOL"},
    correct = 3,
    category = "League of Legends",
    difficulty = "FÁCIL"
)
public class PerguntaLoL02 {
}
