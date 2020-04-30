package com.project.homefoodlatest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MainMenu extends AppCompatActivity {

    ProgressDialog progressDialog;
    ConnectionClass connectionClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Intent intent = getIntent();
        final String unamestr = intent.getStringExtra("name");

        Button btnAll = findViewById(R.id.btnAll);

        connectionClass = new ConnectionClass();
        progressDialog=new ProgressDialog(this);

        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, AllRecipes.class);
                intent.putExtra("name", unamestr);
                startActivity(intent);
            }
        });

        Button btnSearchIngre = findViewById(R.id.btnSearchIngre);

        btnSearchIngre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenu.this, SearchIngre.class);
                intent.putExtra("name", unamestr);
                startActivity(intent);
            }
        });

        Button btnRecomm = findViewById(R.id.btnRecomm);

        btnRecomm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenu.this, ROTD.class);
                intent.putExtra("name", unamestr);
                startActivity(intent);
            }
        });

        Button btnEdit = findViewById(R.id.btnEdit);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenu.this, EditProfile.class);
                intent.putExtra("name", unamestr);
                startActivity(intent);
            }
        });

        Button btnLogout = findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenu.this, Login.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
