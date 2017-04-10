package com.example.android.coms;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Knayak on 28/02/2017.
 */

public class Recharge extends Fragment implements View.OnClickListener {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recharge, container, false);
        Button bChangePack = (Button) rootView.findViewById(R.id.bChangePack);
        Button bMakePay = (Button) rootView.findViewById(R.id.bMakePay);
        bChangePack.setOnClickListener(this);
        bMakePay.setOnClickListener(this);
        return rootView;
    }

    public void onClick(View v) {
        FragmentManager fragmentManager = getFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (v.getId()) {
            case R.id.bChangePack:
                Fragment packages = new Packages();
                fragmentTransaction.replace(R.id.frame_container, packages);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
            case R.id.bMakePay:
                Context context = getContext();
                CharSequence text = "Payment Successfully!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                break;
        }

    }
}
