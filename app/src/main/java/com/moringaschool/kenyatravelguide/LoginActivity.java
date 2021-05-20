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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        mWelcomeMessage = findViewById(R.id.welcomeMessage);
        mLoginButton = (Button)findViewById(R.id.login);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        String text = "<font color=#F3A333>Welcome To</font> <font color=#F16821>Kenya Travel Guide</font>";
        mWelcomeMessage.setText(Html.fromHtml(text));
    }
}
