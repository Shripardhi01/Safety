package com.dynast.womensafety;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.os.Looper;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dynast.womensafety.helper.SharedPrefManager;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.List;

public class BuzzerPage extends AppCompatActivity {

    private static final int REQUEST_CODE_LOCATION_PERMISSION = 1;
    private static final int MULTIPLE_PERMISSIONS = 10;
    String message = "";
    Button btn_safe, btnHelp;
    String name, mobile, address, mob1, mob2, mob3;
    String[] permissions = new String[]{
            Manifest.permission.SEND_SMS,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.ACCESS_FINE_LOCATION};
    String msg = "";
    String loc="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buzzer_page);

        btn_safe = findViewById(R.id.btnsafe);
        btnHelp = findViewById(R.id.btnhelp);

        name = SharedPrefManager.getInstance(getApplicationContext()).getUser().getName();
        mobile = SharedPrefManager.getInstance(getApplicationContext()).getUser().getMobile();
        address = SharedPrefManager.getInstance(getApplicationContext()).getUser().getAddress();
        mob1 = SharedPrefManager.getInstance(getApplicationContext()).getUser().getEm1();
        mob2 = SharedPrefManager.getInstance(getApplicationContext()).getUser().getEm2();
        mob3 = SharedPrefManager.getInstance(getApplicationContext()).getUser().getEm3();
        btnHelp.setBackgroundColor(Color.RED);
        btnHelp.setTextColor(Color.WHITE);
        btn_safe.setBackgroundColor(Color.GREEN);
        btn_safe.setTextColor(Color.WHITE);

        if (checkPermissions()) {
            btnHelp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getCurrentLocation();
//                    Toast.makeText(BuzzerPage.this, message, Toast.LENGTH_SHORT).show();

                    msg = "Help Me! I'm in trouble.\nName: "+name+"\nMobile: "+mobile+"\nAddress: "+address+" Location:  "+loc;
                    if (message.length()>0)
                        sendMessage(msg);
                }
            });

            btn_safe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getCurrentLocation();
                    msg = "I'm safe now Thank You.\nName: "+name+"\nMobile: "+mobile+"\nAddress: "+address+" Location:  "+loc;
                    if (message.length()>0)
                        sendMessage(msg);
                }
            });
        }
//Integer.toString(message.length()) into to string

//        if (ContextCompat.checkSelfPermission(
//                getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION
//        ) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(
//                    this,
//                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
//                    REQUEST_CODE_LOCATION_PERMISSION
//            );
//        } else {
//            btnHelp.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Log.i("namemobile2", name + mobile + address + mob1 + mob2 + mob3);
//                    Log.i("after11", message + mob1);
//                    getCurrentLocation();
//                    Toast.makeText(BuzzerPage.this, message, Toast.LENGTH_SHORT).show();
//                    sendMessage();
//                }
//            });
//
//        }

    }

    private boolean checkPermissions() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p : permissions) {
            result = ContextCompat.checkSelfPermission(this, p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissionsList[], int[] grantResults) {
        switch (requestCode) {
            case MULTIPLE_PERMISSIONS: {
                if (grantResults.length > 0) {
                    for (String per : permissionsList) {
                        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                            getCurrentLocation();
                        } else {
                            // Show permissionsDenied
                            Toast.makeText(this, "Permission denied!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                return;
            }
        }
    }

    private void sendMessage(String msg) {
        //Getting intent and PendingIntent instance
        Intent intent = new Intent(getApplicationContext(), BuzzerPage.class);
//        PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

        //Get the SmsManager instance and call the sendTextMessage method to send message
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(mob1, null, msg, null, null);
        sms.sendTextMessage(mob2, null, msg, null, null);
        sms.sendTextMessage(mob3, null, msg, null, null);
        Log.i("namemobile22",msg);
        Toast.makeText(getApplicationContext(), "Message Sent successfully!",
                Toast.LENGTH_LONG).show();
    }

    private void getCurrentLocation() {
        final LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationServices.getFusedLocationProviderClient(this)
                .requestLocationUpdates(locationRequest, new LocationCallback() {

                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        super.onLocationResult(locationResult);
                        LocationServices.getFusedLocationProviderClient(BuzzerPage.this)
                                .removeLocationUpdates(this);
                        if (locationResult != null && locationResult.getLocations().size() > 0) {
                            int latestLocationIndex = locationResult.getLocations().size() - 1;
                            double latitude =
                                    locationResult.getLocations().get(latestLocationIndex).getLatitude();
                            double longitude =
                                    locationResult.getLocations().get(latestLocationIndex).getLongitude();
                            message = String.format(
                                    "Latitude: %s\nLongitude: %s",
                                    latitude,
                                    longitude
                            );
                            loc = "https://maps.google.com/?q="+latitude+","+longitude;
                        }
                    }
                }, Looper.getMainLooper());
        Log.i("nameLoc", message);
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == REQUEST_CODE_LOCATION_PERMISSION && grantResults.length > 0) {
//            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                getCurrentLocation();
//            } else {
//                Toast.makeText(this, "Permission Denied!", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
}

