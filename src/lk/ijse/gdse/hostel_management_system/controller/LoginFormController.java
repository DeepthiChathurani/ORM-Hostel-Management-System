package lk.ijse.gdse.hostel_management_system.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.hostel_management_system.bo.BOFactory;
import lk.ijse.gdse.hostel_management_system.bo.custom.UserBO;
import lk.ijse.gdse.hostel_management_system.dto.UserDTO;
import lk.ijse.gdse.hostel_management_system.util.Navigation;
import lk.ijse.gdse.hostel_management_system.util.Routes;

import java.io.IOException;
import java.util.List;

public class LoginFormController {
    public AnchorPane ancLogin;
    public TextField txtPassword;
    public TextField txtUserName;
    public Label lblUserName;
    public Label lblPassword;

    UserBO userBO= (UserBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.USERBO);

    public void btnLogin(ActionEvent actionEvent) throws Exception {
//        String userName=txtUserName.getText();
//        String password=txtPassword.getText();
//        List<UserDTO> userDTOS= userBO.getAllUser();
//
//        /*System.out.println(userDTOS);*/
//
//
//        for (UserDTO userDTO:userDTOS){
//            if (userDTO.getUserName().equals(userName) &&  userDTO.getPassword().equals(password)){
////                new Alert(Alert.AlertType.INFORMATION, "Your login").show();
//                Navigation.navigation(Routes.Home,ancLogin);
//            }else {
//                /* new Alert(Alert.AlertType.INFORMATION, "Not login").show();*/
//                lblUserName.setText("Invalid UserName");
//                lblPassword.setText("Invalid Password");
//            }
//        }
        Navigation.navigation(Routes.Home,ancLogin);


    }

    public void btnSing(ActionEvent actionEvent) throws IOException {
        Navigation.navigation(Routes.SINGUP,ancLogin);
    }

    public void btnshow(ActionEvent actionEvent) {
        String password=txtPassword.getText();
        new Alert(Alert.AlertType.INFORMATION,"Password is:" +password).show();
    }
}
