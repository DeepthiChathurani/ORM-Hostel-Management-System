package lk.ijse.gdse.hostel_management_system.bo.custom.impl;

import lk.ijse.gdse.hostel_management_system.bo.custom.StudentBO;
import lk.ijse.gdse.hostel_management_system.dao.DAOFactory;
import lk.ijse.gdse.hostel_management_system.dao.custom.StudentDAO;
import lk.ijse.gdse.hostel_management_system.dto.StudentDTO;
import lk.ijse.gdse.hostel_management_system.entity.Student;
import lk.ijse.gdse.hostel_management_system.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {
    StudentDAO studentDAO= (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENTDAO);
    private Session session;
    @Override
    public List<StudentDTO> getAllStudent() throws Exception {
        session= SessionFactoryConfiguration.getInstance().getSession();
        studentDAO.setSession(session);
        List<Student>allStudent=studentDAO.getAll();
        List<StudentDTO>studentDTOList=new ArrayList<>();
        for (Student student :allStudent){
            studentDTOList.add(new StudentDTO(student.getStdId(),student.getName(),student.getAddress(),
                    student.getContact_no(), LocalDate.parse(String.valueOf(student.getDob())),student.getGender()));
        }
        return studentDTOList;
    }

    @Override
    public String saveStudent(StudentDTO studentDTO) throws Exception {
        session=SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();

        try{
            studentDAO.setSession(session);
            String id=(String) studentDAO.save(new Student(studentDTO.getStudentId(),studentDTO.getName(),
                    studentDTO.getAddress(),studentDTO.getContact(),studentDTO.getDob(),studentDTO.getGender()));
            transaction.commit();
            session.close();
            return id;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return "S00-001";
        }

    }

    @Override
    public boolean updateStudent(StudentDTO studentDTO) throws Exception {
        session=SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();
        try {
            studentDAO.setSession(session);
            studentDAO.update(new Student(studentDTO.getStudentId(),studentDTO.getName(),studentDTO.getAddress(),
                    studentDTO.getContact(),studentDTO.getDob(),studentDTO.getGender()));
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            return false;
        }
    }

    @Override
    public boolean deleteStudent(StudentDTO studentDTO) throws Exception {
        session=SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();
        try {
            studentDAO.setSession(session);
            studentDAO.delete(new Student(studentDTO.getStudentId(),studentDTO.getName(),studentDTO.getAddress(),
                    studentDTO.getContact(),studentDTO.getDob(),studentDTO.getGender()));
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            return false;
        }

    }

    @Override
    public String generateStudentId() throws Exception {
        session=SessionFactoryConfiguration.getInstance().getSession();
        studentDAO.setSession(session);
        return studentDAO.generateNewID();
    }
}
