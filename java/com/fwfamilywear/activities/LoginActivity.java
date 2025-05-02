// File: app/src/main/java/com/fwfamilywear/activities/LoginActivity.java
package com.fwfamilywear.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.fwfamilywear.R;
import com.fwfamilywear.database.DBHelper;
import com.fwfamilywear.models.User;
import com.fwfamilywear.session.SessionManager;

public class LoginActivity extends AppCompatActivity {
    private EditText inputEmail, inputPassword;
    private Button btnLogin;
    private TextView linkRegister;
    private DBHelper dbHelper;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper = new DBHelper(this);
        session = new SessionManager(this);

        inputEmail = findViewById(R.id.email);
        inputPassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btnLogin);
        linkRegister = findViewById(R.id.linkRegister);

        btnLogin.setOnClickListener(v -> {
            String email = inputEmail.getText().toString().trim();
            String pass = inputPassword.getText().toString().trim();
            if (email.isEmpty() || pass.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Please enter credentials", Toast.LENGTH_SHORT).show();
            } else {
                User user = dbHelper.getUser(email, pass);
                if (user != null) {
                    session.createLoginSession(user.getId(), user.getName(), user.getEmail(), user.getRole());
                    if ("Admin".equals(user.getRole())) {
                        startActivity(new Intent(LoginActivity.this, AdminDashboardActivity.class));
                    } else if ("Moderator".equals(user.getRole())) {
                        startActivity(new Intent(LoginActivity.this, ModeratorDashboardActivity.class));
                    } else if ("Vendor".equals(user.getRole())) {
                        startActivity(new Intent(LoginActivity.this, VendorDashboardActivity.class));
                    } else {
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    }
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });

        linkRegister.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });
    }
}
