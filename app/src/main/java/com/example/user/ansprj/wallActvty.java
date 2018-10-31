package com.example.user.ansprj;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class wallActvty extends AppCompatActivity implements View.OnClickListener
{
    private ImageButton imgBtnBks;
    private Button signOutBtn;
    FirebaseAuth fAuth;// = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wall_actvty);
        signOutBtn=(Button)findViewById(R.id.signOutBtn);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.signOutBtn) {
            fAuth.getInstance().signOut();
            Intent intent2 = new Intent(this,signInActivity.class);
            startActivity(intent2);
        }
    }
}