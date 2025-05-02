// File: app/src/main/java/com/fwfamilywear/activities/CheckoutActivity.java
package com.fwfamilywear.activities;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.fwfamilywear.R;
import com.fwfamilywear.database.DBHelper;
import com.fwfamilywear.session.SessionManager;

public class CheckoutActivity extends AppCompatActivity {
    private DBHelper dbHelper;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        dbHelper = new DBHelper(this);
        session = new SessionManager(this);

        Button btnConfirm = findViewById(R.id.btnConfirmOrder);
        btnConfirm.setOnClickListener(v -> {
            int userId = session.getUserId();
            // Create orders for each cart item
            for (com.fwfamilywear.models.CartItem item : dbHelper.getCartItems(userId)) {
                dbHelper.addOrder(userId, item.getProductId(), item.getQuantity(), "Pending");
            }
            dbHelper.clearCart(userId);
            startActivity(new android.content.Intent(CheckoutActivity.this, OrderTrackingActivity.class));
            finish();
        });
    }
}
