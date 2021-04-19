package rs;

import pool.BattleQueue;
import pool.Player;
import pool.PlayerQueue;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("/gate")
public class Gate {
    @GET
    @Produces("text/html")
    @Path("start/{name}/{serial}")
    public String start(@PathParam("name") String name,@PathParam("serial") String serial) {
        System.out.println(serial);
        PlayerQueue.getInstance().addPlayer(new Player(serial,name));
        return "Hello,"+ name+" with a serial "+serial;
    }

    @GET
    @Produces("application/json")
    @Path("pooling/{name}/{serial}")
    public Common pooling(@PathParam("name") String name,@PathParam("serial") String serial) {
        System.out.println(serial);
        Common c;
      if(BattleQueue.getInstance().isPlayerAvailable(serial)!=null){
            String opponent=BattleQueue.getInstance().isPlayerAvailable(serial).getOpponent(serial).getName();
            String battleId=BattleQueue.getInstance().isPlayerAvailable(serial).getBattleId();
           c=new Common("ok","false",opponent,battleId,"");
      }
      else c=new Common("wait","false","null","-1","");
        return c;
    }

    @GET
    @Produces("application/json")
    @Path("attack/{name}/{serial}/{time}")
    public Common attack(@PathParam("name") String name
                        ,@PathParam("serial") String serial
                        ,@PathParam("time") String time) {
        System.out.println(serial);
        BattleQueue.getInstance().isPlayerAvailable(serial).findPlayerById(serial).setAttackTime(time);
        BattleQueue.getInstance().isPlayerAvailable(serial).winnerCheck();
        Common c=new Common("ok","false","","null","");
        return c;
    }

    @GET
    @Produces("application/json")
    @Path("check/{name}/{serial}/{time}")
    public Common check(@PathParam("name") String name
            ,@PathParam("serial") String serial) {
        System.out.println(serial);
        String winner=null;
        if(BattleQueue.getInstance().isPlayerAvailable(serial).getWinner()!=null){
            winner=BattleQueue.getInstance().isPlayerAvailable(serial).getWinner().getName();
            BattleQueue.getInstance().isPlayerAvailable(serial).findPlayerById(serial).setName("final");
        }
        BattleQueue.getInstance().removeBattle(BattleQueue.getInstance().isPlayerAvailable(serial));
        Common c=new Common("ok","false","man","null",winner);
        return c;
    }

}