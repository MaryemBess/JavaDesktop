/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApiAliment;


import Entity.Aliment;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author ASUS
 */
public class FoodDataCollector {
    
    private httpClient hc = new httpClient();
    
    //private recipe r;
    private ObservableList<Aliment> ra;
    
    private int rl;
    
    public ObservableList<Aliment> collectRecipeData(String recipeName, int rl)
    {
        System.out.println("//****recipie name");
        System.out.println(recipeName);
        this.ra = FXCollections.observableArrayList();
        this.rl = rl;
        //this.getNutritionData(recipeName);
        this.getRecipeData(recipeName);
        System.out.println("end recipie nam*********");
        return this.ra;
    }
    
    private void getRecipeData(String recipeName)
    {
        String url = "https://api.edamam.com/search?q="+recipeName.toLowerCase()+"&app_id=b1d1d26b&app_key=9efedb510b575d38ed0bb31caa8e45b1";
        
        try {
            JSONObject recipeResult = hc.sendGet(url);
            JSONArray recipeArray = (JSONArray) recipeResult.get("hits");
            
            if(recipeArray.length() < 1)
            { 
                throw new RuntimeException("Recipe data result is empty! This happen if the recipe name is misspelled");
            }
            
            System.out.println(recipeArray.get(0));
            
            int len = recipeArray.length();
            
            for(int i=0; i<Math.min(len,rl); i++)
            {
            
                JSONObject selectedRecipe = (JSONObject) recipeArray.get(i);
                selectedRecipe = (JSONObject) selectedRecipe.get("recipe");
                String name = (String) selectedRecipe.get("label");
                String recipeIngredients  = (String) selectedRecipe.get("ingredientLines").toString();
                selectedRecipe = (JSONObject) selectedRecipe.get("totalNutrients");
                Double fat               = (Double) ((JSONObject) selectedRecipe.get("FAT")).get("quantity");
                Double protein           = (Double) ((JSONObject) selectedRecipe.get("PROCNT")).get("quantity");
                Double carbs             = (Double) ((JSONObject) selectedRecipe.get("CHOCDF")).get("quantity");
                
                String s = String.valueOf(fat);
                float gra = Float.valueOf(s);
                String s2 = String.valueOf(fat);
                float pro = Float.valueOf(s2);
                String s3 = String.valueOf(fat);
                float car = Float.valueOf(s3);
                String rec=new String();
                
                recipeIngredients.replace(",","\n").replace("[","").replace("]","");
               /* for(int j =1;j<=d.length;j++){
                rec=d[j]+"\n";
                    System.out.println(j);
                    System.out.println(rec);
                }*/
                System.out.println(recipeIngredients.replace(",","\n").replace("[","").replace("]",""));

                Aliment r = new Aliment(name,recipeIngredients.replace(",","\n").replace("[","").replace("]","") ,pro , gra, car);
                
                
                this.ra.add(r);
            }
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(FoodDataCollector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
