// File: app/src/main/java/com/fwfamilywear/adapters/TicketAdapter.java
package com.fwfamilywear.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.fwfamilywear.models.Ticket;
import com.fwfamilywear.R;
import java.util.List;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.ViewHolder> {
    private List<Ticket> tickets;
    private Context context;

    public TicketAdapter(Context context, List<Ticket> tickets) {
        this.context = context;
        this.tickets = tickets;
    }

    @Override
    public TicketAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_ticket, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TicketAdapter.ViewHolder holder, int position) {
        Ticket ticket = tickets.get(position);
        holder.subjectText.setText(ticket.getSubject());
        holder.statusText.setText(ticket.getStatus());
    }

    @Override
    public int getItemCount() {
        return tickets.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView subjectText, statusText;
        public ViewHolder(View itemView) {
            super(itemView);
            subjectText = itemView.findViewById(R.id.ticketSubject);
            statusText = itemView.findViewById(R.id.ticketStatus);
        }
    }
}
