package lk.ijse.gdse.hostel_management_system.bo.custom;

import lk.ijse.gdse.hostel_management_system.bo.SuperBO;
import lk.ijse.gdse.hostel_management_system.dto.RoomDTO;

import java.util.List;


public interface RoomBO extends SuperBO {

    List<RoomDTO> getAllRoom() throws Exception;

    String saveRoom(RoomDTO roomDTO) throws Exception;

    boolean updateRoom(RoomDTO roomDTO) throws Exception;

    boolean deleteRoom(String roomDTO) throws Exception;

    boolean deleteRoom(RoomDTO roomDTO) throws Exception;

    String generateRoomId() throws Exception;;


}
