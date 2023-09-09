package lk.ijse.gdse.hostel_management_system.bo.custom;

import lk.ijse.gdse.hostel_management_system.bo.SuperBO;
import lk.ijse.gdse.hostel_management_system.dto.StudentDTO;

import java.util.List;

public interface StudentBO extends SuperBO {

    List<StudentDTO> getAllStudent() throws Exception;

    String saveStudent(StudentDTO studentDTO) throws Exception;

    boolean updateStudent(StudentDTO studentDTO) throws Exception;

    static boolean deleteStudent(String studentDTO) throws Exception {
        return false;
    }

    boolean deleteStudent(StudentDTO studentDTO) throws Exception;

    String generateStudentId() throws Exception;
}
