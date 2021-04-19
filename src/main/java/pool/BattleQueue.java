package pool;

import java.util.LinkedList;
import java.util.Queue;

public class BattleQueue {
    private BattleQueue() {
    }

    private static BattleQueue battleQueue=new BattleQueue();

    private Queue<Battle> battleLinkedList=new LinkedList<>();

    public static BattleQueue getInstance() {
        return battleQueue;
    }

    public Battle isPlayerAvailable(String id){

        for(Battle b: battleLinkedList){
           if( b.isPlayerValid(id) == true){
               return b;
           }
        }
        return null;
    }

    public synchronized void addBattle(Battle battle) {
        System.out.println("Adding battle");
        battleLinkedList.add(battle);
        System.out.println(battleLinkedList.size());
    }

    public synchronized void removeBattle(Battle battle){
        if(battle.getPlayer1().getName().equals("final") && battle.getPlayer2().getName().equals("final")){
            System.out.println("removing battle...");
            battleLinkedList.remove(battle);
        }
    }
}
