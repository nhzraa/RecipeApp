package com.project.homefoodlatest;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ROTD extends AppCompatActivity {

    private ArrayList<ClassListRecipes> recipeArrayList;  //List items Array
    private MyAppAdapter myAppAdapter; //Array Adapter
    private RecyclerView recyclerView; //RecyclerView
    private RecyclerView.LayoutManager mLayoutManager;
    private boolean isSuccess = false; // boolean

    ProgressDialog progressDialog;

    private static final String DB_URL = "jdbc:mysql://172.20.10.3:3310/recipe_app"; //"jdbc:mysql://DATABASE_IP/DATABASE_NAME";
    private static final String USER = "root";
    private static final String PASS = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotd);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView); //REcyclerview Declaration
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        recipeArrayList = new ArrayList<ClassListRecipes>();// Arraylist Initialization

        progressDialog=new ProgressDialog(this);

        DoAll doAll = new DoAll();
        doAll.execute("");
    }

    private class DoAll extends AsyncTask<String,String,String> {

        public ImageView fav;

        String z = "Internet/DB_Credentials/Windows_FireWall_TurnOn Error, See Android Monitor in the bottom For details!";
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(ROTD.this, "Synchronising",
                    "Recipes are Loading! Please Wait...", true);
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); //Connection Object
                if (conn == null) {
                    z = "Please check your internet connection";
                    isSuccess = false; }
                else {
                    String query="select * from recipes where ingredients like '%ing1%' or '%ing2%' or '%ing3%' order by rand() limit 1";
                    Statement stmt = conn.createStatement();
                    ResultSet rs=stmt.executeQuery(query);
                    if (rs != null) {
                        while (rs.next()) {
                            try {
                                recipeArrayList.add(new ClassListRecipes(
                                        rs.getString(1), rs.getString(2), rs.getString(3),
                                        rs.getString(4), rs.getString(5), rs.getString(6),
                                        rs.getString(7), rs.getString(8)));
                            } catch (Exception ex) { ex.printStackTrace(); } }
                        z = "Found";
                        isSuccess = true; }
                    else {
                        z = "No Data found!";
                        isSuccess = false; } } }
            catch (Exception ex) {
                ex.printStackTrace();
                Writer writer = new StringWriter();
                ex.printStackTrace(new PrintWriter(writer));
                z = writer.toString();
                isSuccess = false; }return z; }

        @Override
        protected void onPostExecute(String z) {

            progressDialog.dismiss();
            Toast.makeText(ROTD.this, z + "", Toast.LENGTH_LONG).show();

            if(isSuccess == true)
            {
                try
                {
                    myAppAdapter = new MyAppAdapter(recipeArrayList , ROTD.this);
                    recyclerView.setAdapter(myAppAdapter);
                } catch (Exception ex) { }
            }

            else { }
        }
    }

    public class MyAppAdapter extends RecyclerView.Adapter<MyAppAdapter.ViewHolder> {
        private List<ClassListRecipes> values;
        public Context context;
        Intent intent = getIntent();
        final String unamestr = intent.getStringExtra("name");

        public class ViewHolder extends RecyclerView.ViewHolder
        {
            public TextView tvTitle, tvCalories, tvPrepTime, tvIngre, tvDir;
            public ImageView recipeImg;
            public View layout;

            public ViewHolder(View v)
            {
                super(v);
                layout = v;
                tvTitle = (TextView) v.findViewById(R.id.tvTitle);
//                tvPrepTime = (TextView) v.findViewById(R.id.tvPrepTime);
//                tvCalories = (TextView) v.findViewById(R.id.tvCalories);
                tvIngre = (TextView) v.findViewById(R.id.tvIngre);
                tvDir = (TextView) v.findViewById(R.id.tvDir);
                recipeImg = (ImageView) v.findViewById(R.id.recipeImg);
            }
        }

        // Constructor
        public MyAppAdapter(List<ClassListRecipes> myDataset,Context context)
        {
            values = myDataset;
            this.context = context;
        }

        // Create new views (invoked by the layout manager) and inflates
        @Override
        public MyAppAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            // create a new view
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View v = inflater.inflate(R.layout.rotd_layout, parent, false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        // Binding items to the view
        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {

            final ClassListRecipes classListRecipes = values.get(position);
            holder.tvTitle.setText(classListRecipes.getTitle());
//            holder.tvCalories.setText(classListRecipes.getCalories());
//            holder.tvPrepTime.setText(classListRecipes.getPrepTime());
            holder.tvIngre.setText(classListRecipes.getIngredients());
            holder.tvDir.setText(classListRecipes.getDirections());
            Picasso.get()
                    .load(classListRecipes.getImage())
                    .resize(110,110)
                    .centerCrop()
                    .into(holder.recipeImg);
        }

        // get item count returns the list item count
        @Override
        public int getItemCount() {
            return values.size();
        }

    }
}