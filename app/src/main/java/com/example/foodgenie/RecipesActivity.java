package com.example.foodgenie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class RecipesActivity extends AppCompatActivity {

    SearchView searchView;

    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private List<Item> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        requestQueue = VolleySingleton.getInstance(this).getRequestQueue();

        list = new ArrayList<>();

        searchView = findViewById(R.id.search_recipe_view);
        searchView.setSubmitButtonEnabled(true);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                fetchRecipe(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void fetchRecipe(String query){

        String url = "https://api.edamam.com/search?app_id=900da95e&app_key=40698503668e0bb3897581f4766d77f9&q="+query;

//        Button button;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("hits");
                    list.clear();
                    for (int i = 0; i < jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i).getJSONObject("recipe");
                        String imgUrl = jsonObject.getString("image");
                        String name = jsonObject.getString("label");
                        String labels = jsonObject.getString("source");
                        String servings = jsonObject.getString("yield");
                        String tCalories = jsonObject.getString("calories");
                        String protein = jsonObject.getJSONObject("totalNutrients").getJSONObject("PROCNT").getString("quantity");
                        String fat = jsonObject.getJSONObject("totalNutrients").getJSONObject("FAT").getString("quantity");
                        String carb = jsonObject.getJSONObject("totalNutrients").getJSONObject("CHOCDF").getString("quantity");
                        String cholesterol = jsonObject.getJSONObject("totalNutrients").getJSONObject("CHOLE").getString("quantity");
                        String sodium = jsonObject.getJSONObject("totalNutrients").getJSONObject("NA").getString("quantity");
                        String calcium = jsonObject.getJSONObject("totalNutrients").getJSONObject("CA").getString("quantity");
                        String magnesium = jsonObject.getJSONObject("totalNutrients").getJSONObject("MG").getString("quantity");
                        String potassium = jsonObject.getJSONObject("totalNutrients").getJSONObject("K").getString("quantity");
                        String iron = jsonObject.getJSONObject("totalNutrients").getJSONObject("FE").getString("quantity");
                        String seeRecipe = jsonObject.getString("url");

                        Item item = new Item(imgUrl, name, labels, servings.split(Pattern.quote("."))[0], tCalories.split(Pattern.quote("."))[0], protein.split(Pattern.quote("."))[0], fat.split(Pattern.quote("."))[0], carb.split(Pattern.quote("."))[0], cholesterol.split(Pattern.quote("."))[0], sodium.split(Pattern.quote("."))[0], calcium.split(Pattern.quote("."))[0], magnesium.split(Pattern.quote("."))[0], potassium.split(Pattern.quote("."))[0], iron.split(Pattern.quote("."))[0], seeRecipe
                                );
                        list.add(item);

                    }

                    RecyclerAdapter adapter = new RecyclerAdapter(RecipesActivity.this, list);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                } catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RecipesActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonObjectRequest);

//        button = findViewById(R.id.see_recipe_btn);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                int position = recyclerView.getChildAdapterPosition((View) v.getParent());
//                Item clickedItem = list.get(position);
//                String seeRecipe = clickedItem.getFoodSeeRecipe();
//
//                Uri uri = Uri.parse(seeRecipe.toString()); // missing 'http://' will cause crashed
//                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                startActivity(intent);
//            }
//        });

    }

}