package lk.ijse.gdse.hotrl_management_system.util;
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
                initUI("LoginFrom.fxml");
                break;
            case SINGUP:
                window.setTitle("SignUp From");
                initUI("SignUpForm.fxml");
                break;
        }
    }
    private static void initUI(String location) throws IOException {
        Navigation.mealPackagesIdMain.getChildren().add(FXMLLoader.load(Navigation.class.getResource("/lk/ijse/gdse/hostel_management_system/view/"+location)));
    }
}
