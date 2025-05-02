// File: app/src/main/java/com/fwfamilywear/adapters/AdminUserAdapter.java
package com.fwfamilywear.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.fwfamilywear.R;
import com.fwfamilywear.models.User;
import java.util.List;

public class AdminUserAdapter extends RecyclerView.Adapter<AdminUserAdapter.ViewHolder> {
    private List<User> users;
    private Context context;
    private OnUserActionListener listener;

    public interface OnUserActionListener {
        void onPromote(User user);
        void onBan(User user);
    }

    public AdminUserAdapter(Context context, List<User> users, OnUserActionListener listener) {
        this.context = context;
        this.users = users;
        this.listener = listener;
    }

    @Override
    public AdminUserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_admin_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdminUserAdapter.ViewHolder holder, int position) {
        User u = users.get(position);
        holder.name.setText(u.getName());
        holder.email.setText(u.getEmail());
        holder.role.setText(u.getRole());
        holder.promote.setOnClickListener(v -> listener.onPromote(u));
        holder.ban.setOnClickListener(v -> listener.onBan(u));
    }

    @Override
    public int getItemCount() { return users.size(); }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, email, role, promote, ban;
        public ViewHolder(View v) {
            super(v);
            name    = v.findViewById(R.id.tvAdminUserName);
            email   = v.findViewById(R.id.tvAdminUserEmail);
            role    = v.findViewById(R.id.tvAdminUserRole);
            promote = v.findViewById(R.id.tvAdminPromote);
            ban     = v.findViewById(R.id.tvAdminBan);
        }
    }
}
