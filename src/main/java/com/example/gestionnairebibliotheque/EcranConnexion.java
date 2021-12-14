package com.example.gestionnairebibliotheque;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;


public class EcranConnexion {
    @FXML
    private Button connexionButton;

    @FXML
    private Label messageChamp;

    @FXML
    private TextField passwordChamp;

    @FXML
    private TextField utilisateurChamp;


    @FXML
    void onConnexionButtonClick(ActionEvent event) {
        Actions actions = new Actions();
        messageChamp.setText("");

        String utilisateur = utilisateurChamp.getText();
        String password = new String(passwordChamp.getText());


        boolean connexionReussi = actions.connexion(utilisateur, password);

        if (connexionReussi){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Tableau.fxml"));
                /*
                 * if "fx:controller" is not set in fxml
                 * fxmlLoader.setController(NewWindowController);
                 */
                Scene scene = new Scene(fxmlLoader.load(), 800, 700);
                Stage stage = new Stage();
                stage.setTitle("Tableau d'émployés");
                stage.setScene(scene);
                stage.show();
                ((Node)(event.getSource())).getScene().getWindow().hide();
            } catch (IOException e) {
                Logger logger = Logger.getLogger(getClass().getName());
                logger.log(Level.SEVERE, "Failed to create new Window.", e);
            }

        }
        else messageChamp.setText("Échec de la connexion... Veuillez réessayer");
    }



}