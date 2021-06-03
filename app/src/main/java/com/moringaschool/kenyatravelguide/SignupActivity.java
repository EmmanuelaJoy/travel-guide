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
import androidx.fragment.app.FragmentManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignupActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference reference;

    @BindView(R.id.signUp) Button mSignUpButton;
    @BindView(R.id.welcomeMessage) TextView mWelcomeMessage;
    @BindView(R.id.loginLink) TextView mLogInMessage;
    @BindView(R.id.email) TextView mEmail;
    @BindView(R.id.username) TextView mUsername;
    @BindView(R.id.password) TextView mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child(Constants.FIREBASE_CHILD_USERS);

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
                if(!validateEmail() || !validateUsername() || !validatePassword()){
                    return;
                } else{
                    String email = mEmail.getEditableText().toString();
                    String username = mUsername.getEditableText().toString();
                    String password =  mPassword.getEditableText().toString();
                    UserHelperClass helperClass = new UserHelperClass(email,username,password);
                    reference.push().setValue(helperClass);
                    Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                    startActivity(intent);
                }

            }
        });

        String title = "<font color=#F3A333>WELCOME TO</font> <font color=#F16821>KENYA TRAVEL GUIDE</font>";
        String text = "<font color=#212121>ALready have an account?</font> <font color=#F16821>Log In</font>";
        mWelcomeMessage.setText(Html.fromHtml(title));
        mLogInMessage.setText(Html.fromHtml(text));
    }

    private Boolean validateEmail(){
        String value = mEmail.getEditableText().toString();
        String emailPattern = "[a-zA-z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (value.isEmpty()){
            mEmail.setError("Field cannot be empty");
            return false;
        } else if(!value.matches(emailPattern)){
            mEmail.setError("Invalid email address");
            return false;
        }else{
            mEmail.setError(null);
            return true;
        }
    }

    private Boolean validateUsername(){
        String value = mUsername.getEditableText().toString();
        String noWhiteSpace = "(?=\\s+$)";

        if (value.isEmpty()){
            mUsername.setError("Field cannot be empty");
            return false;
        } else if(value.length()>=15){
            mUsername.setError("Username too long");
            return false;
        }else if(!value.matches(noWhiteSpace)){
            mUsername.setError("White spaces are not allowed");
            return false;
        }else{
            mUsername.setError(null);
            return true;
        }
    }

    private Boolean validatePassword(){
        String value = mPassword.getEditableText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";

        if (value.isEmpty()){
            mPassword.setError("Field cannot be empty");
            return false;
        } else if(value.length()>=15){
            mPassword.setError("Username too long");
            return false;
        }else if(!value.matches(passwordVal)){
            mPassword.setError("Password is too weak");
            return false;
        }else{
            mPassword.setError(null);
            return true;
        }
    }
}
