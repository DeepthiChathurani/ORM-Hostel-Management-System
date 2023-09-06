package lk.ijse.gdse.hotrl_management_system.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.hotrl_management_system.util.Navigation;
import lk.ijse.gdse.hotrl_management_system.util.Routes;

import java.io.IOException;
import java.util.List;

public class LoginFormController {
    public AnchorPane ancLogin;
    public TextField txtPassword;
    public TextField txtUserName;

    public void btnLogin(ActionEvent actionEvent) {

    }

    public void btnSing(ActionEvent actionEvent) throws IOException {
        Navigation.navigation(Routes.SINGUP,ancLogin);
    }

    public void btnshow(ActionEvent actionEvent) {
        String password=txtPassword.getText();
        new Alert(Alert.AlertType.INFORMATION,"Password is:" +password).show();
    }
}
