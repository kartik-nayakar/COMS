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
import android.widget.Spinner;

/**
 * Created by Knayak on 28/02/2017.
 */

public class Packages extends Fragment implements View.OnClickListener{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.packages,container,false);
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
        Button bPay = (Button) view.findViewById(R.id.bPay);
        bPay.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        Fragment recharge = new Recharge();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_container, recharge);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
