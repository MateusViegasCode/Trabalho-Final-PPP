package quiz.aplicacao.lol;

import quiz.framework.annotation.PerguntaQuiz;

@PerguntaQuiz(
    statement = "Quantos jogadores compõem cada time em uma partida padrão de LoL?",
    alternatives = {"3", "4", "5", "6"},
    correct = 2,
    category = "League of Legends",
    difficulty = "FÁCIL"
)
public class PerguntaLoL03 {
}
