package quiz.aplicacao.lol;

import quiz.framework.annotation.PerguntaQuiz;

@PerguntaQuiz(
    statement = "Qual empresa desenvolve e publica League of Legends?",
    alternatives = {"Blizzard Entertainment", "Riot Games", "Valve", "Electronic Arts"},
    correct = 1,
    category = "League of Legends",
    difficulty = "FÁCIL"
)
public class PerguntaLoL01 {
}
