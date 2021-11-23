package com.semfed.currencyconverter5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TabLayout tabLayout;
    TableLayout tableCurrency;
    ViewPager2 pager2;
    FragmentAdapter adapter;
    Spinner s1, s2;
    String vs1, vs2;
    int i1, i2;
    double changeToSEK, changeFromSEK;
    float inputValue, outputValue;
    EditText eInput;
    TextView tOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        s1 = (Spinner) findViewById(R.id.sp1);
        s2 = (Spinner) findViewById(R.id.sp2);
        eInput = (EditText) findViewById(R.id.editTextAmount);
        tOutput = (TextView) findViewById(R.id.result);

        tableCurrency = (TableLayout) findViewById(R.id.tabcurr);

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




        eInput.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void afterTextChanged(Editable s) {
       /*
                //we get the value to convert
                eInput.getText().toString();
                inputValue = Float.parseFloat(eInput.getText().toString());
                tOutput.setText(outputValue + "");

                vs1 = s1.getSelectedItem().toString();
                vs2 = s2.getSelectedItem().toString();


                for (int i = 1; i <= tableCurrency.getChildCount(); i++) {

                    //qui definisco una riga per ogni I
                    TableRow row = (TableRow) tableCurrency.getChildAt(i);
                    //per ogni riga leggo tutti gli elementi della prima colonna
                    TextView tv = (TextView) row.getChildAt(0);

                    if (vs1.contentEquals(tv.getText())) {
                        i1 = i;
                    }

                    if (vs2.contentEquals(tv.getText())) {
                        i2 = i;
                    }

                    TableRow rigas1 = (TableRow) tableCurrency.getChildAt(i1);
                    TableRow rigas2 = (TableRow) tableCurrency.getChildAt(i2);
                    TextView changeCurrency1 = (TextView) rigas1.getChildAt(1);
                    TextView changeCurrency2 = (TextView) rigas2.getChildAt(1);

                    changeToSEK = 1 / (Float.parseFloat((String) changeCurrency1.getText()));
                    changeFromSEK = Float.parseFloat((String) changeCurrency2.getText());
                    outputValue = (float) ((inputValue / changeToSEK) * changeFromSEK);

                }

                */
            }


        });

        pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}


