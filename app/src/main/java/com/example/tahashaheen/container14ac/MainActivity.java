package com.example.tahashaheen.container14ac;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    Button t16, n22, off;
    TextView text;
    Boolean updateSettingOld;
    Integer whichSettingInteger;

    void savingOldUpdateSetting(Boolean value){
        updateSettingOld = value;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),"font/Lato-Light.ttf");


        t16 = findViewById(R.id.T16);
        n22 = findViewById(R.id.N22);
        off = findViewById(R.id.OFF);

        text = (TextView)findViewById(R.id.text);

        t16.setTypeface(custom_font);
        n22.setTypeface(custom_font);
        off.setTypeface(custom_font);
        text.setTypeface(custom_font);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference confirmationStatus = database.getReference("confirmationStatus");
        final DatabaseReference updateSetting = database.getReference("updateSetting");
        final DatabaseReference whichSetting = database.getReference("whichSetting");
        final DatabaseReference proofOfConnection = database.getReference("proofOfConnection");



        proofOfConnection.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Toast.makeText(getApplicationContext(), "Dil garden garden horiya hai!",
                        Toast.LENGTH_LONG).show();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Failed to read value
                Toast.makeText(getApplicationContext(), "Jani scene off lag raiya hai...",
                        Toast.LENGTH_SHORT).show();
            }
        });

        confirmationStatus.addValueEventListener((new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Toast.makeText(getApplicationContext(), "Befikar hojayen. Aap ka kaam hojaye ga!!",
                        Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Jani scene off lag raiya hai...",
                        Toast.LENGTH_SHORT).show();
            }
        }));

        updateSetting.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Boolean value = dataSnapshot.getValue(Boolean.class);
                savingOldUpdateSetting(value);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(getApplicationContext(), "Jani scene off lag raiya hai...",
                        Toast.LENGTH_SHORT).show();
            }
        });

        whichSetting.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Integer value = dataSnapshot.getValue(Integer.class);
                text.setText(String.valueOf(value));
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(getApplicationContext(), "Jani scene off lag riya hai...",
                        Toast.LENGTH_SHORT).show();
            }
        });

        t16.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                whichSetting.setValue(1);   //16 Turbo
                updateSetting.setValue(!updateSettingOld);
            }
        });

        off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whichSetting.setValue(2);   //OFF
                updateSetting.setValue(!updateSettingOld);
            }
        });

        n22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whichSetting.setValue(3);   //22N
                updateSetting.setValue(!updateSettingOld);
            }
        });


    }
}
