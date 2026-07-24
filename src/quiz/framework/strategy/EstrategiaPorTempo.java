package quiz.framework.strategy;

// Pontos decrescentes conforme o tempo de resposta (máx. 30s por pergunta):
// <10s: 10pts | 10-20s: 5pts | 20-30s: 2pts | >30s: 1pt | erro: 0pts
public class EstrategiaPorTempo implements EstrategiaPontuacao {

    private long questionStartTime;
    private static final long TIME_10_SECONDS = 10000;
    private static final long TIME_20_SECONDS = 20000;
    private static final long TIME_30_SECONDS = 30000;

    public EstrategiaPorTempo() {
        resetTimer();
    }

    @Override
    public int calculate(int currentScore, boolean correct) {
        if (questionStartTime == 0) {
            resetTimer();
        }

        if (!correct) {
            resetTimer();
            return 0;
        }

        long elapsedTime = System.currentTimeMillis() - questionStartTime;
        int points = calculatePointsByTime(elapsedTime);

        resetTimer();
        return points;
    }

    private int calculatePointsByTime(long elapsedTime) {
        if (elapsedTime < TIME_10_SECONDS) {
            return 10;
        } else if (elapsedTime < TIME_20_SECONDS) {
            return 5;
        } else if (elapsedTime < TIME_30_SECONDS) {
            return 2;
        } else {
            return 1;
        }
    }

    public void resetTimer() {
        this.questionStartTime = System.currentTimeMillis();
    }

    @Override
    public String getName() {
        return "Pontuação Baseada em Tempo";
    }
}
