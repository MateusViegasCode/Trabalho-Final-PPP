package quiz.framework.strategy;

// Strategy: calcula os pontos de cada resposta
public interface EstrategiaPontuacao {

    int calculate(int currentScore, boolean correct);

    String getName();
}
