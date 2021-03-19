/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;


import java.sql.ResultSet;

import java.sql.Statement;

import java.sql.Date;


/**
 *
 * @author Maryem
 */
public class User {
   

    private int id;
    private String email;
    private String password;
    private String username;
    private String tel;
    private Date birthDate;
    private String adresse;
    private String roles;
    private String image;
    

    
    public User(int id, String email, String password, String username, String tel, Date birthDate, String adresse, String roles, String image) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.tel = tel;
        this.birthDate = birthDate;
        this.adresse = adresse;
        this.roles = roles;
        this.image = image;
    }
public User(int id , String username, String email,String roles,String adresse,String tel)
{
   this.id=id;
   this.username=username;
   this.email=email;
   this.roles=roles;
   this.adresse=adresse;
   this.tel=tel;  
}
    public User(int id, String mail, String password, String name, String tel, Date birthDate,String adresse, int status, String roles, String image) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.tel = tel;
        this.birthDate = birthDate;
        this.adresse = adresse;
        this.roles = roles;
        this.image = image;
    }


    public User(int aInt, String string, String string0, String string1, String string2) {
    }

    public User(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

 

    public User(int id, String mail, String password, String name, String tel, Date birthDate, String city, String adresse, int zipCode, int status, String roles, int desactivate, int code) {
        this.id = id;
        this.email = mail;
        this.password = password;
        this.username = username;
        this.tel = tel;
        this.birthDate = birthDate;
        this.adresse = adresse;
        this.roles = roles;
    }

    

    public User(String mail, String password) {
        this.email = email;
        this.password = password;
    }

    public User(int id, String mail) {
        this.email = email;

        this.id = id;
    }

    public User(int idU, String name, String roles) {
        this.id = idU;
        this.username = name;
      
        this.roles=roles;
    }

    public User() {
        this.username = "";
        this.email = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int idU) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String mail) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = username;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        return this.id == other.id;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public User(int id, String email, String password, String username, String tel, Date birthDate,  String adresse) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.tel = tel;
        this.birthDate = birthDate;
        this.adresse = adresse;
    }

    

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", mail=" + email + ", password=" + password + ", name=" + username + ", tel=" + tel + ", birthDate=" + birthDate + ", adresse=" + adresse + ", roles=" + roles + ", image=" + image + '}';
    }

    



    public User(int id, String email ,
                        String name, String password, int zipCode,
                        String city, String adresse, String tel )
    {
        this.id=id;
        this.email=email;
        this.username=username;
        this.password=password;
        this.adresse=adresse;
        this.tel=tel;
      
    }
//      public User(int idU, String mail ,
//                        String name, String tel,
//                        String adresse)
//    {
//        this.id=id;
//        this.email=email;
//        this.username=username;
//        this.tel=tel;
//        this.adresse=adresse;
//        
//      
//    }
//        public User(int id, String email ,String username, String password,String adresse, String tel )
//    {
//        this.id=id;
//        this.email=email;
//        this.username=username;
//        this.password=password;
//        this.adresse=adresse;
//        this.tel=tel;
//      
//    }

    public User(int id, String email, String name, String roles) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.roles = roles;
    }

    public User(String name, String email, String tel, String roles,int id) {
        this.username = username;
        this.email = email;
        this.tel = tel;
        this.roles = roles;
        this.id=id;
    }

   

   
     
    
    
}
