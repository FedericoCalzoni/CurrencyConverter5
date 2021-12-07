package com.semfed.currencyconverter5;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity{
    TabLayout tabLayout;
    ViewPager2 pager2;
    FragmentAdapter adapter;
    private static String url = "http://data.fixer.io/api/latest?access_key=349fbdb83017f2b6ca306351be7b82e1";
    String[][] conversionMatrix = new String[7][2];
    private String myString = "hello";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        tabLayout = findViewById(R.id.tab_layout);
        pager2 = findViewById(R.id.view_pager2);

        FragmentManager fm = getSupportFragmentManager();
        adapter = new FragmentAdapter(fm, getLifecycle());
        pager2.setAdapter(adapter);

        tabLayout.addTab(tabLayout.newTab().setText("First"));
        tabLayout.addTab(tabLayout.newTab().setText("Second"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });



    }
    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            if (jsonStr != null) {
                try {

                    JSONObject jsonObj = new JSONObject(jsonStr);
                    JSONObject rates=jsonObj.getJSONObject("rates");
                    String base = jsonObj.getString("base");
                    // looping through All
                    // tmp hash map for single contact
                    // adding each child node to HashMap key => value
                    Double rateEURDouble = rates.getDouble("EUR");
                    Double rateSEKDouble = rates.getDouble("SEK");
                    Double rateUSDDouble = rates.getDouble("USD");
                    Double rateGBPDouble = rates.getDouble("GBP");
                    Double rateCNYDouble = rates.getDouble("CNY");
                    Double rateJPYDouble = rates.getDouble("JPY");
                    Double rateKRWDouble = rates.getDouble("KRW");




                    conversionMatrix[0][0]="EUR";
                    conversionMatrix[1][0]="SEK";
                    conversionMatrix[2][0]="USD";
                    conversionMatrix[3][0]="GBP";
                    conversionMatrix[4][0]="CNY";
                    conversionMatrix[5][0]="JPY";
                    conversionMatrix[6][0]="KRW";
                    conversionMatrix[0][1]= rateEURDouble.toString();
                    conversionMatrix[1][1]= rateSEKDouble.toString();
                    conversionMatrix[2][1]= rateUSDDouble.toString();
                    conversionMatrix[3][1]= rateGBPDouble.toString();
                    conversionMatrix[4][1]= rateCNYDouble.toString();
                    conversionMatrix[5][1]= rateJPYDouble.toString();
                    conversionMatrix[6][1]= rateKRWDouble.toString();

                    Log.d("DEGUB", conversionMatrix[5][1]);


                } catch (final JSONException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
        }

    }
}


