package com.moringaschool.kenyatravelguide.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.moringaschool.kenyatravelguide.adapters.KenyaSightingsListAdapter;
import com.moringaschool.kenyatravelguide.R;
import com.moringaschool.kenyatravelguide.adapters.TouristFacilitiesListAdapter;
import com.moringaschool.kenyatravelguide.models.KenyaSightingsClass;
import com.moringaschool.kenyatravelguide.models.TouristFacilitiesModelClass;
import com.moringaschool.kenyatravelguide.network.OpenTripMapApi;
import com.moringaschool.kenyatravelguide.network.OpenTripMapClient;

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
    RecyclerView sightingsRecyclerView;
    private KenyaSightingsListAdapter mSightingsListAdapter;
    private TouristFacilitiesListAdapter mTouristFacilitiesListAdapter;
    public List<KenyaSightingsClass> kenyaSightingsClass;
    public List<TouristFacilitiesModelClass> touristFacilitiesClass;
    TextView tourist_facilities;
    @BindView(R.id.userName) TextView signedInUserName;
    @BindView(R.id.userEmail) TextView signedInUserEmail;
    @BindView(R.id.userProfileImage) ImageView signedInUserProfileImage;
    @BindView(R.id.sports) TextView mSports;
    @BindView(R.id.nature) TextView mNature;
    @BindView(R.id.amusements) TextView mAmusements;
    @BindView(R.id.accomodation) TextView mAccomodation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        drawerLayout = findViewById(R.id.drawer_layout);
        navMenu = findViewById(R.id.navMenu);
        toolbar = findViewById(R.id.toolbar);
        sightingsRecyclerView = findViewById(R.id.sightings);
        tourist_facilities = findViewById(R.id.tourist_facilities);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
//        navMenu.setNavigationItemSelectedListener(onOptionsItemSelected();
        navMenu.setCheckedItem(R.id.nav_home);

        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if(signInAccount != null){
            signedInUserProfileImage.setImageURI(signInAccount.getPhotoUrl());
            signedInUserName.setText(signInAccount.getDisplayName());
            signedInUserEmail.setText(signInAccount.getEmail());
        }

        OpenTripMapApi openTripMapApi = OpenTripMapClient.getClient();
        Call<KenyaSightingsClass> call = openTripMapApi.getKenyaSightings();

        call.enqueue(new Callback<KenyaSightingsClass>() {
            @Override
            public void onResponse(Call<KenyaSightingsClass> call, Response<KenyaSightingsClass> response) {
                if(response.isSuccessful()){
                    kenyaSightingsClass = response.body().getKenyaSightings();
                    mSightingsListAdapter = new KenyaSightingsListAdapter(DashboardActivity.this, kenyaSightingsClass);
                    sightingsRecyclerView.setAdapter(mSightingsListAdapter);
                    RecyclerView.LayoutManager layoutManager =
                            new LinearLayoutManager(DashboardActivity.this);
                    sightingsRecyclerView.setLayoutManager(layoutManager);
                    sightingsRecyclerView.setHasFixedSize(true);
                }
            }

            @Override
            public void onFailure(Call<KenyaSightingsClass> call, Throwable t) {

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

    public void onClick(View view) {
        String touristFacilities = "<font color=#F3A333>Tourist Facilities</font>";
        tourist_facilities.setText(Html.fromHtml(touristFacilities));
        tourist_facilities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenTripMapApi openTripMapApi = OpenTripMapClient.getClient();
                Call<TouristFacilitiesModelClass> call = openTripMapApi.getTouristFacilitiesKenya();

                call.enqueue(new Callback<TouristFacilitiesModelClass>() {
                    @Override
                    public void onResponse(Call<TouristFacilitiesModelClass> call, Response<TouristFacilitiesModelClass> response) {
                        if(response.isSuccessful()){
                            touristFacilitiesClass = response.body().getTouristFacilities();
                            mTouristFacilitiesListAdapter = new TouristFacilitiesListAdapter(DashboardActivity.this, touristFacilitiesClass);
                            sightingsRecyclerView.setAdapter(mTouristFacilitiesListAdapter);
                            RecyclerView.LayoutManager layoutManager =
                                    new LinearLayoutManager(DashboardActivity.this);
                            sightingsRecyclerView.setLayoutManager(layoutManager);
                            sightingsRecyclerView.setHasFixedSize(true);
                        }
                    }

                    @Override
                    public void onFailure(Call<TouristFacilitiesModelClass> call, Throwable t) {

                    }
                });
            }
        });

        String sports = "<font color=#F3A333>Sports</font>";
        mSports.setText(Html.fromHtml(sports));

        String nature = "<font color=#F3A333>Nature</font>";
        mNature.setText(Html.fromHtml(nature));

        String amusements = "<font color=#F3A333>Amusements</font>";
        mAmusements.setText(Html.fromHtml(amusements));

        String accomodation = "<font color=#F3A333>Accomodation</font>";
        mAccomodation.setText(Html.fromHtml(accomodation));

    }
}