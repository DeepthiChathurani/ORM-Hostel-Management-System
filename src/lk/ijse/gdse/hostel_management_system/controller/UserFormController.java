package lk.ijse.gdse.hostel_management_system.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.hostel_management_system.bo.BOFactory;
import lk.ijse.gdse.hostel_management_system.bo.custom.UserBO;
import lk.ijse.gdse.hostel_management_system.dto.RoomDTO;
import lk.ijse.gdse.hostel_management_system.dto.UserDTO;
import lk.ijse.gdse.hostel_management_system.util.Navigation;
import lk.ijse.gdse.hostel_management_system.util.Routes;

import java.io.IOException;
import java.util.List;

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
    public Button btnSave;
    public Button btnDelete;

    UserBO userBO= (UserBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.USERBO);

    public void initialize() {
        loadAllUsers();
        iniUI();

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colContactNo.setCellValueFactory(new PropertyValueFactory<>("contact"));

        tblUser.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDelete.setDisable(newValue==null);
            btnSave.setText(newValue !=null ? "update" : "save");
            btnSave.setDisable(newValue==null);

            if (newValue!=null){
                txtId.setText(String.valueOf(newValue.getClass()));
                txtName.setText(String.valueOf(newValue.getClass()));
                txtPassword.setText(String.valueOf(newValue.getClass()));
                txtContactNo.setText(String.valueOf(newValue.getClass()));
            }
        });

    }


    private void loadAllUsers(){
        tblUser.getItems().clear();

        try {
            List<UserDTO> userDTOList= userBO.getAllUser();
            ObservableList<UserDTO> observableList= FXCollections.observableList(userDTOList);
            tblUser.setItems(observableList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void iniUI() {
        txtId.clear();
        txtName.clear();
        txtPassword.clear();
        txtContactNo.clear();
        txtId.setDisable(true);
        txtName.setDisable(true);
        txtPassword.setDisable(true);
        txtContactNo.setDisable(true);
        btnSave.setDisable(true);
        btnDelete.setDisable(true);

    }
    private String generateNewIds(){
        try {
            return userBO.generateUserId();
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new id " + e.getMessage()).show();
        }
        return "U00-001";
    }


    public void btnAdd(ActionEvent actionEvent) {
        String id=txtId.getText();
        String userName=txtName.getText();
        String password=txtPassword.getText();
        String contact=txtContactNo.getText();

        if (!userName.matches(".*[a-zA-Z0-9]{4,}")){
            new Alert(Alert.AlertType.ERROR, "Invalid UserName").show();
            txtName.requestFocus();
            return;
        }else if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")){
            new Alert(Alert.AlertType.ERROR, "Invalid Password").show();
            txtPassword.requestFocus();
            return;
        }else if (!contact.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")){
            new Alert(Alert.AlertType.ERROR, "Invalid Email").show();
            txtContactNo.requestFocus();
            return;
        }

            try {
               boolean isSave= Boolean.parseBoolean(userBO.saveUser(new UserDTO(id,userName,password,contact)));
                if (isSave) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Saved!.").show();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Not Save!").show();
                }

                tblUser.getItems().add(new UserDTO(id,userName,password,contact));
            }catch (Exception e){

            }


    }

    public void btnDelete(ActionEvent actionEvent) {
        String id = txtId.getText();
        try {

            boolean isDelete = userBO.deleteUser(id);
            if (isDelete) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted!.").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "NotDelete!").show();
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void btnupdate(ActionEvent actionEvent) {
        String id=txtId.getText();
        String userName=txtName.getText();
        String password=txtPassword.getText();
        String contact=txtContactNo.getText();

        UserDTO userDTO = new UserDTO(id,userName,password,contact);

        try {
            boolean isUpdate = userBO.updateUser(userDTO);
            if (isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION,"Updated!.").show();
            }else {
                new Alert(Alert.AlertType.WARNING, "Not Update").show();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void btnHome(MouseEvent mouseEvent) throws IOException {
        Navigation.navigation(Routes.Home,anc);
    }
}
