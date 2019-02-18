package com.example.user.ansprj;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Wall2Actvty extends AppCompatActivity implements View.OnClickListener {
    private ImageView imgBtnBks;
    private ImageView imgBtnChs;
    private ImageView imgBtnSpr;
    private ImageView imgBtnGmg;
    private ImageView imgBtnFod;
    private ImageView imgBtnMsc;
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

        imgBtnChs = (ImageView) findViewById(R.id.imgBtnChs);
        imgBtnChs.setOnClickListener(this);

        imgBtnSpr = (ImageView) findViewById(R.id.imgBtnSpr);
        imgBtnSpr.setOnClickListener(this);

        imgBtnGmg = (ImageView) findViewById(R.id.imgBtnGmg);
        imgBtnGmg.setOnClickListener(this);

        imgBtnFod = (ImageView) findViewById(R.id.imgBtnFod);
        imgBtnFod.setOnClickListener(this);

        imgBtnMsc = (ImageView) findViewById(R.id.imgBtnMsc);
        imgBtnMsc.setOnClickListener(this);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.optionmenu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuItem1:
                Intent intent2 = new Intent(getApplication(),ReadingActivity.class);
                startActivity(intent2);
                break;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.signOutBtn) {
            //fAuth.
            Intent intent2 = new Intent(this,signInActivity.class);
            startActivity(intent2);
        }
        if(v==imgBtnBks){
            Intent intent2 = new Intent(this,ReadingActivity.class);
            startActivity(intent2);
        }
        if(v==imgBtnChs){
            Intent intent2 = new Intent(this,ChessActivity.class);
            startActivity(intent2);
        }
        if(v==imgBtnSpr){
            Intent intent2 = new Intent(this,SportsActivity.class);
            startActivity(intent2);
        }
        if(v==imgBtnGmg){
            Intent intent2 = new Intent(this,GamingActivity.class);
            startActivity(intent2);
        }if(v==imgBtnFod){
            Intent intent2 = new Intent(this,FoodieActivity.class);
            startActivity(intent2);
        }if(v==imgBtnMsc){
            Intent intent2 = new Intent(this,MusicActivity.class);
            startActivity(intent2);
        }
    }
}
