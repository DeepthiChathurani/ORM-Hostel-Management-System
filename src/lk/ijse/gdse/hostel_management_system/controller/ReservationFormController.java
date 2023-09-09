package lk.ijse.gdse.hostel_management_system.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.hostel_management_system.bo.BOFactory;
import lk.ijse.gdse.hostel_management_system.bo.custom.ReservationBO;
import lk.ijse.gdse.hostel_management_system.dto.ReservationDTO;
import lk.ijse.gdse.hostel_management_system.dto.RoomDTO;
import lk.ijse.gdse.hostel_management_system.dto.StudentDTO;
import lk.ijse.gdse.hostel_management_system.util.Navigation;
import lk.ijse.gdse.hostel_management_system.util.Routes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ReservationFormController {
    public AnchorPane anc;
    public TextField txtResId;
    public DatePicker Date;
    public ComboBox<String> cmbStudentId;
    public ComboBox<String> cmbRoomTypeId;
    public TextField txtStatus;
    public TableView<ReservationDTO> tblReservation;
    public TableColumn<?, ?> colResId;
    public TableColumn<?, ?> colDate;
    public TableColumn<?, ?> colStdId;
    public TableColumn<?, ?> colRoomTypeId;
    public TableColumn<?, ?> colQty;
    public TableColumn<?, ?> colStatus;
    public TableColumn<?, ?> colStdId1;
    public TextField txtQty;
    public CheckBox chckbxStatus;
    public Button btnSave;

    private StudentDTO studentData;
    private RoomDTO roomData;
    private String id;

    ReservationBO reservationBO = (ReservationBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.RESERVATIONBO);

    public void initialize() throws Exception {
        loadAllStudents();
        loadAllRooms();
        loadAll();
        setCheckStatus();
        iniUI();

        colResId.setCellValueFactory(new PropertyValueFactory<>("resId"));
        colStdId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colRoomTypeId.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));


    }

    void setCheckStatus() {
        tblReservation.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
//            btnClear.setDisable(newValue==null);
           btnSave.setText(newValue!=null ? "update" : "save");
            btnSave.setDisable(newValue==null);

            if (newValue != null) {
                if (newValue != null) {
                    txtStatus.setDisable(false);
                    id = newValue.getResId();
                }
            }
        });
    }

    private void loadAllStudents() {
        try {
            ArrayList<StudentDTO> allStudent = (ArrayList<StudentDTO>) reservationBO.geAllStudents();
            for (StudentDTO s : allStudent) {
                cmbStudentId.getItems().add(s.getStudentId());
//                if (s.getStudentId().equals(cmbStudentId.getValue())){
//                    txtName.setText(s.getAddress());
//                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadAllRooms() {
        try {
            ArrayList<RoomDTO> allRooms = (ArrayList<RoomDTO>) reservationBO.getAllRooms();
            for (RoomDTO r : allRooms) {
                cmbRoomTypeId.getItems().add(r.getRoomTypeId());
//                if (r.getRoomTypeId().equals(cmdRoomTypeId.getValue())){
//                    txtRoom.setText(String.valueOf(r.getQty()));
//                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private StudentDTO getStudentDTO() throws Exception {
        String studentId = cmbStudentId.getSelectionModel().getSelectedItem();
        return reservationBO.getStudents(studentId);
    }

    private RoomDTO getRoomDTO() throws Exception {
        String roomId = cmbRoomTypeId.getSelectionModel().getSelectedItem();
        return reservationBO.getRooms(roomId);
    }

    private void loadAll() throws Exception {
        List<ReservationDTO> reservationDTOList = reservationBO.getAllReservation();
        ObservableList<ReservationDTO> dtoObservableList = FXCollections.observableList(reservationDTOList);

        tblReservation.setItems(dtoObservableList);

    }


    private String generateNewIds() {
        try {
            return reservationBO.generateReservationId();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new id " + e.getMessage()).show();
            e.printStackTrace();
        }
        return "RE0-001";
    }

    public void iniUI() {
        cmbStudentId.setDisable(true);
        cmbRoomTypeId.setDisable(true);
//        btnSave.setDisable(true);
//        btnClear.setDisable(true);
//        txtName.setEditable(false);
//        txtRoom.setEditable(false);

    }


    public void btnAdd(ActionEvent actionEvent) throws Exception {
        if (btnSave.getText().equals("Save Reservation")) {
            boolean isSaved = reserveRoom(getData());
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Room Reserved").show();
                tblReservation.getItems().clear();
                tblReservation.refresh();
                loadAll();

            } else {
                new Alert(Alert.AlertType.ERROR, "Error").show();
            }
        } else if (btnSave.getText().equals("update")) {
            if (chckbxStatus.isSelected()) {
                btnSave.setDisable(false);
                String status = "paid";

                boolean isUpdated = reservationBO.checkStatusClick(id, status);
                tblReservation.refresh();
                if (isUpdated) {
                    tblReservation.getItems().clear();
                    tblReservation.getItems().clear();

                    new Alert(Alert.AlertType.CONFIRMATION, "Status updated").show();

                    loadAll();

                    chckbxStatus.setDisable(true);
                    btnSave.setDisable(true);
                    tblReservation.refresh();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Error").show();
                }
            }
        }

    }

    private ReservationDTO getData() throws Exception {

        String status = "unPaid";
        if (chckbxStatus.isSelected()) {
            status = "paid";
        }

        java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        StudentDTO studentData = getStudentDTO();
        RoomDTO roomData = getRoomDTO();

        return new ReservationDTO(
                txtResId.getText(),
                sqlDate,
                status,
                studentData,
                roomData
        );
    }

    private boolean reserveRoom(ReservationDTO reservationDTO) throws Exception {
        boolean isSaved = Boolean.parseBoolean(reservationBO.saveReservation(reservationDTO));

        if (!isSaved) {
            return false;
        }

        RoomDTO roomDTO = reservationDTO.getRoomDTO();
        roomDTO.setQty(roomDTO.getQty() - 1);

        boolean isUpdate = reservationBO.updateRoomQty(roomDTO);

        if (!isUpdate) {
            return false;
        }
        return true;
    }


    public void chckbtnStatus(ActionEvent event) {
        if (btnSave.getText().equals("Update")){
            if (chckbxStatus.isSelected()){
                btnSave.setDisable(false);

                String status="paid";

            }else if (!chckbxStatus.isSelected())btnSave.setDisable(true);
        }
    }
    public void btnHome(MouseEvent mouseEvent) throws IOException {
        Navigation.navigation(Routes.Home, anc);

    }

    public void btnDelete(ActionEvent actionEvent) {
        String resId = txtResId.getText();
        try {

            boolean isDelete = reservationBO.deleteReservation(resId);
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
    }

    public void btnSearch(ActionEvent actionEvent) {
    }

}