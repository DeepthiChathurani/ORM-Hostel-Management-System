package lk.ijse.gdse.hostel_management_system.entity;

import lk.ijse.gdse.hostel_management_system.dto.RoomDTO;
import lk.ijse.gdse.hostel_management_system.dto.StudentDTO;

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

    @ManyToOne
    @Column(name = "student_id")
    private Student stdId;
    @ManyToOne
    @Column(name = "room_type_id")
    private Room roomTypeId;
    @Column(name = "status")
    private String status;



    @JoinColumn(name = "studentId")
    private Student student;


    public Reservation() {
    }
    public Reservation(String id, Date date, Student stdId, Room roomTypeId, String status) {
        this.id = id;
        this.date = date;
        this.stdId = stdId;
        this.roomTypeId = roomTypeId;
        this.status = status;
    }

    public Reservation(String resId, java.sql.Date date, String status, Student student, Room room) {
    }

    public Reservation(String resId, java.sql.Date date, String status) {
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

    public Student getStdId() {
        return stdId;
    }

    public void setStdId(Student stdId) {
        this.stdId = stdId;
    }

    public Room getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Room roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public RoomDTO getRoom() {
        return null;
    }

    public StudentDTO getStudent() {
        return null;
    }
}
