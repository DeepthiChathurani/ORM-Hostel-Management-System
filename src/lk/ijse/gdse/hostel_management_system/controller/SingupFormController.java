package lk.ijse.gdse.hostel_management_system.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import lk.ijse.gdse.hostel_management_system.bo.BOFactory;
import lk.ijse.gdse.hostel_management_system.bo.custom.UserBO;
import lk.ijse.gdse.hostel_management_system.dto.UserDTO;
import lk.ijse.gdse.hostel_management_system.util.Navigation;
import lk.ijse.gdse.hostel_management_system.util.Routes;

import java.io.IOException;
import java.util.List;

public class SingupFormController {
    public AnchorPane anc;
    public TextField txtUserName;
    public TextField txtPassword;
    public TextField txtSingId;
    public Label lblUserName;
    public Label lblPassword;
    public Button btnSign;

    UserBO userBO= (UserBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.USERBO);



    public void btnSign(ActionEvent actionEvent) throws Exception {
        String id=txtSingId.getText();
        String userName=txtUserName.getText();
        String password=txtPassword.getText();

        if(!id.matches("[S000-9]{4}")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Id").show();
            txtSingId.requestFocus();
        }else if (!userName.matches(".*[A-Za-z]{4,}")){
            new Alert(Alert.AlertType.ERROR, "Invalid UserName").show();
            txtUserName.requestFocus();
            txtUserName.setStyle("-fx-border-color: #B22222");
            lblUserName.setText("invalid name");
            return;
        }else if (!password.matches("^([A-Za-z0-9]){8,}$")){
            new Alert(Alert.AlertType.ERROR, "Invalid Password").show();
            txtPassword.requestFocus();
            txtPassword.setStyle("-fx-border-color: #B22222");
            lblPassword.setText("invalid Password");
            return;
        }


        if (btnSign.getText().equalsIgnoreCase("Sign")){
            userBO.saveUser(new UserDTO(id,userName,password));
            new Alert(Alert.AlertType.INFORMATION, "Your login").show();

        }
    }

    public void btnShow(ActionEvent actionEvent) {
        String password=txtPassword.getText();
        new Alert(Alert.AlertType.INFORMATION,"Password is:" +password).show();
       // lblPassword.setText(password);
    }

    public void btnLogin(ActionEvent actionEvent) throws IOException {
        Navigation.navigation(Routes.LOGIN,anc);
    }

}
