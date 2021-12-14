package com.example.gestionnairebibliotheque;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class Tableau implements Initializable {
    Actions actions = new Actions();



    @FXML
    private TextField authorField;

    @FXML
    private TextField chercheField;

    @FXML
    private TableColumn<Livres, String> col_author;

    @FXML
    private TableColumn<Livres, String> col_editeur;

    @FXML
    private TableColumn<Livres, String> col_genre;

    @FXML
    private TableColumn<Livres, Integer> col_id;

    @FXML
    private TableColumn<Livres, String> col_nom_livre;

    @FXML
    private TextField editeurField;

    @FXML
    private TextField genreField;

    @FXML
    private TextField nomField;

    @FXML
    private TableView<Livres> tableLivres;

    @FXML
    private Label messageChamp;

    @FXML
    void ajouterAction(ActionEvent event) {
        new Actions().ajouteLivre(nomField.getText(),authorField.getText(),genreField.getText(),editeurField.getText());
        messageChamp.setText("On a ajouté le livre avec succés!");
    }

    @FXML
    void modifierAction(ActionEvent event) {

    }

    @FXML
    void supprimerAction(ActionEvent event) {

    }

    ObservableList<Livres> listM;

    int index = -1;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement ps = null;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        col_id.setCellValueFactory(new PropertyValueFactory<Livres,Integer>("Id"));
        col_nom_livre.setCellValueFactory(new PropertyValueFactory<Livres,String>("Nom de Livre"));
        col_author.setCellValueFactory(new PropertyValueFactory<Livres,String>("Author"));
        col_genre.setCellValueFactory(new PropertyValueFactory<Livres,String>("Genre"));
        col_editeur.setCellValueFactory(new PropertyValueFactory<Livres,String>("Editeur"));

        listM = Actions.getLivres();
        tableLivres.setItems(listM);
    }
}

