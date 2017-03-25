package com.example.android.coms;


import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private FragmentTransaction fragmentTransaction;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_container, new Home()).commit();

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected (MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_home:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame_container, new Home()).commit();
                        getSupportActionBar().setTitle("Home");
                        mDrawerLayout.closeDrawers();
                        break;

                    case R.id.nav_account:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame_container, new MyAccount()).commit();
                        getSupportActionBar().setTitle("My Profile");
                        mDrawerLayout.closeDrawers();
                        break;

                    case R.id.nav_package:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame_container, new Packages()).commit();
                        getSupportActionBar().setTitle("Channel Packs");
                        mDrawerLayout.closeDrawers();
                        break;

                    case R.id.nav_recharge:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame_container, new Recharge()).commit();
                        getSupportActionBar().setTitle("Recharge");
                        mDrawerLayout.closeDrawers();
                        break;

                    case R.id.nav_offers:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame_container, new Offers()).commit();
                        getSupportActionBar().setTitle("Offers and Discounts");
                        mDrawerLayout.closeDrawers();
                        break;

                    case R.id.nav_transfer:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame_container, new Transfer()).commit();
                        getSupportActionBar().setTitle("Transfer DTH");
                        mDrawerLayout.closeDrawers();
                        break;

                    case R.id.nav_logout:
                        mDrawerLayout.closeDrawers();
                        Intent intent2 = new Intent(HomeActivity.this, LoginActivity.class);
                        HomeActivity.this.startActivity(intent2);
                        finish();
                        break;


                }
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
