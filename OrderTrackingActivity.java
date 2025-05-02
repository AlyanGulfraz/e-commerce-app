// File: app/src/main/java/com/fwfamilywear/activities/OrderTrackingActivity.java
package com.fwfamilywear.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.fwfamilywear.R;
import com.fwfamilywear.adapters.OrderAdapter;
import com.fwfamilywear.database.DBHelper;
import com.fwfamilywear.models.Order;
import com.fwfamilywear.session.SessionManager;
import java.util.List;

public class OrderTrackingActivity extends AppCompatActivity {
    private DBHelper dbHelper;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_tracking);
        dbHelper = new DBHelper(this);
        session = new SessionManager(this);

        RecyclerView recycler = findViewById(R.id.recyclerOrders);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        int userId = session.getUserId();
        List<Order> orders = dbHelper.getOrdersByUser(userId);
        recycler.setAdapter(new OrderAdapter(this, orders));
    }
}
