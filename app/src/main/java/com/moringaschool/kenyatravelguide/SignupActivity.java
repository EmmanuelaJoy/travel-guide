package com.moringaschool.kenyatravelguide;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;

public class SignupActivity extends AppCompatActivity {
    @BindView(R.id.signUp) Button mSignUpButton;
    @BindView(R.id.welcomeMessage) TextView mWelcomeMessage;
    @BindView(R.id.loginLink) TextView mLogInMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        mLogInMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
