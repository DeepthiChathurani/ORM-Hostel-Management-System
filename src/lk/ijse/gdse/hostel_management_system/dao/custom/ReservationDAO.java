package lk.ijse.gdse.hostel_management_system.dao.custom;

import lk.ijse.gdse.hostel_management_system.dao.CrudDAO;
import lk.ijse.gdse.hostel_management_system.entity.Reservation;
import org.hibernate.Session;


public interface ReservationDAO extends CrudDAO<Reservation,String> {

    boolean changeCheckBOXValue(String id, String status);

    void setSession(Session session);
}
