// File: app/src/main/java/com/fwfamilywear/activities/AdminDashboardActivity.java
package com.fwfamilywear.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.fwfamilywear.R;
import com.fwfamilywear.adapters.TicketAdapter;
import com.fwfamilywear.database.DBHelper;
import com.fwfamilywear.models.VendorApplication;
import com.fwfamilywear.models.SaleRequest;
import com.fwfamilywear.models.Ticket;
import java.util.List;
import java.util.stream.Collectors;

public class AdminDashboardActivity extends AppCompatActivity {
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        dbHelper = new DBHelper(this);

        RecyclerView recApps = findViewById(R.id.recyclerVendorApps);
        recApps.setLayoutManager(new LinearLayoutManager(this));
        List<VendorApplication> apps = dbHelper.getVendorApplications();
        // For simplicity, display vendor name and phone in a TicketAdapter
        List<Ticket> appTickets = apps.stream().map(app ->
                new Ticket(app.getId(), app.getUserId(), app.getVendorName(), app.getPhone(), app.getStatus())
        ).collect(Collectors.toList());
        recApps.setAdapter(new TicketAdapter(this, appTickets));

        RecyclerView recSales = findViewById(R.id.recyclerSaleRequests);
        recSales.setLayoutManager(new LinearLayoutManager(this));
        List<SaleRequest> sales = dbHelper.getSaleRequests();
        // Display sale requests (product name as subject) in TicketAdapter
        List<Ticket> saleTickets = sales.stream().map(sr ->
                new Ticket(sr.getId(), sr.getUserId(), sr.getProductName(), "", sr.getStatus())
        ).collect(Collectors.toList());
        recSales.setAdapter(new TicketAdapter(this, saleTickets));
    }
}
