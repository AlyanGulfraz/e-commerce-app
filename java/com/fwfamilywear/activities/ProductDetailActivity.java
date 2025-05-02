// File: app/src/main/java/com/fwfamilywear/activities/ProductDetailActivity.java
package com.fwfamilywear.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.fwfamilywear.R;
import com.fwfamilywear.database.DBHelper;
import com.fwfamilywear.models.Product;
import com.fwfamilywear.session.SessionManager;

public class ProductDetailActivity extends AppCompatActivity {
    private DBHelper dbHelper;
    private SessionManager session;
    private int productId;
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        dbHelper = new DBHelper(this);
        session = new SessionManager(this);

        productId = getIntent().getIntExtra("productId", -1);
        product = dbHelper.getProduct(productId);
        if (product != null) {
            ImageView image = findViewById(R.id.productImage);
            int resId = getResources().getIdentifier(product.getImage(), "drawable", getPackageName());
            image.setImageResource(resId);
            ((TextView)findViewById(R.id.productName)).setText(product.getName());
            ((TextView)findViewById(R.id.productPrice)).setText("$" + product.getPrice());
            ((TextView)findViewById(R.id.productDesc)).setText(product.getDescription());
        }

        Button btnAdd = findViewById(R.id.btnAddToCart);
        btnAdd.setOnClickListener(v -> {
            if (session.isLoggedIn()) {
                int userId = session.getUserId();
                dbHelper.addToCart(userId, productId, 1);
                Toast.makeText(ProductDetailActivity.this, "Added to cart", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(ProductDetailActivity.this, "Please login first", Toast.LENGTH_SHORT).show();
            }
        });

        Button btnSale = findViewById(R.id.btnRequestSale);
        btnSale.setOnClickListener(v -> {
            if (session.isLoggedIn()) {
                int userId = session.getUserId();
                dbHelper.addSaleRequest(userId, productId, "Pending");
                Toast.makeText(ProductDetailActivity.this, "Sale request sent", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(ProductDetailActivity.this, "Please login first", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
