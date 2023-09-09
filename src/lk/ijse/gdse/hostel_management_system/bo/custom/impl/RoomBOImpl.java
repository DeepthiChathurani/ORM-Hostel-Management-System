package lk.ijse.gdse.hostel_management_system.bo.custom.impl;

import lk.ijse.gdse.hostel_management_system.bo.custom.RoomBO;
import lk.ijse.gdse.hostel_management_system.dao.DAOFactory;
import lk.ijse.gdse.hostel_management_system.dao.custom.RoomDAO;
import lk.ijse.gdse.hostel_management_system.dto.RoomDTO;
import lk.ijse.gdse.hostel_management_system.entity.Room;
import lk.ijse.gdse.hostel_management_system.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class RoomBOImpl implements RoomBO {
    RoomDAO roomDAO= (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOMDAO);
    private Session session;
    @Override
    public List<RoomDTO> getAllRoom() throws Exception {
        session= SessionFactoryConfiguration.getInstance().getSession();
        roomDAO.setSession(session);
        List<Room>allRooms=roomDAO.getAll();
        List<RoomDTO>roomDTOS=new ArrayList<>();
        for (Room room : allRooms){
            roomDTOS.add(new RoomDTO(room.getrId(),room.getType(),room.getKeyMoney(),room.getQty()));
        }
        return roomDTOS;
    }

    @Override
    public String saveRoom(RoomDTO roomDTO) throws Exception {
        session= SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();

        try{
            roomDAO.setSession(session);
            String id=(String) roomDAO.save(new Room(roomDTO.getRoomTypeId(),roomDTO.getType(),roomDTO.getKeyMoney(),roomDTO.getQty()));
            transaction.commit();
            session.close();
            return id;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return "RES-001";
        }

    }

    @Override
    public boolean updateRoom(RoomDTO roomDTO) throws Exception {
        session= SessionFactoryConfiguration.getInstance().getSession();
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
    public boolean deleteRoom(RoomDTO roomDTO) throws Exception {
        session= SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();
        try {
            roomDAO.setSession(session);
            roomDAO.delete(new Room(roomDTO.getRoomTypeId(),roomDTO.getType(),roomDTO.getKeyMoney(),
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
public String generateRoomId() throws Exception {
        session= SessionFactoryConfiguration.getInstance().getSession();
        roomDAO.setSession(session);
        return roomDAO.generateNewID();
        }
    @Override
    public boolean deleteRoom(String roomDTO) throws Exception {
        return false;
    }
}
