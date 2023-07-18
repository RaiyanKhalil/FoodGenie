package com.example.foodgenie;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class NutrientActivity extends AppCompatActivity {

    ScrollView scrollView;
    SearchView searchView;

    private RequestQueue requestQueue;

    TextView caloriesOne, total_fatOne, cholesterolOne, sodiumOne, total_carbOne, proteinOne, calciumOne, ironOne, potassiumOne;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrient);

        requestQueue = VolleySingleton.getInstance(this).getRequestQueue();



        caloriesOne = findViewById(R.id.calories);
        total_fatOne = findViewById(R.id.total_fat);
        cholesterolOne = findViewById(R.id.cholesterol);
        sodiumOne = findViewById(R.id.sodium);
        total_carbOne = findViewById(R.id.total_carb);
        proteinOne = findViewById(R.id.protein);
        calciumOne = findViewById(R.id.calcium);
        ironOne = findViewById(R.id.iron);
        potassiumOne = findViewById(R.id.potassium);

        scrollView = findViewById(R.id.nutrition_val);
        scrollView.setVisibility(View.GONE);

        searchView = findViewById(R.id.search_nutrient_view);
        searchView.setSubmitButtonEnabled(true);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                fetchNutrients(query);
                scrollView.setVisibility(View.VISIBLE);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    private void fetchNutrients(String query) {

        String url = "https://api.edamam.com/api/nutrition-data?app_id=e856837c&app_key=a8c7a40631fa5315c857816e6e933b2c&nutrition-type=cooking&ingr="+query;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    String calories = response.getString("calories");
                    String totalFat = response.getJSONObject("totalNutrients").getJSONObject("FAT").getString("quantity");
                    String carb = response.getJSONObject("totalNutrients").getJSONObject("CHOCDF").getString("quantity");
                    String cholesterol = response.getJSONObject("totalNutrients").getJSONObject("CHOLE").getString("quantity");
                    String sodium = response.getJSONObject("totalNutrients").getJSONObject("NA").getString("quantity");
                    String calcium = response.getJSONObject("totalNutrients").getJSONObject("CA").getString("quantity");
                    String potassium = response.getJSONObject("totalNutrients").getJSONObject("K").getString("quantity");
                    String iron = response.getJSONObject("totalNutrients").getJSONObject("FE").getString("quantity");
                    String protein = response.getJSONObject("totalNutrients").getJSONObject("FIBTG").getString("quantity");


                    String energyCal = response.getJSONObject("totalNutrientsKCal").getJSONObject("ENERC_KCAL").getString("quantity");
                    String proteinCal = response.getJSONObject("totalNutrientsKCal").getJSONObject("PROCNT_KCAL").getString("quantity");
                    String fatCal = response.getJSONObject("totalNutrientsKCal").getJSONObject("FAT_KCAL").getString("quantity");
                    String carbCal = response.getJSONObject("totalNutrientsKCal").getJSONObject("CHOCDF_KCAL").getString("quantity");

                    //Enter energy values
                    caloriesOne.setText("Calories "+ calories.split(Pattern.quote("."))[0]);
                    total_fatOne.setText("Total Lipid Fat "+ totalFat.split(Pattern.quote("."))[0] + " g");
                    cholesterolOne.setText("Cholesterol "+ cholesterol.split(Pattern.quote("."))[0]  + " mg");
                    sodiumOne.setText("Sodium "+ sodium.split(Pattern.quote("."))[0] + " mg");
                    total_carbOne.setText("Sugar "+ carb.split(Pattern.quote("."))[0] + " g");
                    proteinOne.setText("Fiber "+ protein.split(Pattern.quote("."))[0] + " g");
                    calciumOne.setText("Calcium "+ calcium.split(Pattern.quote("."))[0] + " mg");
                    ironOne.setText("Iron "+ iron.split(Pattern.quote("."))[0] + " mg");
                    potassiumOne.setText("Potassium "+ potassium.split(Pattern.quote("."))[0] + " mg");



                    PieChart pieChart = findViewById(R.id.chart);

                    ArrayList<PieEntry> entries = new ArrayList<>();
                    entries.add(new PieEntry(Float.parseFloat(proteinCal), "Protein"));
                    entries.add(new PieEntry(Float.parseFloat(fatCal), "Fat"));
                    entries.add(new PieEntry(Float.parseFloat(carbCal), "Carbohydrate"));

                    PieDataSet pieDataSet = new PieDataSet(entries, "Total Energy " + energyCal );
                    pieDataSet.setValueTextSize(16);
                    pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);

                    PieData pieData = new PieData(pieDataSet);
                    pieChart.setData(pieData);

                    pieChart.getDescription().setEnabled(false);
                    pieChart.setCenterText(energyCal+ " kcal");
                    pieChart.setCenterTextSize(16);
                    pieChart.animateY(1000);
                    pieChart.invalidate();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(NutrientActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        VolleySingleton.getInstance(this).getRequestQueue().add(jsonObjectRequest);

    }

}