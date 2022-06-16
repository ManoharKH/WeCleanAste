package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class AdminHomeActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    MeowBottomNavigation bottomNavigation;
    int fragmentID = 1;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        bottomNavigation = findViewById(R.id.bottom_nav);
        bottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.ic_pending));
        bottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.ic_add_driver));
        bottomNavigation.add(new MeowBottomNavigation.Model(4,R.drawable.ic_in_process));
        bottomNavigation.add(new MeowBottomNavigation.Model(5,R.drawable.ic_work_completed));
        bottomNavigation.add(new MeowBottomNavigation.Model(6,R.drawable.ic_resolvedcomp));

        drawerLayout = findViewById(R.id.admin_drawerlayout);
        navigationView = findViewById(R.id.admin_navigation_drawer);

        //getActionBar().hide();
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {

                Fragment fragment = null;
                switch (item.getId()){
                    case 1:
                        fragment = new AdminHomeFragment();
                        break;

                    case 2:
                        fragment = new AdminPendingComplaintFragment();
                        break;

                    case 3:
                        fragment = new AddDriverToComplaintFragment();
                        break;

                    case 4:
                        fragment = new AdminWorkInProgressFragment();
                        break;

                    case 5:
                        fragment = new AdminWorkCompletedFragment();
                        break;

                    case 6:
                        fragment = new AdminResolvedComplaintFragment();
                        break;
                }
                loadFragment(fragment);
            }
        });

//        fragmentID = getIntent().getExtras().getInt("fragmentId");
        bottomNavigation.show(fragmentID,true);

        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                if (item.getId() == 1){
                    Toast.makeText(AdminHomeActivity.this,"Home", Toast.LENGTH_SHORT).show();
                }
                else if (item.getId() == 2){
                    Toast.makeText(AdminHomeActivity.this,"Pending Complaints",Toast.LENGTH_SHORT).show();
                }else if (item.getId() == 3){
                    Toast.makeText(AdminHomeActivity.this,"Add Driver",Toast.LENGTH_SHORT).show();
                }else if (item.getId() ==4){
                    Toast.makeText(AdminHomeActivity.this,"Work In Progress",Toast.LENGTH_SHORT).show();
                }else if (item.getId() == 5){
                    Toast.makeText(AdminHomeActivity.this,"Work Completed",Toast.LENGTH_SHORT).show();
                }else if (item.getId() == 6){
                    Toast.makeText(AdminHomeActivity.this,"Resolved Complaints",Toast.LENGTH_SHORT).show();
                }
            }
        });

        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {

            }
        });
        mAuth = FirebaseAuth.getInstance();


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

                    case R.id.nav_pend_cmp:
                        fragment = new AdminPendingComplaintFragment();
                        loadFragment(fragment);
                        break;

                    case R.id.nav_add_driver:
                        fragment = new AddDriverToComplaintFragment();
                        loadFragment(fragment);
                        break;

                    case R.id.nav_in_progress:
                        fragment = new AdminWorkInProgressFragment();
                        loadFragment(fragment);
                        break;

                    case R.id.nav_resolved_comp:
                        fragment = new AdminResolvedComplaintFragment();
                        loadFragment(fragment);
                        break;

                    case R.id.nav_admin_profile:
                        Toast.makeText(AdminHomeActivity.this, "Admin Details", Toast.LENGTH_SHORT).show();
                        Intent it = new Intent(AdminHomeActivity.this, ProfileActivity.class);
                        startActivity(it);
                        overridePendingTransition(0,0);
                        break;

                    case R.id.nav_admin_logout:
                        Toast.makeText(AdminHomeActivity.this, "Admin logged out", Toast.LENGTH_SHORT).show();
                        mAuth.signOut();
                        Intent i = new Intent(AdminHomeActivity.this,LoginActivity.class);
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
                .replace(R.id.id_framelayout,fragment)
                .commit();
    }

    // Code for Logout
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    /*@Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.logout:
                Toast.makeText(AdminHomeActivity.this,"User logged out",Toast.LENGTH_SHORT).show();
                mAuth.signOut();
                Intent it = new Intent(AdminHomeActivity.this,LoginActivity.class);
                startActivity(it);
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/
}