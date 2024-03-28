package com.example.moviebookings.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.moviebookings.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {

    // khởi tạo các biến cần thiết
    private EditText edtemail, edtpasword,edtverifypassword;
    private Button btnSigup;
    private TextView tvSigup;
    private FirebaseAuth mAuth;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        // ánh xạ các biến đó
        edtemail = findViewById(R.id.edt_Email);
        mAuth = FirebaseAuth.getInstance();
        edtpasword = findViewById(R.id.edt_PassWord);
        edtverifypassword = findViewById(R.id.edt_verifypassword);
        btnSigup = findViewById(R.id.btn_sigup);
        tvSigup = findViewById(R.id.txt_Dangnhap);

        // suử lý sự kiện nhấn nút
        btnSigup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sigup();
            }
        });
        tvSigup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backLogin();
            }
        });
    }

    private void backLogin() {
        Intent intent = new Intent(SignUp.this,Login.class);
        startActivity(intent);
    }

    private void sigup() {
        // khai báo 2 bieen để lấy dữ liệu từ 2 edittext
        String email,password,confirmPassword;
        email = edtemail.getText().toString();
        password = edtpasword.getText().toString();
        confirmPassword = edtverifypassword.getText().toString();
        // kiem tra doi tuong email va password cos rong k?
        // neu rong bat nhap lai
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Vui lòng nhập lại email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Vui lòng nhập lại PassWord", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(confirmPassword)){
            Toast.makeText(this, "Vui lòng nhập lại PassWord", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
            if(task.isSuccessful()){
                Toast.makeText(getApplicationContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignUp.this, Login.class);
                startActivity(intent);
            }else{
                Toast.makeText(getApplicationContext(), "Đăng ký không thành công", Toast.LENGTH_SHORT).show();

            }
            }
        });
    }
}