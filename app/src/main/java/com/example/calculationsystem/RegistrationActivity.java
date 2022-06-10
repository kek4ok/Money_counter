package com.example.calculationsystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrationActivity extends AppCompatActivity {

    EditText Email, Password;
    Button bth_register;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.registration);

        Email = findViewById(R.id.editTextTextEmailAddress);
        Password = findViewById(R.id.editTextTextPassword);
        bth_register = findViewById(R.id.register_button);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();


        bth_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerformAuth();
            }
        });

        Button skip = (Button) findViewById(R.id.skip_register);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main_intent = new Intent(RegistrationActivity.this, MainActivity.class);
                RegistrationActivity.this.startActivity(main_intent);
                RegistrationActivity.this.finish();
            }
        });
    }

    private void PerformAuth() {
        String inputEmail = Email.getText().toString();
        String inputPass = Password.getText().toString();

        progressDialog = new ProgressDialog(this);

        if (!inputEmail.matches(emailPattern)) {
            Email.setError("Неверный формат почты!");
            Email.requestFocus();
        } else if (inputPass.isEmpty() || inputPass.length() < 8) {
            Password.setError("Неверный формат пароля!");
            Password.requestFocus();
        } else {
            progressDialog.setMessage("Пожалуйста, подождите пока я регистрирую Вас...");
            progressDialog.setTitle("Регистрация");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(inputEmail, inputPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        progressDialog.dismiss();
                        sendUserToNextActivity();
                        Toast.makeText(RegistrationActivity.this, "Вы успешно зарегестрированы!", Toast.LENGTH_SHORT).show();
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(RegistrationActivity.this, "Вы уже зарегестрированы!" + task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    private void sendUserToNextActivity() {
        Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
