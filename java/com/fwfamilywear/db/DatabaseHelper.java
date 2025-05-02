// File: app/src/main/java/com/fwfamilywear/database/DBHelper.java
package com.fwfamilywear.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;
import com.fwfamilywear.models.User;
import com.fwfamilywear.models.Category;
import com.fwfamilywear.models.Product;
import com.fwfamilywear.models.CartItem;
import com.fwfamilywear.models.Order;
import com.fwfamilywear.models.Ticket;
import com.fwfamilywear.models.VendorApplication;
import com.fwfamilywear.models.SaleRequest;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "fwfamilywear.db";
    private static final int DATABASE_VERSION = 1;

    // Table Names
    private static final String TABLE_USERS = "users";
    private static final String TABLE_CATEGORIES = "categories";
    private static final String TABLE_PRODUCTS = "products";
    private static final String TABLE_CART = "cart";
    private static final String TABLE_ORDERS = "orders";
    private static final String TABLE_TICKETS = "tickets";
    private static final String TABLE_VENDOR_APPLICATIONS = "vendor_applications";
    private static final String TABLE_SALE_REQUESTS = "sale_requests";

    // Common column names
    private static final String KEY_ID = "id";

    // USERS Table - column names
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_ROLE = "role";

    // CATEGORIES Table - columns
    private static final String KEY_CATEGORY_NAME = "name";
    private static final String KEY_CATEGORY_IMAGE = "image";

    // PRODUCTS Table - columns
    private static final String KEY_PRODUCT_NAME = "name";
    private static final String KEY_PRODUCT_CATEGORY_ID = "category_id";
    private static final String KEY_PRODUCT_PRICE = "price";
    private static final String KEY_PRODUCT_DESC = "description";
    private static final String KEY_PRODUCT_IMAGE = "image";

    // CART Table
    private static final String KEY_CART_USER_ID = "user_id";
    private static final String KEY_CART_PRODUCT_ID = "product_id";
    private static final String KEY_CART_QUANTITY = "quantity";

    // ORDERS Table
    private static final String KEY_ORDER_USER_ID = "user_id";
    private static final String KEY_ORDER_PRODUCT_ID = "product_id";
    private static final String KEY_ORDER_QUANTITY = "quantity";
    private static final String KEY_ORDER_STATUS = "status";

    // TICKETS Table
    private static final String KEY_TICKET_USER_ID = "user_id";
    private static final String KEY_TICKET_SUBJECT = "subject";
    private static final String KEY_TICKET_DESCRIPTION = "description";
    private static final String KEY_TICKET_STATUS = "status";

    // VENDOR APPLICATIONS Table
    private static final String KEY_VENDOR_USER_ID = "user_id";
    private static final String KEY_VENDOR_NAME = "vendor_name";
    private static final String KEY_VENDOR_PHONE = "phone";
    private static final String KEY_VENDOR_DOC = "document";
    private static final String KEY_VENDOR_STATUS = "status";

    // SALE REQUESTS Table
    private static final String KEY_SALE_USER_ID = "user_id";
    private static final String KEY_SALE_PRODUCT_ID = "product_id";
    private static final String KEY_SALE_STATUS = "status";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create USERS table
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_NAME + " TEXT,"
                + KEY_EMAIL + " TEXT UNIQUE,"
                + KEY_PASSWORD + " TEXT,"
                + KEY_ROLE + " TEXT"
                + ")";
        db.execSQL(CREATE_USERS_TABLE);

        // Create CATEGORIES table
        String CREATE_CATEGORIES_TABLE = "CREATE TABLE " + TABLE_CATEGORIES + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_CATEGORY_NAME + " TEXT,"
                + KEY_CATEGORY_IMAGE + " TEXT"
                + ")";
        db.execSQL(CREATE_CATEGORIES_TABLE);

        // Create PRODUCTS table
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + TABLE_PRODUCTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_PRODUCT_NAME + " TEXT,"
                + KEY_PRODUCT_CATEGORY_ID + " INTEGER,"
                + KEY_PRODUCT_PRICE + " REAL,"
                + KEY_PRODUCT_DESC + " TEXT,"
                + KEY_PRODUCT_IMAGE + " TEXT,"
                + "FOREIGN KEY(" + KEY_PRODUCT_CATEGORY_ID + ") REFERENCES "
                + TABLE_CATEGORIES + "(" + KEY_ID + ")"
                + ")";
        db.execSQL(CREATE_PRODUCTS_TABLE);

        // Create CART table
        String CREATE_CART_TABLE = "CREATE TABLE " + TABLE_CART + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_CART_USER_ID + " INTEGER,"
                + KEY_CART_PRODUCT_ID + " INTEGER,"
                + KEY_CART_QUANTITY + " INTEGER,"
                + "FOREIGN KEY(" + KEY_CART_USER_ID + ") REFERENCES "
                + TABLE_USERS + "(" + KEY_ID + "),"
                + "FOREIGN KEY(" + KEY_CART_PRODUCT_ID + ") REFERENCES "
                + TABLE_PRODUCTS + "(" + KEY_ID + ")"
                + ")";
        db.execSQL(CREATE_CART_TABLE);

        // Create ORDERS table
        String CREATE_ORDERS_TABLE = "CREATE TABLE " + TABLE_ORDERS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_ORDER_USER_ID + " INTEGER,"
                + KEY_ORDER_PRODUCT_ID + " INTEGER,"
                + KEY_ORDER_QUANTITY + " INTEGER,"
                + KEY_ORDER_STATUS + " TEXT,"
                + "FOREIGN KEY(" + KEY_ORDER_USER_ID + ") REFERENCES "
                + TABLE_USERS + "(" + KEY_ID + "),"
                + "FOREIGN KEY(" + KEY_ORDER_PRODUCT_ID + ") REFERENCES "
                + TABLE_PRODUCTS + "(" + KEY_ID + ")"
                + ")";
        db.execSQL(CREATE_ORDERS_TABLE);

        // Create TICKETS table
        String CREATE_TICKETS_TABLE = "CREATE TABLE " + TABLE_TICKETS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_TICKET_USER_ID + " INTEGER,"
                + KEY_TICKET_SUBJECT + " TEXT,"
                + KEY_TICKET_DESCRIPTION + " TEXT,"
                + KEY_TICKET_STATUS + " TEXT,"
                + "FOREIGN KEY(" + KEY_TICKET_USER_ID + ") REFERENCES "
                + TABLE_USERS + "(" + KEY_ID + ")"
                + ")";
        db.execSQL(CREATE_TICKETS_TABLE);

        // Create VENDOR_APPLICATIONS table
        String CREATE_VENDOR_TABLE = "CREATE TABLE " + TABLE_VENDOR_APPLICATIONS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_VENDOR_USER_ID + " INTEGER,"
                + KEY_VENDOR_NAME + " TEXT,"
                + KEY_VENDOR_PHONE + " TEXT,"
                + KEY_VENDOR_DOC + " TEXT,"
                + KEY_VENDOR_STATUS + " TEXT,"
                + "FOREIGN KEY(" + KEY_VENDOR_USER_ID + ") REFERENCES "
                + TABLE_USERS + "(" + KEY_ID + ")"
                + ")";
        db.execSQL(CREATE_VENDOR_TABLE);

        // Create SALE_REQUESTS table
        String CREATE_SALE_TABLE = "CREATE TABLE " + TABLE_SALE_REQUESTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_SALE_USER_ID + " INTEGER,"
                + KEY_SALE_PRODUCT_ID + " INTEGER,"
                + KEY_SALE_STATUS + " TEXT,"
                + "FOREIGN KEY(" + KEY_SALE_USER_ID + ") REFERENCES "
                + TABLE_USERS + "(" + KEY_ID + "),"
                + "FOREIGN KEY(" + KEY_SALE_PRODUCT_ID + ") REFERENCES "
                + TABLE_PRODUCTS + "(" + KEY_ID + ")"
                + ")";
        db.execSQL(CREATE_SALE_TABLE);

        // Pre-populate with default data
        // Add a super admin user
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, "Super Admin");
        values.put(KEY_EMAIL, "admin@fw.com");
        values.put(KEY_PASSWORD, "admin"); // In production, use hashing
        values.put(KEY_ROLE, "Admin");
        db.insert(TABLE_USERS, null, values);

        // Add a moderator
        values.clear();
        values.put(KEY_NAME, "Moderator User");
        values.put(KEY_EMAIL, "mod@fw.com");
        values.put(KEY_PASSWORD, "mod");
        values.put(KEY_ROLE, "Moderator");
        db.insert(TABLE_USERS, null, values);

        // Add a vendor
        values.clear();
        values.put(KEY_NAME, "Vendor User");
        values.put(KEY_EMAIL, "vendor@fw.com");
        values.put(KEY_PASSWORD, "vendor");
        values.put(KEY_ROLE, "Vendor");
        db.insert(TABLE_USERS, null, values);

        // Add categories
        values.clear();
        values.put(KEY_CATEGORY_NAME, "Men");
        values.put(KEY_CATEGORY_IMAGE, "category_men");
        db.insert(TABLE_CATEGORIES, null, values);
        values.clear();
        values.put(KEY_CATEGORY_NAME, "Women");
        values.put(KEY_CATEGORY_IMAGE, "category_women");
        db.insert(TABLE_CATEGORIES, null, values);
        values.clear();
        values.put(KEY_CATEGORY_NAME, "Kids");
        values.put(KEY_CATEGORY_IMAGE, "category_kids");
        db.insert(TABLE_CATEGORIES, null, values);

        // Add products
        values.clear();
        values.put(KEY_PRODUCT_NAME, "Men's T-Shirt");
        values.put(KEY_PRODUCT_CATEGORY_ID, 1);
        values.put(KEY_PRODUCT_PRICE, 19.99);
        values.put(KEY_PRODUCT_DESC, "Comfortable cotton t-shirt for men.");
        values.put(KEY_PRODUCT_IMAGE, "mens_tshirt");
        db.insert(TABLE_PRODUCTS, null, values);

        values.clear();
        values.put(KEY_PRODUCT_NAME, "Women's Dress");
        values.put(KEY_PRODUCT_CATEGORY_ID, 2);
        values.put(KEY_PRODUCT_PRICE, 29.99);
        values.put(KEY_PRODUCT_DESC, "Elegant summer dress for women.");
        values.put(KEY_PRODUCT_IMAGE, "womens_dress");
        db.insert(TABLE_PRODUCTS, null, values);

        values.clear();
        values.put(KEY_PRODUCT_NAME, "Kids' Shoes");
        values.put(KEY_PRODUCT_CATEGORY_ID, 3);
        values.put(KEY_PRODUCT_PRICE, 24.99);
        values.put(KEY_PRODUCT_DESC, "Durable and comfortable shoes for kids.");
        values.put(KEY_PRODUCT_IMAGE, "kids_shoes");
        db.insert(TABLE_PRODUCTS, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CART);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TICKETS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VENDOR_APPLICATIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SALE_REQUESTS);
        // Create tables again
        onCreate(db);
    }

    // USER OPERATIONS

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.getName());
        values.put(KEY_EMAIL, user.getEmail());
        values.put(KEY_PASSWORD, user.getPassword());
        values.put(KEY_ROLE, user.getRole());
        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    public User getUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, new String[]{KEY_ID, KEY_NAME, KEY_EMAIL, KEY_PASSWORD, KEY_ROLE},
                KEY_EMAIL + "=? AND " + KEY_PASSWORD + "=?",
                new String[]{email, password}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            User user = new User(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4));
            cursor.close();
            return user;
        }
        return null;
    }

    public boolean checkUser(String email, String password) {
        return getUser(email, password) != null;
    }

    // CATEGORY OPERATIONS

    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_CATEGORIES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Category c = new Category(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
                categories.add(c);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return categories;
    }

    // PRODUCT OPERATIONS

    public List<Product> getProductsByCategory(int categoryId) {
        List<Product> products = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE " + KEY_PRODUCT_CATEGORY_ID + "=?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(categoryId)});
        if (cursor.moveToFirst()) {
            do {
                Product p = new Product(cursor.getInt(0), cursor.getString(1),
                        cursor.getInt(2), cursor.getDouble(3),
                        cursor.getString(4), cursor.getString(5));
                products.add(p);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return products;
    }

    public Product getProduct(int productId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_PRODUCTS, null, KEY_ID + "=?",
                new String[]{String.valueOf(productId)}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            Product p = new Product(cursor.getInt(0), cursor.getString(1),
                    cursor.getInt(2), cursor.getDouble(3),
                    cursor.getString(4), cursor.getString(5));
            cursor.close();
            return p;
        }
        return null;
    }

    // CART OPERATIONS

    public void addToCart(int userId, int productId, int quantity) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Check if item already in cart
        Cursor cursor = db.query(TABLE_CART, null, KEY_CART_USER_ID + "=? AND " + KEY_CART_PRODUCT_ID + "=?",
                new String[]{String.valueOf(userId), String.valueOf(productId)}, null, null, null);
        ContentValues values = new ContentValues();
        if (cursor != null && cursor.moveToFirst()) {
            // Item exists, update quantity
            int currentQty = cursor.getInt(cursor.getColumnIndex(KEY_CART_QUANTITY));
            values.put(KEY_CART_QUANTITY, currentQty + quantity);
            db.update(TABLE_CART, values, KEY_CART_USER_ID + "=? AND " + KEY_CART_PRODUCT_ID + "=?",
                    new String[]{String.valueOf(userId), String.valueOf(productId)});
        } else {
            values.put(KEY_CART_USER_ID, userId);
            values.put(KEY_CART_PRODUCT_ID, productId);
            values.put(KEY_CART_QUANTITY, quantity);
            db.insert(TABLE_CART, null, values);
        }
        if (cursor != null) cursor.close();
        db.close();
    }

    public List<CartItem> getCartItems(int userId) {
        List<CartItem> items = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT c." + KEY_ID + ", c." + KEY_CART_USER_ID + ", c." + KEY_CART_PRODUCT_ID +
                ", c." + KEY_CART_QUANTITY + ", p." + KEY_PRODUCT_NAME + ", p." + KEY_PRODUCT_PRICE +
                " FROM " + TABLE_CART + " c JOIN " + TABLE_PRODUCTS + " p ON c." + KEY_CART_PRODUCT_ID +
                " = p." + KEY_ID + " WHERE c." + KEY_CART_USER_ID + "=?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(userId)});
        if (cursor.moveToFirst()) {
            do {
                CartItem item = new CartItem(cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getInt(2),
                        cursor.getInt(3),
                        cursor.getString(4),
                        cursor.getDouble(5));
                items.add(item);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return items;
    }

    public void clearCart(int userId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CART, KEY_CART_USER_ID + "=?", new String[]{String.valueOf(userId)});
        db.close();
    }

    // ORDER OPERATIONS

    public void addOrder(int userId, int productId, int quantity, String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ORDER_USER_ID, userId);
        values.put(KEY_ORDER_PRODUCT_ID, productId);
        values.put(KEY_ORDER_QUANTITY, quantity);
        values.put(KEY_ORDER_STATUS, status);
        db.insert(TABLE_ORDERS, null, values);
        db.close();
    }

    public List<Order> getOrdersByUser(int userId) {
        List<Order> orders = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT o." + KEY_ID + ", o." + KEY_ORDER_USER_ID + ", o." + KEY_ORDER_PRODUCT_ID +
                ", o." + KEY_ORDER_QUANTITY + ", o." + KEY_ORDER_STATUS +
                ", p." + KEY_PRODUCT_NAME +
                " FROM " + TABLE_ORDERS + " o JOIN " + TABLE_PRODUCTS + " p ON o." + KEY_ORDER_PRODUCT_ID +
                " = p." + KEY_ID + " WHERE o." + KEY_ORDER_USER_ID + "=?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(userId)});
        if (cursor.moveToFirst()) {
            do {
                Order order = new Order(cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getInt(2),
                        cursor.getInt(3),
                        cursor.getString(4),
                        cursor.getString(5));
                orders.add(order);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return orders;
    }

    // TICKET OPERATIONS

    public void addTicket(int userId, String subject, String description, String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TICKET_USER_ID, userId);
        values.put(KEY_TICKET_SUBJECT, subject);
        values.put(KEY_TICKET_DESCRIPTION, description);
        values.put(KEY_TICKET_STATUS, status);
        db.insert(TABLE_TICKETS, null, values);
        db.close();
    }

    public List<Ticket> getTicketsByUser(int userId) {
        List<Ticket> tickets = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_TICKETS, null, KEY_TICKET_USER_ID + "=?", new String[]{String.valueOf(userId)}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Ticket t = new Ticket(cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4));
                tickets.add(t);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return tickets;
    }

    public List<Ticket> getAllTickets() {
        List<Ticket> tickets = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_TICKETS;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Ticket t = new Ticket(cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4));
                tickets.add(t);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return tickets;
    }

    // VENDOR APPLICATION OPERATIONS

    public void addVendorApplication(int userId, String name, String phone, String document, String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_VENDOR_USER_ID, userId);
        values.put(KEY_VENDOR_NAME, name);
        values.put(KEY_VENDOR_PHONE, phone);
        values.put(KEY_VENDOR_DOC, document);
        values.put(KEY_VENDOR_STATUS, status);
        db.insert(TABLE_VENDOR_APPLICATIONS, null, values);
        db.close();
    }

    public List<VendorApplication> getVendorApplications() {
        List<VendorApplication> apps = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_VENDOR_APPLICATIONS;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                VendorApplication app = new VendorApplication(cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5));
                apps.add(app);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return apps;
    }

    public void updateVendorStatus(int appId, String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_VENDOR_STATUS, status);
        db.update(TABLE_VENDOR_APPLICATIONS, values, KEY_ID + "=?", new String[]{String.valueOf(appId)});
        db.close();
    }

    // SALE REQUEST OPERATIONS

    public void addSaleRequest(int userId, int productId, String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_SALE_USER_ID, userId);
        values.put(KEY_SALE_PRODUCT_ID, productId);
        values.put(KEY_SALE_STATUS, status);
        db.insert(TABLE_SALE_REQUESTS, null, values);
        db.close();
    }

    public List<SaleRequest> getSaleRequests() {
        List<SaleRequest> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT sr." + KEY_ID + ", sr." + KEY_SALE_USER_ID +
                ", sr." + KEY_SALE_PRODUCT_ID + ", sr." + KEY_SALE_STATUS +
                ", p." + KEY_PRODUCT_NAME +
                " FROM " + TABLE_SALE_REQUESTS + " sr JOIN " + TABLE_PRODUCTS + " p ON sr." +
                KEY_SALE_PRODUCT_ID + " = p." + KEY_ID;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                SaleRequest sr = new SaleRequest(cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getInt(2),
                        cursor.getString(3),
                        cursor.getString(4));
                list.add(sr);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public void updateSaleStatus(int requestId, String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_SALE_STATUS, status);
        db.update(TABLE_SALE_REQUESTS, values, KEY_ID + "=?", new String[]{String.valueOf(requestId)});
        db.close();
    }
}
