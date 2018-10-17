package com.example.user.ansprj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class signInActivity extends AppCompatActivity
{

    private EditText email;
    private EditText password;
    private Button goBtn;
    private Button signUpButton;
    private int count = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        email =  (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        goBtn =  (Button) findViewById(R.id.goBtn);
        goBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                validate(email.getText().toString(), password.getText().toString());
            }
        });
        signUpButton = (Button) findViewById(R.id.signInButton); // go to sign in activity
        signUpButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent1 = new Intent(signInActivity.this, signUpActivity.class);
                startActivity(intent1);
            }
        });
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_actvty);
    }

    private void validate(String email,String password)
    {
        if( (email == "admin") && (password == "3082001"))
        {
            Intent intent2 = new Intent(signInActivity.this, wallActvty.class);
            startActivity(intent2);
        } else {
            count--;
            if(count == 0)
                goBtn.setEnabled(false);
        }


    }
}
