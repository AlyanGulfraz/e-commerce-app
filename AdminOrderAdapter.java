// File: app/src/main/java/com/fwfamilywear/adapters/AdminOrderAdapter.java
package com.fwfamilywear.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.fwfamilywear.R;
import com.fwfamilywear.models.Order;
import java.util.List;

public class AdminOrderAdapter extends RecyclerView.Adapter<AdminOrderAdapter.ViewHolder> {
    private List<Order> orders;
    private Context context;
    private OnOrderActionListener listener;

    public interface OnOrderActionListener {
        void onUpdateStatus(Order order, String newStatus);
    }

    public AdminOrderAdapter(Context context, List<Order> orders, OnOrderActionListener listener) {
        this.context = context;
        this.orders = orders;
        this.listener = listener;
    }

    @Override
    public AdminOrderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_admin_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdminOrderAdapter.ViewHolder holder, int position) {
        Order o = orders.get(position);
        holder.id.setText("Order #" + o.getId());
        holder.product.setText(o.getProductName());
        holder.status.setText(o.getStatus());
        // Example: clicking status cycles through Pending→Shipped→Delivered
        holder.status.setOnClickListener(v -> {
            String next = o.getStatus().equals("Pending") ? "Shipped" :
                    o.getStatus().equals("Shipped") ? "Delivered" : "Completed";
            listener.onUpdateStatus(o, next);
        });
    }

    @Override
    public int getItemCount() { return orders.size(); }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView id, product, status;
        public ViewHolder(View v) {
            super(v);
            id       = v.findViewById(R.id.tvAdminOrderId);
            product  = v.findViewById(R.id.tvAdminOrderProduct);
            status   = v.findViewById(R.id.tvAdminOrderStatus);
        }
    }
}
