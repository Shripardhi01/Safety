package com.dynast.womensafety;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.dynast.womensafety.helper.SharedPrefManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (SharedPrefManager.getInstance(getApplicationContext()).isLoggedIn()) {
            finish();
            startActivity(new Intent(getApplicationContext(), BuzzerPage.class));

        } else {
            finish();
            startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
        }

        final String mob1 = SharedPrefManager.getInstance(getApplicationContext()).getUser().getEm1();

    }
}
