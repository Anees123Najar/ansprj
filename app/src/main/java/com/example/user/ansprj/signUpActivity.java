package com.example.user.ansprj;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signUpActivity extends AppCompatActivity
{
    final String TAG = "FIREBASE";
    private FirebaseAuth mAuth;
    private Button signUpBtn;
    private EditText emladdrssEt, passEt;

    private  RadioGroup signUpRadGrp;
    private  RadioButton readingRadBtn, gamingRadBtn, chessRadBtn, sportsRadBtn, fodieRadBtn, musicRadBtn;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_actvty);

        signUpRadGrp = (RadioGroup) findViewById(R.id.signUpRadGrp);

        readingRadBtn = findViewById(R.id.readingRadBtn);
        gamingRadBtn = findViewById(R.id.gamingRadBtn);
        chessRadBtn = findViewById(R.id.chessRadBtn);
        sportsRadBtn = findViewById(R.id.sportsRadBtn);
        fodieRadBtn = findViewById(R.id.fodieRadBtn);
        musicRadBtn = findViewById(R.id.musicRadBtn);

        readingRadBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                gamingRadBtn.setChecked(false);
                chessRadBtn.setChecked(false);
                sportsRadBtn.setChecked(false);
                fodieRadBtn.setChecked(false);
                musicRadBtn.setChecked(false);
            }
        });

        gamingRadBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                readingRadBtn.setChecked(false);
                chessRadBtn.setChecked(false);
                sportsRadBtn.setChecked(false);
                fodieRadBtn.setChecked(false);
                musicRadBtn.setChecked(false);
            }
        });

        chessRadBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                readingRadBtn.setChecked(false);
                gamingRadBtn.setChecked(false);
                sportsRadBtn.setChecked(false);
                fodieRadBtn.setChecked(false);
                musicRadBtn.setChecked(false);
            }
        });

        sportsRadBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                readingRadBtn.setChecked(false);
                gamingRadBtn.setChecked(false);
                chessRadBtn.setChecked(false);
                fodieRadBtn.setChecked(false);
                musicRadBtn.setChecked(false);
            }
        });

        fodieRadBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                readingRadBtn.setChecked(false);
                gamingRadBtn.setChecked(false);
                chessRadBtn.setChecked(false);
                sportsRadBtn.setChecked(false);
                musicRadBtn.setChecked(false);
            }
        });

        musicRadBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                readingRadBtn.setChecked(false);
                gamingRadBtn.setChecked(false);
                chessRadBtn.setChecked(false);
                sportsRadBtn.setChecked(false);
                fodieRadBtn.setChecked(false);
            }
        });

        passEt = findViewById(R.id.passEt);
        emladdrssEt = findViewById(R.id.emladdrssEt);
        mAuth = FirebaseAuth.getInstance();
        signUpBtn = (Button) findViewById(R.id.signUpBtn);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(emladdrssEt.getText().toString().equals("") || passEt.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Empty uesrname or password", Toast.LENGTH_LONG ).show();
                }
                else
                {
                    createUser(emladdrssEt.getText().toString(),passEt.getText().toString());
                }
            }
        });
    }

    @Override
    public void onStart()
    {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //  updateUI(currentUser);
    }
    public void createUser(String email, String password)
    {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful())
                        {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent i = new Intent(signUpActivity.this, Wall2Actvty.class);
                            startActivity(i);
                            //updateUI(user);
                        }
                        else
                        {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(signUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                        // ...
                    }
                });
    }
}