package com.example.user.ansprj;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SharedActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etName;
    Button btnSave;
    TextView tvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared2);

        etName =  (EditText) findViewById(R.id.etName);
        btnSave =  (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        SharedPreferences pref = getSharedPreferences("mypref",MODE_PRIVATE);
        // my pref hei bas klme, ism el folder, mn2dr n3'eirha la settings aw...
        String name = pref.getString("name",null);
        //in case shared preferences does not contain keyword 'name' null is returned

        tvName = findViewById(R.id.tvName);
        if(name != null){
            tvName.setText(name);
        }

        SharedPreferences.Editor editor = pref.edit();
        // keyword,value format at values to be saved in the sheredPreferences
        editor.putString("name",etName.getText().toString());
        //commit the changes to the file and save
        editor.commit();

    }
}