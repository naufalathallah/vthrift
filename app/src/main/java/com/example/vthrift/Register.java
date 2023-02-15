package com.example.vthrift;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {
    EditText usernamereg,passwordreg;
    Button btregister;
    DBHelper db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        db = new DBHelper(this);
        usernamereg = findViewById(R.id.usernamereg);
        passwordreg = findViewById(R.id.passwordreg);
        btregister = findViewById(R.id.btregister);

        btregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = usernamereg.getText().toString();
                String pass = passwordreg.getText().toString();

                if (user.equals("")||pass.equals(""))
                    Toast.makeText(Register.this, "Silahkan isi Username/Password", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuser = db.checkuser(user);
                    if (checkuser==false){
                        Boolean insert = db.insertUser(user, pass);
                        if (insert==true){
                            Toast.makeText(Register.this, "Pendaftaran Berhasil", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), Login.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(Register.this, "Pendaftaran Gagal", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(Register.this, "Username Sudah Digunakan", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
