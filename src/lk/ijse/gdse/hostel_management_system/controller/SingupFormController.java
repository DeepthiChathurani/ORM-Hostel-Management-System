package lk.ijse.gdse.hostel_management_system.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class SingupFormController {
    public AnchorPane anc;
    public TextField txtUserName;
    public TextField txtPassword;
    public TextField txtSingId;
    public Label lblUserName;
    public Label lblPassword;

    public void btnSing(ActionEvent actionEvent) {

    }

    public void btnShow(ActionEvent actionEvent) {
        String password=txtPassword.getText();
        new Alert(Alert.AlertType.INFORMATION,"Password is:" +password).show();
       // lblPassword.setText(password);
    }
}
