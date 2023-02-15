package com.example.vthrift;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    EditText username,password;
    Button btlogin;
    TextView register;
    DBHelper db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btlogin = findViewById(R.id.btlogin);
        register = findViewById(R.id.register);
        db = new DBHelper(this);

        btlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (user==("")||pass.equals(""))
                    Toast.makeText(Login.this,"Data Harus Diisi", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = db.checkData(user,pass);
                    if (checkuserpass==true) {
                        Toast.makeText(Login.this, "Berhasil Masuk", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(Login.this,"Gagal Masuk", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Register.class));
            }
        });
    }
}
