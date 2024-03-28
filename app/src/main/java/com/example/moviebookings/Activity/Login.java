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

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.moviebookings.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    //khai báo
   private EditText edtemail,edtpass;
   private Button btnsigIn,btnregister;

    private FirebaseAuth mAuth;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_login);
        // ánh xạ các biến
        mAuth = FirebaseAuth.getInstance();
        edtemail = findViewById(R.id.edt_UserName);
        edtpass = findViewById(R.id.edt_PassWord);
        btnsigIn= findViewById(R.id.btn_Sigin);
        btnregister = findViewById(R.id.btn_dangky);
        // sử lý sự kiện của nút nhấn.
        btnsigIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }

            private void register() {
                // khái báo Intent để chuyển đổi giữa hai màn hình với nhau
                // khi nhấn vào btn register thì sẽ chuển sang màn hình register
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
            }
        });
    }
        

    private void login() {
        // khai báo 2 bieen để lấy dữ liệu từ 2 edittext
        String email,password;
        email = edtemail.getText().toString();
        password = edtpass.getText().toString();
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


        // gọi phương thức này để đăng nhập email và password
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                // kiểm tra đăng nhập thành công hay không mình sử dụng task biến trả về
                if(task.isSuccessful()){
                    Toast.makeText(Login.this, "Đăng Nhập Thành Công", Toast.LENGTH_SHORT).show();
                    // nếu đăng nhập thành công thì mình sẽ chuyển qua màn hình tiếp theo
                    Intent intent = new Intent(Login.this,MainActivity.class);
                    startActivity(intent);
                }else{
                    // nếu không ddanwg nhập thành công thì?
                    Toast.makeText(getApplicationContext(), "Đăng Nhập Không Thành Công", Toast.LENGTH_SHORT).show();


                }
            }
        });
    }
}