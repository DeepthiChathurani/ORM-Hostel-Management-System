package lk.ijse.gdse.hostel_management_system.util;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {
    private static AnchorPane mealPackagesIdMain;
    public static void navigation(Routes routes,AnchorPane mealPackagesIdMain) throws IOException {
        Navigation.mealPackagesIdMain = mealPackagesIdMain;
        Navigation.mealPackagesIdMain.getChildren().clear();
        Stage window = (Stage) Navigation.mealPackagesIdMain.getScene().getWindow();

        switch (routes) {
            case LOGIN:
                window.setTitle("Login From");
                initUI("LoginForm.fxml");
                break;
            case SINGUP:
                window.setTitle("SignUp From");
                initUI("SignUpForm.fxml");
                break;
            case Home:
                window.setTitle("Home From");
                initUI("HomeForm.fxml");
                break;
            case STUDENT:
                window.setTitle("Student From");
                initUI("StudentForm.fxml");
                break;
            case ROOM:
                window.setTitle("Room From");
                initUI("RoomForm.fxml");
                break;
            case RESERVATION:
                window.setTitle("Reservation From");
                initUI("ReservationForm.fxml");
                break;
            case USER:
                window.setTitle("User From");
                initUI("UserForm.fxml");
                break;
        }
    }
    private static void initUI(String location) throws IOException {
        Navigation.mealPackagesIdMain.getChildren().add(FXMLLoader.load(Navigation.class.getResource("/lk/ijse/gdse/hostel_management_system/view/"+location)));
    }
}
