package com.project.homefoodlatest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.Statement;


public class SignUp extends AppCompatActivity

{
    EditText fName, uName, password, email;
    Button btnSignUp;
    ProgressDialog progressDialog;
    ConnectionClass connectionClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        fName= (EditText) findViewById(R.id.fName);
        email= (EditText) findViewById(R.id.email);
        password= (EditText) findViewById(R.id.password);
        uName= (EditText) findViewById(R.id.uName);
        btnSignUp= (Button) findViewById(R.id.btnSignUp);

        connectionClass = new ConnectionClass();

        progressDialog=new ProgressDialog(this);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Doregister doregister = new Doregister();
                doregister.execute("");
            }
        });
    }

    public class Doregister extends AsyncTask<String,String,String>
    {


        String fullnamestr=fName.getText().toString();
        String usernamestr=uName.getText().toString();
        String emailstr=email.getText().toString();
        String passstr=password.getText().toString();

        String z="";
        boolean isSuccess=false;

        @Override
        protected void onPreExecute() {
            progressDialog.setMessage("Loading..");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            if(fullnamestr.trim().equals("")|| usernamestr.trim().equals("")|| emailstr.trim().equals("") || passstr.trim().equals(""))
                z = "Please enter all fields....";
            else {
                try {
                    Connection con = connectionClass.CONN();
                    if (con == null) {
                        z = "Please check your internet connection";
                    } else {

                        String query="insert into users (userFName, username, userPass, userEmail) " +
                                "values('"+fullnamestr+"','"+usernamestr+"','"+passstr+"','"+emailstr+"')";

                        Statement stmt = con.createStatement();
                        stmt.executeUpdate(query);

                        z = "Register successful";
                        isSuccess=true;
                    }
                }
                catch (Exception ex) {
                    isSuccess = false;
                    z = "Exceptions"+ex;
                } } return z; }

        @Override

        protected void onPostExecute(String s) {

            Toast.makeText(getBaseContext(),""+z,Toast.LENGTH_LONG).show();


            if(isSuccess) {
                startActivity(new Intent(SignUp.this, Login.class));

            }


            progressDialog.hide();
        }
    }
}
