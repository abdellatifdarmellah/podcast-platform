package com.example.podcastapp;

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

public class SignUpController implements Initializable {

    @FXML
    private TextField emailInput;
    @FXML
    private TextField usernameInput;
    @FXML
    private PasswordField passwordInput;
    @FXML
    private Label successText, errorText, appLogo;
    @FXML
    private Button SignInButton;
    private ImageView ivLogo;

    int ln;
    @FXML
    void CreateAnAccount(MouseEvent event) throws IOException {
        String path = "src/logins.txt";
        File file = new File(path);
        if (!emailInput.getText().isEmpty() && !usernameInput.getText().isEmpty() && !passwordInput.getText().isEmpty())
        {
            countLines();
            try {
                RandomAccessFile raf = new RandomAccessFile(file, "rw");
                for (int i = 0; i < ln; i++) {
                    raf.readLine();
                }
                raf.writeBytes("\n");
                raf.writeBytes(emailInput.getText() + ";");
                raf.writeBytes(usernameInput.getText() + ";");
                raf.writeBytes(passwordInput.getText() + "\n");
                successText.setVisible(true);
                errorText.setVisible(false);
            } catch (FileNotFoundException ex) {
                errorText.setVisible(true);
            }
        } else {
            errorText.setVisible(true);
        }

    }

    void countLines() {
        String path = "src/logins.txt";
        File file = new File(path);
        try {
            ln = 1;
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            for (int i = 0; raf.readLine() != null; i++) {
                ln++;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void SignIn(MouseEvent event) throws IOException {
        Stage stage = (Stage) SignInButton.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("sign-in.fxml"));
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