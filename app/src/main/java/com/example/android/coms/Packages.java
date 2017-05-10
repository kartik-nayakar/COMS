package com.example.android.coms;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import java.util.List;

/**
 * Created by Knayak on 28/02/2017.
 */

public class Packages extends Fragment implements View.OnClickListener, MultiSelectionSpinner.OnMultipleItemsSelectedListener{
    private String radioButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.packages,container,false);
        Button bPay = (Button) view.findViewById(R.id.bPay);


        //************ MultiSelectionSpinner
        String[] array = {"English", "Hindi", "Marathi", "Tamil", "Malayalam", "Telugu"};
        MultiSelectionSpinner multiSelectionSpinner = (MultiSelectionSpinner) view.findViewById(R.id.spinner_packs);
        multiSelectionSpinner.setItems(array);
        multiSelectionSpinner.setSelection(new int[]{});
        multiSelectionSpinner.setListener(this);
        //************
        bPay.setOnClickListener(this);
        //******************* spinner to view Months for payment
        Spinner spinner = (Spinner) view.findViewById(R.id.pay_spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.months_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        //*******************

        //*********** radio button
        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                if(checkedId == R.id.radioButton1) {
                    radioButton = "SD";
                }else {
                    radioButton= "HD";
                }
            }
        });
        //***********
        //******** View Visibility
        Button btn = (Button) view.findViewById(R.id.btn);
        btn.setOnClickListener(this);
        bPay.setOnClickListener(this);
         //*********

        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.bPay :
                Fragment recharge = new Recharge();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_container, recharge);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
            case R.id.btn :

                break;
        }
    }


    @Override
    public void selectedIndices(List<Integer> indices) {

    }

    @Override
    public void selectedStrings(List<String> strings) {

    }

}
