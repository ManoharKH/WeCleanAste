package com.example.myapplication;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CitizenHomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CitizenHomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private float v=0;
    Button raiseComp, myComp;
    TextView raisec, compraise, myc, compmy;
    RelativeLayout yel, blck;
    ImageView yimg, bimg;


    public CitizenHomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CitizenHomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CitizenHomeFragment newInstance(String param1, String param2) {
        CitizenHomeFragment fragment = new CitizenHomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_citizen_home, container, false);

        raiseComp = view.findViewById(R.id.raisecomplaint);
        myComp = view.findViewById(R.id.mycomplaints);
        raisec = view.findViewById(R.id.raisew);
        compraise = view.findViewById(R.id.compw);
        myc = view.findViewById(R.id.myw);
        compmy = view.findViewById(R.id.compww);
        yel = view.findViewById(R.id.yclr);
        blck = view.findViewById(R.id.bclr);
        yimg = view.findViewById(R.id.rc_img);
        bimg = view.findViewById(R.id.mc_img);




        yel.setTranslationX(800);
        blck.setTranslationX(800);
        raisec.setTranslationX(800);
        compraise.setTranslationX(800);
        raiseComp.setTranslationX(800);
        myc.setTranslationX(800);
        compmy.setTranslationX(800);
        myComp.setTranslationX(800);
        yimg.setTranslationX(800);
        bimg.setTranslationX(800);

        yel.setAlpha(v);
        blck.setAlpha(v);
        raisec.setAlpha(v);
        compraise.setAlpha(v);
        raiseComp.setAlpha(v);
        myc.setAlpha(v);
        compmy.setAlpha(v);
        myComp.setAlpha(v);
        yimg.setAlpha(v);
        bimg.setAlpha(v);

        yel.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        raisec.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        compraise.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        raiseComp.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        yimg.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        blck.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        myc.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        compmy.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        myComp.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        bimg.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();







        return view;


    }






}