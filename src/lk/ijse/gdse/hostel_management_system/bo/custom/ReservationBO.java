package lk.ijse.gdse.hostel_management_system.bo.custom;

import lk.ijse.gdse.hostel_management_system.dto.ReservationDTO;
import lk.ijse.gdse.hostel_management_system.dto.RoomDTO;
import lk.ijse.gdse.hostel_management_system.dto.StudentDTO;
import lk.ijse.gdse.hostel_management_system.projection.StudentDetailsDTO;

import java.util.List;

public interface ReservationBO extends StudentBO{
    List<ReservationDTO> getAllReservation() throws Exception;

    String saveReservation(ReservationDTO reservationDTO) throws Exception;

    boolean updateReservation(ReservationDTO reservationDTO) throws Exception;

    boolean deleteReservation(String reservationDTO) throws Exception;

    boolean deleteReservation(ReservationDTO reservationDTO) throws Exception;

    String generateReservationId() throws Exception;;

    StudentDTO getStudents(String id) throws Exception;

    RoomDTO getRooms(String id) throws Exception;

    List<String> getStudentIds();

    List<String> getRoomIds();

    List<StudentDTO> geAllStudents() throws Exception;

    List<RoomDTO>getAllRooms() throws Exception;

    boolean updateRoomQty(RoomDTO roomDTO) throws Exception;

    List<StudentDetailsDTO>getAllProjection();

    boolean checkStatusClick(String id,String status);

}
