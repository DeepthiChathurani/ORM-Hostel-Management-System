package lk.ijse.gdse.hostel_management_system.bo;


import lk.ijse.gdse.hostel_management_system.bo.custom.impl.ReservationBOImpl;
import lk.ijse.gdse.hostel_management_system.bo.custom.impl.RoomBOImpl;
import lk.ijse.gdse.hostel_management_system.bo.custom.impl.StudentBOImpl;
import lk.ijse.gdse.hostel_management_system.bo.custom.impl.UserBOImpl;

public class BOFactory {
    public static BOFactory boFactory;

    private BOFactory(){

    }

    public static BOFactory getBoFactory(){
        if (boFactory==null){
            boFactory=new BOFactory();
        }
        return boFactory;
    }

    public enum  BOTypes{
        RESERVATIONBO,ROOMBO,STUDENTBO,USERBO
    }
    public SuperBO getBo(BOTypes boTypes){
        switch (boTypes){
            case RESERVATIONBO:
                return (SuperBO) new ReservationBOImpl();
            case ROOMBO:
                return (SuperBO) new RoomBOImpl();
            case STUDENTBO:
                return (SuperBO) new StudentBOImpl();
            case USERBO:
                return (SuperBO) new UserBOImpl();
        }
        return null;
    }
}
