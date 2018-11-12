package com.example.user.ansprj;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    final String TAG = "firebase";
    private FirebaseAuth mAuth;
    private Button signInBtn;
    private EditText emladdrssEt, passEt;

    private  RadioGroup signUpRadGrp;
    private  RadioButton readingRadBtn, gamingRadBtn,
            chessRadBtn, sportsRadBtn, fodieRadBtn, musicRadBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_actvty);
        signUpRadGrp = (RadioGroup) findViewById(R.id.signUpRadGrp);
        signUpRadGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.readingRadBtn) {
                    Toast.makeText(signUpActivity.this, "choice: A",Toast.LENGTH_SHORT).show();
                }
            }
        });
        passEt = findViewById(R.id.passEt);
        emladdrssEt = findViewById(R.id.emladdrssEt);
        mAuth = FirebaseAuth.getInstance();
        signInBtn = (Button) findViewById(R.id.signInBtn);
        signInBtn.setOnClickListener(new View.OnClickListener() {
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
                            Intent i = new Intent(signUpActivity.this, wallActvty.class);
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