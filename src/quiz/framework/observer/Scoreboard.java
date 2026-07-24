package quiz.framework.observer;

import java.util.ArrayList;
import java.util.List;

// Observador concreto: acompanha vários jogadores e apura o vencedor
public class Scoreboard implements ObservadorQuiz {

    private List<Jogador> players;
    private String boardName;

    public Scoreboard(String boardName) {
        this.boardName = boardName;
        this.players = new ArrayList<>();
    }

    public void addPlayer(Jogador player) {
        if (!players.contains(player)) {
            players.add(player);
            player.addObserver(this);
        }
    }

    public void removePlayer(Jogador player) {
        players.remove(player);
        player.removeObserver(this);
    }

    @Override
    public void update(Jogador player) {
        // placar é recalculado sob demanda a partir de 'players', nada a fazer aqui
    }

    // Monta o placar como texto, para ser exibido pela Exibicao da aplicação (sem depender de console)
    public String render() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n").append("=".repeat(80)).append("\n");
        sb.append("PLACAR: ").append(boardName).append("\n");
        sb.append("=".repeat(80)).append("\n");

        if (players.isEmpty()) {
            sb.append("Nenhum jogador registrado.\n");
            return sb.toString();
        }

        // ordena por pontuação, do maior pro menor
        List<Jogador> sortedPlayers = new ArrayList<>(players);
        sortedPlayers.sort((p1, p2) -> Integer.compare(p2.getScore(), p1.getScore()));

        int position = 1;
        for (Jogador player : sortedPlayers) {
            sb.append(position).append(". ").append(player).append("\n");
            position++;
        }

        sb.append("=".repeat(80)).append("\n");
        return sb.toString();
    }

    public Jogador getWinner() {
        if (players.isEmpty()) {
            return null;
        }

        Jogador winner = players.get(0);
        for (Jogador player : players) {
            if (player.getScore() > winner.getScore()) {
                winner = player;
            }
        }
        return winner;
    }

    public int getPlayerCount() {
        return players.size();
    }

    public List<Jogador> getPlayers() {
        return new ArrayList<>(players);
    }
}
