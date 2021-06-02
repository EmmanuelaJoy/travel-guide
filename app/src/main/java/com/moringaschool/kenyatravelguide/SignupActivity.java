package com.moringaschool.kenyatravelguide;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignupActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference reference;
    int userId = 0;
    @BindView(R.id.signUp) Button mSignUpButton;
    @BindView(R.id.welcomeMessage) TextView mWelcomeMessage;
    @BindView(R.id.loginLink) TextView mLogInMessage;
    @BindView(R.id.email) TextView mEmail;
    @BindView(R.id.username) TextView mUsername;
    @BindView(R.id.password) TextView mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        ButterKnife.bind(this);

        mLogInMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @org.jetbrains.annotations.NotNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            userId = (int) snapshot.getChildrenCount();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull @org.jetbrains.annotations.NotNull DatabaseError error) {

                    }
                });
                String email = mEmail.getEditableText().toString();
                String username = mUsername.getEditableText().toString();
                String password =  mPassword.getEditableText().toString();
                UserHelperClass helperClass = new UserHelperClass(email,username,password);
                reference.child(String.valueOf(userId+1)).setValue(helperClass);
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        String title = "<font color=#F3A333>WELCOME TO</font> <font color=#F16821>KENYA TRAVEL GUIDE</font>";
        String text = "<font color=#212121>ALready have an account?</font> <font color=#F16821>Log In</font>";
        mWelcomeMessage.setText(Html.fromHtml(title));
        mLogInMessage.setText(Html.fromHtml(text));
    }
}
