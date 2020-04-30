package com.project.homefoodlatest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class EditProfile extends AppCompatActivity

{
    EditText fName, uName, password, email;
    Button btnUpdate, btnDelete;
    ProgressDialog progressDialog;
    ConnectionClass connectionClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        fName= (EditText) findViewById(R.id.fName);
        email= (EditText) findViewById(R.id.email);
        password= (EditText) findViewById(R.id.password);
        uName= (EditText) findViewById(R.id.uName);
        btnUpdate= (Button) findViewById(R.id.btnUpdate);
        btnDelete= (Button) findViewById(R.id.btnDelete);

        Intent intent = getIntent();
        final String unamestr = intent.getStringExtra("name");

        progressDialog=new ProgressDialog(this);
        connectionClass = new ConnectionClass();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DoEdit doEdit = new DoEdit();
                doEdit.execute("");
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DoDelete doDelete = new DoDelete();
                doDelete.execute("");
            }
        });

    }

    public class DoEdit extends AsyncTask<String,String,String>
    {

        String fullnamestr=fName.getText().toString();
        String emailstr=email.getText().toString();
        String passstr=password.getText().toString();

        Intent intent = getIntent();
        final String unamestr = intent.getStringExtra("name");


        String z="";
        boolean isSuccess=false;

        String un, pwd;

        @Override
        protected void onPreExecute() {
            progressDialog.setMessage("Loading..");
            progressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            if(fullnamestr.trim().equals("")|| emailstr.trim().equals("") || passstr.trim().equals(""))
                z = "Please enter all fields....";
            else
            {
                try {
                    Connection con = connectionClass.CONN();
                    if (con == null) {
                        z = "Please check your internet connection";
                    } else {

                        String query="update users set userFname = '"+fullnamestr+"', userPass = '"+passstr+"', userEmail = '"+emailstr+"' where username = '"+unamestr+"' ";

                        Statement stmt = con.createStatement();
                        stmt.executeUpdate(query);

                        z = "Profile Updated!";
                        isSuccess=true;


                    }
                }
                catch (Exception ex)
                {
                    isSuccess = false;
                    z = "Exceptions"+ex;
                }
            }
            return z;
        }

        @Override

        protected void onPostExecute(String s) {

            Toast.makeText(getBaseContext(),""+z,Toast.LENGTH_LONG).show();


            if(isSuccess) {
                startActivity(new Intent(EditProfile.this, MainMenu.class));
            }

            progressDialog.hide();
        }
    }

    public class DoDelete extends AsyncTask<String,String,String>
    {

        String fullnamestr=fName.getText().toString();
        String emailstr=email.getText().toString();
        String passstr=password.getText().toString();

        Intent intent = getIntent();
        final String unamestr = intent.getStringExtra("name");

        String z="";
        boolean isSuccess=false;

        String un, pwd;

        @Override
        protected void onPreExecute() {
            progressDialog.setMessage("Loading..");
            progressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

//            if(fullnamestr.trim().equals("")|| emailstr.trim().equals("") || passstr.trim().equals(""))
//                z = "Please enter all fields....";
//            else
//            {
                try {
                    Connection con = connectionClass.CONN();
                    if (con == null) {
                        z = "Please check your internet connection";
                    } else {

                        String query=" DELETE FROM users WHERE username = '"+unamestr+"' ";

                        Statement stmt = con.createStatement();
                        stmt.executeUpdate(query);

                        z = "Profile Successfully Deleted!";
                        isSuccess=true;
                    }
                }
                catch (Exception ex)
                {
                    isSuccess = false;
                    z = "Exceptions"+ex;
                }
//            }
            return z;
        }

        @Override

        protected void onPostExecute(String s) {

            Toast.makeText(getBaseContext(),""+z,Toast.LENGTH_LONG).show();


            if(isSuccess) {
                startActivity(new Intent(EditProfile.this, Login.class));
            }

            progressDialog.hide();
        }
    }
}
