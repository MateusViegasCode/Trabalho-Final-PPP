package quiz.aplicacao.lol;

import quiz.framework.annotation.PerguntaQuiz;

@PerguntaQuiz(
    statement = "Qual é o nome da moeda premium usada para comprar skins na loja de LoL?",
    alternatives = {"Essência Azul", "Cristais de Honra", "Ouro", "Riot Points (RP)"},
    correct = 3,
    category = "League of Legends",
    difficulty = "MÉDIO"
)
public class PerguntaLoL07 {
}
