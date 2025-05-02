// File: app/src/main/java/com/fmfamilywear/activities/UserProfileActivity.java
package com.fmfamilywear.activities;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.fmfamilywear.R;
import com.fmfamilywear.db.DatabaseHelper;
import com.fmfamilywear.models.User;
import com.fmfamilywear.utils.SessionManager;

public class UserProfileActivity extends AppCompatActivity {
    @Override protected void onCreate(Bundle s){
        super.onCreate(s);
        setContentView(R.layout.activity_user_profile);
        SessionManager sm = new SessionManager(this);
        String email = sm.getEmail();
        DatabaseHelper db = new DatabaseHelper(this);
        User u = db.getUserByEmail(email);
        ((TextView)findViewById(R.id.tvName)).setText(u.getName());
        ((TextView)findViewById(R.id.tvEmail)).setText(u.getEmail());
    }
}
