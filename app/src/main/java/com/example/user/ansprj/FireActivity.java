package com.example.user.ansprj;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class FireActivity extends AppCompatActivity {

    private ListView lvUsers;
    ArrayList<String> users;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire);

        lvUsers = (ListView)findViewById(R.id.lvUsers);
        users = new ArrayList<>();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, users);
    }
}
