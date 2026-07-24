package quiz.framework.strategy;

// +10 pontos por acerto, sem penalidade
public class EstrategiaDezPontos implements EstrategiaPontuacao {

    @Override
    public int calculate(int currentScore, boolean correct) {
        if (correct) {
            return 10;
        }
        return 0;
    }

    @Override
    public String getName() {
        return "Dez Pontos por Acerto";
    }
}
