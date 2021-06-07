package com.moringaschool.kenyatravelguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navMenu;
    Toolbar toolbar;
    @BindView(R.id.userName) TextView signedInUserName;
    @BindView(R.id.userEmail) TextView signedInUserEmail;
    @BindView(R.id.userProfileImage) ImageView signedInUserProfileImage;
    @BindView(R.id.tourist_facilities) TextView tourist_facilities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        drawerLayout = findViewById(R.id.drawer_layout);
        navMenu = findViewById(R.id.navMenu);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
//        navMenu.setNavigationItemSelectedListener(this);
        navMenu.setCheckedItem(R.id.nav_home);

        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if(signInAccount != null){
            signedInUserProfileImage.setImageURI(signInAccount.getPhotoUrl());
            signedInUserName.setText(signInAccount.getDisplayName());
            signedInUserEmail.setText(signInAccount.getEmail());
        }

        tourist_facilities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenTripMapApi openTripMapApi = OpenTripMapClient.getClient();
                Call<TouristFacilitiesModelClass> call = openTripMapApi.getTouristFacilitiesKenya();

                call.enqueue(new Callback<TouristFacilitiesModelClass>() {
                    @Override
                    public void onResponse(Call<TouristFacilitiesModelClass> call, Response<TouristFacilitiesModelClass> response) {
                        if(response.isSuccessful()){
                            List<TouristFacilitiesModelClass> touristFacilitiesList = response.body().getTouristFacilities();
                            String[] touristFacilityNames = new String[touristFacilitiesList.size()];
                            String[] touristFacilityKind = new String[touristFacilitiesList.size()];

                            for(int i = 0; i<touristFacilityNames.length; i++){
                                touristFacilityNames[i] = touristFacilitiesList.get(i).getName();
                            }

                            for (int i = 0; i<touristFacilityKind.length; i++){
                                touristFacilityKind[i] = touristFacilitiesList.get(i).getKind();
                            }


                        }
                    }

                    @Override
                    public void onFailure(Call<TouristFacilitiesModelClass> call, Throwable t) {

                    }
                });
            }
        });

    }

    @Override
    public void onBackPressed(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull @NotNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:
                break;
            case R.id.nav_account:
                break;
            case R.id.nav_favorites:
                break;
            case R.id.nav_settings:
                break;
            case R.id.nav_help:
                break;
            case R.id.nav_information:
                break;
            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return super.onOptionsItemSelected(item);
    }

}