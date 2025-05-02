// File: app/src/main/java/com/fwfamilywear/activities/HomeActivity.java
package com.fwfamilywear.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.fwfamilywear.R;
import com.fwfamilywear.adapters.CategoryAdapter;
import com.fwfamilywear.adapters.ProductAdapter;
import com.fwfamilywear.database.DBHelper;
import com.fwfamilywear.models.Category;
import com.fwfamilywear.models.Product;
import com.fwfamilywear.session.SessionManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private DBHelper dbHelper;
    private SessionManager session;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        dbHelper = new DBHelper(this);
        session = new SessionManager(this);

        // Setup toolbar and drawer
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navView = findViewById(R.id.nav_view);
        Menu menu = navView.getMenu();
        // Hide menu items based on role
        String role = session.getUserDetails().get(SessionManager.KEY_ROLE);
        if (!"Admin".equals(role)) {
            menu.findItem(R.id.nav_admin).setVisible(false);
        }
        if (!"Moderator".equals(role)) {
            menu.findItem(R.id.nav_moderator).setVisible(false);
        }
        if (!"Vendor".equals(role)) {
            menu.findItem(R.id.nav_vendor).setVisible(false);
        }
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.nav_orders:
                        startActivity(new Intent(HomeActivity.this, OrderTrackingActivity.class));
                        break;
                    case R.id.nav_support:
                        startActivity(new Intent(HomeActivity.this, TicketActivity.class));
                        break;
                    case R.id.nav_vendor_apply:
                        startActivity(new Intent(HomeActivity.this, VendorApplyActivity.class));
                        break;
                    case R.id.nav_admin:
                        startActivity(new Intent(HomeActivity.this, AdminDashboardActivity.class));
                        break;
                    case R.id.nav_moderator:
                        startActivity(new Intent(HomeActivity.this, ModeratorDashboardActivity.class));
                        break;
                    case R.id.nav_vendor:
                        startActivity(new Intent(HomeActivity.this, VendorDashboardActivity.class));
                        break;
                    case R.id.nav_logout:
                        session.logoutUser();
                        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                        finish();
                        break;
                }
                drawerLayout.closeDrawers();
                return true;
            }
        });

        // Setup category RecyclerView
        RecyclerView recCat = findViewById(R.id.recyclerCategories);
        recCat.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        List<Category> categories = dbHelper.getAllCategories();
        CategoryAdapter categoryAdapter = new CategoryAdapter(this, categories, new CategoryAdapter.OnCategoryClickListener() {
            @Override public void onCategoryClick(Category category) {
                Intent i = new Intent(HomeActivity.this, ProductListActivity.class);
                i.putExtra("categoryId", category.getId());
                i.putExtra("categoryName", category.getName());
                startActivity(i);
            }
        });
        recCat.setAdapter(categoryAdapter);

        // Setup popular products RecyclerView
        RecyclerView recProd = findViewById(R.id.recyclerProducts);
        recProd.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        // For simplicity, show first category products as popular
        if (!categories.isEmpty()) {
            List<Product> products = dbHelper.getProductsByCategory(categories.get(0).getId());
            ProductAdapter productAdapter = new ProductAdapter(this, products, new ProductAdapter.OnProductClickListener() {
                @Override public void onProductClick(Product product) {
                    Intent i = new Intent(HomeActivity.this, ProductDetailActivity.class);
                    i.putExtra("productId", product.getId());
                    startActivity(i);
                }
            });
            recProd.setAdapter(productAdapter);
        }

        // Bottom navigation
        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_home:
                    // already on home
                    return true;
                case R.id.nav_cart:
                    startActivity(new Intent(HomeActivity.this, CartActivity.class));
                    return true;
                case R.id.nav_support:
                    startActivity(new Intent(HomeActivity.this, TicketActivity.class));
                    return true;
            }
            return false;
        });
    }
}
