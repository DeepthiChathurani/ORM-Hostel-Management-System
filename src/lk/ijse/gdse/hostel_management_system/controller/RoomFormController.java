package lk.ijse.gdse.hostel_management_system.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.hostel_management_system.util.Navigation;
import lk.ijse.gdse.hostel_management_system.util.Routes;

import java.io.IOException;

public class RoomFormController {
    public AnchorPane anc;
    public TextField txtRId;
    public TextField txtType;
    public TextField txtKeyMoney;
    public TextField txtQty;
    public TableView tblRoom;
    public TableColumn colTypeId;
    public TableColumn ColType;
    public TableColumn colKeyMoney;
    public TableColumn colQty;

    public void btnSave(ActionEvent actionEvent) {
    }

    public void btnDelete(ActionEvent actionEvent) {
    }

    public void btnUpdate(ActionEvent actionEvent) {
    }

    public void btnHome(MouseEvent mouseEvent) throws IOException {
        Navigation.navigation(Routes.Home,anc);
    }
}
