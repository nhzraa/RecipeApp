package com.project.homefoodlatest;
import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.StrictMode;
import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.xml.transform.Result;
public class ConnectionClass {

    String classs = "com.mysql.jdbc.Driver";

    String url = "jdbc:mysql://172.20.10.3:3310/recipe_app";
    String un = "root";
    String password = "";

    @SuppressLint("NewApi")
    public Connection CONN() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        String ConnURL = null;
        try {
            Class.forName(classs);
            conn = DriverManager.getConnection(url, un, password);
        } catch (SQLException se) {
            Log.e("ERROR", se.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("ERROR", e.getMessage());
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage());
        }
        return conn;
    }

//    public void addtoFav(String recipeID, String usernamestr) {
//
//        try {
//
//            Class.forName(classs);
//            ConnectionClass conn = new ConnectionClass();
//
//            Connection con = connectionClass.CONN();
//            con = DriverManager.getConnection(url, un, password);
//
//            String query = "insert into search (username, recipeID) values ('"+usernamestr+"', '"+recipeID+"');";
//            Statement stmt = conn.createStatement();
//            stmt.executeUpdate(query);
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            Writer writer = new StringWriter();
//            ex.printStackTrace(new PrintWriter(writer));
//        }
//    }

//    public void removeFav(String recipeID) {
//
//        try {
//
//            Class.forName(classs);
//            Connection conn = DriverManager.getConnection(url, un, password);
//
//            String query = "delete from search where recipeID = '"+recipeID+"'";
//            Statement stmt = conn.createStatement();
//            stmt.executeUpdate(query);
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            Writer writer = new StringWriter();
//            ex.printStackTrace(new PrintWriter(writer));
//        }
//    }

//    public Boolean isFav(String recipeID) {
//
//        try {
//
//            Class.forName(classs);
//            Connection conn = DriverManager.getConnection(url, un, password);
//
//            String query = "SELECT users.username = 'z', \n" +
//                    "recipes.title, recipes.image, recipes.calories, recipes.prepTime, recipes.ingredients, recipes.directions, recipes.recipeFav\n" +
//                    "FROM recipes, search, users\n" +
//                    "WHERE search.username = users.username\n" +
//                    "AND search.recipeID = recipes.recipeID\n" +
//                    "AND recipes.recipeFav = 'true'";
//            Statement stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery(query);
//
//            if(rs!= null)
//            {
//                while (rs.next())
//                {
//                    try {
//                        favArrayList.add(new ClassListFavorites(
//                                rs.getString(1),
//                                rs.getString(2),
//                                rs.getString(3),
//                                rs.getString(4),
//                                rs.getString(5),
//                                rs.getString(6),
//                                rs.getString(7),
//                                rs.getString(8)));
//                    } catch (Exception ex) {
//                        ex.printStackTrace();
//                    }
//                }
//
//                isSuccess = true;
//            }
//
//            else {
//                isSuccess = false; }
//
//        } catch (Exception ex)
//        {
//            ex.printStackTrace();
//            Writer writer = new StringWriter();
//            ex.printStackTrace(new PrintWriter(writer));
//            isSuccess = false;
//        }
//
//        return isSuccess;
//    }
}
