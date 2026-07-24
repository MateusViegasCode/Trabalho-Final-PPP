package quiz.aplicacao.programacao;

import quiz.framework.annotation.PerguntaQuiz;

@PerguntaQuiz(
    statement = "A chamada sequencial obj.getX().getY().getZ().metodo() representa uma violação direta de qual princípio de projeto?",
    alternatives = {"Princípio Aberto/Fechado", "Princípio da Responsabilidade Única", "Princípio de Substituição de Liskov", "PrincípioLei de Demeter"},
    correct = 3,
    category = "Princípios de Projeto",
    difficulty = "MÉDIO"
)
public class PerguntaProgramacao03 {
}