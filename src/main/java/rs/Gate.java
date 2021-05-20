package rs;

import pool.BattleQueue;
import pool.Player;
import pool.PlayerQueue;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("/gate")
public class Gate {
    @GET
    @Produces("application/json")
    @Path("start/{name}/{serial}/{counter}")
    public Common start(@PathParam("name") String name, @PathParam("serial") String serial,@PathParam("counter") String counter) {
        System.out.println(serial);
        Common c;
        //check if the player is already existto prevent from making new player
        if(Integer.parseInt(counter)>=7 && BattleQueue.getInstance().isPlayerAvailable(serial) == null && PlayerQueue.getInstance().isPlayerAvailable(serial)){
            PlayerQueue.getInstance().removePlayer(serial);
        }
        if (!PlayerQueue.getInstance().isPlayerAvailable(serial) && BattleQueue.getInstance().isPlayerAvailable(serial) == null  && Integer.parseInt(counter)<7) {
            PlayerQueue.getInstance().addPlayer(new Player(serial, name));
            c = new Common("startok", "false", "", "", "", "");
            return c;
        } else if (PlayerQueue.getInstance().isPlayerAvailable(serial) && BattleQueue.getInstance().isPlayerAvailable(serial) == null && Integer.parseInt(counter)<7) {
            c = new Common("alreadystarted", "false", "", "", "", "");
            return c;
        } else if (BattleQueue.getInstance().isPlayerAvailable(serial) != null && Integer.parseInt(counter)<7) {
            String opponent = BattleQueue.getInstance().isPlayerAvailable(serial).getOpponent(serial).getName();
            String battleId = BattleQueue.getInstance().isPlayerAvailable(serial).getBattleId();
            String duelTime = BattleQueue.getInstance().isPlayerAvailable(serial).getBattleTime();
            c = new Common("attack", "false", opponent, battleId, "", duelTime);
            return c;
        } else {
            c = new Common("no", "false", "", "", "", "");
            return c;
        }
    }

    @GET
    @Produces("application/json")
    @Path("pooling/{name}/{serial}")
    public Common pooling(@PathParam("name") String name, @PathParam("serial") String serial) {
        System.out.println(serial);
        Common c;
        if (BattleQueue.getInstance().isPlayerAvailable(serial) != null) {
            String opponent = BattleQueue.getInstance().isPlayerAvailable(serial).getOpponent(serial).getName();
            String battleId = BattleQueue.getInstance().isPlayerAvailable(serial).getBattleId();
            String duelTime = BattleQueue.getInstance().isPlayerAvailable(serial).getBattleTime();
            c = new Common("attack", "false", opponent, battleId, "", duelTime);
        } else c = new Common("wait", "false", "null", "-1", "", "");
        return c;
    }

    @GET
    @Produces("application/json")
    @Path("attack/{name}/{serial}/{time}")
    public Common attack(@PathParam("name") String name
            , @PathParam("serial") String serial
            , @PathParam("time") String time) throws CloneNotSupportedException {
        System.out.println(serial);
        Common c = new Common("", "false", "", "null", "", "");
        if (BattleQueue.getInstance().isPlayerAvailable(serial) != null &&
                BattleQueue.getInstance().isPlayerAvailable(serial).findPlayerById(serial).getAttackTime() == null) {
            BattleQueue.getInstance().isPlayerAvailable(serial).findPlayerById(serial).setAttackTime(time);
            String battleId=BattleQueue.getInstance().isPlayerAvailable(serial).getBattleId();
            if (BattleQueue.getInstance().isPlayerAvailable(serial).getWinner() == null) {
                BattleQueue.getInstance().isPlayerAvailable(serial).winnerCheck();
            }
            c = new Common("ok", "false", "", battleId, "", "");
        } else if (BattleQueue.getInstance().isPlayerAvailable(serial) != null &&
                BattleQueue.getInstance().isPlayerAvailable(serial).findPlayerById(serial).getAttackTime() != null) {
            String battleId=BattleQueue.getInstance().isPlayerAvailable(serial).getBattleId();

            c = new Common("alreadyattacked", "false", "", battleId, "", "");
        } else {
            c = new Common("nobattle", "false", "", "null", "", "");
        }
        return c;
    }

    @GET
    @Produces("application/json")
    @Path("check/{name}/{serial}/{battleId}")
    public Common check(@PathParam("name") String name
            , @PathParam("serial") String serial,@PathParam("battleId") String battleId) throws CloneNotSupportedException {
        System.out.println(serial);
        String winner = null;
        if (BattleQueue.getInstance().isPlayerAvailable(serial) != null && BattleQueue.getInstance().isPlayerAvailable(serial).getWinner() == null) {
            BattleQueue.getInstance().isPlayerAvailable(serial).getOpponent(serial).setAttackTime("99999999999999999");
            BattleQueue.getInstance().isPlayerAvailable(serial).winnerCheck();
        }
        if (BattleQueue.getInstance().isPlayerAvailable(serial).getWinner() != null) {
            winner = BattleQueue.getInstance().isPlayerAvailable(serial).getWinner().getName();
            BattleQueue.getInstance().isPlayerAvailable(serial).findPlayerById(serial).setName("final");
//            BattleQueue.getInstance().removeBattle(BattleQueue.getInstance().isPlayerAvailable(serial));
            BattleQueue.getInstance().isPlayerAvailable(serial).findPlayerById(serial).setSerial("");
            BattleQueue.getInstance().removeBattle(BattleQueue.getInstance().isBattleAvailable(battleId));

        }
//        BattleQueue.getInstance().removeBattle(BattleQueue.getInstance().isPlayerAvailable(serial));
        Common c = new Common("ok", "false", "man", "null", winner, "");
        return c;
    }

}