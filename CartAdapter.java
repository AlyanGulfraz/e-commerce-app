// File: app/src/main/java/com/fwfamilywear/adapters/CartAdapter.java
package com.fwfamilywear.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.fwfamilywear.models.CartItem;
import com.fwfamilywear.R;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private List<CartItem> items;
    private Context context;

    public CartAdapter(Context context, List<CartItem> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CartAdapter.ViewHolder holder, int position) {
        CartItem item = items.get(position);
        holder.nameText.setText(item.getProductName());
        holder.qtyText.setText("Qty: " + item.getQuantity());
        holder.priceText.setText("Price: $" + item.getProductPrice());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameText, qtyText, priceText;
        public ViewHolder(View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.cartProductName);
            qtyText = itemView.findViewById(R.id.cartQuantity);
            priceText = itemView.findViewById(R.id.cartPrice);
        }
    }
}
