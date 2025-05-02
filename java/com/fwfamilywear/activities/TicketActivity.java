// File: app/src/main/java/com/fwfamilywear/activities/TicketActivity.java
package com.fwfamilywear.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.fwfamilywear.R;
import com.fwfamilywear.adapters.TicketAdapter;
import com.fwfamilywear.database.DBHelper;
import com.fwfamilywear.models.Ticket;
import com.fwfamilywear.session.SessionManager;
import java.util.List;

public class TicketActivity extends AppCompatActivity {
    private DBHelper dbHelper;
    private SessionManager session;
    private RecyclerView recycler;
    private EditText inputSubject, inputDesc;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);
        dbHelper = new DBHelper(this);
        session = new SessionManager(this);

        inputSubject = findViewById(R.id.ticketSubject);
        inputDesc = findViewById(R.id.ticketDescription);
        btnSubmit = findViewById(R.id.btnSubmitTicket);
        recycler = findViewById(R.id.recyclerTickets);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        String role = session.getUserDetails().get(SessionManager.KEY_ROLE);
        if ("Admin".equals(role) || "Moderator".equals(role)) {
            // Show all tickets
            List<Ticket> tickets = dbHelper.getAllTickets();
            recycler.setAdapter(new TicketAdapter(this, tickets));
            // hide form
            inputSubject.setVisibility(EditText.GONE);
            inputDesc.setVisibility(EditText.GONE);
            btnSubmit.setVisibility(Button.GONE);
        } else {
            // Show form and user's tickets
            btnSubmit.setOnClickListener(v -> {
                int userId = session.getUserId();
                dbHelper.addTicket(userId, inputSubject.getText().toString(), inputDesc.getText().toString(), "Open");
                loadUserTickets();
            });
            loadUserTickets();
        }
    }

    private void loadUserTickets() {
        int userId = session.getUserId();
        List<Ticket> tickets = dbHelper.getTicketsByUser(userId);
        recycler.setAdapter(new TicketAdapter(this, tickets));
        inputSubject.setText("");
        inputDesc.setText("");
    }
}
