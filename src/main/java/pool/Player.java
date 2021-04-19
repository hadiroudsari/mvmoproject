package pool;

public class Player {
  private String serial;
  private String name;
  private String attackTime;
    public Player(String serial, String name) {
        this.serial = serial;
        this.name = name;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAttackTime() {
        return attackTime;
    }

    public void setAttackTime(String attackTime) {
        this.attackTime = attackTime;
    }
}
