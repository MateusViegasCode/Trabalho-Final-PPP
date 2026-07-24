package quiz.framework.strategy;

// +10 pontos por acerto, -5 por erro (nunca fica negativa)
public class EstrategiaPenalizacao implements EstrategiaPontuacao {

    private static final int PONTOS_ACERTO = 10;
    private static final int PENALIDADE_ERRO = 5;

    @Override
    public int calculate(int currentScore, boolean correct) {
        if (correct) {
            return PONTOS_ACERTO;
        }

        int penalidade = Math.min(PENALIDADE_ERRO, currentScore);
        return -penalidade;
    }

    @Override
    public String getName() {
        return "Penalização por Erro";
    }
}
