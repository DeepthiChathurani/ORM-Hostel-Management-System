package lk.ijse.gdse.hostel_management_system.dao.custom;

import lk.ijse.gdse.hostel_management_system.dao.CrudDAO;
import lk.ijse.gdse.hostel_management_system.entity.Room;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public interface RoomDAO extends CrudDAO<Room,String> {
    List<Room> getAll() throws SQLException, ClassNotFoundException;

    String save(Room dto) throws SQLException, ClassNotFoundException;

    void update(Room dto) throws SQLException, ClassNotFoundException;

    void delete(Room dto) throws SQLException, ClassNotFoundException;

    String generateNewID() throws SQLException, ClassNotFoundException;

    Room getObject(String s) throws SQLException, ClassNotFoundException;

    void setSession(Session session);

    List<String> geIds() throws Exception;
}
