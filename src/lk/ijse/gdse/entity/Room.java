package lk.ijse.gdse.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "room")
public class Room {

    @Id
    @Column(name = "room_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String rId;
    @Column(name = "type")
    private String type;
    @Column(name = "key_money")
    private String keyMoney;
    @Column(name = "qty")
    private int qty;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "room")
    private List<Reservation> reservationList=new ArrayList<>();

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
