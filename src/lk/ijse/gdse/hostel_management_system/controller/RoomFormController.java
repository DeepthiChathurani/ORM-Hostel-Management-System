package lk.ijse.gdse.hostel_management_system.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.hostel_management_system.bo.BOFactory;
import lk.ijse.gdse.hostel_management_system.bo.custom.RoomBO;
import lk.ijse.gdse.hostel_management_system.dto.RoomDTO;
import lk.ijse.gdse.hostel_management_system.util.Navigation;
import lk.ijse.gdse.hostel_management_system.util.Routes;

import java.io.IOException;
import java.util.ArrayList;

public class RoomFormController {
    public AnchorPane anc;
    public TextField txtRId;
    public TextField txtType;
    public TextField txtKeyMoney;
    public TextField txtQty;
    public TableView<RoomDTO> tblRoom;
    public TableColumn<RoomDTO, String> colTypeId;
    public TableColumn<RoomDTO, String> ColType;
    public TableColumn<RoomDTO, String> colKeyMoney;
    public TableColumn<RoomDTO, String> colQty;
    public Button btnSave;
    public Button btnDelete;

    RoomBO roomBO= (RoomBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.ROOMBO);


    public void initialize(){

        iniUI();
        lordAllRoom();

        colTypeId.setCellValueFactory(new PropertyValueFactory<>("roomTypeId"));
        ColType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colKeyMoney.setCellValueFactory(new PropertyValueFactory<>("keyMoney"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));

        tblRoom.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDelete.setDisable(newValue==null);
            btnSave.setText(newValue != null ? "update" : "save");
            btnSave.setDisable(newValue==null);

            if (newValue!=null){
                txtRId.setText(newValue.getRoomTypeId());
                txtType.setText(newValue.getType());
                txtKeyMoney.setText(newValue.getKeyMoney());
                txtQty.setText(String.valueOf(newValue.getQty()));
            }
        });

    }
    public void iniUI(){
        txtRId.clear();
        txtType.clear();
        txtKeyMoney.clear();
        txtQty.clear();
        txtRId.setDisable(true);
        txtType.setDisable(true);
        txtKeyMoney.setDisable(true);
        txtQty.setDisable(true);
        btnSave.setDisable(true);
        btnDelete.setDisable(true);
    }


    private void lordAllRoom(){
        tblRoom.getItems().clear();

        try {
            ArrayList<RoomDTO> roomDTOArrayList= (ArrayList<RoomDTO>) roomBO.getAllRoom();
            System.out.println(roomDTOArrayList);
            for (RoomDTO  r : roomDTOArrayList){
                tblRoom.getItems().add(new RoomDTO(r.getRoomTypeId(),r.getType(),r.getKeyMoney(),r.getQty()));
            }

        }catch (Exception e){

        }

    }

    private String generateNewIds() throws Exception {
        try {
            return roomBO.generateRoomId();
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new id " + e.getMessage()).show();
            e.printStackTrace();
        }
        return "RES-001";
    }

    public void btnSave(ActionEvent actionEvent) {
        String roomId=txtRId.getText();
        String type=txtType.getText();
        String keyMoney=txtKeyMoney.getText();
        int qty= Integer.parseInt(txtQty.getText());

        if (!type.matches("[A-Za-z0-9 ]+")){
            new Alert(Alert.AlertType.ERROR, "Invalid Type").show();
            txtType.requestFocus();
            return;
        }else if (!keyMoney.matches("^[0-9]+[.]?[0-9]*$")){
            new Alert(Alert.AlertType.ERROR, "Invalid keyMoney").show();
            txtKeyMoney.requestFocus();
            return;
        }else if (!txtQty.getText().matches("^\\d+$")){
            new Alert(Alert.AlertType.ERROR, "Invalid keyMoney").show();
            txtQty.getText();
            return;
        }


            try {
               boolean isSave= Boolean.parseBoolean(roomBO.saveRoom(new RoomDTO(roomId,type,keyMoney,qty)));

                if (isSave) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Saved!.").show();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Not Save!").show();
                }
                tblRoom.getItems().add(new RoomDTO(roomId,type,keyMoney,qty));

            } catch (Exception e) {

                e.printStackTrace();
            }


       // btnAddNewRoom.fire();
    }

    public void btnDelete(ActionEvent actionEvent) {
        String roomTypeId = txtRId.getText();
        try {

            boolean isDelete = roomBO.deleteRoom(roomTypeId);
            if (isDelete) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted!.").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "NotDelete!").show();
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void btnUpdate(ActionEvent actionEvent) {
        String id = txtRId.getText();
        String type = txtType.getText();
        String lkr = txtKeyMoney.getText();
        int qty = Integer.parseInt(txtQty.getText());

        RoomDTO roomDTO = new RoomDTO(id, type, lkr, qty);

        try {
            boolean isUpdate = roomBO.updateRoom(roomDTO);
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
