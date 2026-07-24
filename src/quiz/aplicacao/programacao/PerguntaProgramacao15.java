package quiz.aplicacao.programacao;

import quiz.framework.annotation.PerguntaQuiz;

@PerguntaQuiz(
    statement = "No uso de um framework como Spring Boot, instanciar manualmente uma dependência via 'new' dentro do construtor de um Controller gera qual problema em relação ao contêiner de Injeção de Dependências?",
    alternatives = {"O framework detecta o 'new' e o transforma em um Bean automaticamente.", "O código falhará no momento da compilação.", "Gera um erro de segurança de tipagem (Type Safety).", "Não utiliza o mecanismo de injeção, desperdiçando benefícios como o gerenciamento do ciclo de vida e a facilidade na realização de testes."},
    correct = 3,
    category = "Frameworks",
    difficulty = "DIFÍCIL"
)

public class PerguntaProgramacao15 {
    
}
