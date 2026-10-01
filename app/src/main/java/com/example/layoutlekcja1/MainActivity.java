package com.example.layoutlekcja1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText firstNameInput, lastNameInput, emailInput, passwordInput;
    TextView messageView;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstNameInput = findViewById(R.id.firstName);
        lastNameInput = findViewById(R.id.lastName);
        emailInput = findViewById(R.id.email);
        passwordInput = findViewById(R.id.password);
        messageView = findViewById(R.id.validationMessage);
        submitButton = findViewById(R.id.registerButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkForm();
            }
        });
    }

    public void checkForm() {
        String firstName = firstNameInput.getText().toString();
        String lastName = lastNameInput.getText().toString();
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();

        String result = validate(firstName, lastName, email, password);
        
        messageView.setText(result);
        messageView.setVisibility(View.VISIBLE);
    }

    public String validate(String firstName, String lastName, String email, String password) {
        if (firstName.equals("") || lastName.equals("") || email.equals("") || password.equals("")) {
            return "Uzupełnij wszystkie pola";
        }
        
        if (email.contains("@") == false || email.contains(".") == false) {
            return "Podaj poprawny adres email";
        }

        String passwordError = checkPassword(password);
        
        if (passwordError.equals("") == false) {
            return passwordError;
        }

        return "Dane są poprawne";
    }

    public String checkPassword(String password) {
        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasSpecialChar = false;

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (Character.isUpperCase(c)) hasUpperCase = true;
            if (Character.isLowerCase(c)) hasLowerCase = true;
            if (Character.isLetterOrDigit(c) == false) hasSpecialChar = true;
        }

        String errorInfo = "";
        if (password.length() < 8) {
            errorInfo = errorInfo + "\n- minimum 8 znaków";
        }
        if (hasUpperCase == false) {
            errorInfo = errorInfo + "\n- duża litera";
        }
        if (hasLowerCase == false) {
            errorInfo = errorInfo + "\n- mała litera";
        }
        if (hasSpecialChar == false) {
            errorInfo = errorInfo + "\n- znak specjalny";
        }

        if (errorInfo.equals("")) {
            return "";
        } else {
            return "Hasło — brakujące wymagania:" + errorInfo;
        }
    }
}
