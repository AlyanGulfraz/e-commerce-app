// File: app/src/main/java/com/fmfamilywear/activities/TicketListActivity.java
package com.fmfamilywear.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.fmfamilywear.R;
import com.fmfamilywear.adapters.TicketAdapter;
import com.fmfamilywear.db.DatabaseHelper;
import com.fmfamilywear.models.Ticket;
import java.util.List;

public class TicketListActivity extends AppCompatActivity {
    private RecyclerView rvTickets;
    private TicketAdapter adapter;
    private DatabaseHelper db;
    @Override protected void onCreate(Bundle s){
        super.onCreate(s);
        setContentView(R.layout.activity_ticket_list);
        rvTickets = findViewById(R.id.rvTickets);
        db = new DatabaseHelper(this);
        List<Ticket> tickets = db.getAllTickets();
        adapter = new TicketAdapter(tickets, t -> {
            // On click: open detail
        });
        rvTickets.setLayoutManager(new LinearLayoutManager(this));
        rvTickets.setAdapter(adapter);
    }
}
