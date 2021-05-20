package com.moringaschool.kenyatravelguide;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private Button mLoginButton;
    private TextView mWelcomeMessage;
    private TextView mSignUpMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        mWelcomeMessage = findViewById(R.id.welcomeMessage);
        mSignUpMessage = findViewById(R.id.signUpLink);
        mSignUpMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
        mLoginButton = (Button)findViewById(R.id.login);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        String title = "<font color=#F3A333>WELCOME TO</font> <font color=#F16821>KENYA TRAVEL GUIDE</font>";
        String text = "<font color=#212121>Don't have an account?</font> <font color=#F16821>Sign Up</font>";
        mWelcomeMessage.setText(Html.fromHtml(title));
        mSignUpMessage.setText(Html.fromHtml(text));
    }
}
