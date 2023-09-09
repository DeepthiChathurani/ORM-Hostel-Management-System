package lk.ijse.gdse.hostel_management_system.bo.custom;

import lk.ijse.gdse.hostel_management_system.bo.SuperBO;
import lk.ijse.gdse.hostel_management_system.dto.UserDTO;

import java.util.List;

public interface UserBO extends SuperBO {
    List<UserDTO> getAllUser() throws Exception;

    String saveUser(UserDTO userDTO) throws Exception;

    boolean updateUser(UserDTO userDTO) throws Exception;

    boolean deleteUser(String userDTO) throws Exception;

    boolean deleteUser(UserDTO userDTO) throws Exception;

    String generateUserId() throws Exception;;
}
