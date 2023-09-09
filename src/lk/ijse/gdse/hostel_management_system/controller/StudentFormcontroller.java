package lk.ijse.gdse.hostel_management_system.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.hostel_management_system.bo.BOFactory;
import lk.ijse.gdse.hostel_management_system.bo.custom.StudentBO;
import lk.ijse.gdse.hostel_management_system.dto.StudentDTO;
import lk.ijse.gdse.hostel_management_system.util.Navigation;
import lk.ijse.gdse.hostel_management_system.util.Routes;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class StudentFormcontroller {
    public AnchorPane anc;
    public ComboBox<String> cmbGender;
    public TextField txtId;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtContactNo;
    public DatePicker dockerDOB;
    public TableView tblStudentDetails;
    public TableColumn<Object, Object> columnId;
    public TableColumn columnName;
    public TableColumn clmnAddress;
    public TableColumn clmnContactNo;
    public TableColumn clmnDOB;
    public TableColumn clmnGender;
    public Button btnSave;

    StudentBO studentBO= (StudentBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.STUDENTBO);
    public void initialize(){
        setCmdGender();
        lordAllStudent();
        iniUI();
        columnId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmnAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        clmnContactNo.setCellValueFactory(new PropertyValueFactory<>("contact"));
        clmnDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        clmnGender.setCellValueFactory(new PropertyValueFactory<>("gender"));

        tblStudentDetails.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnSave.setText(newValue !=null? "update" : "save");
            btnSave.setDisable(newValue==null);

//            if (newValue!=null){
//                txtId.setText(newValue.);
//                txtName.setText(newValue.getName());
//                txtAddress.setText(newValue.getAddress());
//                txtContactNo.setText(newValue.getContact());
//                dockerDOB.requestFocus();

                if (newValue.toString().equals("Male")){
                    setCmdGender();
                    cmbGender.getSelectionModel().select(0);
                }else {
                    cmbGender.getSelectionModel().select(1);
                }

        });
    }

    public void iniUI(){
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtContactNo.clear();
        dockerDOB.hide();
        cmbGender.getSelectionModel().clearSelection();
        txtId.setDisable(true);
        txtName.setDisable(true);
        txtAddress.setDisable(true);
        txtContactNo.setDisable(true);
        dockerDOB.setDisable(true);
        cmbGender.setDisable(true);
        btnSave.setDisable(true);


    }

    private void lordAllStudent(){
        tblStudentDetails.getItems().clear();

        try {
            ArrayList<StudentDTO> allStudents= (ArrayList<StudentDTO>) studentBO.getAllStudent();
            for (StudentDTO s : allStudents){
                tblStudentDetails.getItems().add(new StudentDTO(s.getStudentId(),s.getName(),s.getAddress(),s.getContact(),s.getDob(),s.getGender()));
            }
        }catch (Exception e){

        }
    }
    private String generateNewIds(){
        try {
            return studentBO.generateStudentId();
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new id " + e.getMessage()).show();
            e.printStackTrace();
        }
        return "S00-001";
    }
    private void setCmdGender(){
        ArrayList<String> genders=new ArrayList<>();
        genders.add("Male");
        genders.add("Female");

        ObservableList<String> observableList= FXCollections.observableList(genders);
        cmbGender.setItems(observableList);
    }
    public void btnSave(ActionEvent actionEvent) throws Exception {
        String id=txtId.getText();
        String name=txtName.getText();
        String address=txtAddress.getText();
        String contact=txtContactNo.getText();
        LocalDate dob=dockerDOB.getValue();
        String gender= cmbGender.getSelectionModel().getSelectedItem();

        if (!name.matches(".*[a-zA-Z0-9]{4,}")){
            new Alert(Alert.AlertType.ERROR, "Invalid name").show();
            txtName.requestFocus();
            return;
        }else if (!address.matches(".*[a-zA-Z0-9]{4,}")){
            new Alert(Alert.AlertType.ERROR, "Address should not ").show();
            txtAddress.requestFocus();
            return;
        }else if (!contact.matches(".*(?:7|0|(?:\\\\+94))[0-9]{9,10}")){
            new Alert(Alert.AlertType.ERROR, "Contact should not ").show();
            txtContactNo.requestFocus();
            return;
        }else if (!dob.equals("\\b\\d{4}-\\d{2}-\\d{2}\\b")) {
            new Alert(Alert.AlertType.ERROR, "Dob should not ").show();
            dockerDOB.requestFocus();
            return;
        }
               boolean isSave= Boolean.parseBoolean(studentBO.saveStudent(new StudentDTO(id,name,address,contact,dob,gender)));

        if (isSave) {
            new Alert(Alert.AlertType.CONFIRMATION, "Saved!.").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Not Save!").show();
        }
        tblStudentDetails.getItems().add(new StudentDTO(id,name,address,contact,dob,gender));

            StudentDTO studentDTO= (StudentDTO) tblStudentDetails.getSelectionModel().getSelectedItem();
            studentDTO.setName(name);
            studentDTO.setAddress(address);
            studentDTO.setContact(contact);
            studentDTO.setDob(dob);
            studentDTO.setGender(gender);
            tblStudentDetails.refresh();

    }

    public void btnDelete(ActionEvent actionEvent) {
        String id = txtId.getText();
        try {

            boolean isDelete = StudentBO.deleteStudent(id);
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
        String id = txtId.getText();
        String name = txtName.getText();
        String add = txtAddress.getText();
        String contact = txtContactNo.getText();
        LocalDate dob = dockerDOB.getValue();
        String gender = null;
//        if (cmbGender.isSelected()) {
//            gender = rdbFemale.getText();
//        } else if (rdbMale.isSelected()) {
//            gender = rdbMale.getText();
//        }
        StudentDTO studentDTO = new StudentDTO(id, name, add, contact, dob, gender);
        try {
            boolean isUpdated = studentBO.updateStudent(studentDTO);
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION,"Updated!.").show();
            }else {
                new Alert(Alert.AlertType.WARNING, "Not Update").show();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void btnSearch(ActionEvent actionEvent) {
    }

    public void btnHome(MouseEvent mouseEvent) throws IOException {
        Navigation.navigation(Routes.Home,anc);
    }
}
