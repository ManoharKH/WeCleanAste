package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;


public class CitizenHomeActivity extends AppCompatActivity {

    Button raiseComplaint, mycomplaints;
    FirebaseAuth mAuth;
    MeowBottomNavigation bottomNavigation;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    ImageView menuIcon;

//    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citizen_home);


        bottomNavigation = findViewById(R.id.bottom_nav);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_drawer);
        menuIcon = findViewById(R.id.menu_icon);

//        getSupportActionBar().hide();
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


//
//       toolbar = findViewById(R.id.toolbar);
//       setSupportActionBar(toolbar);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar, R.string.open, R.string.close);
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();



//


        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_baseline_email_24));

        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_camera));
//        bottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.ic_baseline_arrow_back_24));

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else drawerLayout.openDrawer(GravityCompat.START);
            }
        });


        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {

                Fragment fragment = null;
                switch (item.getId()) {
                    case 1:
                        fragment = new CitizenHomeFragment();
                        break;

                    case 2:
                        fragment = new CitizenMycomplaintFragment();
                        break;

                    case 3:
                        fragment = new CitizenRaiseComplaintFragment();
                        break;
                }
                loadFragment(fragment);
            }
        });

        bottomNavigation.show(1, true);

        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                if (item.getId() == 1) {
                    Toast.makeText(CitizenHomeActivity.this, "Home", Toast.LENGTH_SHORT).show();
                } else if (item.getId() == 2) {
                    Toast.makeText(CitizenHomeActivity.this, "MyComplaints", Toast.LENGTH_SHORT).show();
                } else if (item.getId() == 3) {
                    Toast.makeText(CitizenHomeActivity.this, "RaiseComplaint", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {

            }
        });

//        raiseComplaint = (Button) findViewById(R.id.btn_raisecomplaint);
//        mycomplaints = (Button) findViewById(R.id.btn_mycomplaints);
        mAuth = FirebaseAuth.getInstance();

//        raiseComplaint.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent it = new Intent(CitizenHomeActivity.this,RaisecomplaintActivity.class);
//                startActivity(it);
//            }
//        });
//
//        mycomplaints.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent it = new Intent(CitizenHomeActivity.this,MyComplaintActivity.class);
//                startActivity(it);
//            }
//        });



        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment = null;
                switch (item.getItemId()){
                    case R.id.nav_home:
                        fragment =new CitizenHomeFragment();
                        loadFragment(fragment);
                        break;

                    case R.id.nav_my_cmp:
                        fragment = new CitizenMycomplaintFragment();
                        loadFragment(fragment);
                        break;

                    case R.id.nav_raise_cmp:
                        fragment = new CitizenRaiseComplaintFragment();
                        loadFragment(fragment);
                        break;

                    case R.id.nav_reportbug:
                        break;

                    case R.id.nav_privacy:
                        break;

                    case R.id.nav_profile:
                        Toast.makeText(CitizenHomeActivity.this, "User Details", Toast.LENGTH_SHORT).show();
                        Intent it = new Intent(CitizenHomeActivity.this, ProfileActivity.class);
                        startActivity(it);
                        overridePendingTransition(0,0);
                        break;

                    case R.id.nav_logout:
                        Toast.makeText(CitizenHomeActivity.this, "User logged out", Toast.LENGTH_SHORT).show();
                        mAuth.signOut();
                        Intent i = new Intent(CitizenHomeActivity.this, LoginActivity.class);
                        startActivity(i);
                        return true;

                    case R.id.nav_rate:
                        break;

                    case R.id.nav_share:
                        break;

                    default:
                        return true;


                }

                item.setChecked(true);
                drawerLayout.closeDrawers();


                return true;
            }
        });






    }


    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.id_framelayout, fragment)
                .commit();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }




    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


}