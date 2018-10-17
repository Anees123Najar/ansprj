package com.example.user.ansprj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class signUpActivity extends AppCompatActivity
{
    private Button signInBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        signInBtn = (Button) findViewById(R.id.signInBtn);
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentwallActivity = new Intent(signUpActivity.this, wallActvty.class);
            }
        });
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_actvty);
    }
}
