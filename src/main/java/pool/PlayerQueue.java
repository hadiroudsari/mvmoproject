package pool;

import java.util.LinkedList;
import java.util.Queue;

public class PlayerQueue {

    private PlayerQueue() {
    }

    private static PlayerQueue playerQueue = new PlayerQueue();

    public static PlayerQueue getInstance() {
        return playerQueue;
    }

    private Queue<Player> playerWatingQueue = new LinkedList<>();

    public synchronized void addPlayer(Player player) {
        System.out.println(playerWatingQueue.size());
        if (!isPlayerAvailable(player.getSerial())) {
            System.out.println("Adding player to player queue...");
            playerWatingQueue.add(player);
        }
        System.out.println(playerWatingQueue.size());
        if (playerWatingQueue.size() >= 2) {
            Player player1 = playerWatingQueue.poll();
            Player player2 = playerWatingQueue.poll();
            Battle battle = new Battle(player1, player2);
            battle.setBattleTime((System.currentTimeMillis() + 20000l) + "");
            BattleQueue.getInstance().addBattle(battle);
        }
    }

    public synchronized void removePlayer(String serial) {
        System.out.println(playerWatingQueue.size());
        if (isPlayerAvailable(serial)) {
            System.out.println("removing player from player queue...");
            playerWatingQueue.removeIf(player -> player.getSerial().equals(serial));
        }
        System.out.println(playerWatingQueue.size());
    }

    public boolean isPlayerAvailable(String serial) {
        for (Player p : playerWatingQueue) {
            if (p.getSerial().equals(serial))
                return true;
        }
        return false;
    }


}
