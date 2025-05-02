// File: app/src/main/java/com/fwfamilywear/activities/CartActivity.java
package com.fwfamilywear.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.fwfamilywear.R;
import com.fwfamilywear.adapters.CartAdapter;
import com.fwfamilywear.database.DBHelper;
import com.fwfamilywear.models.CartItem;
import com.fwfamilywear.session.SessionManager;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    private DBHelper dbHelper;
    private SessionManager session;
    private RecyclerView recycler;
    private TextView totalAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        dbHelper = new DBHelper(this);
        session = new SessionManager(this);

        recycler = findViewById(R.id.recyclerCart);
        totalAmount = findViewById(R.id.totalAmount);
        Button btnCheckout = findViewById(R.id.btnCheckout);

        int userId = session.getUserId();
        List<CartItem> items = dbHelper.getCartItems(userId);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        CartAdapter adapter = new CartAdapter(this, items);
        recycler.setAdapter(adapter);

        double total = 0;
        for (CartItem item : items) {
            total += item.getProductPrice() * item.getQuantity();
        }
        totalAmount.setText("Total: $" + total);

        btnCheckout.setOnClickListener(v -> {
            if (items.isEmpty()) {
                return;
            }
            startActivity(new Intent(CartActivity.this, CheckoutActivity.class));
        });
    }
}
