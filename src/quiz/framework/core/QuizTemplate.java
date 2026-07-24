package quiz.framework.core;

import quiz.framework.display.Exibicao;
import quiz.framework.strategy.EstrategiaPontuacao;
import java.util.List;

//Classe abstrata que implementa o padrão Template Method
public abstract class QuizTemplate {
    
    protected SessaoQuiz session;
    protected Exibicao display;
    protected EstrategiaPontuacao strategy;
    
    //Método Template que define o fluxo principal do quiz

    public final void execute() {
        initializeQuiz();
        runQuizLoop();
        displayFinalResult();
        finalizeQuiz();
    }
    
    //Inicializa o quiz configurando estratégia, display e criando perguntas
    private void initializeQuiz() {
        this.strategy = getScoreStrategy();
        this.display = getDisplay();
        
        List<Pergunta> questions = createQuestions();
        this.session = new SessaoQuiz(questions, strategy);
        
        display.showMessage("Quiz iniciado! Total de perguntas: " + 
                           session.getTotalQuestions());
    }
    
    //Loop principal que executa o quiz pergunta por pergunta
    private void runQuizLoop() {
        while (!session.isFinished()) {
            display.clear();
            
            // Mostrar número da pergunta
            int questionNumber = session.getCurrentQuestionNumber();
            int total = session.getTotalQuestions();
            display.showMessage(String.format("Pergunta %d de %d", 
                                             questionNumber, total));
            
            //Mostrar pergunta
            Pergunta currentQuestion = session.getCurrentQuestion();
            display.showQuestion(currentQuestion);
            
            //Obter resposta
            int userAnswer = display.getUserAnswer();
            
            //Registrar resposta (calcula pontos internamente)
            boolean isCorrect = session.registerAnswer(userAnswer);
            display.updateStats(session.getScore(), session.getHits(), session.getErrors());
            
            //Mostrar resultado
            showAnswerFeedback(isCorrect, currentQuestion, userAnswer);
            
            //Aguardar antes de próxima pergunta
            if (!session.isFinished()) {
                display.waitForNext();
            }
        }
    }
    

     //Exibe feedback sobre a resposta do usuário
     //isCorrect se a resposta estava correta
     //question pergunta respondida
     //userAnswer resposta do usuário
    private void showAnswerFeedback(boolean isCorrect, Pergunta question, int userAnswer) {
        display.clear();

        StringBuilder feedback = new StringBuilder();
        if (isCorrect) {
            feedback.append("✓ Resposta correta!\n");
        } else {
            feedback.append("✗ Resposta incorreta!\n");
        }

        feedback.append("Sua resposta: ")
                .append(question.getAlternatives().get(userAnswer))
                .append("\n");

        if (!isCorrect) {
            feedback.append("Resposta correta: ")
                    .append(question.getAlternatives().get(question.getCorrectAnswer()))
                    .append("\n");
        }

        feedback.append("Pontuação atual: ")
                .append(session.getScore())
                .append("\n");

        feedback.append("Acertos: ")
                .append(session.getHits())
                .append(" | Erros: ")
                .append(session.getErrors());

        display.showMessage(feedback.toString());
    }
    
    //Exibe o resultado final do quiz
    private void displayFinalResult() {
        display.clear();
        display.updateStats(session.getScore(), session.getHits(), session.getErrors());
        display.showMessage("===== QUIZ FINALIZADO =====");
        display.showResult(session.getResult());
    }
    
    //Finaliza o quiz
    protected void finalizeQuiz() {

    }
    
    //hooks a serem implementados por subclasses

    protected abstract List<Pergunta> createQuestions();
    
    //Subclasses devem fornecer a estratégia de pontuação
    protected abstract EstrategiaPontuacao getScoreStrategy();
    
    //Subclasses devem fornecer a implementação de display
    protected abstract Exibicao getDisplay();
}
