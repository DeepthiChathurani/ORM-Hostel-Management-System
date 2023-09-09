package lk.ijse.gdse.hostel_management_system.dao.custom;

import lk.ijse.gdse.hostel_management_system.projection.StudentDetailsDTO;

import java.util.List;

public interface QueryDAO extends StudentDAO{
    List<StudentDetailsDTO> getAllStudentProjection();
}
