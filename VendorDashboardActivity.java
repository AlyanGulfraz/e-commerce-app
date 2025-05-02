// File: app/src/main/java/com/fwfamilywear/activities/VendorDashboardActivity.java
package com.fwfamilywear.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.fwfamilywear.R;
import com.fwfamilywear.adapters.TicketAdapter;
import com.fwfamilywear.database.DBHelper;
import com.fwfamilywear.models.SaleRequest;
import com.fwfamilywear.models.Ticket;
import java.util.List;
import java.util.stream.Collectors;

public class VendorDashboardActivity extends AppCompatActivity {
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_dashboard);
        dbHelper = new DBHelper(this);

        RecyclerView recSale = findViewById(R.id.recyclerSaleRequests);
        recSale.setLayoutManager(new LinearLayoutManager(this));
        List<SaleRequest> sales = dbHelper.getSaleRequests();
        // For simplicity, display sale requests in TicketAdapter
        List<Ticket> saleTickets = sales.stream().map(sr ->
                new Ticket(sr.getId(), sr.getUserId(), sr.getProductName(), "", sr.getStatus())
        ).collect(Collectors.toList());
        recSale.setAdapter(new TicketAdapter(this, saleTickets));
    }
}
