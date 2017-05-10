package com.example.android.coms;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Knayak on 25/03/2017.
 */

public class Home extends Fragment implements View.OnClickListener {
    CoordinatorLayout coordinatorLayout;
    Button bLogout;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_home, container, false);
        // Inflate the layout for this fragment
                bLogout = (Button) view.findViewById(R.id.bLogout);
                bLogout.setOnClickListener(this);
                Button bMyAccount = (Button) view.findViewById(R.id.bMyAccount);
                bMyAccount.setOnClickListener(this);
                Button bPacks = (Button) view.findViewById(R.id.bPacks);
                bPacks.setOnClickListener(this);
                Button bRecharge = (Button) view.findViewById(R.id.bRecharge);
                bRecharge.setOnClickListener(this);
                Button bOffers = (Button) view.findViewById(R.id.bOffers);
                bOffers.setOnClickListener(this);
                Button bTransfer = (Button) view.findViewById(R.id.bTransfer);
                bTransfer.setOnClickListener(this);
                coordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.coordinatorLayout);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bLogout:
                Snackbar snackbar = Snackbar.make(coordinatorLayout, "Long Press to Logout!", Snackbar.LENGTH_LONG);
                snackbar.show();

                bLogout.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        Intent myIntent = new Intent(getActivity(), LoginActivity.class);
                        getActivity().startActivity(myIntent);
                        getActivity().finish();
                        return true;
                    }
                });
                break;
            case R.id.bMyAccount:
                MyAccount account = new MyAccount();
                this.getFragmentManager().beginTransaction()
                        .replace(R.id.frame_container, account, null)
                        .addToBackStack(null)
                        .commit();
                HomeActivity.changeDrawerItem(1);
                break;
            case R.id.bPacks:
                Packages packs = new Packages();
                this.getFragmentManager().beginTransaction()
                        .replace(R.id.frame_container, packs, null)
                        .addToBackStack(null)
                        .commit();
                HomeActivity.changeDrawerItem(2);
                break;
            case R.id.bRecharge:
                Recharge recharge = new Recharge();
                this.getFragmentManager().beginTransaction()
                        .replace(R.id.frame_container, recharge, null)
                        .addToBackStack(null)
                        .commit();
                HomeActivity.changeDrawerItem(3);
                break;
            case R.id.bOffers:
                Offers offers = new Offers();
                this.getFragmentManager().beginTransaction()
                        .replace(R.id.frame_container, offers, null)
                        .addToBackStack(null)
                        .commit();
                HomeActivity.changeDrawerItem(4);
                break;
            case R.id.bTransfer:
                Transfer transfer = new Transfer();
                this.getFragmentManager().beginTransaction()
                        .replace(R.id.frame_container, transfer, null)
                        .addToBackStack(null)
                        .commit();
                HomeActivity.changeDrawerItem(5);
                break;
        }
    }

}
