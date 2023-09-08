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

public class UserFormController {
    public AnchorPane anc;
    public TextField txtId;
    public TextField txtName;
    public TextField txtPassword;
    public TextField txtContactNo;
    public TableView tblUser;
    public TableColumn colId;
    public TableColumn ColName;
    public TableColumn colPassword;
    public TableColumn colContactNo;

    public void btnAdd(ActionEvent actionEvent) {
    }

    public void btnDelete(ActionEvent actionEvent) {
    }

    public void btnupdate(ActionEvent actionEvent) {
    }

    public void btnHome(MouseEvent mouseEvent) throws IOException {
        Navigation.navigation(Routes.Home,anc);
    }
}
