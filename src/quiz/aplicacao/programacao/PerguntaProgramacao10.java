package quiz.aplicacao.programacao;

import quiz.framework.annotation.PerguntaQuiz;

@PerguntaQuiz(
    statement = "Qual é a principal desvantagem de acoplar a configuração de dependências por meio de fábricas (Factory) em vez de usar Injeção de Dependências pura?",
    alternatives = {"Aumenta a velocidade de compilação do projeto.", "A classe continua acoplada ao mecanismo de criação/fornecimento do objeto (a própria fábrica).", "Impede o uso de interfaces nas classes do sistema.", "Gera baixo acoplamento, mas viola a coesão."},
    correct = 1,
    category = "Injeção de Dependências",
    difficulty = "DIFÍCIL"
)

public class PerguntaProgramacao10 {
    
}
