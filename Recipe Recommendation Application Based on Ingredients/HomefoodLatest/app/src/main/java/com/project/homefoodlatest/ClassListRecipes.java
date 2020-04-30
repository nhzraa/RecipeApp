package com.project.homefoodlatest;

public class ClassListRecipes
{
    public String image;
    public String title;
    public String calories;
    public String prepTime;
    public String recipeID;
    public String link;
    public String ingredients;
    public String directions;
    public String recipeFav;

    public ClassListRecipes(String recipeID, String title, String link, String image, String calories, String prepTime, String ingredients, String directions) {
        this.recipeID = recipeID;
        this.title = title;
        this.link = link;
        this.image = image;
        this.calories = calories;
        this.prepTime = prepTime;
        this.ingredients = ingredients;
        this.directions = directions;
    }

    public String getRecipeID() { return recipeID; }

    public String getTitle() { return title; }

    public String getLink() { return link; }

    public String getImage() { return image; }

    public String getCalories() { return calories; }

    public String getPrepTime() { return prepTime; }

    public String getIngredients() { return ingredients; }

    public String getDirections(){ return directions; }

    public void setRecipeID(String recipeID) { this.recipeID = recipeID; }

    public void setTitle(String title) { this.title = title; }

    public void setImage(String image) { this.image = image; }

    public void setLink(String link) { this.link = link; }

    public void setCalories(String calories) { this.calories = calories; }

    public void setIngredients(String ingredients) { this.ingredients = ingredients; }

    public void setDirections(String directions) { this.directions = directions; }

    public void setPrepTime(String prepTime) { this.prepTime = prepTime; }
}