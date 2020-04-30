package com.project.homefoodlatest;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.project.homefoodlatest.ClassListSearch;
import com.project.homefoodlatest.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<ClassListSearch> values;
    public Context context;
    //Intent intent = getIntent();
    //final String unamestr = intent.getStringExtra("name");

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView tvTitle, tvCalories, tvPrepTime, tvIngre, tvDir, tvUname;
        public ImageView recipeImg;
        public View layout;

        public ViewHolder(View v)
        {
            super(v);
            layout = v;
            //tvTitle = (TextView) v.findViewById(R.id.tvTitle);
            //tvUname = (TextView)v.findViewById(R.id.tvUname);
//                tvPrepTime = (TextView) v.findViewById(R.id.tvPrepTime);
//                tvCalories = (TextView) v.findViewById(R.id.tvCalories);
            tvIngre = (TextView) v.findViewById(R.id.tvIngre);
//                tvDir = (TextView) v.findViewById(R.id.tvDir);
//                recipeImg = (ImageView) v.findViewById(R.id.recipeImg);
        }
    }

    // Constructor
    public MyAdapter(List<ClassListSearch> myDataset,Context context)
    {
        values = myDataset;
        this.context = context;
    }

    // Create new views (invoked by the layout manager) and inflates
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
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

        final ClassListSearch classListSearch = values.get(position);
        //holder.tvTitle.setText(classListRecipes.getTitle());
//            holder.tvCalories.setText(classListRecipes.getCalories());
//            holder.tvPrepTime.setText(classListRecipes.getPrepTime());
        holder.tvIngre.setText(classListSearch.getSearchIngre());
        //holder.tvUname.setText(classListSearch.getUsername());
//            holder.tvDir.setText(classListRecipes.getDirections());
//            Picasso.get()
//                    .load(classListRecipes.getImage())
//                    .resize(110,110)
//                    .centerCrop()
//                    .into(holder.recipeImg);
    }

    // get item count returns the list item count
    @Override
    public int getItemCount() {
        return values.size();
    }

}