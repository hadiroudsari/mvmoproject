package rs;

public class Common {

    private String status;
    private String error;
    private String opponent;
    private String battleId;

    public Common(String status, String error, String opponent, String battleId) {
        this.status = status;
        this.error = error;
        this.opponent = opponent;
        this.battleId = battleId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getOpponent() {
        return opponent;
    }

    public void setOpponent(String opponent) {
        this.opponent = opponent;
    }

    public String getBattleId() {
        return battleId;
    }

    public void setBattleId(String battleId) {
        this.battleId = battleId;
    }
}
