// File: app/src/main/java/com/fwfamilywear/activities/ProductListActivity.java
package com.fwfamilywear.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.fwfamilywear.R;
import com.fwfamilywear.adapters.ProductAdapter;
import com.fwfamilywear.database.DBHelper;
import com.fwfamilywear.models.Product;
import java.util.List;

public class ProductListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        DBHelper dbHelper = new DBHelper(this);

        int categoryId = getIntent().getIntExtra("categoryId", -1);
        String categoryName = getIntent().getStringExtra("categoryName");
        setTitle(categoryName);

        RecyclerView recycler = findViewById(R.id.recyclerProducts);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        if (categoryId != -1) {
            List<Product> products = dbHelper.getProductsByCategory(categoryId);
            ProductAdapter adapter = new ProductAdapter(this, products, new ProductAdapter.OnProductClickListener() {
                @Override public void onProductClick(Product product) {
                    Intent i = new Intent(ProductListActivity.this, ProductDetailActivity.class);
                    i.putExtra("productId", product.getId());
                    startActivity(i);
                }
            });
            recycler.setAdapter(adapter);
        }
    }
}
