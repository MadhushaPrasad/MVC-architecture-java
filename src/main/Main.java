/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Madhusha Prasad
 */
public class Main extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        Class.forName("com.mysql.jdbc.Driver");
        
        Parent root = FXMLLoader.load(getClass().getResource("/view/fxml.fxml"));
        Scene mainScene = new Scene(root);
        
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Sunday Class");
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.centerOnScreen();
    }
}
