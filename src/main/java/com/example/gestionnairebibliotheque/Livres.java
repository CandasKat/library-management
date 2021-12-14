package com.example.gestionnairebibliotheque;

public class Livres {
    private int id;
    private String nom_livre;
    private String author;
    private String genre;
    private String editeur;

    public Livres(int id, String nom_livre, String author, String genre, String editeur) {
        this.id = id;
        this.nom_livre = nom_livre;
        this.author = author;
        this.genre = genre;
        this.editeur = editeur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_livre() {
        return nom_livre;
    }

    public void setNom_livre(String nom_livre) {
        this.nom_livre = nom_livre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getEditeur() {
        return editeur;
    }

    public void setEditeur(String editeur) {
        this.editeur = editeur;
    }
}
