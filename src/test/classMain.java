/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;


import entite.e_book;
import service.e_bookCRUD;
import utils.myconnexion;

/**
 *
 * @author Kenza
 */
public class classMain {
    public static void main(String[] args) {
        myconnexion myco=new myconnexion();
        e_bookCRUD bookCrud= new e_bookCRUD();
        e_book book= new e_book(1, "Victor Hugo", "Les Contemplations","trisstesse" , 1, 33, 5);
        e_book book2= new e_book(1, "Victor Hugoooooo", "Les Contemplations","trisstesse" , 1, 33, 5);
        //bookCrud.ajouterBook(book);
        //bookCrud.modifieBook(book2);
        bookCrud.afficherBook();
    }
}
