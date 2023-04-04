package lk.ijse.gdse.entity;

public class Room {

    private String rId;
    private String type;
    private String keyMoney;
    private int qty;

    public Room() {
    }

    public Room(String rId, String type, String keyMoney, int qty) {
        this.rId = rId;
        this.type = type;
        this.keyMoney = keyMoney;
        this.qty = qty;
    }

    public String getrId() {
        return rId;
    }

    public void setrId(String rId) {
        this.rId = rId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKeyMoney() {
        return keyMoney;
    }

    public void setKeyMoney(String keyMoney) {
        this.keyMoney = keyMoney;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
