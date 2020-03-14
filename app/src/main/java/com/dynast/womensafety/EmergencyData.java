package com.dynast.womensafety;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dynast.womensafety.helper.SharedPrefManager;
import com.dynast.womensafety.helper.User;

public class EmergencyData extends AppCompatActivity {

    EditText n1, n2, n3, add;
    Button btnSave;
    String name, mobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_data);

        n1 = findViewById(R.id.etmob1);
        n2 = findViewById(R.id.etmob2);
        n3 = findViewById(R.id.etmob3);
        add = findViewById(R.id.etadd);
        btnSave = findViewById(R.id.btnSave);

        name = SharedPrefManager.getInstance(getApplicationContext()).getUser().getName();
        mobile = SharedPrefManager.getInstance(getApplicationContext()).getUser().getMobile();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String address = add.getText().toString();
                String mob1 = n1.getText().toString();
                String mob2 = n2.getText().toString();
                String mob3 = n3.getText().toString();
                Log.i("namemobile",name+mobile);
                User user = new User(1, name, mobile, address, mob1, mob2, mob3);
                if (n1.length()<10 && n2.length()<10 && n3.length()<10){
                    Toast.makeText(EmergencyData.this, "Invalid Mobile Numbers", Toast.LENGTH_SHORT).show();
                }else{
                    SharedPrefManager.getInstance(getApplicationContext()).userData(user);
                    Log.i("namemobile1",name+mobile+address+mob1+mob2+mob3);
                    startActivity(new Intent(getApplicationContext(), BuzzerPage.class));
                }
            }
        });


    }
}
