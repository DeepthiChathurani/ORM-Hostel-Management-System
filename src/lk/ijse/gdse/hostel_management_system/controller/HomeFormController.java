package lk.ijse.gdse.hostel_management_system.controller;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.hostel_management_system.util.Navigation;
import lk.ijse.gdse.hostel_management_system.util.Routes;

import java.io.IOException;

public class HomeFormController {

    public AnchorPane anc;

    public void btnStudent(MouseEvent mouseEvent) throws IOException {
        Navigation.navigation(Routes.STUDENT,anc);
    }

    public void btnRoom(MouseEvent mouseEvent) throws IOException {
        Navigation.navigation(Routes.ROOM,anc);
    }

    public void btnReservation(MouseEvent mouseEvent) throws IOException {
        Navigation.navigation(Routes.RESERVATION,anc);
    }

    public void btnUser(MouseEvent mouseEvent) throws IOException {
        Navigation.navigation(Routes.USER,anc);
    }

    public void btnLogOut(MouseEvent mouseEvent) throws IOException {
        Navigation.navigation(Routes.LOGIN,anc);
    }
}
