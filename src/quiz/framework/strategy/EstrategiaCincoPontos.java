package quiz.framework.strategy;

// +5 pontos por acerto, sem penalidade
public class EstrategiaCincoPontos implements EstrategiaPontuacao {

    @Override
    public int calculate(int currentScore, boolean correct) {
        if (correct) {
            return 5;
        }
        return 0;
    }

    @Override
    public String getName() {
        return "Cinco Pontos por Acerto";
    }
}
