package quiz.aplicacao.programacao;

import quiz.framework.annotation.PerguntaQuiz;

@PerguntaQuiz(
    statement = "Qual padrão de projeto garante que uma classe possui, no máximo, uma instância e oferece um ponto único de acesso a ela?",
    alternatives = {"Fábrica", "Singleton", "Proxy", "Fachada"},
    correct = 1,
    category = "Padrões de Projeto",
    difficulty = "FÁCIL"
)
public class PerguntaProgramacao01 {
}