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

public class SearchIngre extends AppCompatActivity
{
    EditText ingre1, ingre2, ingre3;
    Button btnSubmit;
    ProgressDialog progressDialog;
    ConnectionClass connectionClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_ingre);

        ingre1= (EditText) findViewById(R.id.ingre1);
        ingre2= (EditText) findViewById(R.id.ingre2);
        ingre3= (EditText) findViewById(R.id.ingre3);
        btnSubmit= (Button) findViewById(R.id.btnSubmit);

        connectionClass = new ConnectionClass();

        progressDialog=new ProgressDialog(this);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DoSearch dosearch=new DoSearch();
                dosearch.execute();
            }
        });
    }

    private class DoSearch extends AsyncTask<String,String,String>{


        String ingre1str=ingre1.getText().toString();
        String ingre2str=ingre2.getText().toString();
        String ingre3str=ingre3.getText().toString();

        String ingred = ingre1str + ingre2str + ingre3str;

        Intent intent = getIntent();
        final String unamestr = intent.getStringExtra("name");


        String z="";
        boolean isSuccess=false;

        String in1, in2, in3, u;

        @Override
        protected void onPreExecute() {
            progressDialog.setMessage("Loading...");
            progressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            if(ingre1str.trim().equals(""))
                z = "Error! Please enter the required field!";
            else {
                try {
                    Connection con = connectionClass.CONN();
                    if (con == null) {
                        z = "Please check your internet connection";
                    } else {
                        Statement stmt = con.createStatement();

                        ResultSet rs=stmt.executeQuery
                                (" select * from recipes where ingredients like '%"+ingre1str+"%' or '%"+ingre2str+"%' or '%"+ingre3str+"%'");
                        while (rs.next()) {
                            in1= rs.getString("ingredients");
                            in2=rs.getString("ingredients");
                            in3=rs.getString("ingredients");

                            if(in1.contains(ingre1str)|| in2.contains(ingre2str) || in3.contains(ingre3str)) {
                                isSuccess=true;
                                z = "Data Found!" ;
                            } else {
                                isSuccess = false;
                                z = "Sorry! Zero Data Found!"; } } } }
                catch (Exception ex) {
                    isSuccess = false;
                    z = "Exceptions"+ex; } }return z; }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(getBaseContext(),""+z,Toast.LENGTH_LONG).show();

            if(isSuccess)
            {
                Intent intent=new Intent(SearchIngre.this,SearchResult.class);
                intent.putExtra("ing1",ingre1str);
                intent.putExtra("ing2", ingre2str);
                intent.putExtra("ing3", ingre3str);
                startActivity(intent);
            }
            progressDialog.hide();
        }
    }
}
