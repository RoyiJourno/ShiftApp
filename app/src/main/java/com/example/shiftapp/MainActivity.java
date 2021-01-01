package com.example.shiftapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.royrodriguez.transitionbutton.TransitionButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText userEmail,userPass;
    Button btnLogin,btnSignup;
    private FirebaseAuth mAuth;
    private TransitionButton transitionButton;
    LinearLayout linearLayout;


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        initViews();
        initListener();
        linearLayout = findViewById(R.id.linerView);
        transitionButton = findViewById(R.id.transition_button);
        transitionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the loading animation when the user tap the button
                transitionButton.startAnimation();

                // Do your networking task or background work here.
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        boolean isSuccessful = true;

                        // Choose a stop animation if your call was succesful or not
                        if (isSuccessful) {
                            userLogin();
                            //transitionButton.stopAnimation(TransitionButton.StopAnimationStyle.EXPAND, new TransitionButton.OnAnimationStopEndListener() {
 //                               @Override
//                                public void onAnimationStopEnd() {
                                    //Intent intent = new Intent(getBaseContext(), HomeActivity.class);
                                    //intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                    //startActivity(intent);
                              //  }
                            //});
                        } else {
                            transitionButton.stopAnimation(TransitionButton.StopAnimationStyle.SHAKE, null);
                        }
                    }
                }, 2000);
            }
        });
    }

    private void initListener() {
        btnSignup.setOnClickListener(this);
    }

    private void initViews() {
        userEmail = findViewById(R.id.userEmail);
        userPass = findViewById(R.id.userPassword);
        btnSignup = findViewById(R.id.btnSignup);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btnSignup: userSignup();break;
        }
    }

    private void userLogin() {
        mAuth.signInWithEmailAndPassword(userEmail.getText().toString(),userPass.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        if(user != null){
            startActivity(new Intent(MainActivity.this,HomeActivity.class));
        }
    }

    private void userSignup() {
        startActivity(new Intent(MainActivity.this,SignupActivity.class));
    }
}