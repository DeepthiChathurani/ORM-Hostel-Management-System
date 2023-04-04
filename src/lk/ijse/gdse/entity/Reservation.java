package lk.ijse.gdse.entity;

import java.util.Date;

public class Reservation {

    private String id;
    private Date date;
    private String stdId;
    private String roomTypeId;
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
