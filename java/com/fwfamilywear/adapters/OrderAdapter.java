// File: app/src/main/java/com/fwfamilywear/adapters/OrderAdapter.java
package com.fwfamilywear.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.fwfamilywear.models.Order;
import com.fwfamilywear.R;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    private List<Order> orders;
    private Context context;

    public OrderAdapter(Context context, List<Order> orders) {
        this.context = context;
        this.orders = orders;
    }

    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OrderAdapter.ViewHolder holder, int position) {
        Order order = orders.get(position);
        holder.nameText.setText(order.getProductName());
        holder.statusText.setText(order.getStatus());
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameText, statusText;
        public ViewHolder(View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.orderProductName);
            statusText = itemView.findViewById(R.id.orderStatus);
        }
    }
}
