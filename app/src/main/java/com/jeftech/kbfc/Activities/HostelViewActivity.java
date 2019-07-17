package com.jeftech.kbfc.Activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.jeftech.kbfc.R;
import com.squareup.picasso.Picasso;

public class HostelViewActivity extends AppCompatActivity {

    ImageView iv;
    TextView tvName, tvRooms, tvPhone, tvPlace;
    String name, rooms, phone, place, url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hostel_view);

        tvName = findViewById(R.id.tvName);
        tvRooms = findViewById(R.id.tvRooms);
        tvPhone = findViewById(R.id.tvPhone);
        tvPlace = findViewById(R.id.tvPlace);
        iv = findViewById(R.id.iv);

        Intent i = getIntent();
        name = i.getStringExtra("name");
        rooms = i.getStringExtra("rooms");
        phone = i.getStringExtra("phone");
        place = i.getStringExtra("place");
        url = i.getStringExtra("url");

        Picasso.get().load(url).into(iv);
        tvName.setText(name);
        tvRooms.setText("Available rooms: " + rooms);
        tvPhone.setText("Contact number: " + phone);
        tvPlace.setText("Place: " + place);
    }
}