package com.example.gestionnairebibliotheque;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;


/**
 *
 * @author candas
 */
public class Actions {
    private static Statement statement = null;
    private PreparedStatement preparedStatement = null;



    static ResultSet yap(){
        try {
            statement = ConnectDB().createStatement();
            String interrogation = "Select * From livres";
            ResultSet rs;

            rs = statement.executeQuery(interrogation);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void supprimer(int id){
        String interrogation = "Delete from livres where id = ?";
        try {
            preparedStatement = ConnectDB().prepareStatement(interrogation);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void metJourLivres (int id, String nouvelNom, String nouvelAuthor, String nouvelGenre, String nouvelEditeur){
        String interrogation = "Update livres set nom = ?, nom_author = ?, genre = ?, editeur = ? where id = ?";

        try {
            preparedStatement = ConnectDB().prepareStatement(interrogation);
            preparedStatement.setString(1,nouvelNom);
            preparedStatement.setString(2,nouvelAuthor);
            preparedStatement.setString(3,nouvelGenre);
            preparedStatement.setString(4,nouvelEditeur);

            preparedStatement.setInt(5,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void ajouteLivre(String nom_livre, String author, String genre, String editeur){
        String interrogation = "Insert into livres (nom,nom_author,genre,editeur) VALUES(?,?,?,?)";

        try {
            preparedStatement = ConnectDB().prepareStatement(interrogation);
            preparedStatement.setString(1,nom_livre);
            preparedStatement.setString(2,author);
            preparedStatement.setString(3,genre);
            preparedStatement.setString(4,editeur);
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



    public boolean connexion(String utilisateur, String password){
        String sorgu = "Select * From admins where nom = ? and motPasse = ?";
        try {
            preparedStatement = ConnectDB().prepareStatement(sorgu);
            preparedStatement.setString(1, utilisateur);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();


            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    public static Connection ConnectDB(){
        String url = "jdbc:mysql://" + Database.host + ":" + Database.port + "/" + Database.nom_db;

        try {            
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Pilote introuvable...");
        }
        try{
            Connection con = DriverManager.getConnection(url, Database.utilisateur, Database.password);
            System.out.println("Connexion réussie...");
            return con;
        }catch (Exception ex) {
             System.out.println("La connexion a échoué");
             return null;
        }
    }
    public static ObservableList<Livres> getLivres(){
        Connection conn = ConnectDB();
        ObservableList<Livres> list = FXCollections.observableArrayList();

        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("select * from livres");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                list.add(new Livres(Integer.parseInt(rs.getString("id_livre")), rs.getString("nom"), rs.getString("nom_author"), rs.getString("genre"), rs.getString("editeur")));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return list;
    }


}
