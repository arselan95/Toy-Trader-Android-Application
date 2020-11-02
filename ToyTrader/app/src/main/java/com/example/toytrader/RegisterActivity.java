package com.example.toytrader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity implements FirebaseListener {
    private TextView loginText;
    private EditText email, password, firstName, lastName, phoneNumber, address, pinCode;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setupAllViews();
        setupTextWatchers();
        onClickLoginText();
        onClickRegisterButton();
    }

    private void setupAllViews() {
        email = findViewById(R.id.register_email);
        password = findViewById(R.id.register_password);
        firstName = findViewById(R.id.register_firstName);
        lastName = findViewById(R.id.register_lastName);
        phoneNumber = findViewById(R.id.register_phone);
        address = findViewById(R.id.register_address);
        pinCode = findViewById(R.id.register_pincode);
        loginText = (TextView)findViewById(R.id.login_text);
        registerButton =(Button)findViewById(R.id.register_btn);
    }

    private void setupTextWatchers() {
        email.addTextChangedListener(new CustomTextWatcher(email));
        password.addTextChangedListener(new CustomTextWatcher(password));
        firstName.addTextChangedListener(new CustomTextWatcher(firstName));
        lastName.addTextChangedListener(new CustomTextWatcher(lastName));
        phoneNumber.addTextChangedListener(new CustomTextWatcher(phoneNumber));
        address.addTextChangedListener(new CustomTextWatcher(address));
        pinCode.addTextChangedListener(new CustomTextWatcher(pinCode));
    }


    public void onClickLoginText(){
        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(".LoginActivity");
                startActivity(intent);
            }
        });
    }

    public void onClickRegisterButton(){
        final RegisterActivity la = this;
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkValidations()) {
                    FirebaseHelper.getInstance().signupWith(email.getText().toString(), password.getText().toString(), la);
                }
            }
        });
    }

    private Boolean checkValidations() {
        // Add Validations
        return true;
    }

    @Override
    public <T> void getFBData(T event) {
        System.out.println("Event" + event.toString());
//                Intent intent = new Intent(".UserHomeActivity");
//                startActivity(intent);
    }
}


class CustomTextWatcher implements TextWatcher {
    private EditText mEditText;

    public CustomTextWatcher(EditText e) {
        mEditText = e;
    }

    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    public void afterTextChanged(Editable s) {
    }
}

public interface FirebaseListener {
    // you can define any parameter as per your requirement
    public <T> void getFBData(T event);
}