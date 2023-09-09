package lk.ijse.gdse.hostel_management_system.dao.custom;

import lk.ijse.gdse.hostel_management_system.dao.CrudDAO;
import lk.ijse.gdse.hostel_management_system.entity.Users;
import org.hibernate.Session;

public interface UserDAO extends CrudDAO<Users,String> {
    void setSession(Session session);
}
