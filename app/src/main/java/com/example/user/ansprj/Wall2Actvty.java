package com.example.user.ansprj;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Wall2Actvty extends AppCompatActivity implements View.OnClickListener {
    private ImageView imgBtnBks;
    private Button signOutBtn;
    FirebaseAuth fAuth;// = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wall_actvty);
        signOutBtn=(Button)findViewById(R.id.signOutBtn);
        signOutBtn.setOnClickListener(this);
        //fAuth = FirebaseAuth.
        imgBtnBks = (ImageView) findViewById(R.id.imgBtnBks);
        imgBtnBks.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.signOutBtn) {
            //fAuth.
            Intent intent2 = new Intent(this,signInActivity.class);
            startActivity(intent2);
        }
        if(v==imgBtnBks){
            Intent intent2 = new Intent(this,MainActivity.class);
            startActivity(intent2);
        }
    }
}
