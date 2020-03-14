package com.dynast.womensafety;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dynast.womensafety.helper.SharedPrefManager;
import com.dynast.womensafety.helper.User;

public class RegisterActivity extends AppCompatActivity {

    EditText name, mobile;
    Button bntreg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.etname);
        mobile = findViewById(R.id.etmobile);
        bntreg = findViewById(R.id.btnReg);
        bntreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n = name.getText().toString();
                String m = mobile.getText().toString();
                if (m.length() < 10) {
                    Toast.makeText(RegisterActivity.this, "Invalid Mobile Number", Toast.LENGTH_SHORT).show();
                } else {

                    User user = new User(1, n, m);
                    SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
                    startActivity(new Intent(getApplicationContext(), Information.class));
                }
            }
        });
    }
}
