package com.example.toytrader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements FirebaseListener {
    private TextView registerText;
    private EditText email, password;
    private Button loginButton;
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setupAllViews();
        setupTextWatchers();
        setupOnClickListeners();
    }

    private void setupAllViews() {
        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.login_button);
        registerText = (TextView) findViewById(R.id.register_text);
        spinner = (ProgressBar) findViewById(R.id.progressBar_cyclic);
        spinner.setVisibility(View.GONE);
    }

    private void setupTextWatchers() {
        email.addTextChangedListener(new CustomTextWatcher(email));
        password.addTextChangedListener(new CustomTextWatcher(password));
    }

    private void setupOnClickListeners(){
        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(".RegisterActivity");
                startActivity(intent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickRegisterButton();
            }
        });
    }

    private Boolean checkValidations() {
        if(email.getText().toString().isEmpty()) {
            SnackbarHelper.showMessage("Please Enter Email", getCurrentFocus());
            return false;
        }else if(password.getText().toString().isEmpty()) {
            SnackbarHelper.showMessage("Please Enter Password", getCurrentFocus());
            return false;
        }
        return true;
    }

    private void onClickRegisterButton(){
        final LoginActivity la = this;
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkValidations()) {
                    spinner.setVisibility(View.VISIBLE);
                    FirebaseHelper.getInstance().signinWith(email.getText().toString(), password.getText().toString(), la);
                }
            }
        });
    }


    @Override
    public <T> void getFBData(T event) {
        spinner.setVisibility(View.GONE);
        if(event != null) {
            Intent intent = new Intent(".UserHomeActivity");
            startActivity(intent);
            this.finish();
        }
    }
}

