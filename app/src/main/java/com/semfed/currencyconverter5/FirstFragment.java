package com.semfed.currencyconverter5;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstFragment extends Fragment{

    private double rateFromEur;
    String currency1;
    String currency2;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FirstFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FirstFragment newInstance(String param1, String param2) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
   }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_first, container, false);
        EditText enteredValue = (EditText) view.findViewById(R.id.editTextAmount);
        Spinner firstCurrency = (Spinner) view.findViewById(R.id.sp1);
        Spinner secondCurrency = (Spinner) view.findViewById(R.id.sp2);
        TextView tvResult = (TextView) view.findViewById(R.id.result);

        enteredValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().trim().length() > 0) {
                    double input = Double.parseDouble(enteredValue.getText().toString());
                    currency1 = firstCurrency.getSelectedItem().toString();
                    currency2 = secondCurrency.getSelectedItem().toString();
                    //double firstCoefficient = findValueFromCurrency(conversionMatrix,currency1);
                    //double secondCoefficient = findValueFromCurrency(conversionMatrix,currency2);
                    //float tot= (float) ((input / firstCoefficient) * secondCoefficient);
                    //tvResult.setText(tot+"");
                    double conversionValue = 0;

                    tvResult.setText(conversionValue+"");
                }
            }
        });


        return view;
    }

    /*
    String[][] conversionMatrix = {
            {"EUR","1"},
            {"SEK","10.18"},
            {"USD","1.12"},
            {"GBP","0.84"},
            {"CNY","7.19"},
            {"JPY","129.46"},
            {"KRW","1339.26"},
    };

     */

    public double findValueFromCurrency(String[][] matrix, String currency) {
        for (String[] strings : matrix) {
            if (strings[0].compareTo(currency) == 0)
                return Double.parseDouble(strings[1]);
        }
        return 0;
    }

}