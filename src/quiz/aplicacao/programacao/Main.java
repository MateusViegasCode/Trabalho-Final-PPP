package quiz.aplicacao.programacao;

import quiz.framework.core.QuizTemplate;
import quiz.framework.factory.FabricaQuiz;

// Main do Quiz de Programação
public class Main {
    
    public static void main(String[] args) {        
        try {
            // Passo 1: Criar a factory
            FabricaQuiz factory = new FabricaQuizProgramacao();
            
            // Passo 2: Exibir informações
            System.out.println(factory.getQuizName());
            System.out.println(factory.getQuizDescription());
            System.out.println("Iniciando...\n");
            
            // Passo 3: Obter instância do quiz
            QuizTemplate quiz = factory.createQuiz();
            
            // Passo 4: Executar
            quiz.execute();
            
            System.out.println("\nQuiz finalizado com sucesso!");
            System.exit(0);
            
        } catch (Exception e) {
            System.err.println("Erro ao executar o quiz: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}
