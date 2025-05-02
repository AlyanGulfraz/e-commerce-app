// File: app/src/main/java/com/fwfamilywear/adapters/CategoryAdapter.java
package com.fwfamilywear.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.fwfamilywear.models.Category;
import com.fwfamilywear.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private List<Category> categories;
    private Context context;
    private OnCategoryClickListener listener;

    public interface OnCategoryClickListener {
        void onCategoryClick(Category category);
    }

    public CategoryAdapter(Context context, List<Category> categories, OnCategoryClickListener listener) {
        this.context = context;
        this.categories = categories;
        this.listener = listener;
    }

    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryAdapter.ViewHolder holder, int position) {
        Category category = categories.get(position);
        holder.nameText.setText(category.getName());
        // Assuming drawable resource name matches category image
        int resId = context.getResources().getIdentifier(category.getImage(), "drawable", context.getPackageName());
        holder.iconImage.setImageResource(resId);
        holder.itemView.setOnClickListener(v -> listener.onCategoryClick(category));
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameText;
        public ImageView iconImage;
        public ViewHolder(View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.categoryName);
            iconImage = itemView.findViewById(R.id.categoryImage);
        }
    }
}
