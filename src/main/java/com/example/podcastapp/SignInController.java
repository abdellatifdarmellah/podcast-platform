package com.example.podcastapp;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class SignInController implements Initializable {
    @FXML
    private TextField username;
    @FXML
    private ImageView ivLogo;
    @FXML
    private PasswordField password;

    @FXML
    private Button forgotPassword, SignIn, SignUp;

    @FXML
    private CheckBox rememberMe;

    @FXML
    private Label errorMsg, appLogo;

    @FXML
    void SignIn(MouseEvent event) {
        verifyLogin(username.getText(), password.getText());

    }
    @FXML
    void ClosePage(MouseEvent event) {
        Platform.exit();
    }

    void verifyLogin(String username, String password) {
        String tempEmail;
        String tempUsername;
        String tempPassword;
        Scanner x;
        boolean found = false;
        String path = "src/logins.txt";

        try {
            x = new Scanner(new File(path));
            x.useDelimiter("[;\n]");
            while (x.hasNext() && !found) {
                tempEmail = x.next();
                tempUsername = x.next();
                tempPassword = x.next();
                if (tempUsername.trim().equals(username.trim()) && tempPassword.trim().equals(password.trim())) {
                    toHomePage();
                    found = true;
                }
                else {
                    errorMsg.setVisible(true);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void toHomePage() throws IOException {
        Stage stage = (Stage) SignIn.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("home-page.fxml"));
        primaryStage.setTitle("Welcome to podcastio");
        primaryStage.setScene(new Scene(root, 800, 570));
        primaryStage.show();
    }
    @FXML
    void SignUp(MouseEvent event) throws IOException {
        Stage stage = (Stage) SignUp.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("sign-up.fxml"));
        primaryStage.setTitle("Welcome to podcastio");
        primaryStage.setScene(new Scene(root, 800, 570));
        primaryStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image logo = new Image(new File("src/assets/podcast.png").toURI().toString());
        ivLogo = new ImageView(logo);
        ivLogo.setFitHeight(18);
        ivLogo.setFitWidth(18);
        appLogo.setGraphic(ivLogo);
    }
}
