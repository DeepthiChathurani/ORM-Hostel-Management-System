package lk.ijse.gdse.hostel_management_system.dao.custom;

import lk.ijse.gdse.hostel_management_system.dao.CrudDAO;
import lk.ijse.gdse.hostel_management_system.entity.Student;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public interface StudentDAO extends CrudDAO<Student,String> {
    List<Student> getAll() throws SQLException, ClassNotFoundException;

    String save(Student dto) throws SQLException, ClassNotFoundException;

    void update(Student dto) throws SQLException, ClassNotFoundException;

    void delete(Student dto) throws SQLException, ClassNotFoundException;

    String generateNewID() throws Exception;

    Student getObject(String s) throws Exception;

    void setSession(Session session);

    List<String> geIds() throws Exception;
}
