package lk.ijse.gdse.hostel_management_system.dao;

import java.util.List;

public interface CrudDAO<T,ID> {
    List<T> getAll() throws Exception;
    ID save(T dto) throws Exception;
    void update(T dto) throws Exception;
    void delete(T dto) throws Exception;
    ID generateNewID() throws Exception;
    T getObject(ID id) throws Exception;
}
