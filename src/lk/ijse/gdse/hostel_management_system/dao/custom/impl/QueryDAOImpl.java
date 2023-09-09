package lk.ijse.gdse.hostel_management_system.dao.custom.impl;


import lk.ijse.gdse.hostel_management_system.dao.custom.QueryDAO;
import lk.ijse.gdse.hostel_management_system.entity.Student;
import lk.ijse.gdse.hostel_management_system.projection.StudentDetailsDTO;
import lk.ijse.gdse.hostel_management_system.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {

    private Session session;
    @Override
    public List<StudentDetailsDTO> getAllStudentProjection() {
        session= SessionFactoryConfiguration.getInstance().getSession();
        String sql="SELECT new lk.ijse.gdse.hostel_management_system.projection.StudentDetailsDTO(s.id,s.name,s.contact,r.date,r.id,r.room) FROM Student s INNER join s.reservationList r WHERE r.status='unPaid'";

        Query query=session.createQuery(sql);
        List<StudentDetailsDTO>list=query.list();
        session.close();
        return list;

    }

    @Override
    public List<Student> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String save(Student dto) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public void update(Student dto) throws SQLException, ClassNotFoundException {

    }

    @Override
    public void delete(Student dto) throws SQLException, ClassNotFoundException {

    }

    @Override
    public String generateNewID() throws Exception {
        return null;
    }

    @Override
    public Student getObject(String s) throws Exception {
        return null;
    }

    @Override
    public void setSession(Session session) {

    }

    @Override
    public List<String> geIds() throws Exception {
        return null;
    }
}
