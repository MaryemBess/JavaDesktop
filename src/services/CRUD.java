/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maryem
 * @param <HK>
 */
public interface CRUD <HK>{
    int insert(HK i);
//    void Delete(int id);
//    int update(HK u);
    int SingIn(HK si);
    int VerifierCompte(HK verif,String code);
   // HK chercher(int id);
    String getMd5(String mdp);
   // ArrayList<User> getshow();
    
}
