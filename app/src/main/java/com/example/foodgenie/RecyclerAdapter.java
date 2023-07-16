package com.example.foodgenie;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.regex.Pattern;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    Context context;
    List<Item> itemList;
    View view;

    public RecyclerAdapter(Context context, List<Item> itemList){
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recipe_card, parent, false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.setFoodImage(item.getFoodImage());
        holder.setFoodName(item.getFoodName());
        holder.setFoodLabels(item.getFoodLabels());
        holder.setFoodServings(item.getFoodServings());
        holder.setFoodTotalCalories(item.getFoodTotalCalories());
        holder.setFoodProtein(item.getFoodProtein());
        holder.setFoodFat(item.getFoodFat());
        holder.setFoodCarb(item.getFoodCarb());
        holder.setFoodCholesterol(item.getFoodCholesterol());
        holder.setFoodSodium(item.getFoodSodium());
        holder.setFoodCalcium(item.getFoodCalcium());
        holder.setFoodMagnesium(item.getFoodMagnesium());
        holder.setFoodPotassium(item.getFoodPotassium());
        holder.setFoodIron(item.getFoodIron());
        holder.setFoodSeeRecipe(item.getFoodSeeRecipe());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView foodImage;
        TextView foodName, foodLabels, foodServings, foodTotalCalories,
                foodProtein, foodFat, foodCarb, foodCholesterol, foodSodium,
                 foodCalcium, foodMagnesium, foodPotassium, foodIron;
        String foodSeeRecipe;
        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
        }

        public void setFoodImage(String url) {
            foodImage = view.findViewById(R.id.food_image);
            Glide.with(context).load(url).into(foodImage);
        }

        public void setFoodName(String fName) {
            foodName = view.findViewById(R.id.food_name);
            foodName.setText(fName);
        }

        public void setFoodLabels(String fLabels) {
            foodLabels = view.findViewById(R.id.food_labels);
            foodLabels.setText("From: " + fLabels);
        }

        public void setFoodServings(String fServings) {
            foodServings = view.findViewById(R.id.num_of_servings);
            foodServings.setText(fServings + " Servings");        }

        public void setFoodTotalCalories(String fTotalCalories) {
            foodTotalCalories = view.findViewById(R.id.total_calories);
            foodTotalCalories.setText(fTotalCalories + " kcal");               }

        public void setFoodProtein(String fProtein) {
            foodProtein = view.findViewById(R.id.protein_num);
            foodProtein.setText("Protein: " + fProtein + "g");
        }

        public void setFoodFat(String fFat) {
            foodFat = view.findViewById(R.id.fat_num);
            foodFat.setText("Fat: " + fFat + "g");
        }

        public void setFoodCarb(String fCarb) {
            foodCarb = view.findViewById(R.id.carb_num);
            foodCarb.setText("Carb: " + fCarb + "g");
        }

        public void setFoodCholesterol(String fCholesterol) {
            foodCholesterol = view.findViewById(R.id.cholestrol_num);
            foodCholesterol.setText("Cholesterol: " + fCholesterol + "mg");
        }

        public void setFoodSodium(String fSodium) {
            foodSodium = view.findViewById(R.id.sodium_num);
            foodSodium.setText("Sodium: " + fSodium + "mg");
        }

        public void setFoodCalcium(String fCalcium) {
            foodCalcium = view.findViewById(R.id.calcium_num);
            foodCalcium.setText("Calcium: " + fCalcium + "mg");
        }

        public void setFoodMagnesium(String fMagnesium) {
            foodMagnesium = view.findViewById(R.id.magnesium_num);
            foodMagnesium.setText("Magnesium: " + fMagnesium + "mg");
        }

        public void setFoodPotassium(String fPotassium) {
            foodPotassium = view.findViewById(R.id.potassium_num);
            foodPotassium.setText("Potassium: " + fPotassium + "mg");
        }

        public void setFoodIron(String fIron) {
            foodIron = view.findViewById(R.id.iron_num);
            foodIron.setText("Iron: " + fIron + "mg");
        }

        public void setFoodSeeRecipe(String foodSeeRecipe) {
            this.foodSeeRecipe = foodSeeRecipe;
        }
    }
}
