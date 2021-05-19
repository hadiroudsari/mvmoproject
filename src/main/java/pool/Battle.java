package pool;

public class Battle {

  private String battleId;
  private Player player1;
  private Player player2;
  private Player winner;
  private static int idCounter=1;
  private boolean busy;
  private String BattleTime;
  public Battle(Player player1, Player player2) {
    this.battleId= String.valueOf(idCounter++);
    this.player1 = player1;
    this.player2 = player2;
  }

  public Player getPlayer1() {
    return player1;
  }

  public void setPlayer1(Player player1) {
    this.player1 = player1;
  }

  public Player getPlayer2() {
    return player2;
  }

  public void setPlayer2(Player player2) {
    this.player2 = player2;
  }

  public String getBattleId() {
    return battleId;
  }

  public void setBattleId(String battleId) {
    this.battleId = battleId;
  }

  public boolean isBusy() {
    return busy;
  }

  public void setBusy(boolean busy) {
    this.busy = busy;
  }

  public Player getWinner() {
    return winner;
  }

  public void setWinner(Player winner) {
    this.winner = winner;
  }

  public String getBattleTime() {
    return BattleTime;
  }

  public void setBattleTime(String battleTime) {
    BattleTime = battleTime;
  }

  public boolean isPlayerValid(String id) {
    if(this.player1.getSerial().equals(id) || this.player2.getSerial().equals(id))
      return true;
    else
      return false;
  }

  public Player getOpponent(String id){
    if(player1.getSerial().equals(id))
      return player2;
    else
      return player1;
  }

  public Player findPlayerById(String id){
    if(player1.getSerial().equals(id))
      return player1;
    else if(player2.getSerial().equals(id))
      return player2;

    return null;
  }

  public synchronized void winnerCheck() throws CloneNotSupportedException {
    System.out.println("in winner check");
    System.out.println(this.player1.getAttackTime());
    System.out.println(this.player2.getAttackTime());
    if(this.player1.getAttackTime()!=null && this.player2.getAttackTime()!=null){
      if (Long.parseLong(this.player1.getAttackTime())< Long.parseLong(this.player2.getAttackTime())){
        this.winner= (Player) player1.clone();
      }else
        this.winner= (Player) player2.clone();
    }
  }
}
