package com.project.homefoodlatest;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Login extends AppCompatActivity {
    EditText username, password;
    Button btnLogin;
    ProgressDialog progressDialog;
    ConnectionClass connectionClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView signUp = findViewById(R.id.signUp);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
            }
        });


        username= (EditText) findViewById(R.id.username);
        password= (EditText) findViewById(R.id.password);
        btnLogin= (Button) findViewById(R.id.btnLogin);
        connectionClass = new ConnectionClass();

        progressDialog=new ProgressDialog(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dologin dologin=new Dologin();
                dologin.execute();
            }
        });

    }

    private class Dologin extends AsyncTask<String,String,String>{


        String unamestr=username.getText().toString();
        String passstr=password.getText().toString();

        String z="";
        boolean isSuccess=false;

        String un, pwd;

        @Override
        protected void onPreExecute() {
            progressDialog.setMessage("Loading...");
            progressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            if(unamestr.trim().equals("")|| passstr.trim().equals(""))
                z = "Please enter all fields..";
            else {
                try {
                    Connection con = connectionClass.CONN();
                    if (con == null) {
                        z = "Please check your internet connection";
                    } else {
                        String query=" select * from users where username like '"+unamestr+"' and userPass like '"+passstr+"' ";
                        Statement stmt = con.createStatement();
                        ResultSet rs=stmt.executeQuery(query);
                        while (rs.next()) {
                            un= rs.getString("username");
                            pwd=rs.getString("userPass");

                            if(un.equals(unamestr)&& pwd.equals(passstr)) {
                                isSuccess=true;
                                z = "Login successful! Hello " +un ;
                            } else {
                                isSuccess = false;
                                z = "Login error! Please check your credentials correctly";
                            } } } }
                catch (Exception ex) {
                    isSuccess = false;
                    z = "Exceptions"+ex;
                } }
            return z;
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(getBaseContext(),""+z,Toast.LENGTH_LONG).show();

            if(isSuccess)
            {
                Intent intent=new Intent(Login.this,MainMenu.class);
                intent.putExtra("name",unamestr);
                startActivity(intent);
            }
            progressDialog.hide();
        }
    }
}
