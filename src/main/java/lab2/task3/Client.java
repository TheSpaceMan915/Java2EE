package lab2.task3;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Client extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        BorderPane pane_main = new BorderPane();
        Scene scene = new Scene(pane_main,600,600);

        TextField field_login = new TextField();
        PasswordField field_password = new PasswordField();

        field_login.setPrefColumnCount(10);
        field_password.setPrefColumnCount(10);
        field_login.setText("Eeeerooock");
        field_password.setText("H3PO");

        Label label_login = new Label("Enter your login");
        Label label_password = new Label("Enter your password");
        Label label_request = new Label("Choose the type of your request");

        ObservableList<String> list_requests = FXCollections.observableArrayList("GET","POST");
        ComboBox<String> box_requests = new ComboBox<>(list_requests);
        box_requests.setValue("POST");

        Button button_return = new Button("Go back");
        Button button_enter = new Button("Enter");


        //adding ActionListeners


        //setting up the GridPane
        GridPane gridpane = new GridPane();


        primaryStage.setTitle("Client");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
