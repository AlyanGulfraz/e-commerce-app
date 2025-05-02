// File: app/src/main/java/com/fmfamilywear/activities/TicketDetailActivity.java
package com.fmfamilywear.activities;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.fmfamilywear.R;
import com.fmfamilywear.db.DatabaseHelper;
import com.fmfamilywear.models.Ticket;

public class TicketDetailActivity extends AppCompatActivity {
    @Override protected void onCreate(Bundle s){
        super.onCreate(s);
        setContentView(R.layout.activity_ticket_detail);
        TextView tvSubject = findViewById(R.id.tvTicketSubject);
        TextView tvMessage = findViewById(R.id.tvTicketMessage);
        // TODO: load Ticket by ID from intent
        Ticket t = /* db fetch */;
        tvSubject.setText(t.getSubject());
        tvMessage.setText(t.getMessage());
    }
}
