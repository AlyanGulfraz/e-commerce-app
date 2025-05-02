// File: app/src/main/java/com/fwfamilywear/activities/VendorApplyActivity.java
package com.fwfamilywear.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.fwfamilywear.R;
import com.fwfamilywear.database.DBHelper;
import com.fwfamilywear.session.SessionManager;

public class VendorApplyActivity extends AppCompatActivity {
    private EditText inputName, inputPhone, inputDoc;
    private Button btnSubmit;
    private DBHelper dbHelper;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_apply);
        dbHelper = new DBHelper(this);
        session = new SessionManager(this);

        inputName = findViewById(R.id.vendorName);
        inputPhone = findViewById(R.id.vendorPhone);
        inputDoc = findViewById(R.id.vendorDoc);
        btnSubmit = findViewById(R.id.btnSubmitApplication);

        btnSubmit.setOnClickListener(v -> {
            int userId = session.getUserId();
            String name = inputName.getText().toString().trim();
            String phone = inputPhone.getText().toString().trim();
            String doc = inputDoc.getText().toString().trim();
            if (name.isEmpty() || phone.isEmpty() || doc.isEmpty()) {
                Toast.makeText(VendorApplyActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                dbHelper.addVendorApplication(userId, name, phone, doc, "Pending");
                Toast.makeText(VendorApplyActivity.this, "Application submitted", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
