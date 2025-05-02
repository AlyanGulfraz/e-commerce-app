// File: app/src/main/java/com/fwfamilywear/activities/ModeratorDashboardActivity.java
package com.fwfamilywear.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.fwfamilywear.R;
import com.fwfamilywear.adapters.TicketAdapter;
import com.fwfamilywear.database.DBHelper;
import com.fwfamilywear.models.Ticket;
import java.util.List;

public class ModeratorDashboardActivity extends AppCompatActivity {
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moderator_dashboard);
        dbHelper = new DBHelper(this);

        RecyclerView recTickets = findViewById(R.id.recyclerTickets);
        recTickets.setLayoutManager(new LinearLayoutManager(this));
        List<Ticket> tickets = dbHelper.getAllTickets();
        recTickets.setAdapter(new TicketAdapter(this, tickets));
    }
}
