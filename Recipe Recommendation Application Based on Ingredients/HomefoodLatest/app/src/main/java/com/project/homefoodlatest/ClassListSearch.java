package com.project.homefoodlatest;

public class ClassListSearch
{
    public String username;
    //public String recipeID;
    public String searchIngre;

    public ClassListSearch(String username, String searchIngre) {
        //this.recipeID = recipeID;
        this.username = username;
        this.searchIngre = searchIngre;
    }

   //public String getRecipeID() { return recipeID; }

    public String getUsername() { return username; }

    public String getSearchIngre() { return searchIngre; }

    //public void setRecipeID(String recipeID) { this.recipeID = recipeID; }

    public void setUsername(String username) { this.username = username; }

    public void setSearchIngre(String searchIngre) { this.searchIngre = searchIngre; }
}