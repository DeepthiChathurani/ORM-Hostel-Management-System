package lk.ijse.gdse.hostel_management_system.entity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "reservation")
public class Reservation {

    @Id
    @Column(name = "res_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column(name = "date")
    private Date date;
    @Column(name = "student_id")
    private String stdId;
    @Column(name = "room_type_id")
    private String roomTypeId;
    @Column(name = "status")
    private String status;

    public Reservation() {
    }
    public Reservation(String id, Date date, String stdId, String roomTypeId, String status) {
        this.id = id;
        this.date = date;
        this.stdId = stdId;
        this.roomTypeId = roomTypeId;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStdId() {
        return stdId;
    }

    public void setStdId(String stdId) {
        this.stdId = stdId;
    }

    public String getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(String roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
