package com.example.user.ansprj;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Wall2Actvty extends AppCompatActivity implements View.OnClickListener {
    private ImageButton imgBtnBks;
    private Button signOutBtn;
    FirebaseAuth fAuth;// = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wall_actvty);
        signOutBtn=(Button)findViewById(R.id.signOutBtn);
        signOutBtn.setOnClickListener(this);
        //fAuth = FirebaseAuth.

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.signOutBtn) {
            //fAuth.
            Intent intent2 = new Intent(this,signInActivity.class);
            startActivity(intent2);
        }
    }
}
