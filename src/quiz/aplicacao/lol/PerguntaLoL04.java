package quiz.aplicacao.lol;

import quiz.framework.annotation.PerguntaQuiz;

@PerguntaQuiz(
    statement = "Qual estrutura precisa ser destruída para vencer a partida?",
    alternatives = {"Inibidor", "Nexus", "Torre central", "Covil do Barão"},
    correct = 1,
    category = "League of Legends",
    difficulty = "FÁCIL"
)
public class PerguntaLoL04 {
}
