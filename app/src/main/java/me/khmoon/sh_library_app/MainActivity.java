package me.khmoon.sh_library_app;

import android.os.Bundle;

import me.khmoon.sh_library_app.R;

import me.khmoon.sh_library_app.util.Util;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.MenuItem;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private TextView mTextMessage;
    HomeFragment homeFragment = new HomeFragment();
    ReadingRoomFragment readingRoomFragment = new ReadingRoomFragment();
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    transaction.replace(R.id.container, homeFragment);
                    transaction.commit();
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    transaction.replace(R.id.container, readingRoomFragment);
                    transaction.commit();
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
                case R.id.navigation_all:
                    mTextMessage.setText(R.string.title_all);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.container, homeFragment);
        transaction.commit();

        try {
            mTextMessage.setText(Util.getProperty("name", getApplicationContext()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
