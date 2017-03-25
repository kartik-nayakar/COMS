package com.example.android.coms;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Knayak on 28/02/2017.
 */

public class MyAccount extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_account, container, false);
        // Inflate the layout for this fragment
        UserDetails ud= new UserDetails();
        String name= ud.getName();
        String username= ud.getUsername();
        String password= ud.getPassword();
        Long mob_num= ud.getMob_num();
        String email= ud.getEmail();
        String address= ud.getAddress();

        TextView textView11= (TextView) view.findViewById(R.id.textView11);
        textView11.setText(name);
        TextView textView13= (TextView) view.findViewById(R.id.textView13);
        textView13.setText(username);
        TextView textView15= (TextView) view.findViewById(R.id.textView15);
        textView15.setText(password);
        TextView textView17= (TextView) view.findViewById(R.id.textView17);
        textView17.setText(""+mob_num);
        TextView textView19= (TextView) view.findViewById(R.id.textView19);
        textView19.setText(email);
        TextView textView21= (TextView) view.findViewById(R.id.textView21);
        textView21.setText(address);

        return view;
    }
}
