package lk.ijse.gdse.hostel_management_system.bo.custom.impl;

import lk.ijse.gdse.hostel_management_system.bo.custom.ReservationBO;
import lk.ijse.gdse.hostel_management_system.dao.DAOFactory;
import lk.ijse.gdse.hostel_management_system.dao.custom.QueryDAO;
import lk.ijse.gdse.hostel_management_system.dao.custom.ReservationDAO;
import lk.ijse.gdse.hostel_management_system.dao.custom.RoomDAO;
import lk.ijse.gdse.hostel_management_system.dao.custom.StudentDAO;
import lk.ijse.gdse.hostel_management_system.dto.ReservationDTO;
import lk.ijse.gdse.hostel_management_system.dto.RoomDTO;
import lk.ijse.gdse.hostel_management_system.dto.StudentDTO;
import lk.ijse.gdse.hostel_management_system.entity.Reservation;
import lk.ijse.gdse.hostel_management_system.entity.Room;
import lk.ijse.gdse.hostel_management_system.entity.Student;
import lk.ijse.gdse.hostel_management_system.projection.StudentDetailsDTO;
import lk.ijse.gdse.hostel_management_system.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static lk.ijse.gdse.hostel_management_system.util.SessionFactoryConfiguration.getInstance;

public  class ReservationBOImpl implements ReservationBO {
    ReservationDAO reservationDAO= (ReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RESRVATIONDAO);
    StudentDAO studentDAO= (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENTDAO);
    RoomDAO roomDAO= (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOMDAO);
    QueryDAO queryDAO= (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERY);
    private Session session;
    @Override
    public List<ReservationDTO> getAllReservation() throws Exception {
        session= getInstance().getSession();
        reservationDAO.setSession(session);
        List<Reservation>reservations= reservationDAO.getAll();
        List<ReservationDTO>reservationDTOS=new ArrayList<>();
        for (Reservation reservation:reservations) {
            reservationDTOS.add(
                    new ReservationDTO(
                            reservation.getId(),
                            (Date) reservation.getDate(),
                            reservation.getStatus(),
                            new StudentDTO(
                                    reservation.getStudent().getStudentId(),
                                    reservation.getStudent().getName(),
                                    reservation.getStudent().getAddress(),
                                    reservation.getStudent().getContact(),
                                    reservation.getStudent().getDob(),
                                    reservation.getStudent().getGender()
                            ),
                            new RoomDTO(
                                    reservation.getRoom().getRoomTypeId(),
                                    reservation.getRoom().getType(),
                                    reservation.getRoom().getKeyMoney(),
                                    reservation.getRoom().getQty())));
        }
        return  reservationDTOS;
    }

    @Override
    public String saveReservation(ReservationDTO reservationDTO)  {
        session= getInstance().getSession();
        Transaction transaction=session.beginTransaction();

        try {
            reservationDAO.setSession(session);
            reservationDAO.save(new Reservation(
                            reservationDTO.getResId(),
                            reservationDTO.getDate(),
                            reservationDTO.getStatus(),
                            new Student(
                                    reservationDTO.getStudentDTO().getStudentId(),
                                    reservationDTO.getStudentDTO().getName(),
                                    reservationDTO.getStudentDTO().getAddress(),
                                    reservationDTO.getStudentDTO().getContact(),
                                    reservationDTO.getStudentDTO().getDob(),
                                    reservationDTO.getStudentDTO().getGender()
                            ),
                            new Room(
                                    reservationDTO.getRoomDTO().getRoomTypeId(),
                                    reservationDTO.getRoomDTO().getType(),
                                    reservationDTO.getRoomDTO().getKeyMoney(),
                                    reservationDTO.getRoomDTO().getQty()
                            )
                    )
            );
            transaction.commit();
            session.close();
            return String.valueOf(true);
        }catch (Exception e){
            transaction.rollback();
            session.close();
            return null;
        }

    }

    @Override
    public boolean updateReservation(ReservationDTO reservationDTO){
        session= getInstance().getSession();
        Transaction transaction=session.beginTransaction();
        try {
            reservationDAO.setSession(session);
            reservationDAO.update(new Reservation(
                            reservationDTO.getResId(),
                            reservationDTO.getDate(),
                            reservationDTO.getStatus(),
                            new Student(
                                    reservationDTO.getStudentDTO().getStudentId(),
                                    reservationDTO.getStudentDTO().getName(),
                                    reservationDTO.getStudentDTO().getAddress(),
                                    reservationDTO.getStudentDTO().getContact(),
                                    reservationDTO.getStudentDTO().getDob(),
                                    reservationDTO.getStudentDTO().getGender()
                            ),
                            new Room(
                                    reservationDTO.getRoomDTO().getRoomTypeId(),
                                    reservationDTO.getRoomDTO().getType(),
                                    reservationDTO.getRoomDTO().getKeyMoney(),
                                    reservationDTO.getRoomDTO().getQty()
                            )
                    )
            );
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            return false;
        }
    }

    @Override
    public boolean deleteReservation(String reservationDTO){
        return false;
    }

    @Override
    public boolean deleteReservation(ReservationDTO reservationDTO) {
        session= getInstance().getSession();
        Transaction transaction=session.beginTransaction();
        try {
            reservationDAO.setSession(session);
            reservationDAO.delete(new Reservation(reservationDTO.getResId(),reservationDTO.getDate(),
                    reservationDTO.getStatus()));
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
    public String generateReservationId() throws Exception {
        session= getInstance().getSession();
        reservationDAO.setSession(session);
        return reservationDAO.generateNewID();
    }

    @Override
    public StudentDTO getStudents(String id){
        try {
            session= getInstance().getSession();
            Transaction transaction=session.beginTransaction();
            studentDAO.setSession(session);
            Student student=studentDAO.getObject(id);
            transaction.commit();
            session.close();
            return new StudentDTO(student.getStdId(),student.getName(),student.getAddress(),student.getContact_no(),
                    LocalDate.parse(String.valueOf(student.getDob())),student.getGender());
        }catch (Exception e){
            session.close();
            return null;
        }

    }

    @Override
    public RoomDTO getRooms(String id){
        try {
            session= getInstance().getSession();
            Transaction transaction=session.beginTransaction();
            roomDAO.setSession(session);
            Room room=roomDAO.getObject(id);
            transaction.commit();
            session.close();
            return new RoomDTO(room.getrId(),room.getType(),room.getKeyMoney(),room.getQty());
        }catch (Exception e){
            session.close();
            return null;
        }

    }

    @Override
    public List<String> getStudentIds() {
        try {
            session= getInstance().getSession();
            studentDAO.setSession(session);
            session.close();
            return studentDAO.geIds();
        }catch (Exception e){
            session.close();
            return null;
        }

    }

    @Override
    public List<String> getRoomIds() {
        try {
            session= getInstance().getSession();
            roomDAO.setSession(session);
            return roomDAO.geIds();
        }catch (Exception e){
            session.close();
            return null;
        }
    }

    @Override
    public List<StudentDTO> geAllStudents() throws Exception {
        session= getInstance().getSession();
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
    public List<RoomDTO> getAllRooms() throws Exception {
        session= getInstance().getSession();
        roomDAO.setSession(session);
        List<Room>allRooms=roomDAO.getAll();
        List<RoomDTO>roomDTOS=new ArrayList<>();
        for (Room room : allRooms){
            roomDTOS.add(new RoomDTO(room.getrId(),room.getType(),room.getKeyMoney(),room.getQty()));
        }
        return roomDTOS;
    }

    @Override
    public boolean updateRoomQty(RoomDTO roomDTO){
        session= getInstance().getSession();
        Transaction transaction=session.beginTransaction();
        try {
            roomDAO.setSession(session);
            roomDAO.update(new Room(roomDTO.getRoomTypeId(),roomDTO.getType(),roomDTO.getKeyMoney(),
                    roomDTO.getQty()));
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
    public List<StudentDetailsDTO> getAllProjection() {
        session= getInstance().getSession();
        return queryDAO.getAllStudentProjection();
    }

    @Override
    public boolean checkStatusClick(String id, String status) {
        session= getInstance().getSession();
        Transaction transaction=session.beginTransaction();
        boolean isUpdate=false;
        try {
            reservationDAO.setSession(session);
            isUpdate=reservationDAO.changeCheckBOXValue(id,status);
            transaction.commit();
            session.close();
        }catch (Exception e){
            transaction.rollback();
            session.close();
        }
        return isUpdate;
    }

    @Override
    public List<StudentDTO> getAllStudent(){
        return null;
    }

    @Override
    public String saveStudent(StudentDTO studentDTO){
        return null;
    }

    @Override
    public boolean updateStudent(StudentDTO studentDTO){
        return false;
    }

    @Override
    public boolean deleteStudent(StudentDTO studentDTO){
        return false;
    }

    @Override
    public String generateStudentId(){
        return null;
    }
}
