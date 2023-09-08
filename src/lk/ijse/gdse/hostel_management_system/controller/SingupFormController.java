package lk.ijse.gdse.hostel_management_system.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
    public Button btnSign;

    public void btnSign(ActionEvent actionEvent) {
        String id=txtSingId.getText();
        String userName=txtUserName.getText();
        String password=txtPassword.getText();
        if(!id.matches("[000-9]{3}")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Id").show();
            txtSingId.requestFocus();
        }else if (!userName.matches(".*[A-Za-z]{4,}")){
            new Alert(Alert.AlertType.ERROR, "Invalid UserName").show();
            txtUserName.requestFocus();
            return;
        }else if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")){
            new Alert(Alert.AlertType.ERROR, "Invalid Password").show();
            txtPassword.requestFocus();
            return;
        }


        if (btnSign.getText().equalsIgnoreCase("Sign")){
           // userBO.saveUser(new UserDTO(id,userName,password));
            new Alert(Alert.AlertType.INFORMATION, "Your login").show();
        }
    }

    public void btnShow(ActionEvent actionEvent) {
        String password=txtPassword.getText();
        new Alert(Alert.AlertType.INFORMATION,"Password is:" +password).show();
       // lblPassword.setText(password);
    }
}
